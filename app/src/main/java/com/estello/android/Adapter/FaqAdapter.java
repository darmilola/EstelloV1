package com.estello.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.estello.android.ViewModel.FaqAnswerModel;
import com.estello.android.ViewModel.FaqModel;
import com.estello.android.ViewHolders.FaqAnswerViewHolder;
import com.estello.android.ViewHolders.FaqQuestionViewHolder;

import com.estello.android.R;


import java.util.List;

import androidx.annotation.NonNull;

public class FaqAdapter extends ExpandableRecyclerAdapter<FaqModel, FaqAnswerModel, FaqQuestionViewHolder, FaqAnswerViewHolder> {


    private LayoutInflater mInflater;
    public FaqAdapter(Context context,@NonNull List<FaqModel> parentList) {
        super(parentList);
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FaqQuestionViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View questionView = mInflater.inflate(R.layout.faq_question, parentViewGroup, false);
        return new FaqQuestionViewHolder(questionView);
    }

    @NonNull
    @Override
    public FaqAnswerViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View answerView = mInflater.inflate(R.layout.faq_answer,childViewGroup, false);
        return new FaqAnswerViewHolder(answerView);
    }

    @Override
    public void onBindParentViewHolder(@NonNull FaqQuestionViewHolder parentViewHolder, int parentPosition, @NonNull FaqModel parent) {

    }

    @Override
    public void onBindChildViewHolder(@NonNull FaqAnswerViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull FaqAnswerModel child) {

    }



}
