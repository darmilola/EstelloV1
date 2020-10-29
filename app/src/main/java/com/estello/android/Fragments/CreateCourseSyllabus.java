package com.estello.android.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.Adapter.SyllabusAdapter;
import com.estello.android.CreateCourseAddNewSyllabusSection;
import com.estello.android.ViewModel.SyllabusSection;
import com.estello.android.ViewModel.SyllabusSectionDetails;
import com.google.android.material.button.MaterialButton;


import com.estello.android.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateCourseSyllabus extends Fragment implements Step {


    MaterialButton addNewSyllabus;
    View view;
    RecyclerView recyclerView;
    List<SyllabusSection> syllabusSectionList = new ArrayList<>();
    public CreateCourseSyllabus() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_create_course_syllabus, container, false);

        initView();
        populateList();

        return  view;
    }

    private void initView(){

        recyclerView = view.findViewById(R.id.create_course_add_new__syllabus_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        addNewSyllabus = view.findViewById(R.id.create_course_syllabus_add_new_syllabus_button);
        addNewSyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getContext(), CreateCourseAddNewSyllabusSection.class),0);
            }});
    }


    private void populateList(){

        SyllabusSectionDetails syllabusSectionDetails = new SyllabusSectionDetails("This is going to be the place where the section description will be so thats fantastic okay yeah thank you mehn good to go","5 readings, 4 videos","3 hours live");
        SyllabusSectionDetails syllabusSectionDetails2 = new SyllabusSectionDetails("This is going to be the place where the secti good to go","8 readings, 10 videos","5 hours live");

        SyllabusSection section = new SyllabusSection("Day 1","This is My First Title", Arrays.asList(syllabusSectionDetails,syllabusSectionDetails2));

        SyllabusSection section2 = new SyllabusSection("Day 2","This is My Second Title", Arrays.asList(syllabusSectionDetails2));

        syllabusSectionList.add(section);
        syllabusSectionList.add(section2);

        SyllabusAdapter adapter  = new SyllabusAdapter(getContext(),syllabusSectionList);
        recyclerView.setAdapter(adapter);
    }


    @Nullable
    @Override
    public VerificationError verifyStep() {

        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
