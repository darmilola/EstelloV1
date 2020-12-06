package com.estello.android.ViewModel;

public class CommunityViewMetadata {

    private int viewType;
    private String title;

    public CommunityViewMetadata(String title, int viewType){
        this.viewType = viewType;
        this.title = title;
    }

    public int getViewType() {
        return viewType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
