package com.estello.android.ViewModel;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class SkillsAndTagsModel implements Parcelable {

    private String label;
    private String id;
    private Drawable drawable;

    public SkillsAndTagsModel(String label,Drawable drawable){
        this.label = label;
        this.drawable = drawable;
    }


    protected SkillsAndTagsModel(Parcel in) {
        label = in.readString();
        id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(label);
        dest.writeString(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SkillsAndTagsModel> CREATOR = new Creator<SkillsAndTagsModel>() {
        @Override
        public SkillsAndTagsModel createFromParcel(Parcel in) {
            return new SkillsAndTagsModel(in);
        }

        @Override
        public SkillsAndTagsModel[] newArray(int size) {
            return new SkillsAndTagsModel[size];
        }
    };

    public String getLabel() {
        return label;
    }

    public Drawable getDrawable() {
        return drawable;
    }
}
