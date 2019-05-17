package com.example.theavansmovieapp.ApplicationLogic.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theavansmovieapp.Domain.Review;
import com.example.theavansmovieapp.R;

import java.util.List;

/** gemaakt door Anjuli **/
//Adapter voor de reviews in Film - Details pagina. Zorgt ervoor dat de recyclerview gevuld is met Viewholder items met reviews

public class FilmReviewsAdapter extends RecyclerView.Adapter<FilmReviewsAdapter.ReviewHolder> {

    private List<Review> reviews;
    private Context context;

    public FilmReviewsAdapter(List<Review> reviews, Context context) {
        this.reviews = reviews;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.review, viewGroup, false);
        return new ReviewHolder(v);
    }

    @Override
    public void onBindViewHolder(ReviewHolder reviewHolder, int i) {
        final Review review = reviews.get(i);
        Log.v("onBindViewholder", "reviews" + reviews.size());

        reviewHolder.author.setText(review.getAuthor());
        reviewHolder.content.setText(review.getContent());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ReviewHolder extends RecyclerView.ViewHolder{

        public TextView author;
        public TextView content;

        public ReviewHolder(View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.reviewAuthor);
            content = itemView.findViewById(R.id.reviewContent);
        }
    }
}
