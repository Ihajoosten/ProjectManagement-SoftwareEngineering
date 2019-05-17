package com.example.theavansmovieapp.ApplicationLogic.Adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.theavansmovieapp.Domain.Trailer;
import com.example.theavansmovieapp.GUI.Activity.MovieDetailActivity;
import com.example.theavansmovieapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;
/** gemaakt door Anjuli **/
//Adapter voor de trailers in de MovieDetails activity, zet de trailers in de recyclerview.
public class FilmTrailersAdapter extends RecyclerView.Adapter<FilmTrailersAdapter.TrailerViewHolder> {

    private List<Trailer> trailers;
    private static String YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v=%s";
    private static String YOUTUBE_THUMBNAIL_URL = "https://img.youtube.com/vi/";
    private MovieDetailActivity activity;

    public FilmTrailersAdapter(List<Trailer> trailers, MovieDetailActivity activity) {
        this.trailers = trailers;
        this.activity = activity;
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.thumbnail_trailer, parent, false);
        return new TrailerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder trailerViewHolder, int i) {
        final Trailer trailer = trailers.get(i);

        Picasso.get().load(YOUTUBE_THUMBNAIL_URL + trailer.getKey()+ "/0.jpg").into(trailerViewHolder.thumbnail) ;
        trailerViewHolder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTrailer(String.format(YOUTUBE_VIDEO_URL, trailer.getKey()));
            }
        });
    }

    @Override
    public int getItemCount() {

        return trailers.size();
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder {

        public ImageView thumbnail;

        public TrailerViewHolder(View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }

    private void showTrailer(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
    }
}

