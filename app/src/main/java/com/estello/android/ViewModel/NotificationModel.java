package com.estello.android.ViewModel;

public class NotificationModel {

    private  int notificationType;
    private String notificationText;

    public NotificationModel(int NotificationType){
        this.notificationType = NotificationType;
    }

    public int getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(int notificationType) {
        this.notificationType = notificationType;
    }
}
