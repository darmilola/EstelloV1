package com.estello.android.ViewModel;

public class QuizModel {

    private String quizNumber;
    private String quizTitle;
    private String quizDescription;

    public QuizModel(String quizNumber, String quizTitle, String quizDescription){

        this.quizNumber = quizNumber;
        this.quizTitle = quizTitle;
        this.quizDescription = quizDescription;
    }

    public QuizModel(){

    }

    public String getQuizDescription() {
        return quizDescription;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public String getQuizNumber() {
        return quizNumber;
    }

    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }

    public void setQuizNumber(String quizNumber) {
        this.quizNumber = quizNumber;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }
}
