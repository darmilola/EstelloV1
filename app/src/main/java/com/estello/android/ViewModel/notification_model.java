package com.estello.android.ViewModel;

public class notification_model {
    private String id;
    private String postId;
    private String notificationText;
    private String profileImageUrl;


    public notification_model(){

    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setId(String id) {
        this.id = id;
    }



    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }


}
