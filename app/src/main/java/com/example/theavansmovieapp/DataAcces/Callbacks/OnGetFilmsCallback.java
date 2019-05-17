package com.example.theavansmovieapp.DataAcces.Callbacks;

import com.example.theavansmovieapp.Domain.Film;

import java.util.List;

/**     Gemaakt door Tessa      **/
//Callback voor films

public interface OnGetFilmsCallback {

    void onSuccess(List<Film> films);

    void onError();
}