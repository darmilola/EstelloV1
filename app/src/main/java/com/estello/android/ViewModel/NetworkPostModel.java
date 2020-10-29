package com.estello.android.ViewModel;

import java.util.ArrayList;

public class NetworkPostModel {

    private String postId;
    private String senderId;
    private String senderFirstname;
    private String senderLastname;
    private String senderProfession;
    private String senderProfileImageUrl;
    private String postText;
    private String postCommentCount;
    private String postReactionCount;
    private String postTimeStamp;
    private ArrayList<NetworkPostImageModel> networkPostImageModelArrayList;
    private int viewType;


    public NetworkPostModel(){

    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public int getViewType() {
        return viewType;
    }

    public ArrayList<NetworkPostImageModel> getNetworkPostImageModelArrayList() {
        return networkPostImageModelArrayList;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setNetworkPostImageModelArrayList(ArrayList<NetworkPostImageModel> networkPostImageModelArrayList) {
        this.networkPostImageModelArrayList = networkPostImageModelArrayList;
    }
}
