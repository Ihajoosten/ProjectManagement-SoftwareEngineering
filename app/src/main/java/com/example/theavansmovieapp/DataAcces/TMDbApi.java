package com.example.theavansmovieapp.DataAcces;

import com.example.theavansmovieapp.Domain.Account;
import com.example.theavansmovieapp.Domain.AccountList;
import com.example.theavansmovieapp.Domain.Favorite;
import com.example.theavansmovieapp.Domain.Film;
import com.example.theavansmovieapp.Domain.Rating;
import com.example.theavansmovieapp.Domain.Review;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**     Gemaakt door Tessa      **/
//De gegevens uit de API worden aangeroepen, hierbij is Retrofit gebruikt

public interface TMDbApi {


    //Films ophalen
    @GET("{path1}/{path2}")
    Call<FilmsResponse> callFilms(
            @Path("path1") String path1,
            @Path("path2") String path2,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("sort_by") String sortBy,
            @Query("page") int page
    );


    //Films ophalen op basis van genre, alleen discover, 28 is action
    @GET("discover/movie")
    Call<FilmsResponse> callFilmsByGenre(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("sort_by") String sortBy,
            @Query("page") int page,
            @Query("with_genres") String genre
    );


    //Door Anjuli, ophalen film op basis van filmID
    @GET("movie/{movie_id}")
    Call<Film> callFilmDetail(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );


    //Ophalen genres
    @GET("genre/movie/list")
    Call<GenresResponse> getGenres(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );


    // door luc
    // trailers op te halen
    @GET("movie/{movie_id}/videos")
    Call<TrailerResponse> getTrailers(
            @Path("movie_id") int id,
            @Query("api_key") String apiKEy,
            @Query("language") String language
    );


    // door luc
    //ophalen reviews
    @GET("movie/{movie_id}/reviews")
    Call<ReviewResponse> getReviews(
            @Path("movie_id") int id,
            @Query("api_key") String apiKEy,
            @Query("language") String language
    );


    //Lijsten ophalen uit account
    @GET("account/{account_id}/lists")
    Call<ListsResponse> callAccountLists(
            @Path("account_id") int AccountId,
            @Query("api_key") String apiKey,
            @Query("session_id") String sessionId,
            @Query("language") String language,
            @Query("page") int page
    );


    //Account details ophalen
    @GET("account")
    Call<Account> callAccountDetails(
            @Query("api_key") String apiKey,
            @Query("session_id") String sessionId
    );


    //Lijst films ophalen, testlijst id = 108319
    @GET("list/{list_id}")
    Call<ListItemResponse> callListFilms(
            @Path("list_id") int ListId,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );


    //Film uit favorieten
    @GET("account/{account_id}/favorite/movies")
    Call<FilmsResponse> callFavoriteFilms(
            @Path("account_id") int AccountId,
            @Query("api_key") String apiKey,
            @Query("session_id") String sessionId,
            @Query("language") String language,
            @Query("sort_by") String sortBy,
            @Query("page") int page
    );


    //Film toevoegen aan/verwijderen uit favorieten
    @POST("account/{account_id}/favorite")
    Call<Favorite> callMarkFavorite(
            @Header("Content-Type") String contentType,
            @Body Favorite favorite,
            @Path("account_id") int AccountId,
            @Query("api_key") String apiKey,
            @Query("session_id") String sessionId
    );


    //Lijsten maken
    @POST("list")
    Call<NewList> callCreateList(
            @Body NewList newList,
            @Query("api_key") String apiKey,
            @Query("session_id") String sessionId
    );


    //Lijsten deleten
    @DELETE("list/{list_id}")
    Call<FilmsResponse> callListFilmsDelete(
            @Path("list_id") int ListId,
            @Query("api_key") String apiKey,
            @Query("session_id") String sessionId
    );


    //Film toevoegen aan lijst
    @POST("list/{list_id}/add_item")
    Call<NewFilm> callAddFilmToList(
            @Body NewFilm newFilm,
            @Path("list_id") int ListId,
            @Query("api_key") String apiKey,
            @Query("session_id") String sessionId
    );


    //Film verwijderen uit lijst
    @POST("list/{list_id}/remove_item")
    Call<NewFilm> callRemoveFilmFromList(
            @Body NewFilm newFilm,
            @Path("list_id") int ListId,
            @Query("api_key") String apiKey,
            @Query("session_id") String sessionId
    );


    //Zoeken
    @GET("search/movie")
    Call<FilmsResponse> callSearch(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String query,
            @Query("page") int page
    );


    //Film raten
    @POST("movie/{movie_id}/rating")
    Call<Rating> callRateFilm(
            @Body Rating rating,
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey,
            @Query("session_id") String sessionId
    );

}
