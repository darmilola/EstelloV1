package com.estello.android.ViewModel;

public class ExploreCommuntyBillboardItem {

    private int imageUrl;
    private int itemType;

    public ExploreCommuntyBillboardItem(int imageUrl){
        this.imageUrl = imageUrl;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }
}
