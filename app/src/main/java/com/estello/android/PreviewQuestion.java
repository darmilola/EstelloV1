package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.graphics.PorterDuff;
import android.os.Bundle;

import com.slackwise.slackwisequestionview.SlackWiseQuestionView;

public class PreviewQuestion extends AppCompatActivity {

  SlackWiseQuestionView questionView;
  Toolbar toolbar;

  String code = " public static void main(String[] args) {\n" +
          "        int scores[] = {1,4,8,0};\n" +
          "        String classNames[] = {\"sola\",\"tolu\",\"dapo\",\"ayo\"};\n" +
          "        boolean found = false;\n" +
          "        Scanner input = new Scanner(System.in);\n" +
          "        System.out.println(\"Input the name you are looking for\");\n" +
          "        String nametofind = input.nextLine();\n" +
          "        \n" +
          "         for(int i = 0; i < classNames.length; i++){\n" +
          "             if(classNames[i].equalsIgnoreCase(nametofind)){\n" +
          "                 found = true;\n" +
          "                 System.out.println(\"I FOUND AYO AT INDEX\");\n" +
          "                 System.out.println(i);\n" +
          "                 break;\n" +
          "             }\n" +
          "          }\n" +
          "         if(found == false){\n" +
          "             System.out.println(\"I cant find what you are looking for\");\n" +
          "         }\n" +
          "         \n" +
          "    }\n" +
          "    \n" +
          "}";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_preview_question);
    toolbar = findViewById(R.id.preview_question_toolbar);
    questionView = findViewById(R.id.preview_question_question_view);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(this,R.color.White), PorterDuff.Mode.SRC_ATOP);


  }
}
