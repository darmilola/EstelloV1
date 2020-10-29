package com.estello.android.ViewModel;

import android.os.Parcel;
import android.os.Parcelable;

public class CreateQuizQuestionAnswers implements Parcelable {

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answerOption;
    private int viewType;

    public CreateQuizQuestionAnswers(String optionA, String optionB, String optionC, String optionD, String answerOption,int viewType) {

        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answerOption = answerOption;
        this.viewType = viewType;
    }

    protected CreateQuizQuestionAnswers(Parcel in) {
        optionA = in.readString();
        optionB = in.readString();
        optionC = in.readString();
        optionD = in.readString();
        answerOption = in.readString();
        viewType = in.readInt();
    }

    public static final Creator<CreateQuizQuestionAnswers> CREATOR = new Creator<CreateQuizQuestionAnswers>() {
        @Override
        public CreateQuizQuestionAnswers createFromParcel(Parcel in) {
            return new CreateQuizQuestionAnswers(in);
        }

        @Override
        public CreateQuizQuestionAnswers[] newArray(int size) {
            return new CreateQuizQuestionAnswers[size];
        }
    };

    public String getAnswerOption() {
        return answerOption;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public void setAnswerOption(String answerOption) {
        this.answerOption = answerOption;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(optionA);
        parcel.writeString(optionB);
        parcel.writeString(optionC);
        parcel.writeString(optionD);
        parcel.writeString(answerOption);
        parcel.writeInt(viewType);
    }
}