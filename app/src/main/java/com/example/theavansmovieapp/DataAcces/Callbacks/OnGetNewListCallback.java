package com.example.theavansmovieapp.DataAcces.Callbacks;

import com.example.theavansmovieapp.DataAcces.NewList;
import com.example.theavansmovieapp.Domain.Film;

import java.util.List;

/**     Gemaakt door Tessa      **/
//Callback voor het aanmaken van een nieuwe lijst

public interface OnGetNewListCallback {

    void onSuccess(NewList newList);

    void onError();
}