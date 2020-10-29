package com.estello.android.ViewModel;

import android.os.Parcel;
import android.os.Parcelable;

public class CourseWeekSectionDetailsModel implements Parcelable {


    private int viewType;

    public CourseWeekSectionDetailsModel(int viewType) {

        this.viewType = viewType;
    }

    protected CourseWeekSectionDetailsModel(Parcel in) {
        viewType = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(viewType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CourseWeekSectionDetailsModel> CREATOR = new Creator<CourseWeekSectionDetailsModel>() {
        @Override
        public CourseWeekSectionDetailsModel createFromParcel(Parcel in) {
            return new CourseWeekSectionDetailsModel(in);
        }

        @Override
        public CourseWeekSectionDetailsModel[] newArray(int size) {
            return new CourseWeekSectionDetailsModel[size];
        }
    };

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
