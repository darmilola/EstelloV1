package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;


import com.estello.android.Adapter.InstructorProfileCourseAdapter;
import com.estello.android.ViewModel.CoursesModel;

import java.util.ArrayList;

public class InstructorsProfile extends AppCompatActivity {


    Toolbar toolbar;
    TextView title;
    RecyclerView recyclerView;
    ArrayList<CoursesModel> courses_modelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructors_profile);
        populateList();
        initRecyclerView();
        initView();
    }


    private void initView(){

        toolbar = findViewById(R.id.instructor_profile_toolbar);



        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(InstructorsProfile.this,R.color.White), PorterDuff.Mode.SRC_ATOP);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        if (menuItem.getItemId() == android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void initRecyclerView(){

        recyclerView = findViewById(R.id.instructor_profile_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(InstructorsProfile.this, LinearLayoutManager.VERTICAL,false);
        InstructorProfileCourseAdapter adapter = new InstructorProfileCourseAdapter(courses_modelArrayList,InstructorsProfile.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void populateList(){
        CoursesModel courses_model = new CoursesModel();

        for(int i = 0; i < 10; i++){
            courses_modelArrayList.add(courses_model);
        }
    }
}
