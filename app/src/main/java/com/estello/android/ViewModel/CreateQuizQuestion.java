package com.estello.android.ViewModel;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

public class CreateQuizQuestion extends ExpandableGroup<CreateQuizQuestionAnswers> {


    private String questionNumber;
    private String question;
    private int viewType;
    private ArrayList<CreateQuizQuestionAnswers> answersArrayList = new ArrayList<>();



    public CreateQuizQuestion(String questionNumber, String question, ArrayList<CreateQuizQuestionAnswers> answersArrayList, int viewType){

        super(questionNumber,answersArrayList);
        this.questionNumber = questionNumber;
        this.question = question;
        this.answersArrayList = answersArrayList;
        this.viewType = viewType;
    }



    public ArrayList<CreateQuizQuestionAnswers> getAnswersArrayList() {
        return answersArrayList;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public List<CreateQuizQuestionAnswers> getItems() {
        return super.getItems();
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }


    public String getQuestion() {
        return question;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setAnswersArrayList(ArrayList<CreateQuizQuestionAnswers> answersArrayList) {
        this.answersArrayList = answersArrayList;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

}
