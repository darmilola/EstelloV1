package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import com.estello.android.Adapter.TrainingSequenceAdapter;
import com.estello.android.ViewModel.course_welcome_syllabus_model;
import com.transferwise.sequencelayout.SequenceLayout;

import java.util.ArrayList;

public class course_training_welcome_page extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton enter;
    SequenceLayout sequenceLayout;
    TrainingSequenceAdapter adapter;
    ArrayList<course_welcome_syllabus_model> course_welcome_syllabus_modelArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_training_welcome_page);
        initView();
        setUpAdapter();

    }


    private void initView(){


        toolbar = findViewById(R.id.course_training_welcome_toolbar);
        enter = findViewById(R.id.course_training_welcome_enter);
        toolbar = findViewById(R.id.course_training_welcome_toolbar);
        sequenceLayout = findViewById(R.id.course_training_syllabus_step);



        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(this,R.color.black), PorterDuff.Mode.SRC_ATOP);


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(course_training_welcome_page.this,course_training_page.class));
            }
        });

    }

    private void setUpAdapter(){

        course_welcome_syllabus_model model = new course_welcome_syllabus_model(false,"Section 1","Introduction to DeltaStream","This is a very fantastic library from transferwise, its so good thanks to transferwise for giving us this, you are appreciated");

        course_welcome_syllabus_model model2 = new course_welcome_syllabus_model(false,"Section 2","Database Designs for SQL Servers and MySql Database Designs for SQL Servers and MySql Database Designs for SQL Servers and MySql","common mehn am here t talk about the description of the sequence adapter its really fantastic from transferwise");

        course_welcome_syllabus_model model3 = new course_welcome_syllabus_model(true,"Section 3","Database Designs for SQL Servers and MySql ","common mehn am here t talk about the description of the sequence adapter its really fantastic from transferwise");

        course_welcome_syllabus_model model4 = new course_welcome_syllabus_model(false,"Section 4","Database Designs for SQL Servers and MySql Database Designs for SQL Servers and MySql","common mehn am here t talk about the description of the sequence adapter its really fantastic from transferwise");

        course_welcome_syllabus_model model5 = new course_welcome_syllabus_model(false,"Section 5","Database Designs for SQL Servers and MySql Database Designs for SQL Servers and MySql Database Designs for SQL Servers and MySql Database Designs for SQL Servers and MySql Database Designs for SQL Servers and MySql Database Designs for SQL Servers and MySql ","common mehn am here t talk about the description of the sequence adapter its really fantastic from transferwise");

        course_welcome_syllabus_model model6 = new course_welcome_syllabus_model(false,"Section 6","Database Designs for SQL Servers and MySql","common mehn am here t talk about the description of the sequence adapter its really fantastic from transferwise");

        course_welcome_syllabus_modelArrayList.add(model);
        course_welcome_syllabus_modelArrayList.add(model2);
        course_welcome_syllabus_modelArrayList.add(model3);
        course_welcome_syllabus_modelArrayList.add(model4);
        course_welcome_syllabus_modelArrayList.add(model5);
        course_welcome_syllabus_modelArrayList.add(model6);

        adapter = new TrainingSequenceAdapter(course_welcome_syllabus_modelArrayList);

        sequenceLayout.setAdapter(adapter);

    }


}
