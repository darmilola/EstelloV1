package com.estello.android.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.estello.android.Adapter.GradesAssessmentAdapter;

import com.estello.android.ViewModel.GradesAssessmentModel;
import com.estello.android.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseActivityGrades extends Fragment {


    View view;
    RecyclerView recyclerView;
    GradesAssessmentAdapter gradesAssessmentAdapter;
    ArrayList<GradesAssessmentModel> gradesAssessmentModelArrayList = new ArrayList<>();
    public CourseActivityGrades() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_course_activity_grades, container, false);

         populateList();
         initRecyclerView();
         return  view;
    }

    private void initRecyclerView(){
        recyclerView = view.findViewById(R.id.grades_assessment_recyclerview);
        GradesAssessmentAdapter adapter = new GradesAssessmentAdapter(gradesAssessmentModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void populateList(){
        GradesAssessmentModel model = new GradesAssessmentModel();
        for(int i = 0; i < 10; i++){

            gradesAssessmentModelArrayList.add(model);
        }
    }

}
