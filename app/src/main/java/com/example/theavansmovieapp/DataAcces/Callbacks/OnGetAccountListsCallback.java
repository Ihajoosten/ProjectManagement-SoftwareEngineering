package com.example.theavansmovieapp.DataAcces.Callbacks;

import com.example.theavansmovieapp.Domain.Account;
import com.example.theavansmovieapp.Domain.AccountList;
import com.example.theavansmovieapp.Domain.Film;

import java.util.List;

/**     Gemaakt door Tessa      **/
//Callback voor lijsten

public interface OnGetAccountListsCallback {

    void onSuccess(List<AccountList> accountlist);

    void onError();
}