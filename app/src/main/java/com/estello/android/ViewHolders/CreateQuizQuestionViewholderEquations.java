package com.estello.android.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.estello.android.ViewModel.CreateQuizQuestion;

import com.estello.android.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import androidx.annotation.NonNull;
import katex.hourglass.in.mathlib.MathView;

public class CreateQuizQuestionViewholderEquations extends GroupViewHolder {
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */


    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;
    private TextView questionNumber;
    private MathView question;
    private ImageView dropdown;


    public CreateQuizQuestionViewholderEquations(@NonNull View itemView) {
        super(itemView);

        questionNumber = itemView.findViewById(R.id.create_quiz_question_number_equations);
        question = itemView.findViewById(R.id.create_quiz_question_question__equations);
        dropdown = itemView.findViewById(R.id.create_quiz_question_dropdown_equations);





    }


    public void Bind(CreateQuizQuestion createQuizQuestion){

        //question.setText(createQuizQuestion.getQuestion());
        questionNumber.setText("Question "+createQuizQuestion.getQuestionNumber());
    }


}
