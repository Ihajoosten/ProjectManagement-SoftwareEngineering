package com.example.theavansmovieapp.DataAcces.Callbacks;

import com.example.theavansmovieapp.Domain.Trailer;

import java.util.List;

/**     Gemaakt door Anjuli      **/
//Callback voor trailers

public interface OnGetTrailersCallBack {
    void onSuccess(List<Trailer> trailers);

    void onError();
}
