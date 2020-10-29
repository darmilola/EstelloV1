package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;


import com.estello.android.Adapter.InstructorProfileCourseAdapter;
import com.estello.android.Adapter.OrganisationInstructorAdapter;
import com.estello.android.ViewModel.CoursesModel;
import com.estello.android.ViewModel.InstructorsItemOffsetDecoration;
import com.estello.android.ViewModel.OrganisationProfileInstructorsModel;

import java.util.ArrayList;

public class OrganisationProfile extends AppCompatActivity {


    Toolbar toolbar;
    TextView title;
    RecyclerView instructorsRecyclerView;
    ArrayList<OrganisationProfileInstructorsModel> instructorsModelArrayList = new ArrayList<>();

    RecyclerView recyclerView;
    ArrayList<CoursesModel> courses_modelArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation_profile);


        populateList();
        initRecyclerView();
        initView();
    }

    private void initView(){

        toolbar = findViewById(R.id.organisation_profile_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(OrganisationProfile.this,R.color.White), PorterDuff.Mode.SRC_ATOP);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        if (menuItem.getItemId() == android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void initRecyclerView(){

        instructorsRecyclerView = findViewById(R.id.organisation_instructors_profile_recyclerview);
        LinearLayoutManager gridLayoutManager = new GridLayoutManager(OrganisationProfile.this,2);
        OrganisationInstructorAdapter adapter = new OrganisationInstructorAdapter(instructorsModelArrayList);
        InstructorsItemOffsetDecoration itemOffsetDecoration = new InstructorsItemOffsetDecoration(OrganisationProfile.this,R.dimen.fab_margin);
        //instructorsRecyclerView.addItemDecoration(itemOffsetDecoration);
        instructorsRecyclerView.setLayoutManager(gridLayoutManager);
        instructorsRecyclerView.setAdapter(adapter);


        recyclerView = findViewById(R.id.instructor_profile_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrganisationProfile.this, LinearLayoutManager.VERTICAL,false);
        InstructorProfileCourseAdapter adapter2 = new InstructorProfileCourseAdapter(courses_modelArrayList,OrganisationProfile.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter2);

    }

    private void populateList(){
        OrganisationProfileInstructorsModel model = new OrganisationProfileInstructorsModel("Shane James White",R.drawable.man);
        OrganisationProfileInstructorsModel model2 = new OrganisationProfileInstructorsModel("Shadraq Abednego",R.drawable.model);
        OrganisationProfileInstructorsModel model3 = new OrganisationProfileInstructorsModel("Torio Jackson",R.drawable.woman2);
        OrganisationProfileInstructorsModel model4 = new OrganisationProfileInstructorsModel("Shala Nevada",R.drawable.woman3);

        for(int i = 0; i < 2; i++){
            instructorsModelArrayList.add(model);
            instructorsModelArrayList.add(model2);
            instructorsModelArrayList.add(model3);
            instructorsModelArrayList.add(model4);
        }

        CoursesModel courses_model = new CoursesModel();

        for(int i = 0; i < 10; i++){
            courses_modelArrayList.add(courses_model);
        }
    }

}
