package com.example.theavansmovieapp.ApplicationLogic.Listeners;

import android.os.AsyncTask;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.theavansmovieapp.DataAcces.FilmsRepository;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetFavoriteCallback;
import com.example.theavansmovieapp.Domain.Favorite;
import com.example.theavansmovieapp.GUI.Activity.MovieDetailActivity;
/** gemaakt door Anjuli **/
// hartje in details pagina: listener voor als daarop is geklikt: toevoegen aan favorieten
public class HartListener implements CompoundButton.OnCheckedChangeListener {

    private MovieDetailActivity activity;
    private ScaleAnimation scaleAnimation;
    private int filmID;

    public HartListener(MovieDetailActivity activity, ScaleAnimation scaleAnimation, int filmID) {
        this.activity = activity;
        this.scaleAnimation = scaleAnimation;
        this.filmID = filmID;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

        compoundButton.startAnimation(scaleAnimation);

        if (isChecked) {
            Favorite fav = new Favorite("movie",filmID, true);
            AsyncTask<Favorite, Void, Void> async = new FavoriteAsync(true).execute(fav);
        }else if (!isChecked){
            Favorite fav = new Favorite("movie",filmID, false);
            AsyncTask<Favorite, Void, Void> asyncTask = new FavoriteAsync(false).execute(fav);
        }
    }

    private class FavoriteAsync extends AsyncTask<Favorite, Void, Void> {

        private FilmsRepository repository;
        private boolean isChecked;

        public FavoriteAsync(boolean isChecked) {
            this.isChecked = isChecked;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            repository = repository.getInstance();
        }

        @Override
        protected Void doInBackground(Favorite... favorites) {
            Favorite fav = favorites[0];
            repository.markFavorite(fav, new OnGetFavoriteCallback() {
                @Override
                public void onSuccess(Favorite favorite) {
                    if (isChecked){
                    Toast.makeText(activity, "added to favorite", Toast.LENGTH_SHORT).show();}
                    else if (!isChecked){
                        Toast.makeText(activity, "deleted from favorite", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError() {
                    Toast.makeText(activity, "went into onError", Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        }
    }
}