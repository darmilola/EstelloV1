package com.estello.android.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.Adapter.LessonsAdapter;
import com.estello.android.Adapter.LessonsTypeLiveAdapter;
import com.estello.android.R;
import com.estello.android.ViewModel.CoursesModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveLessons extends Fragment {


    View view;
    RecyclerView recyclerView;
    private ArrayList<CoursesModel> coursesModelArrayList = new ArrayList<>();
    public LiveLessons() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_live_lessons, container, false);

        initView();
        return view;
    }

    private void initView(){
        recyclerView = view.findViewById(R.id.lesson_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        CoursesModel coursesModel = new CoursesModel();
        for(int i = 0; i < 20; i++){
            coursesModelArrayList.add(coursesModel);
        }
        LessonsTypeLiveAdapter adapter = new LessonsTypeLiveAdapter(coursesModelArrayList,getContext());
        recyclerView.setAdapter(adapter);
    }

}
