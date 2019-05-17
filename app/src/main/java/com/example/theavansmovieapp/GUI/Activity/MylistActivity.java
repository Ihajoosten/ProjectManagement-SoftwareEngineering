package com.example.theavansmovieapp.GUI.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.theavansmovieapp.ApplicationLogic.Adapters.MyListsAdapter;
import com.example.theavansmovieapp.DataAcces.FilmsRepository;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetAccountListsCallback;
import com.example.theavansmovieapp.Domain.AccountList;
import com.example.theavansmovieapp.R;

import java.util.List;

/** gemaakt door luc **/

public class MylistActivity extends AppCompatActivity {

    private RecyclerView accountListsRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FilmsRepository filmsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /** de layout instantieren en koppelen aan de views en adapater */
        filmsRepository = FilmsRepository.getInstance();
        accountListsRecyclerView = findViewById(R.id.overview_mylist_rv);
        accountListsRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        accountListsRecyclerView.setLayoutManager(layoutManager);

        getListData();

        /** add back arrow to toolbar */
        if (getSupportActionBar() != null){
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
                        Intent a = new Intent(MylistActivity.this,HomeActivity.class);
                        startActivity(a);
                        Toast.makeText(MylistActivity.this, "Home screen opened", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_profile:
                        Intent b = new Intent(MylistActivity.this,ProfileActivity.class);
                        startActivity(b);
                        Toast.makeText(MylistActivity.this, "Profile screen opened", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_favorites:
                        Intent c = new Intent(MylistActivity.this,FavoritesListActivity.class);
                        startActivity(c);
                        Toast.makeText(MylistActivity.this, "Favorites list screen opened", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });
    }

    /** ophalen van de lijsten die gekoppeld zijn aan het account */
    public void getListData()
    {
        filmsRepository.getAccountLists(1, "EN", new OnGetAccountListsCallback()
        {
            @Override
            public void onSuccess(List<AccountList> accountlist)
            {
                accountListsRecyclerView.setAdapter(new MyListsAdapter(MylistActivity.this, accountlist, MylistActivity.this));
        }

            @Override
            public void onError()
            {
                /** geeft een melding als er een error optreedt */
                Toast.makeText(MylistActivity.this,
                        "Please check your internet connection.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

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
