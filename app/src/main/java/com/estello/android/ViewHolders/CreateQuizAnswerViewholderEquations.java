package com.estello.android.ViewHolders;

import android.view.View;
import android.widget.TextView;


import com.estello.android.ViewModel.CreateQuizQuestionAnswers;
import com.estello.android.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import androidx.annotation.NonNull;
import katex.hourglass.in.mathlib.MathView;

public class CreateQuizAnswerViewholderEquations extends ChildViewHolder {


    MathView optionA,optionB,optionC,optionD;
    TextView answerOption;


    public CreateQuizAnswerViewholderEquations(@NonNull View itemView) {
        super(itemView);
        optionA = itemView.findViewById(R.id.create_quiz_question_optionA_value_equations);
        optionB = itemView.findViewById(R.id.create_quiz_question_optionB_value_equations);
        optionC = itemView.findViewById(R.id.create_quiz_question_optionC_value_equations);
        optionD = itemView.findViewById(R.id.create_quiz_question_optionD_value_equations);
        answerOption = itemView.findViewById(R.id.create_quiz_question_answer_value_equation);
    }

    public void Bind(CreateQuizQuestionAnswers createQuizQuestionAnswers){


    }
}
