package com.example.theavansmovieapp.DataAcces.Callbacks;

import com.example.theavansmovieapp.Domain.Film;
import com.example.theavansmovieapp.Domain.ListItem;

import java.util.List;

/**     Gemaakt door Tessa      **/
//Callback voor films uit een specifieke lijst

public interface OnGetListItemsCallback {

    void onSuccess(List<ListItem> listItems);

    void onError();
}
