package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.estello.android.Adapter.MyCoursesAdapter;
import com.estello.android.ViewModel.learningModel;
import com.google.android.material.button.MaterialButton;


import java.util.ArrayList;

public class MyCourses extends AppCompatActivity {

    RecyclerView recyclerView;
    MyCoursesAdapter myCoursesAdapter;
    ArrayList<learningModel> learningModelArrayList = new ArrayList<>();
    Toolbar toolbar;
    TextView title;
    MaterialButton startnewcourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses);

        initView();
        populateList();
        initRecyclerView();
    }

    private void initRecyclerView(){
        recyclerView = findViewById(R.id.my_courses_recyclerview);
        myCoursesAdapter = new MyCoursesAdapter(learningModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myCoursesAdapter);

    }

    public void populateList(){

        learningModel learningModel = new learningModel();

        for(int i = 0; i < 3; i++){
            learningModelArrayList.add(learningModel);
        }

    }

    private void initView(){

        toolbar = findViewById(R.id.mycourses_toolbar);
        title = findViewById(R.id.mycourses_toolbar_title);
        startnewcourse = findViewById(R.id.my_courses_startnew_course);
        Typeface customfont2= Typeface.createFromAsset(getAssets(), "font/latoreg.ttf");
        Typeface customfont= Typeface.createFromAsset(getAssets(),"lato.ttf");
        title.setTypeface(customfont2);
        startnewcourse.setTypeface(customfont);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(this,R.color.White), PorterDuff.Mode.SRC_ATOP);


        startnewcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyCourses.this,CreateNewCourse.class));
            }
        });
    }
}
