package com.estello.android.ViewModel;

import android.os.Parcel;
import android.os.Parcelable;

public class training_materials_section_details_model implements Parcelable {

    private String fileId;
    private String fileDownloadUrl;
    private String fileSize;
    private String fileApproximateCompletionTime;
    private int fileType;

    public training_materials_section_details_model(){

    }

    protected training_materials_section_details_model(Parcel in) {
        fileId = in.readString();
        fileDownloadUrl = in.readString();
        fileSize = in.readString();
        fileApproximateCompletionTime = in.readString();
        fileType = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fileId);
        dest.writeString(fileDownloadUrl);
        dest.writeString(fileSize);
        dest.writeString(fileApproximateCompletionTime);
        dest.writeInt(fileType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<training_materials_section_details_model> CREATOR = new Creator<training_materials_section_details_model>() {
        @Override
        public training_materials_section_details_model createFromParcel(Parcel in) {
            return new training_materials_section_details_model(in);
        }

        @Override
        public training_materials_section_details_model[] newArray(int size) {
            return new training_materials_section_details_model[size];
        }
    };

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }



}
