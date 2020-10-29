package com.estello.android.ViewModel;

public class SearchHistoryModel {

    private String searchQuery;

    public SearchHistoryModel(String searchQuery){

        this.searchQuery = searchQuery;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

}
