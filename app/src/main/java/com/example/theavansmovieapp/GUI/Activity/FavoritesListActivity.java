package com.example.theavansmovieapp.GUI.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.theavansmovieapp.ApplicationLogic.Adapters.FilmsAdapter;
import com.example.theavansmovieapp.ApplicationLogic.Adapters.FilmsOverviewAdapter;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetFilmsCallback;
import com.example.theavansmovieapp.DataAcces.FilmsRepository;
import com.example.theavansmovieapp.Domain.Film;
import com.example.theavansmovieapp.R;

import java.util.List;

/** gemaakt door luc */

public class FavoritesListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FilmsRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
                 * navigation_favorites wordt er een melding gegeven dat het homescherm al actief is.
                 */
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent a = new Intent(FavoritesListActivity.this, HomeActivity.class);
                        startActivity(a);
                        Toast.makeText(FavoritesListActivity.this, "Home screen opened", Toast.LENGTH_LONG).show();

                        break;
                    case R.id.navigation_profile:
                        Intent b = new Intent(FavoritesListActivity.this, ProfileActivity.class);
                        startActivity(b);
                        Toast.makeText(FavoritesListActivity.this, "Profile screen opened", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_favorites:
                        Toast.makeText(FavoritesListActivity.this, "Favorites list already opened", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });


        repository = repository.getInstance();

        /** recyclerview attributen koppelen aan de views */
        /** hieronder wordt de layout bepaald en gekoppeld */
        recyclerView = findViewById(R.id.favorites_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        repository.getFavoriteFilms(new OnGetFilmsCallback() {
            @Override
            public void onSuccess(List<Film> films) {
                recyclerView.setAdapter(new FilmsOverviewAdapter(films, FavoritesListActivity.this));
            }

            /** bij een error komt er een melding */
            @Override
            public void onError() {

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
}
