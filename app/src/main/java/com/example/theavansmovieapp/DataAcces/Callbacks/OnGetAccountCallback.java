package com.example.theavansmovieapp.DataAcces.Callbacks;

import com.example.theavansmovieapp.Domain.Account;
import com.example.theavansmovieapp.Domain.Film;

import java.util.List;

/**     Gemaakt door Tessa      **/
//Callback voor account

public interface OnGetAccountCallback {

    void onSuccess(Account account);

    void onError();
}