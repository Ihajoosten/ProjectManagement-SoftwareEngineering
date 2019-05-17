package com.example.theavansmovieapp.GUI.Animations;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theavansmovieapp.ApplicationLogic.Adapters.PopupAddToListAdapter;
import com.example.theavansmovieapp.ApplicationLogic.Listeners.PopupAddToListListener;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetAccountListsCallback;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetOneFilmCallback;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetRatingCallback;
import com.example.theavansmovieapp.DataAcces.FilmsRepository;
import com.example.theavansmovieapp.Domain.AccountList;
import com.example.theavansmovieapp.Domain.Film;
import com.example.theavansmovieapp.Domain.Rating;
import com.example.theavansmovieapp.GUI.Activity.MovieDetailActivity;
import com.example.theavansmovieapp.R;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/** gemaakt door luc */

public class PopupAddMovie extends Activity {

    /** klas attributen */
    private int filmID;
    private String filmName;
    private TextView filmTitle;
    private ImageButton closeButton;
    private RecyclerView recyclerView;
    private FilmsRepository repository;
    private List<AccountList> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_window_add_movie_to_list);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*0.93), (int) (height*0.9));

        setUI();

        filmID = getIntent().getExtras().getInt("filmID");
        filmName = getIntent().getExtras().getString("filmName");
        filmTitle.setText("Choose a list to add the movie: " + filmName);

        LinearLayoutManager manager = new LinearLayoutManager(PopupAddMovie.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        repository = repository.getInstance();
        repository.getAccountLists(1, "en", new OnGetAccountListsCallback() {
            @Override
            public void onSuccess(List<AccountList> accountlist) {
                recyclerView.setAdapter(new PopupAddToListAdapter(accountlist, PopupAddMovie.this, filmID));
            }

            @Override
            public void onError() {

            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                finish();
            }
        });
    }

    public void setUI(){
        filmTitle = findViewById(R.id.add_movie_title);
        closeButton = findViewById(R.id.ib_close);
        recyclerView = findViewById(R.id.pop_up_select_movie_recycler_view);

    }
}