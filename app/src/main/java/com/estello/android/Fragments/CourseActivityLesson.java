package com.estello.android.Fragments;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.estello.android.Adapter.LessonsAdapter;
import com.estello.android.ViewModel.CoursesModel;
import com.google.android.material.tabs.TabLayout;
import com.estello.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseActivityLesson extends Fragment {


    View view;
    RecyclerView recyclerView;
    private ArrayList<CoursesModel> coursesModelArrayList = new ArrayList<>();



    public CourseActivityLesson() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view =  inflater.inflate(R.layout.fragment_course_activity_lessons, container, false);
         initView();
         return  view;
    }

    private void initView(){
        recyclerView = view.findViewById(R.id.lesson_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        CoursesModel coursesModel = new CoursesModel();
        for(int i = 0; i < 20; i++){
            coursesModelArrayList.add(coursesModel);
        }
        LessonsAdapter adapter = new LessonsAdapter(coursesModelArrayList,getContext());
        recyclerView.setAdapter(adapter);
    }

}
