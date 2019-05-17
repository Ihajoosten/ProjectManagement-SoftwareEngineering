package com.example.theavansmovieapp.DataAcces.Callbacks;

import com.example.theavansmovieapp.DataAcces.NewFilm;

/**     Gemaakt door Tessa      **/
//Callback voor het toevoegen van een film aan een lijst

public interface OnGetNewFilmCallback {

    void onSuccess(NewFilm newFilm);

    void onError();
}