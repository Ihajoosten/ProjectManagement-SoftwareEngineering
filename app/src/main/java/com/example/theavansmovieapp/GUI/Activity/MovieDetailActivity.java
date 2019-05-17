package com.example.theavansmovieapp.GUI.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.theavansmovieapp.ApplicationLogic.Adapters.FilmReviewsAdapter;
import com.example.theavansmovieapp.ApplicationLogic.Adapters.FilmTrailersAdapter;
import com.example.theavansmovieapp.ApplicationLogic.Listeners.HartListener;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetGenresCallBack;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetOneFilmCallback;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetReviewsCallback;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetTrailersCallBack;
import com.example.theavansmovieapp.DataAcces.FilmsRepository;

import com.example.theavansmovieapp.Domain.Film;
import com.example.theavansmovieapp.Domain.Genre;
import com.example.theavansmovieapp.Domain.Review;
import com.example.theavansmovieapp.Domain.Trailer;
import com.example.theavansmovieapp.GUI.Animations.Popup;
import com.example.theavansmovieapp.GUI.Animations.PopupAddMovie;
import com.example.theavansmovieapp.GUI.Animations.PopupGenres;
import com.example.theavansmovieapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/** gemaakt door luc */

public class MovieDetailActivity extends AppCompatActivity {

    private static String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w780";

    private ImageView filmBackdrop;
    private TextView filmTitle;
    private TextView filmGenres;
    private TextView filmOverview;
    private TextView filmOverviewLabel;
    private TextView filmReleaseYear;
    private RatingBar filmRating;
    private RecyclerView filmTrailers;
    private RecyclerView filmReviews;
    private int filmID;
    private TextView trailersLabel;
    private Button mAddToListButton;

    private List<Film> favorites = new ArrayList<>();
    private FilmsRepository repository;
    private Boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        repository = repository.getInstance();

        setUI();

        filmID = getIntent().getExtras().getInt("film");

        getMovie();

        //Floating rating knopje
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent met film
                Intent intent = new Intent(MovieDetailActivity.this, Popup.class);
                intent.putExtra("film", filmID);
                startActivity(intent);
            }
        });

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        CompoundButton buttonFavorite = findViewById(R.id.button_favorite);

        final ScaleAnimation scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f);
        scaleAnimation.setDuration(500);
        BounceInterpolator bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);

        buttonFavorite.setOnCheckedChangeListener(new HartListener(MovieDetailActivity.this,scaleAnimation, filmID));

        mAddToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailActivity.this, PopupAddMovie.class);
                intent.putExtra("add", true);
                intent.putExtra("filmID", filmID);
                intent.putExtra("filmName", filmTitle.getText());
                startActivity(intent);
            }
        });

    }

    /** attributen koppelen van de views en button */
    public void setUI() {
        filmBackdrop = findViewById(R.id.cover);
        filmTitle = findViewById(R.id.detail_title);
        filmGenres = findViewById(R.id.detail_genre_tv);
        filmOverview = findViewById(R.id.overview_tv);
        filmOverviewLabel = findViewById(R.id.overview_label_tv);
        filmRating = findViewById(R.id.movieDetailsRating_rb);
        filmReleaseYear = findViewById(R.id.detail_year_tv);
        filmTrailers = findViewById(R.id.trailers_recyclerview);
        filmReviews = findViewById(R.id.review_recyclerview);
        trailersLabel = findViewById(R.id.trailersLabel);
        mAddToListButton = findViewById(R.id.add_movie_to_list_btn);
    }

    /** ophalen van de film data voor in het detail scherm */
    private void getMovie() {
        repository.getFilmDetails(filmID, "en", new OnGetOneFilmCallback() {
            @Override
            public void onSuccess(Film film) {
                filmTitle.setText(film.getTitle());
                filmOverviewLabel.setVisibility(View.VISIBLE);
                filmOverview.setText(film.getOverview());
                filmRating.setVisibility(View.VISIBLE);
                filmRating.setRating(film.getRating() / 2);
                String year = film.getReleaseDate().substring(0, 4);
                filmReleaseYear.setText(year);
                getGenres(film);
                Picasso.get().load(IMAGE_BASE_URL + film.getBackdrop()).into(filmBackdrop);
                getTrailers(film);
                getReviews(film);
            }

            /** als de data niet wordt opgehaald en er een error komt wordt het afgebroken en terug genavigeerd */
            @Override
            public void onError() {
                finish();
            }
        });
    }

    /** ophalen van de genres zodat de film kan worden toegevoegd aan een lijst */
    private void getGenres(final Film film) {
        repository.getGenres(new OnGetGenresCallBack() {
            @Override
            public void onSuccess(List<Genre> genres) {
                if (film.getGenres() != null) {
                    List<String> currentGenres = new ArrayList<>();
                    for (Genre genre : film.getGenres()) {
                        currentGenres.add(genre.getName());
                    }
                    filmGenres.setText(TextUtils.join(", ", currentGenres));
                }
            }

            @Override
            public void onError() {
                /** doet niks bij een error */
            }
        });
    }

    /** een methode die een boolean als waarde teruggeeft die overschreven wordt. dit is in
     * verband met het 'back' (pijltje) knop om terug te gaan naar het vorige scherm */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /** ophalen van de trailers en weergeven en de adapter koppelen aan de opgehaalde data */
    private void getTrailers(Film film) {
        repository.getTrailers(film.getId(), new OnGetTrailersCallBack() {
            @Override
            public void onSuccess(List<Trailer> trailers) {
                LinearLayoutManager manager = new LinearLayoutManager(MovieDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
                filmTrailers.setLayoutManager(manager);
                filmTrailers.setAdapter(new FilmTrailersAdapter(trailers, MovieDetailActivity.this));
            }

            @Override
            public void onError() {
                // Do nothing
                trailersLabel.setVisibility(View.GONE);
            }
        });
    }


    /** ophalen van de reviews en koppelen van de adapter aan de opgehaalde data */
    private void getReviews(Film film) {
        repository.getReviews(film.getId(), new OnGetReviewsCallback() {
            @Override
            public void onSuccess(List<Review> reviews) {
                LinearLayoutManager manager = new LinearLayoutManager(MovieDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
                filmReviews.setLayoutManager(manager);
                Log.v("getReviews: onSucces: ", "aangeroepen");
                filmReviews.setAdapter(new FilmReviewsAdapter(reviews, MovieDetailActivity.this));
            }

            @Override
            public void onError() {
                // Do nothing
                Log.v("getReviews: onError: ", "aangeroepen");
            }
        });
    }

}
