package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;


import com.estello.android.Adapter.more_adapter;
import com.estello.android.ViewModel.CoursesModel;

import java.util.ArrayList;

public class StartingSoonMore extends AppCompatActivity {

    Toolbar toolbar;
    TextView title;
    RecyclerView recyclerView;
    ArrayList<CoursesModel> courses_modelArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_soon_more);
        populateList();
        initRecyclerView();
        initView();
    }

    private void initView(){

        toolbar = findViewById(R.id.starting_soon_more_toolbar);
        title = findViewById(R.id.starting_soon_more_title);
        Typeface customfont= Typeface.createFromAsset(getAssets(), "font/latoreg.ttf");
        title.setTypeface(customfont);



        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(StartingSoonMore.this,R.color.White), PorterDuff.Mode.SRC_ATOP);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        if (menuItem.getItemId() == android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void initRecyclerView(){

        recyclerView = findViewById(R.id.starting_soon_more_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StartingSoonMore.this, LinearLayoutManager.VERTICAL,false);
        more_adapter starting_soon_more_adapter = new more_adapter(courses_modelArrayList,StartingSoonMore.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(starting_soon_more_adapter);

    }

    private void populateList(){
        CoursesModel courses_model = new CoursesModel();

        for(int i = 0; i < 10; i++){
            courses_modelArrayList.add(courses_model);
        }
    }


}
