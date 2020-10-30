package com.estello.android.ViewModel;

import java.util.ArrayList;

public class ForumPostModel {
    private ArrayList<User> recentReplyingUsersList;
    private ArrayList<ForumPostAttachmentsModel> postAttachmentList;
    private String forumPostText;
    private String forumPostSenderName;
    private String forumPostSenderId;
    private String forumPostSenderPhotoUrl;
    private int type;
    private String postDate;
    private String postGroupDate;

    public ForumPostModel(ArrayList<User> recentReplyingUsersList, ArrayList<ForumPostAttachmentsModel> postAttachmentList,int type){

        this.postAttachmentList = postAttachmentList;
        this.recentReplyingUsersList = recentReplyingUsersList;
        this.type = type;
    }

    public ArrayList<ForumPostAttachmentsModel> getPostAttachmentList() {
        return postAttachmentList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getPostDate() {
        return postDate;
    }

    public String getPostGroupDate() {
        return postGroupDate;
    }

    public void setPostGroupDate(String postGroupDate) {
        this.postGroupDate = postGroupDate;
    }

    public ArrayList<User> getRecentReplyingUsersList() {
        return recentReplyingUsersList;
    }

    public void setPostAttachmentList(ArrayList<ForumPostAttachmentsModel> postAttachmentList) {
        this.postAttachmentList = postAttachmentList;
    }

    public void setRecentReplyingUsersList(ArrayList<User> recentReplyingUsersList) {
        this.recentReplyingUsersList = recentReplyingUsersList;
    }
}
