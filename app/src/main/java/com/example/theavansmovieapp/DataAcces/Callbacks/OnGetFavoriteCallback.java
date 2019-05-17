package com.example.theavansmovieapp.DataAcces.Callbacks;


import com.example.theavansmovieapp.Domain.Favorite;

/**     Gemaakt door Tessa      **/
//Callback voor favorieten

public interface OnGetFavoriteCallback {

    void onSuccess(Favorite favorite);

    void onError();
}
