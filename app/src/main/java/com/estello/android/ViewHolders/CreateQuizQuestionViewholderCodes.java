package com.estello.android.ViewHolders;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.estello.android.ViewModel.CreateQuizQuestion;
import com.estello.android.R;
import com.protectsoft.webviewcode.Codeview;
import com.protectsoft.webviewcode.Settings;


import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import androidx.annotation.NonNull;

public class CreateQuizQuestionViewholderCodes extends GroupViewHolder {
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */


    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;
    private TextView questionNumber;
    private WebView question;
    private ImageView dropdown;


    public CreateQuizQuestionViewholderCodes(@NonNull View itemView) {
        super(itemView);

        questionNumber = itemView.findViewById(R.id.create_quiz_question_number_codes);
        question = itemView.findViewById(R.id.create_quiz_question_question_codes);
        dropdown = itemView.findViewById(R.id.create_quiz_question_dropdown_codes);





    }


    public void Bind(CreateQuizQuestion createQuizQuestion, Context context){

        //question.setText(createQuizQuestion.getQuestion());

        String code = "public static void main(String[] args) { \n" +
                "\n" +
                "//comments\n" +
                "   for(int i =0; i < 10; i++) {\n" +
                "       addnum();\n" +
                "   }\n" +
                "\n" +
                "}\n";

        Codeview.with(context)
                .withCode(code)
                .setStyle(Settings.WithStyle.XCODE)
                .setLang(Settings.Lang.JAVA)
                .into(question);
        questionNumber.setText("Question "+createQuizQuestion.getQuestionNumber());
    }

}
