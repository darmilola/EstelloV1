package com.estello.android.ViewModel;

public class FaqAnswerModel {

    private String mFaqAnswer;
    private String mFaqQuestionId;


    public FaqAnswerModel(){

    }

    public String getmFaqAnswer() {
        return mFaqAnswer;
    }

    public String getmFaqQuestionId() {
        return mFaqQuestionId;
    }

    public void setmFaqAnswer(String mFaqAnswer) {
        this.mFaqAnswer = mFaqAnswer;
    }

    public void setmFaqQuestionId(String mFaqQuestionId) {
        this.mFaqQuestionId = mFaqQuestionId;
    }

}
