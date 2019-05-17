package com.example.theavansmovieapp.GUI.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.theavansmovieapp.ApplicationLogic.Adapters.FilmsAdapter;
import com.example.theavansmovieapp.ApplicationLogic.Adapters.FilmsOverviewAdapter;
import com.example.theavansmovieapp.ApplicationLogic.Listeners.ZoekbalkListener;
import com.example.theavansmovieapp.DataAcces.FilmsRepository;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetFilmsCallback;
import com.example.theavansmovieapp.Domain.Film;
import com.example.theavansmovieapp.GUI.Animations.Popup;
import com.example.theavansmovieapp.GUI.Animations.PopupGenres;
import com.example.theavansmovieapp.R;
import java.util.List;

/** gemaakt door luc */

public class FilmOverviewActivity extends AppCompatActivity {

    /** klas attributen */
    private RecyclerView filmsRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FilmsRepository filmsRepository;
    private SearchView zoekbalk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_overview);
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        /** add back arrow to toolbar */
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        /** hieronder is de bottom navigation te zien. een simpel menu balkje met 3 knoppen die elk navigeren naar een ander scherm */
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                /** als er op een van de menu knoppen gedrukt wordt, word er gekeken op welke knop is gedrukt.
                 * hieronder is te zien dat er 3 verschillende mogelijkheiden zijn (3 cases) wanneer het id waarop gedrukt is overeenkomt met
                 * navigation_home dan wordt er een intent gestart die een navigeert naar het hoofdscherm van de app. komt het item id overeenkomt met
                 * navigation_profile dan wordt er een intent gestart naar het profile scherm. als het item id overeenkomt met
                 * navigation_favorites dan wordt er een intent gestart die door navigeert naar de favorieten lijst van het account.
                 */
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent a = new Intent(FilmOverviewActivity.this, HomeActivity.class);
                        startActivity(a);
                        Toast.makeText(FilmOverviewActivity.this, "Home screen opened", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_profile:
                        Intent b = new Intent(FilmOverviewActivity.this, ProfileActivity.class);
                        startActivity(b);
                        Toast.makeText(FilmOverviewActivity.this, "Profile screen opened", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_favorites:
                        Intent c = new Intent(FilmOverviewActivity.this, FavoritesListActivity.class);
                        startActivity(c);
                        Toast.makeText(FilmOverviewActivity.this, "Favorites list screen opened", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });


        /** koppelen van de views en adapter en bepalen van de layout */
        zoekbalk = findViewById(R.id.film_overview_zoekbalk);
        zoekbalk.setOnQueryTextListener(new ZoekbalkListener(FilmOverviewActivity.this, FilmOverviewActivity.this));

        filmsRepository = FilmsRepository.getInstance();

        filmsRecyclerView = findViewById(R.id.movie_overview_list_popular_rv);
        filmsRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        filmsRecyclerView.setLayoutManager(layoutManager);

        String type = getIntent().getExtras().getString("type");
        getData(type);
    }

    /** ophalen van de films */
    public void getData(String input) {

        if (input.equals("discover")) {
            /** All films (discover) in recyclerview zetten */

            filmsRepository.getFilms("discover", "movie", "en", "popularity.desc", 1, new OnGetFilmsCallback() {
                @Override
                public void onSuccess(List<Film> films) {
                    FilmsOverviewAdapter discoverFilms = new FilmsOverviewAdapter(films, FilmOverviewActivity.this);
                    filmsRecyclerView.setAdapter(discoverFilms);
                }

                /** bij een error komt er een melding */
                @Override
                public void onError() {
                    Toast.makeText(FilmOverviewActivity.this,
                            "Please check your internet connection.",
                            Toast.LENGTH_SHORT).show();
                }
            });

        } else if (input.equals("upcoming")) {
            //Popular films in recyclerview zetten
            filmsRepository.getFilms("movie", "upcoming", "en", "popularity.desc", 1, new OnGetFilmsCallback() {
                @Override
                public void onSuccess(List<Film> films) {
                    filmsRecyclerView.setAdapter(new FilmsOverviewAdapter(films, FilmOverviewActivity.this));
                }

                /** bij een error komt er een melding */
                @Override
                public void onError() {
                    Toast.makeText(FilmOverviewActivity.this,
                            "Please check your internet connection.",
                            Toast.LENGTH_SHORT).show();
                }
            });

        } else if (input.equals("highRated")) {
            //Top rated films in recyclerview zetten
            filmsRepository.getFilms("movie", "top_rated", "en", "popularity.desc", 1, new OnGetFilmsCallback() {
                @Override
                public void onSuccess(List<Film> films) {

                    filmsRecyclerView.setAdapter(new FilmsOverviewAdapter(films, FilmOverviewActivity.this));
                }

                /** bij een error komt er een melding */
                @Override
                public void onError() {
                    Toast.makeText(FilmOverviewActivity.this,
                            "Please check your internet connection.",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /** methode voor het laten weergeven van de films, koppelen van de adapter */
    public void showFilms(List<Film> films) {
        filmsRecyclerView.setAdapter(new FilmsOverviewAdapter(films, FilmOverviewActivity.this));

    }

    /** een methode die een boolean als waarde teruggeeft die overschreven wordt. dit is in
     * verband met het 'back' (pijltje) knop om terug te gaan naar het vorige scherm */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort:

                showSortMenu();
                return true;

            case android.R.id.home:
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** sorteer menu laten weergeven */
    private void showSortMenu() {
        PopupMenu sortMenu = new PopupMenu(this, findViewById(R.id.sort));
        sortMenu.inflate(R.menu.menu_movies_sort);
        sortMenu.show();

        /** Gemaakt door Tessa */
        /** verschillende acties wanneer er op een item geklikt wordt van het menu */
        sortMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sortByTitle:
                       sortByTitle();
                        break;
                    case R.id.sortByDate:
                        sortByReleaseDate();
                        break;
                    case R.id.sortByRating:
                        sortByTopRated();
                        break;
                    case R.id.sortByGenre:
                        Intent intent = new Intent(FilmOverviewActivity.this, PopupGenres.class);
                        intent.putExtra("genre", true);
                        startActivityForResult(intent, 1);
                        //startActivity(intent);

                        break;
               }
                return true;
            }
        });

    }

/** achterhalen of de request OK is */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");

                sortByGenre(result);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    /** laten weergeven van het sorteer menu */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /** Gemaakt door Tessa */
    /** methode voor het sorteren op title */
    public void sortByTitle(){
        filmsRepository.getFilms("discover", "movie", "en", "original_title.asc", 1, new OnGetFilmsCallback() {
                @Override
                public void onSuccess(List<Film> films) {
                    FilmsOverviewAdapter discoverFilms = new FilmsOverviewAdapter(films, FilmOverviewActivity.this);
                    filmsRecyclerView.setAdapter(discoverFilms);
                }

                @Override
                public void onError() {
                    Toast.makeText(FilmOverviewActivity.this,
                    "Please check your internet connection.",
                    Toast.LENGTH_SHORT).show();
                }
        });
    }

    /** Gemaakt door Tessa */
    /** methode voor het sorteren van de release date */
    public void sortByReleaseDate(){
        filmsRepository.getFilms("discover", "movie", "en", "release_date.desc", 1, new OnGetFilmsCallback() {
            @Override
            public void onSuccess(List<Film> films) {
                FilmsOverviewAdapter discoverFilms = new FilmsOverviewAdapter(films, FilmOverviewActivity.this);
                filmsRecyclerView.setAdapter(discoverFilms);
            }

            @Override
            public void onError() {
                Toast.makeText(FilmOverviewActivity.this,
                        "Please check your internet connection.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /** Gemaakt door Tessa */
    /** methode voor het sorteren op rating */
    public void sortByTopRated(){
        filmsRepository.getFilms("discover", "movie", "en", "vote_average.desc", 1, new OnGetFilmsCallback() {
            @Override
            public void onSuccess(List<Film> films) {
                FilmsOverviewAdapter discoverFilms = new FilmsOverviewAdapter(films, FilmOverviewActivity.this);
                filmsRecyclerView.setAdapter(discoverFilms);
            }

            @Override
            public void onError() {

            }
        });
    }

    /** Gemaakt door Tessa */
    /** Sorteren bij Genre, 28 gaat action films */
    public void sortByGenre(final String genre){
        filmsRepository.getFilmsByGenre("en", "popularity.desc", 1, genre, new OnGetFilmsCallback() {

            @Override
            public void onSuccess(List<Film> films) {
                FilmsOverviewAdapter discoverFilms = new FilmsOverviewAdapter(films, FilmOverviewActivity.this);
                filmsRecyclerView.setAdapter(discoverFilms);
            }

            @Override
            public void onError() {
                Toast.makeText(FilmOverviewActivity.this,
                        "Please check your internet connection.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }



}