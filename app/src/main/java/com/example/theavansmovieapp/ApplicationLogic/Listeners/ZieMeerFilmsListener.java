package com.example.theavansmovieapp.ApplicationLogic.Listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.theavansmovieapp.GUI.Activity.FilmOverviewActivity;
/** gemaakt door Anjuli **/
//Listener om naar de FilmOverview pagina te gaan
public class ZieMeerFilmsListener implements View.OnClickListener {

    private Context context;
    private String type;

    public ZieMeerFilmsListener(Context context, String type) {
        this.context = context;
        this.type = type;
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(context, FilmOverviewActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }
}
