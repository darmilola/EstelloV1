package com.estello.android.ViewModel;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.ArrayList;
import java.util.List;

public class FaqModel implements Parent<FaqAnswerModel> {

    private String FaqQuestion;
    private String FaqId;
    private List<FaqAnswerModel> faqAnswerModelList;


    public FaqModel(FaqAnswerModel faqAnswerModel){

        this.faqAnswerModelList = new ArrayList<>();
        this.faqAnswerModelList.add(faqAnswerModel);
    }

    public String getFaqId() {
        return FaqId;
    }

    public String getFaqQuestion() {
        return FaqQuestion;
    }

    public void setFaqId(String faqId) {
        FaqId = faqId;
    }

    public void setFaqQuestion(String faqQuestion) {
        FaqQuestion = faqQuestion;
    }


    @Override
    public List<FaqAnswerModel> getChildList() {
        return faqAnswerModelList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
