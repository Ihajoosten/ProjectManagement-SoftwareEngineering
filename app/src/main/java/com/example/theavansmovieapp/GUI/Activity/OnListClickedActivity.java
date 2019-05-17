package com.example.theavansmovieapp.GUI.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.theavansmovieapp.ApplicationLogic.Adapters.FilmsInMyListAdapter;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetFilmsCallback;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetListItemsCallback;
import com.example.theavansmovieapp.DataAcces.FilmsRepository;
import com.example.theavansmovieapp.Domain.Film;
import com.example.theavansmovieapp.Domain.ListItem;
import com.example.theavansmovieapp.R;

import java.util.List;

/** gemaakt door luc */

public class OnListClickedActivity extends AppCompatActivity {

    private int listID;
    private RecyclerView recyclerView;
    private FilmsRepository repository;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_list_clicked);

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
                        Intent a = new Intent(OnListClickedActivity.this,HomeActivity.class);
                        startActivity(a);
                        Toast.makeText(OnListClickedActivity.this, "Home screen opened", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_profile:
                        Intent b = new Intent(OnListClickedActivity.this,ProfileActivity.class);
                        startActivity(b);
                        Toast.makeText(OnListClickedActivity.this, "Profile screen opened", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_favorites:
                        Intent c = new Intent(OnListClickedActivity.this,FavoritesListActivity.class);
                        startActivity(c);
                        Toast.makeText(OnListClickedActivity.this, "Favorites list screen opened", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });


        listID = getIntent().getExtras().getInt("listID");

        /** de layout instantieren en koppelen aan de views en adapater */
        repository = repository.getInstance();
        recyclerView = findViewById(R.id.clicked_list_movies_rv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        repository.getListFilms(listID, "en", new OnGetListItemsCallback() {
            @Override
            public void onSuccess(List<ListItem> listItems) {
                recyclerView.setAdapter(new FilmsInMyListAdapter(listItems));
                Toast.makeText(OnListClickedActivity.this, "listitems: " + listItems.size(), Toast.LENGTH_SHORT).show();
            }

            /** bij een error komt er een melding */
            @Override
            public void onError() {
                Toast.makeText(OnListClickedActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
