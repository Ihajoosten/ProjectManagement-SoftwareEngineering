package com.example.theavansmovieapp.GUI.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theavansmovieapp.DataAcces.FilmsRepository;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetAccountCallback;
import com.example.theavansmovieapp.Domain.Account;
import com.example.theavansmovieapp.R;
import com.squareup.picasso.Picasso;

/** gemaakt door luc */

public class ProfileActivity extends AppCompatActivity
{
    /** de klas attributen **/
    private static String IMAGE_BASE_URL = "https://www.gravatar.com/avatar/";
    private static String IMAGE_EXTENTION = ".jpg";
    private FilmsRepository repository;
    private ImageView profilePicture;
    private TextView username;
    private TextView adult;
    private TextView language;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        repository = repository.getInstance();
        setUI();

        getUserInfo();

        /** add back arrow to toolbar */
        if (getSupportActionBar() != null)
        {
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
                 * navigation_profile dan wordt er geen intent gestart omdat het profile scherm al actief is. als het item id overeenkomt met
                 * navigation_favorites dan wordt er een intent gestart die door navigeert naar de favorieten lijst van het account
                 */
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent a = new Intent(ProfileActivity.this,HomeActivity.class);
                        startActivity(a);
                        Toast.makeText(ProfileActivity.this, "Home screen opened", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_profile:
                        Toast.makeText(ProfileActivity.this, "Profile screen already opened", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_favorites:
                        Intent c = new Intent(ProfileActivity.this,FavoritesListActivity.class);
                        startActivity(c);
                        Toast.makeText(ProfileActivity.this, "Favorites list screen opened", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });
    }

    public void setUI()
    {
        /** Set textviews and imageview */
        profilePicture = findViewById(R.id.profile_avatar);
        username = findViewById(R.id.username_tv);
        adult = findViewById(R.id.adult_tv);
        language = findViewById(R.id.language_tv);
    }

    public void getUserInfo()
    {
        /** get user information */
        repository.getAccountDetails(new OnGetAccountCallback()
        {
            @Override
            public void onSuccess(Account account)
            {
                /** set user information */
                username.setText(account.getUsername());
                adult.setText(String.valueOf(account.isInclude_adult()));
                language.setText(account.getLanguage_code());
                Picasso.get().load(IMAGE_BASE_URL + account.getGravatarHash()).into(profilePicture);
            }

            @Override
            public void onError()
            {
                finish();
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
