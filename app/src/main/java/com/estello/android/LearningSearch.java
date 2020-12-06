package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.estello.android.Adapter.LearningSearchClassesAdapter;
import com.estello.android.Adapter.LearningSearchHistoryAdapter;
import com.estello.android.Adapter.LearningSearchSkillsTeachersAdapter;
import com.estello.android.ViewModel.CoursesModel;
import com.estello.android.ViewModel.LearningSearchModel;

import java.util.ArrayList;

public class LearningSearch extends AppCompatActivity {


    NestedScrollView recentSearchRoot;
    NestedScrollView classesRoot;
    LinearLayout teacherSkillLayout;
    RecyclerView recentSearchRecyclerview,classesRecyclerview,teacherSkillRecyclerview;
    ArrayList<LearningSearchModel> learningSearchArrayList = new ArrayList<>();
    ArrayList<CoursesModel> coursesModelArrayList = new ArrayList<>();
    LinearLayout filterSearch;
    ImageView cancelSearch;
    TextView resetHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_search);
        initView();
    }

    private void initView(){
        recentSearchRoot = findViewById(R.id.recent_search_rootview);
        classesRoot = findViewById(R.id.classes_rootview);
        teacherSkillLayout = findViewById(R.id.learning_search_skill_teacher_rootview);
        recentSearchRecyclerview = findViewById(R.id.learning_recent_search_recyclerview);
        classesRecyclerview = findViewById(R.id.learning_classes_search_recyclerview);
        teacherSkillRecyclerview = findViewById(R.id.learning_search_skill_teacher_recyclerview);
        cancelSearch = findViewById(R.id.learning_search_cancel);
        resetHistory = findViewById(R.id.learning_recent_search_reset);
        filterSearch = findViewById(R.id.learning_filter_classes_layout);


        cancelSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacherSkillLayout.setVisibility(View.GONE);
                classesRoot.setVisibility(View.VISIBLE);
                recentSearchRoot.setVisibility(View.GONE);
            }
        });
        resetHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacherSkillLayout.setVisibility(View.VISIBLE);
                classesRoot.setVisibility(View.GONE);
                recentSearchRoot.setVisibility(View.GONE);
            }
        });
        filterSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearningSearch.this,LearningSearchFilter.class));
            }
        });
        LearningSearchModel learningSearchModel = new LearningSearchModel(0);
        LearningSearchModel learningSearchModel1 = new LearningSearchModel(1);
        CoursesModel coursesModel = new CoursesModel();

        for(int i = 0; i < 20; i++){
            coursesModelArrayList.add(coursesModel);
            learningSearchArrayList.add(learningSearchModel);
            learningSearchArrayList.add(learningSearchModel1);
        }

        LearningSearchClassesAdapter classesAdapter = new LearningSearchClassesAdapter(learningSearchArrayList,this);
        LearningSearchSkillsTeachersAdapter skillsTeachersAdapter = new LearningSearchSkillsTeachersAdapter(learningSearchArrayList,this);
        LearningSearchHistoryAdapter searchHistoryAdapter = new LearningSearchHistoryAdapter(coursesModelArrayList,this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recentSearchRecyclerview.setLayoutManager(layoutManager);
        classesRecyclerview.setLayoutManager(layoutManager2);
        teacherSkillRecyclerview.setLayoutManager(layoutManager3);

        recentSearchRecyclerview.setAdapter(searchHistoryAdapter);
        classesRecyclerview.setAdapter(classesAdapter);
        teacherSkillRecyclerview.setAdapter(skillsTeachersAdapter);



    }
}
