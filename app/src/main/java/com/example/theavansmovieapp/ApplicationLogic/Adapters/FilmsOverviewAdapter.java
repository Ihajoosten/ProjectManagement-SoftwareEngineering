package com.example.theavansmovieapp.ApplicationLogic.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.theavansmovieapp.ApplicationLogic.Listeners.FilmListener;
import com.example.theavansmovieapp.Domain.Film;
import com.example.theavansmovieapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/** gemaakt door Anjuli **/
//Koppelt de data aan de lijst items op de FilmOvervire pagina
//Lijst van alle films

public class FilmsOverviewAdapter extends RecyclerView.Adapter<FilmsOverviewAdapter.FilmsViewHolder> {

    private List<Film> films;
    private Context context;
    private String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w154";

    public FilmsOverviewAdapter(List<Film> films, Context context) {
        this.films = films;
        this.context = context;
    }

    @Override
    public FilmsOverviewAdapter.FilmsViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.overview_movies_item, parent, false);

        return new FilmsOverviewAdapter.FilmsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FilmsOverviewAdapter.FilmsViewHolder holder, int i) {
        Film film = films.get(i);
        holder.title.setText(film.getTitle());

        String imagePath = IMAGE_BASE_URL + film.getPosterPath();
        Picasso.get().load(imagePath).into(holder.poster);

        FilmListener listener = new FilmListener(context, film);
        holder.poster.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public class FilmsViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public ImageView poster;

        public FilmsViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title_tv);
            poster = itemView.findViewById(R.id.item_movie_poster_image);
        }
    }
}
