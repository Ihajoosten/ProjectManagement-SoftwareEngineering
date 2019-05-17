package com.example.theavansmovieapp.DataAcces.Callbacks;


import com.example.theavansmovieapp.Domain.Rating;

/**     Gemaakt door Tessa      **/
//Callback voor rating

public interface OnGetRatingCallback {

    void onSuccess(Rating rating);

    void onError();
}
