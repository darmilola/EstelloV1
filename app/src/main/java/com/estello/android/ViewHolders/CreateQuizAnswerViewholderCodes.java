package com.estello.android.ViewHolders;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;


import com.estello.android.ViewModel.CreateQuizQuestion;
import com.protectsoft.webviewcode.Codeview;
import com.protectsoft.webviewcode.Settings;

import com.estello.android.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import androidx.annotation.NonNull;

public class CreateQuizAnswerViewholderCodes extends ChildViewHolder {


    WebView optionA,optionB,optionC,optionD;
    TextView answerOption;


    public CreateQuizAnswerViewholderCodes(@NonNull View itemView) {
        super(itemView);
        optionA = itemView.findViewById(R.id.create_quiz_question_optionA_value_codes);
        optionB = itemView.findViewById(R.id.create_quiz_question_optionB_value_codes);
        optionC = itemView.findViewById(R.id.create_quiz_question_optionC_value_codes);
        optionD = itemView.findViewById(R.id.create_quiz_question_optionD_value_codes);
        answerOption = itemView.findViewById(R.id.create_quiz_question_answer_value_codes);
    }




    public void Bind(CreateQuizQuestion createQuizQuestion, Context context){

        //question.setText(createQuizQuestion.getQuestion());

        String a = "x = 100";
        String b = "x = 70";
        String c = "x = 90";
        String d = "x = 1001";

        Codeview.with(context)
                .withCode(a)
                .setStyle(Settings.WithStyle.XCODE)
                .setLang(Settings.Lang.JAVA)
                .into(optionA);

        Codeview.with(context)
                .withCode(b)
                .setStyle(Settings.WithStyle.XCODE)
                .setLang(Settings.Lang.JAVA)
                .into(optionB);

        Codeview.with(context)
                .withCode(c)
                .setStyle(Settings.WithStyle.XCODE)
                .setLang(Settings.Lang.JAVA)
                .into(optionC);

        Codeview.with(context)
                .withCode(d)
                .setStyle(Settings.WithStyle.XCODE)
                .setLang(Settings.Lang.JAVA)
                .into(optionD);

    }
}
