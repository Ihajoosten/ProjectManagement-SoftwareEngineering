package com.example.theavansmovieapp.DataAcces.Callbacks;

import com.example.theavansmovieapp.Domain.Film;

/**     Gemaakt door Anjuli      **/
//Callback voor film details

public interface OnGetOneFilmCallback {
    void onSuccess(Film film);

    void onError();
}