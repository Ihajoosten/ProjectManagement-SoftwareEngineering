package com.example.theavansmovieapp.ApplicationLogic.Listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.theavansmovieapp.ApplicationLogic.Adapters.ListsAdapter;
import com.example.theavansmovieapp.DataAcces.FilmsRepository;
import com.example.theavansmovieapp.Domain.AccountList;
import com.example.theavansmovieapp.GUI.Activity.MylistActivity;
import com.example.theavansmovieapp.GUI.Activity.OnListClickedActivity;
/** gemaakt door Anjuli **/
//Opent de films die in een lijst zitten
public class GoToListContentListener implements View.OnClickListener {

    private MylistActivity activity;
    private int listID;
    private FilmsRepository filmsRepository;
    private Context context;

    public GoToListContentListener(MylistActivity activity, AccountList accountList, Context context) {
        this.listID = accountList.getId();
        filmsRepository = FilmsRepository.getInstance();
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, OnListClickedActivity.class);
        intent.putExtra("listID", listID);
        activity.startActivity(intent);
    }
}
