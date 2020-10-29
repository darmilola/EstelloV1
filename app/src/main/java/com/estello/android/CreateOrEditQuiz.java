package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import com.estello.android.Adapter.CreateQuizQuestionAdapter;
import com.estello.android.ViewModel.CreateQuizQuestion;
import com.estello.android.ViewModel.CreateQuizQuestionAnswers;
import com.google.android.material.button.MaterialButton;


import java.util.ArrayList;
import java.util.List;

public class CreateOrEditQuiz extends AppCompatActivity {

    Toolbar toolbar;
    List<CreateQuizQuestion> createQuizQuestionList = new ArrayList<>();
    CreateQuizQuestionAdapter adapter;
    RecyclerView recyclerView;
    MaterialButton addNewQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_edit_quiz);

        initView();
        populateList();
    }

    private void initView(){


        addNewQuestion = findViewById(R.id.create_or_edit_quiz_item_add_question);
        toolbar = findViewById(R.id.create_or_edit_quiz_item_toolbar);
        recyclerView = findViewById(R.id.create_or_edit_quiz_item_question_recyclerview);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(this,R.color.White), PorterDuff.Mode.SRC_ATOP);

        addNewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateOrEditQuiz.this,AddNewQuizQuestion.class));
            }
        });
    }


    private void populateList(){

        CreateQuizQuestionAnswers answer = new CreateQuizQuestionAnswers("Nigeria","USA","South Africa","UK","B",3);
        CreateQuizQuestionAnswers answer2 = new CreateQuizQuestionAnswers("Gordon Brown","Arnold Shwazenneger","George Washington","George Floyd","D",4);
        CreateQuizQuestionAnswers answer3 = new CreateQuizQuestionAnswers("Gordon Brown","Arnold Shwazenneger","George Washington","George Floyd","D",5);

        ArrayList<CreateQuizQuestionAnswers> answersArrayList1 = new ArrayList<>();
        answersArrayList1.add(answer);


        ArrayList<CreateQuizQuestionAnswers> answersArrayList2 = new ArrayList<>();
        answersArrayList2.add(answer2);

        ArrayList<CreateQuizQuestionAnswers> answersArrayList3 = new ArrayList<>();
        answersArrayList3.add(answer3);



        CreateQuizQuestion quizQuestion = new CreateQuizQuestion("1", "Who was Killed in May Minnesota", answersArrayList2,0);
        CreateQuizQuestion quizQuestion2 = new CreateQuizQuestion("2", "Where was May Minnesota Killing held",answersArrayList1,1);
        CreateQuizQuestion quizQuestion3 = new CreateQuizQuestion("2", "Where was May Minnesota Killing held",answersArrayList1,2);


        createQuizQuestionList = new ArrayList<>();
        createQuizQuestionList.add(quizQuestion);
        createQuizQuestionList.add(quizQuestion2);
        createQuizQuestionList.add(quizQuestion3);

        adapter = new CreateQuizQuestionAdapter(CreateOrEditQuiz.this,createQuizQuestionList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CreateOrEditQuiz.this, LinearLayoutManager.VERTICAL,false);


        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }
}
