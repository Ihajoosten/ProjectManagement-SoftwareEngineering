package com.example.theavansmovieapp.GUI.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.theavansmovieapp.ApplicationLogic.Adapters.FilmsAdapter;
import com.example.theavansmovieapp.ApplicationLogic.Listeners.ZieMeerFilmsListener;
import com.example.theavansmovieapp.DataAcces.FilmsRepository;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetFilmsCallback;
import com.example.theavansmovieapp.Domain.Film;
import com.example.theavansmovieapp.R;

import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


/** gemaakt door Luc */

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /** klas attributen */
    private RecyclerView upcomingRecyclerView;
    private RecyclerView highratedRecyclerView;
    private RecyclerView discoverRecyclerView;
    private FilmsAdapter filmsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FilmsRepository filmsRepository;
    private ZieMeerFilmsListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        /** layout koppelen aan de views */
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /** hieronder is de bottom navigation te zien. een simpel menu balkje met 3 knoppen die elk navigeren naar een ander scherm */
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                /** als er op een van de menu knoppen gedrukt wordt, word er gekeken op welke knop is gedrukt.
                 * hieronder is te zien dat er 3 verschillende mogelijkheiden zijn (3 cases) wanneer het id waarop gedrukt is overeenkomt met
                 * navigation_home dan wordt er een melding gegeven dat het homescherm al actief is. komt het item id overeenkomt met
                 * navigation_profile dan wordt er een intent gestart naar het profile scherm. als het item id overeenkomt met
                 * navigation_favorites dan wordt er een intent gestart die door navigeert naar de favorieten lijst van het account.
                 */
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Toast.makeText(HomeActivity.this, "You are already on the home screen", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_profile:
                        Intent b = new Intent(HomeActivity.this,ProfileActivity.class);
                        startActivity(b);
                        Toast.makeText(HomeActivity.this, "Profile screen opened", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_favorites:
                        Intent c = new Intent(HomeActivity.this,FavoritesListActivity.class);
                        startActivity(c);
                        Toast.makeText(HomeActivity.this, "Favorites list screen opened", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });

        /** ophalen van de instance */
        filmsRepository = FilmsRepository.getInstance();

        /** recyclerview attributen koppelen aan de views */
        upcomingRecyclerView =  findViewById(R.id.movies_list_upcoming_rv);
        highratedRecyclerView = findViewById(R.id.movies_list_high_rated_rv);
        discoverRecyclerView =  findViewById(R.id.movies_list_discover_rv);

        /** de zie meer knop koppelen */
        Button zieMeerDiscover = findViewById(R.id.see_more_discover_movies_button);
        Button zieMeerUpcoming = findViewById(R.id.see_more_upcoming_movies);
        Button zieMeerHighRated = findViewById(R.id.see_more_high_rated_movies);

        /** hieronder wordt de layout bepaald en gekoppeld */
        upcomingRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        upcomingRecyclerView.setLayoutManager(layoutManager);

        highratedRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        highratedRecyclerView.setLayoutManager(layoutManager);

        discoverRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        discoverRecyclerView.setLayoutManager(layoutManager);

        /** aanmaken en uitvoeren van een AsycnTask */
        HomeAsynctask asynctask = new HomeAsynctask();
        asynctask.execute();

        //Listener toevoegen aan Zie Meer buttons
        zieMeerDiscover.setOnClickListener(new ZieMeerFilmsListener(HomeActivity.this, "discover"));
        zieMeerHighRated.setOnClickListener(new ZieMeerFilmsListener(HomeActivity.this, "highRated"));
        zieMeerUpcoming.setOnClickListener(new ZieMeerFilmsListener(HomeActivity.this, "upcoming"));
    }

    /** methode om de navagatie drawer te sluiten */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    /** hieronder worden verschillende acties ondernomen wanneer er op een menu item geklikt wordt.
     *  elk van de items hebben hun eigen navigatie door middel van intents.
     *  nadat er doorgenavigeerd is naar dat bepaalde scherm komt er een melding dat je je op dat scherm bevindt */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mijn_lijsten) {
            Intent goToMyLists = new Intent(HomeActivity.this, MylistActivity.class);
            String message = "Opened my lists";
            goToMyLists.putExtra(EXTRA_MESSAGE, message);
            startActivity(goToMyLists);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_aanmaken_lijst) {
            Intent goToCreateLists = new Intent(HomeActivity.this, CreateListsActivity.class);
            String message = "Opened create lists";
            goToCreateLists.putExtra(EXTRA_MESSAGE, message);
            startActivity(goToCreateLists);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_delete_lijsten) {
            Intent goToDeleteLists = new Intent(HomeActivity.this, DeleteListsActivity.class);
            String message = "Opened delete lists";
            goToDeleteLists.putExtra(EXTRA_MESSAGE, message);
            startActivity(goToDeleteLists);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Text");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
            startActivity(Intent.createChooser(sharingIntent, "Share using"));

        } else if (id == R.id.nav_settings) {
            Intent goToSettings = new Intent(HomeActivity.this, SettingsActivity.class);
            String message = "Opened settings";
            goToSettings.putExtra(EXTRA_MESSAGE, message);
            startActivity(goToSettings);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_about) {
            Intent goToSettings = new Intent(HomeActivity.this, InfoActivity.class);
            String message = "Opened App Info";
            goToSettings.putExtra(EXTRA_MESSAGE, message);
            startActivity(goToSettings);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /** een AsyncTask zodat de main Thread niet alles in zijn eentje hoeft te doen */
    class HomeAsynctask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            /** upcoming films in recyclerview zetten */
            filmsRepository.getFilms("movie", "upcoming","en","release_date.asc", 1, new OnGetFilmsCallback() {
                @Override
                public void onSuccess(List<Film> films) {
                    upcomingRecyclerView.setAdapter(new FilmsAdapter(films, HomeActivity.this));
                }

                /** bij een error komt er een melding */
                @Override
                public void onError() {
                    Toast.makeText(HomeActivity.this,
                            "Please check your internet connection.",
                            Toast.LENGTH_SHORT).show();
                }
            });

            /** Top rated films in recyclerview zetten */
            filmsRepository.getFilms("movie", "top_rated","en","popularity.desc", 1, new OnGetFilmsCallback() {
                @Override
                public void onSuccess(List<Film> films) {

                    highratedRecyclerView.setAdapter(new FilmsAdapter(films, HomeActivity.this));
                }

                /** bij een error komt er een melding */
                @Override
                public void onError() {
                    Toast.makeText(HomeActivity.this,
                            "Please check your internet connection.",
                            Toast.LENGTH_SHORT).show();
                }
            });

            /** All films (discover) in recyclerview zetten */
            filmsRepository.getFilms("discover", "movie","en","popularity.desc", 1, new OnGetFilmsCallback() {
                @Override
                public void onSuccess(List<Film> films) {
                    discoverRecyclerView.setAdapter(new FilmsAdapter(films, HomeActivity.this));
                }

                /** bij een error komt er een melding */
                @Override
                public void onError() {
                    Toast.makeText(HomeActivity.this,
                            "Please check your internet connection.",
                            Toast.LENGTH_SHORT).show();
                }
            });

            return null;
        }
    }
}