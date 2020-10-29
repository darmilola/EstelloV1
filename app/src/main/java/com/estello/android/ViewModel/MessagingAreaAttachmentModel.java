package com.estello.android.ViewModel;

public class MessagingAreaAttachmentModel {

    private int attachmentType;

    public MessagingAreaAttachmentModel(int attachmentType){

        this.attachmentType = attachmentType;
    }

    public int getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(int attachmentType) {
        this.attachmentType = attachmentType;
    }

}
