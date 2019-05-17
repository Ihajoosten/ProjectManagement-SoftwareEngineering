package com.example.theavansmovieapp.GUI.Animations;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theavansmovieapp.DataAcces.FilmsRepository;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetOneFilmCallback;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetRatingCallback;
import com.example.theavansmovieapp.Domain.Film;
import com.example.theavansmovieapp.Domain.Rating;
import com.example.theavansmovieapp.R;

/** gemaakt door luc */

public class Popup extends Activity {

    private TextView filmTitle;
    private Button submit_bt;
    private RatingBar ratingBar;
    private ImageButton closeButton;
    private FilmsRepository repository;
    private int filmID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_window_layout);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*0.93), (int) (height*0.9));

        setUI();

        getMovie();

        /** onclick listener voor de closebutton */
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                finish();
            }
        });

        /** onclick listener voor de submit button */
        submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rating rating = new Rating(ratingBar.getRating());
                repository.rateFilm(rating, filmID, new OnGetRatingCallback() {
                    @Override
                    public void onSuccess(Rating rating) {
                        finish();
                        Toast.makeText(Popup.this, "Rating succesfull! You rated: " + ratingBar.getRating(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(Popup.this, "Something went wrong...", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    /** setten van de user interface */
    public void setUI(){
        filmTitle = findViewById(R.id.rating_film_title);
        submit_bt = findViewById(R.id.button);
        ratingBar = findViewById(R.id.ratingBar);
        closeButton = findViewById(R.id.ib_close);
        repository = repository.getInstance();
        filmID = getIntent().getExtras().getInt("film");

    }

    /** ophalen van de film data*/
    public void getMovie(){
        repository.getFilmDetails(filmID, "en", new OnGetOneFilmCallback() {
            @Override
            public void onSuccess(Film film) {
                filmTitle.setText(film.getTitle());
            }

            @Override
            public void onError() {

            }
        });
    }
}
