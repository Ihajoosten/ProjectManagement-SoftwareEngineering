package com.example.theavansmovieapp.DataAcces.Callbacks;

import com.example.theavansmovieapp.Domain.Review;
import java.util.List;

/**     Gemaakt door Tessa      **/
//Callback voor reviews

public interface OnGetReviewsCallback {

    void onSuccess(List<Review> reviews);

    void onError();
}
