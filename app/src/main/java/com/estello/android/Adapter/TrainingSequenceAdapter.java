package com.estello.android.Adapter;


import com.estello.android.ViewModel.course_welcome_syllabus_model;
import com.estello.android.R;

import com.transferwise.sequencelayout.SequenceAdapter;
import com.transferwise.sequencelayout.SequenceStep;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TrainingSequenceAdapter extends SequenceAdapter<course_welcome_syllabus_model> {

    ArrayList<course_welcome_syllabus_model> course_welcome_syllabus_modelArrayList;

    public TrainingSequenceAdapter(ArrayList<course_welcome_syllabus_model> course_welcome_syllabus_models){
        this.course_welcome_syllabus_modelArrayList = course_welcome_syllabus_models;
    }

    @Override
    public void bindView(@NotNull SequenceStep sequenceStep, @NotNull course_welcome_syllabus_model course_welcome_syllabus_model) {

        sequenceStep.setActive(course_welcome_syllabus_model.getIsActive());
        sequenceStep.setAnchor(course_welcome_syllabus_model.getDivision());
        sequenceStep.setTitle(course_welcome_syllabus_model.getTitle());
        sequenceStep.setSubtitle(course_welcome_syllabus_model.getDescription());
        sequenceStep.setAnchorTextAppearance(R.style.SequenceDesc);
        sequenceStep.setTitleTextAppearance(R.style.SequenceTitle);
        sequenceStep.setSubtitleTextAppearance(R.style.SequenceDesc);


    }

    @Override
    public int getCount() {
        return course_welcome_syllabus_modelArrayList.size();
    }

    @NotNull
    @Override
    public course_welcome_syllabus_model getItem(int i) {
        return course_welcome_syllabus_modelArrayList.get(i);
    }
}
