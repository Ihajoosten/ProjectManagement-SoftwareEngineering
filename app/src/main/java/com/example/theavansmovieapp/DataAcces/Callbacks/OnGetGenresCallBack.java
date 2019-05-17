package com.example.theavansmovieapp.DataAcces.Callbacks;

import com.example.theavansmovieapp.Domain.Genre;

import java.util.List;

/**     Gemaakt door Anjuli      **/
//Callback voor genres

public interface OnGetGenresCallBack {

    void onSuccess(List<Genre> genres);

    void onError();

}
