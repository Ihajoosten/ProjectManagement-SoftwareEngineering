package com.example.theavansmovieapp.GUI.Animations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetGenresCallBack;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetOneFilmCallback;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetRatingCallback;
import com.example.theavansmovieapp.DataAcces.FilmsRepository;
import com.example.theavansmovieapp.Domain.Film;
import com.example.theavansmovieapp.Domain.Genre;
import com.example.theavansmovieapp.Domain.Rating;
import com.example.theavansmovieapp.GUI.Activity.FilmOverviewActivity;
import com.example.theavansmovieapp.GUI.Activity.MainActivity;
import com.example.theavansmovieapp.R;

import java.util.ArrayList;
import java.util.List;

/** gemaakt door luc */

public class PopupGenres extends Activity {

    private TextView filmTitle;
    private ImageButton closeButton;
    private ListView listView;
    private FilmsRepository repository;
    private ArrayAdapter arrayAdapter;
    private String genreId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_window_select_genre_layout);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*0.93), (int) (height*0.9));

        setUI();

        giveGenres();
        setClickListener();

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                finish();
            }
        });
    }

    //Gemaakt door Tessa
    public void giveGenres(){
        repository.getGenres(new OnGetGenresCallBack() {
            @Override
            public void onSuccess(List<Genre> genres) {
                genresToList(genres);
            }

            @Override
            public void onError() {

            }
        });
    }

    //Gemaakt door Tessa
    public void genresToList(List<Genre> genres) {
        ArrayList<String> genreNames = new ArrayList<>();
        for (Genre genre : genres) {
            String genreName = genre.getName();
            genreNames.add(genreName);
        }
       arrayAdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, genreNames);
        listView.setAdapter(arrayAdapter);
    }

    //Gemaakt door Tessa
    public void setClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final String item = (String) arrayAdapter.getItem(position);

                repository.getGenres(new OnGetGenresCallBack() {
            @Override
            public void onSuccess(List<Genre> genres) {
                for (Genre genre : genres) {
                    String genreName = genre.getName();
                    if(genreName.equals(item)){
                        int id = genre.getId();
                        String idString = "" + id;
                        genreId = idString;
                        Toast.makeText(PopupGenres.this, "Genre: " + genreName, Toast.LENGTH_SHORT).show();

                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result", genreId);
                        setResult(Activity.RESULT_OK,returnIntent);
                        finish();
                    }
                }
            }
            @Override
            public void onError() {
                Toast.makeText(PopupGenres.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

            }
        });
    }

    public void setUI(){
        filmTitle = findViewById(R.id.textView2);
        closeButton = findViewById(R.id.ib_close);
        listView = findViewById(R.id.pop_up_genres_list_view);
        repository = repository.getInstance();

    }

}
