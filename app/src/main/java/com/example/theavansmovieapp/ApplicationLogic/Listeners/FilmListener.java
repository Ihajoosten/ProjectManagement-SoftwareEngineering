package com.example.theavansmovieapp.ApplicationLogic.Listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.theavansmovieapp.Domain.Film;
import com.example.theavansmovieapp.GUI.Activity.MovieDetailActivity;
/** gemaakt door Anjuli **/
//Listener voor films -> gaat naar film Details pagina
public class FilmListener implements View.OnClickListener {

    private Context context;
    private int filmID;

    public FilmListener(Context context, Film film) {
        this.context = context;
        this.filmID = film.getId();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra("film", filmID);
        context.startActivity(intent);
    }
}
