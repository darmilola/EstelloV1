package com.estello.android.ViewModel;

public class NetworkPostImageModel {

    private String imgId;
    private String imageUrl;

    public NetworkPostImageModel(String imgId, String imageUrl){

        this.imgId = imgId;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }
}
