package com.example.theavansmovieapp.GUI.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.theavansmovieapp.ApplicationLogic.Adapters.ListsAdapter;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetAccountListsCallback;
import com.example.theavansmovieapp.DataAcces.FilmsRepository;
import com.example.theavansmovieapp.Domain.AccountList;
import com.example.theavansmovieapp.Domain.Film;
import com.example.theavansmovieapp.GUI.Activity.HomeActivity;
import com.example.theavansmovieapp.GUI.Activity.ProfileActivity;
import com.example.theavansmovieapp.R;

import java.util.List;

/** gemaakt door luc */

public class DeleteListsActivity extends AppCompatActivity
{
    /** klas attributen */
    private RecyclerView accountListsRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FilmsRepository filmsRepository;
    private ListsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Create activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_lists);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /** koppelen van attributen aan de view en bepalen van de layout */
        filmsRepository = FilmsRepository.getInstance();
        accountListsRecyclerView = findViewById(R.id.overview_deletelist_rv);
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
                 * navigation_favorites dan wordt er een intent gestart die door navigeert naar de favorieten lijst van het account.
                 */
                switch (item.getItemId())
                {
                    case R.id.navigation_home:
                        Intent a = new Intent(DeleteListsActivity.this, HomeActivity.class);
                        startActivity(a);
                        Toast.makeText(DeleteListsActivity.this, "Home screen opened", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_profile:
                        Intent b = new Intent(DeleteListsActivity.this, ProfileActivity.class);
                        startActivity(b);
                        Toast.makeText(DeleteListsActivity.this, "Profile screen opened", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_favorites:
                        Toast.makeText(DeleteListsActivity.this, "Favorites list opened", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });
    }

    /** ophalen van de films */
    public void getListData()
    {
        filmsRepository.getAccountLists(1, "EN", new OnGetAccountListsCallback()
        {
            @Override
            public void onSuccess(List<AccountList> accountlist)
            {
                adapter = new ListsAdapter(accountlist, DeleteListsActivity.this);

                accountListsRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onError()
            {
                Toast.makeText(DeleteListsActivity.this,
                        "Please check your internet connection.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /** een methode die een boolean als waarde teruggeeft die overschreven wordt. dit is in
     * verband met het 'back' (pijltje) knop om terug te gaan naar het vorige scherm */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Gemaakt door Tessa */
    /** Pagina altijd up to date */
    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
