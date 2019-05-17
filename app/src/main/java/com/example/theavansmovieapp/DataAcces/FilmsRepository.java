package com.example.theavansmovieapp.DataAcces;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetAccountCallback;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetAccountListsCallback;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetFavoriteCallback;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetFilmsCallback;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetGenresCallBack;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetListItemsCallback;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetNewFilmCallback;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetNewListCallback;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetOneFilmCallback;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetRatingCallback;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetReviewsCallback;
import com.example.theavansmovieapp.DataAcces.Callbacks.OnGetTrailersCallBack;
import com.example.theavansmovieapp.Domain.Account;
import com.example.theavansmovieapp.Domain.Favorite;
import com.example.theavansmovieapp.Domain.Film;
import com.example.theavansmovieapp.Domain.Rating;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**     Gemaakt door Tessa      **/
//Methodes om gegevens uit de API op te halen

public class FilmsRepository {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "7f978edae911d5a9659e24694755bc70";
    private static final String SESSION_ID = "3d92ceb7aae6a1056bbd5f7e74c842885b989371";
    private static final int ACCOUNT_ID = 8340706;
    private static final String CONTENT_TYPE = "application/json;charset=utf-8";

    //Inlog gegevens: tessa.numan
    //Wachtwoord: knuffelbeertjes

    private static FilmsRepository repository;

    private TMDbApi api;

    private FilmsRepository(TMDbApi api) {
        this.api = api;
    }

    public static FilmsRepository getInstance() {
        if (repository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            repository = new FilmsRepository(retrofit.create(TMDbApi.class));
        }
        return repository;
    }


    //Films ophalen uit API
    public void getFilms(String path1, String path2, String language, String sortBy, int page, final OnGetFilmsCallback callback) {
        api.callFilms(path1, path2,API_KEY, language, sortBy, page )
                .enqueue(new Callback<FilmsResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<FilmsResponse> call, @NonNull Response<FilmsResponse> response)
                    {
                        Log.d("inputType", response.toString());
                        if (response.isSuccessful()) {
                            FilmsResponse filmsResponse = response.body();
                            if (filmsResponse != null && filmsResponse.getFilms() != null) {
                                callback.onSuccess(filmsResponse.getFilms());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<FilmsResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }


    //Films ophalen op basis van genre, alleen discover, 28 is action
    public void getFilmsByGenre(String language, String sortBy, int page, String genre, final OnGetFilmsCallback callback) {
        api.callFilmsByGenre(API_KEY, language, sortBy, page, genre)
                .enqueue(new Callback<FilmsResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<FilmsResponse> call, @NonNull Response<FilmsResponse> response) {
                        if (response.isSuccessful()) {
                            FilmsResponse filmsResponse = response.body();
                            if (filmsResponse != null && filmsResponse.getFilms() != null) {
                                callback.onSuccess(filmsResponse.getFilms());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<FilmsResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }


    //Reviews ophalen
    public void getReviews(int movieId, final OnGetReviewsCallback callback) {
        api.getReviews(movieId, API_KEY, "en")
                .enqueue(new Callback<ReviewResponse>() {
                    @Override
                    public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                        if (response.isSuccessful()) {
                            ReviewResponse reviewResponse = response.body();
                            if (reviewResponse != null && reviewResponse.getReviews() != null) {
                                callback.onSuccess(reviewResponse.getReviews());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<ReviewResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }


    //Accountdetails ophalen
    public void getAccountDetails(final OnGetAccountCallback callback) {
        api.callAccountDetails(API_KEY, SESSION_ID)
                .enqueue(new Callback<Account>() {
                    @Override
                    public void onResponse(@NonNull Call<Account> call, @NonNull Response<Account> response) {
                        if (response.isSuccessful()) {
                            Account account = response.body();
                            if (account != null && account.getId() != 0) {
                                callback.onSuccess(account);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }
                    @Override
                    public void onFailure(Call<Account> call, Throwable t) {
                        callback.onError();
                    }
                });
    }


    //Lijsten ophalen uit account
    public void getAccountLists(int page, String language, final OnGetAccountListsCallback callback) {
        api.callAccountLists(ACCOUNT_ID, API_KEY, SESSION_ID, language, page)
                .enqueue(new Callback<ListsResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ListsResponse> call, @NonNull Response<ListsResponse> response) {
                        if (response.isSuccessful()) {
                            ListsResponse listsResponse = response.body();
                            if (listsResponse != null && listsResponse.getLists() != null) {
                                callback.onSuccess(listsResponse.getLists());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<ListsResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }


    //Films uit lijst ophalen
    public void getListFilms(int listId, String language, final OnGetListItemsCallback callback) {
        api.callListFilms(listId, API_KEY, language)
                .enqueue(new Callback<ListItemResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ListItemResponse> call, @NonNull Response<ListItemResponse> response) {
                        if (response.isSuccessful()) {
                            ListItemResponse listItemResponse = response.body();
                            if (listItemResponse != null && listItemResponse.getListItems() != null) {
                                callback.onSuccess(listItemResponse.getListItems());
                            } else {
                                callback.onError();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ListItemResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }


    //Favorieten ophalen
    public void getFavoriteFilms(final OnGetFilmsCallback callback) {
        api.callFavoriteFilms(ACCOUNT_ID, API_KEY, SESSION_ID, "en", "popular.desc", 1)
                .enqueue(new Callback<FilmsResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<FilmsResponse> call, @NonNull Response<FilmsResponse> response) {
                        if (response.isSuccessful()) {
                            FilmsResponse filmsResponse = response.body();
                            if (filmsResponse != null && filmsResponse.getFilms() != null) {
                                callback.onSuccess(filmsResponse.getFilms());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<FilmsResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }


    //Film toevoegen aan/verwijderen uit favorieten
    public void markFavorite(Favorite favorite, final OnGetFavoriteCallback callback) {
        api.callMarkFavorite(CONTENT_TYPE, favorite, ACCOUNT_ID, API_KEY, SESSION_ID)
                .enqueue(new Callback<Favorite>() {
                    @Override
                    public void onResponse(@NonNull Call<Favorite> call, @NonNull Response<Favorite> response) {

                        if (response.isSuccessful()) {
                            Favorite favorite = response.body();
                            if (favorite != null) {
                                callback.onSuccess(favorite);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }

                    }

                    @Override
                    public void onFailure(Call<Favorite> call, Throwable t) {
                        callback.onError();
                    }
                });
    }


    //Details van een film ophalen
    public void getFilmDetails(int filmID, String language, final OnGetOneFilmCallback callback) {
        api.callFilmDetail(filmID, API_KEY, language)
                .enqueue(new Callback<Film>() {
                    @Override
                    public void onResponse(Call<Film> call, Response<Film> response) {
                        if (response.isSuccessful()) {
                            Film film = response.body();
                            if (film != null) {
                                callback.onSuccess(film);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Film> call, Throwable t) {

                    }
                });
    }


    //Films zoeken
    public void getSearch(String language, String query, int page, final OnGetFilmsCallback callback) {
        api.callSearch(API_KEY, language, query, page)
                .enqueue(new Callback<FilmsResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<FilmsResponse> call, @NonNull Response<FilmsResponse> response) {
                        if (response.isSuccessful()) {
                            FilmsResponse filmsResponse = response.body();
                            if (filmsResponse != null && filmsResponse.getFilms() != null) {
                                callback.onSuccess(filmsResponse.getFilms());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<FilmsResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }


    //Create list
    public void createList(NewList newList, final OnGetNewListCallback callback) {
        api.callCreateList(newList, API_KEY, SESSION_ID)
                .enqueue(new Callback<NewList>() {
                    @Override
                    public void onResponse(@NonNull Call<NewList> call, @NonNull Response<NewList> response) {
                        if (response.isSuccessful()) {
                            NewList newList = response.body();
                            if (newList != null) {
                                callback.onSuccess(newList);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<NewList> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

    //Lijst verwijderen
    public void deleteList(int listId, final OnGetFilmsCallback callback) {
        api.callListFilmsDelete(listId, API_KEY, SESSION_ID)
                .enqueue(new Callback<FilmsResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<FilmsResponse> call, @NonNull Response<FilmsResponse> response) {
                        if (response.isSuccessful()) {
                            Log.d("DELETE", response.toString());
                            Log.d("DELETE", api.toString());

                            FilmsResponse filmsResponse = response.body();
                            if (filmsResponse != null && filmsResponse.getFilms() != null) {
                                callback.onSuccess(filmsResponse.getFilms());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<FilmsResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }


    //Film aan lijst toevoegen
    public void addFilmToList(NewFilm newFilm, int listId, final OnGetNewFilmCallback callback) {
        api.callAddFilmToList(newFilm, listId, API_KEY, SESSION_ID)
                .enqueue(new Callback<NewFilm>() {
                    @Override
                    public void onResponse(@NonNull Call<NewFilm> call, @NonNull Response<NewFilm> response) {
                        if (response.isSuccessful()) {
                            NewFilm newFilm = response.body();
                            if (newFilm != null) {
                                callback.onSuccess(newFilm);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<NewFilm> call, Throwable t) {
                        callback.onError();
                    }
                });
    }


    //Film uit lijst verwijderen
    public void removeFilmFromList(NewFilm newFilm, int listId, final OnGetNewFilmCallback callback) {
        api.callRemoveFilmFromList(newFilm, listId, API_KEY, SESSION_ID)
                .enqueue(new Callback<NewFilm>() {
                    @Override
                    public void onResponse(@NonNull Call<NewFilm> call, @NonNull Response<NewFilm> response) {
                        if (response.isSuccessful()) {
                            NewFilm newFilm = response.body();
                            if (newFilm != null) {
                                callback.onSuccess(newFilm);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<NewFilm> call, Throwable t) {
                        callback.onError();
                    }
                });
    }


    public void getGenres(final OnGetGenresCallBack callback) {
        api.getGenres(API_KEY, "en")
                .enqueue(new Callback<GenresResponse>() {
                    @Override
                    public void onResponse(Call<GenresResponse> call, Response<GenresResponse> response) {
                        if (response.isSuccessful()) {
                            GenresResponse genresResponse = response.body();
                            if (genresResponse != null && genresResponse.getGenres() != null) {
                                callback.onSuccess(genresResponse.getGenres());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<GenresResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }


    //Film raten
    public void rateFilm(Rating rating, int movieId, final OnGetRatingCallback callback) {
        api.callRateFilm(rating, movieId, API_KEY, SESSION_ID)
                .enqueue(new Callback<Rating>() {
                    @Override
                    public void onResponse(@NonNull Call<Rating> call, @NonNull Response<Rating> response) {
                        if (response.isSuccessful()) {
                            Rating rating = response.body();
                            if (rating != null) {
                                callback.onSuccess(rating);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<Rating> call, Throwable t) {
                        callback.onError();
                    }
                });
    }


    public void getTrailers(int movieId, final OnGetTrailersCallBack callback) {
        api.getTrailers(movieId, API_KEY, "en")
                .enqueue(new Callback<TrailerResponse>() {
                    @Override
                    public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                        if (response.isSuccessful()) {
                            TrailerResponse trailerResponse = response.body();
                            if (trailerResponse != null && trailerResponse.getTrailers() != null) {
                                callback.onSuccess(trailerResponse.getTrailers());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<TrailerResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }
}
