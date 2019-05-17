package com.example.theavansmovieapp.ApplicationLogic.Listeners;

import android.content.Context;
import android.widget.SearchView;

import com.example.theavansmovieapp.DataAcces.FilmsRepository;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetFilmsCallback;
import com.example.theavansmovieapp.Domain.Film;
import com.example.theavansmovieapp.GUI.Activity.FilmOverviewActivity;

import java.util.List;
/** gemaakt door Anjuli **/
//Zoekfunctie in Filmoverview activity's
public class ZoekbalkListener implements SearchView.OnQueryTextListener {

    private FilmsRepository repository;
    private Context context;
    private FilmOverviewActivity activity;

    public ZoekbalkListener(Context context, FilmOverviewActivity activity) {
        repository = repository.getInstance();
        this.activity = activity;
        this.context = context;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        repository.getSearch("en", query, 1, new OnGetFilmsCallback() {
            @Override
            public void onSuccess(List<Film> films) {
                activity.showFilms(films);
            }

            @Override
            public void onError() {

            }
        });
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
