package com.estello.android.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.estello.android.Adapter.MyCoursesAdapter;

import com.estello.android.ViewModel.learningModel;
import com.estello.android.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCourses extends Fragment {


    RecyclerView recyclerView;
    View view;
    MyCoursesAdapter myCoursesAdapter;
    ArrayList<learningModel> learningModelArrayList = new ArrayList<>();
    public MyCourses() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view =  inflater.inflate(R.layout.fragment_my_courses, container, false);
         populateList();
         initRecyclerView();
         return view;

    }

    private void initRecyclerView(){
        recyclerView = view.findViewById(R.id.my_courses_recyclerview);
        myCoursesAdapter = new MyCoursesAdapter(learningModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myCoursesAdapter);

    }

    public void populateList(){

        learningModel learningModel = new learningModel();

        for(int i = 0; i < 3; i++){
            learningModelArrayList.add(learningModel);
        }

    }



}
