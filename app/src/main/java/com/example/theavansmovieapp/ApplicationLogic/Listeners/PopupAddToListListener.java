package com.example.theavansmovieapp.ApplicationLogic.Listeners;

import android.view.View;
import android.widget.Toast;

import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetNewFilmCallback;
import com.example.theavansmovieapp.DataAcces.FilmsRepository;
import com.example.theavansmovieapp.DataAcces.NewFilm;
import com.example.theavansmovieapp.Domain.AccountList;
import com.example.theavansmovieapp.GUI.Animations.PopupAddMovie;
/** gemaakt door Anjuli **/
//Listener om film toe te voegen aan een lijst
public class PopupAddToListListener implements View.OnClickListener {

    private AccountList list;
    private PopupAddMovie activity;
    private FilmsRepository repository;
    private int filmID;

    public PopupAddToListListener(AccountList list, PopupAddMovie activity, int filmID) {
        this.list = list;
        this.activity = activity;
        this.filmID = filmID;
        this.repository = repository.getInstance();
    }

    @Override
    public void onClick(View v) {
        NewFilm newFilm = new NewFilm(filmID);
        repository.addFilmToList(newFilm, list.getId(), new OnGetNewFilmCallback() {
            @Override
            public void onSuccess(NewFilm newFilm) {
                Toast.makeText(activity, "Film added to " + list.getName(), Toast.LENGTH_SHORT).show();
                activity.finish();
            }

            @Override
            public void onError() {

            }
        });
    }
}
