/*
Gemaakt door: Stefan Ilmer
Datum laatste update: 02-04-2019

Gecontroleerd door:
Gecontroleerd op:
*/

package com.example.theavansmovieapp.ApplicationLogic.Listeners;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.theavansmovieapp.ApplicationLogic.Adapters.ListsAdapter;
import com.example.theavansmovieapp.DataAcces.FilmsRepository;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetFilmsCallback;
import com.example.theavansmovieapp.Domain.AccountList;
import com.example.theavansmovieapp.Domain.Film;
import com.example.theavansmovieapp.GUI.Activity.DeleteListsActivity;

import java.util.List;
/** gemaakt door Anjuli **/
//Listener om lijsten te verwijderen
public class ListListener implements View.OnClickListener
{
    private DeleteListsActivity activity;
    private int listID;
    private FilmsRepository filmsRepository;
    private ListsAdapter adapter;

    public ListListener(DeleteListsActivity activity, AccountList accountList, ListsAdapter adapter) {
        this.adapter = adapter;
        this.activity = activity;
        this.listID = accountList.getId();
        filmsRepository = FilmsRepository.getInstance();
    }

    @Override
    public void onClick(View v)
    {

        Log.d("ListID", String.valueOf(listID));
        adapter.notifyDataSetChanged();

        filmsRepository.deleteList(listID, new OnGetFilmsCallback()
        {
            @Override
            public void onSuccess(List<Film> films) {

            }

            @Override
            public void onError() {
                Toast.makeText(activity, "Lijst verwijderd", Toast.LENGTH_LONG).show();
                activity.finish();
            }
        });
    }
}
