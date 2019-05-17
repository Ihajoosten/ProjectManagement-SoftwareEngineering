package com.example.theavansmovieapp.ApplicationLogic.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.theavansmovieapp.Domain.Film;
import com.example.theavansmovieapp.Domain.ListItem;
import com.example.theavansmovieapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/** Gemaakt door Anjuli **/
//Adapter om de films van een meegegevn listID in de recyclerview te plaatsen van de OnListClickecActivity

public class FilmsInMyListAdapter extends RecyclerView.Adapter<FilmsInMyListAdapter.FilmViewHolder> {

    private List<ListItem> films;
    private String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w154";

    public FilmsInMyListAdapter(List<ListItem> films) {
        this.films = films;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_movie, viewGroup, false);
        return new FilmViewHolder(v);
    }

    @Override
    public void onBindViewHolder( FilmViewHolder holder, int i) {
            ListItem film = films.get(i);

            holder.title.setText(film.getTitle());
        String imagePath = IMAGE_BASE_URL + film.getPosterPath();
        Picasso.get().load(imagePath).into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public ImageView poster;

        public FilmViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title_tv);
            poster = itemView.findViewById(R.id.item_movie_poster_image);

        }
    }
}
