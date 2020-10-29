package com.estello.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.estello.android.ViewModel.CreateQuizQuestion;
import com.estello.android.ViewHolders.CreateQuizAnswerViewholderCodes;
import com.estello.android.ViewHolders.CreateQuizAnswerViewholderEquations;
import com.estello.android.ViewHolders.CreateQuizAnswerViewholderText;
import com.estello.android.ViewHolders.CreateQuizQuestionViewholderCodes;
import com.estello.android.ViewHolders.CreateQuizQuestionViewholderEquations;
import com.estello.android.ViewHolders.CreateQuizQuestionViewholderText;

import com.estello.android.R;

import com.thoughtbot.expandablerecyclerview.MultiTypeExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

import androidx.annotation.NonNull;

import static android.view.LayoutInflater.from;

public class CreateQuizQuestionAdapter extends MultiTypeExpandableRecyclerViewAdapter<GroupViewHolder, ChildViewHolder> {

    private static final int CODE_QUESTION_VIEWTYPE = 0;
    private static final int EQUATION_QUESTION_VIEWTYPE = 1;
    private static final int TEXT_QUESTION_VIEWTYPE = 2;
    private static final int CODE_ANSWER_VIEWTYPE = 3;
    private static final int EQUATIONS_ANSWER_VIEWTYPE = 4;
    private static final int TEXT_ANSWER_VIEWTYPE = 5;
    private LayoutInflater mInflater;
    Context context;
    List<CreateQuizQuestion> createQuizQuestionList;




    public CreateQuizQuestionAdapter(Context context, @NonNull List<CreateQuizQuestion> parentList) {
        super(parentList);
        this.createQuizQuestionList = parentList;
        this.context = context;
    }

    @Override
    public GroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case CODE_QUESTION_VIEWTYPE:
                View codeQuestion = from(parent.getContext()).inflate(R.layout.create_quiz_question_codes, parent, false);
                return new CreateQuizQuestionViewholderCodes(codeQuestion);
            case EQUATION_QUESTION_VIEWTYPE:
                View equationQuestion = from(parent.getContext()).inflate(R.layout.create_quiz_question_item_equations, parent, false);
                return new CreateQuizQuestionViewholderEquations(equationQuestion);
            case TEXT_QUESTION_VIEWTYPE:
                View textQuestion = from(parent.getContext()).inflate(R.layout.create_quiz_question_item_text, parent, false);
                return new CreateQuizQuestionViewholderText(textQuestion);
            default:
                throw new IllegalArgumentException("Invalid viewType");
        }
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case CODE_ANSWER_VIEWTYPE:
                View codeAnswer = from(parent.getContext()).inflate(R.layout.create_quiz_question_answer_codes, parent, false);
                return new CreateQuizAnswerViewholderCodes(codeAnswer);
            case EQUATIONS_ANSWER_VIEWTYPE:
                View equationAnswer = from(parent.getContext()).inflate(R.layout.create_quiz_item_answer_equations, parent, false);
                return new CreateQuizAnswerViewholderEquations(equationAnswer);
            case TEXT_ANSWER_VIEWTYPE:
                View textAnswer = from(parent.getContext()).inflate(R.layout.create_quiz_question_answer_text, parent, false);
                return new CreateQuizAnswerViewholderText(textAnswer);
            default:
                throw new IllegalArgumentException("Invalid viewType");
        }
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {


        if(holder instanceof CreateQuizAnswerViewholderCodes){
            CreateQuizAnswerViewholderCodes viewholderCodes = (CreateQuizAnswerViewholderCodes) holder;
            viewholderCodes.Bind((CreateQuizQuestion) group,context);
        }
    }


    @Override
    public void onBindGroupViewHolder(GroupViewHolder holder, int flatPosition, ExpandableGroup group) {

        if(holder instanceof CreateQuizQuestionViewholderCodes){
            CreateQuizQuestionViewholderCodes viewholderCodes = (CreateQuizQuestionViewholderCodes)holder;
            viewholderCodes.Bind((CreateQuizQuestion) group,context);
        }
    }


    @Override
    public int getChildViewType(int position, ExpandableGroup group, int childIndex) {
        if (((CreateQuizQuestion) group).getItems().get(childIndex).getViewType() == 3) {
            return CODE_ANSWER_VIEWTYPE;
        }
        else if (((CreateQuizQuestion) group).getItems().get(childIndex).getViewType() == 4) {
            return EQUATIONS_ANSWER_VIEWTYPE;
        }
        else if (((CreateQuizQuestion) group).getItems().get(childIndex).getViewType() == 5) {
            return TEXT_ANSWER_VIEWTYPE;
        }
        else {
            return TEXT_ANSWER_VIEWTYPE;
        }
    }

    @Override
    public boolean isChild(int viewType) {
        return viewType == CODE_ANSWER_VIEWTYPE || viewType == EQUATIONS_ANSWER_VIEWTYPE || viewType == TEXT_ANSWER_VIEWTYPE;
    }

    @Override
    public int getGroupViewType(int position, ExpandableGroup group) {
        if (((CreateQuizQuestion) group).getViewType() == 0) {
            return CODE_QUESTION_VIEWTYPE;
        }
        else if (((CreateQuizQuestion) group).getViewType() == 1) {
            return EQUATION_QUESTION_VIEWTYPE;
        }
        else if (((CreateQuizQuestion) group).getViewType() == 2) {
            return TEXT_QUESTION_VIEWTYPE;
        }
        else {
            return TEXT_QUESTION_VIEWTYPE;
        }
    }

    @Override
    public boolean isGroup(int viewType) {
        return viewType == CODE_QUESTION_VIEWTYPE || viewType == EQUATION_QUESTION_VIEWTYPE || viewType == TEXT_QUESTION_VIEWTYPE;

    }




}
