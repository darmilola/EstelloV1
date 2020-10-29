package com.estello.android.ViewHolders;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import com.estello.android.ViewModel.CreateQuizQuestionAnswers;
import com.estello.android.R;

import androidx.annotation.NonNull;

public class CreateQuizQuestionAnswerViewHolder extends ChildViewHolder {
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */

    TextView optionA,optionB,optionC,optionD,answerOption;
    public CreateQuizQuestionAnswerViewHolder(@NonNull View itemView) {
        super(itemView);

        optionA = itemView.findViewById(R.id.create_quiz_question_optionA_value);
        optionB = itemView.findViewById(R.id.create_quiz_question_optionB_value);
        optionC = itemView.findViewById(R.id.create_quiz_question_optionC_value);
        optionD = itemView.findViewById(R.id.create_quiz_question_optionD_value);
        answerOption = itemView.findViewById(R.id.create_quiz_question_answer_value);
    }

    public void Bind(CreateQuizQuestionAnswers createQuizQuestionAnswers){

        optionA.setText(createQuizQuestionAnswers.getOptionA());
        optionB.setText(createQuizQuestionAnswers.getOptionB());
        optionC.setText(createQuizQuestionAnswers.getOptionC());
        optionD.setText(createQuizQuestionAnswers.getOptionD());
    }
}
