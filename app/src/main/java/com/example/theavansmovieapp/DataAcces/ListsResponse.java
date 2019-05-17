package com.example.theavansmovieapp.DataAcces;

import com.example.theavansmovieapp.Domain.AccountList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**     Gemaakt door Tessa      **/
//De gegevens over de gemaakte lijsten worden verzameld


public class ListsResponse {

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("total_results")
    @Expose
    private int totalResults;

    @SerializedName("results")
    @Expose
    private List<AccountList> lists;

    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<AccountList> getLists() {
        return lists;
    }

    public void setLists(List<AccountList> lists) {
        this.lists = lists;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
