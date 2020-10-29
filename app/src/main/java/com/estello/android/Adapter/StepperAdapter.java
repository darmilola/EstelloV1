package com.estello.android.Adapter;

import android.content.Context;
import android.os.Bundle;

import com.estello.android.Fragments.CreateCourseDetails;
import com.estello.android.Fragments.CreateCourseQuizFragment;
import com.estello.android.Fragments.CreateCourseSyllabus;
import com.estello.android.Fragments.CreateCourseUploadIntroVideo;
import com.estello.android.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

public class StepperAdapter extends AbstractFragmentStepAdapter {

    private static final String CURRENT_STEP_POSITION_KEY = "position";

    public StepperAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {

        if(position == 0){

            final CreateCourseDetails step = new CreateCourseDetails();
            Bundle b = new Bundle();
            b.putInt(CURRENT_STEP_POSITION_KEY, position);
            step.setArguments(b);
            return step;
        }
        else if(position == 1){

            final CreateCourseUploadIntroVideo step = new CreateCourseUploadIntroVideo();
            Bundle b = new Bundle();
            b.putInt(CURRENT_STEP_POSITION_KEY, position);
            step.setArguments(b);
            return step;
        }
        else if(position == 2){

            final CreateCourseSyllabus step = new CreateCourseSyllabus();
            Bundle b = new Bundle();
            b.putInt(CURRENT_STEP_POSITION_KEY, position);
            step.setArguments(b);
            return step;
        }
        else if(position == 3){

            final CreateCourseQuizFragment step = new CreateCourseQuizFragment();
            Bundle b = new Bundle();
            b.putInt(CURRENT_STEP_POSITION_KEY, position);
            step.setArguments(b);
            return step;
        }
        else{
            final CreateCourseDetails step = new CreateCourseDetails();
            Bundle b = new Bundle();
            b.putInt(CURRENT_STEP_POSITION_KEY, position);
            step.setArguments(b);
            return step;
        }


    }

    @Override
    public int getCount() {
        return 6;
    }


    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        StepViewModel.Builder builder = new StepViewModel.Builder(context);
        builder.setBackButtonStartDrawableResId(R.drawable.ms_ic_chevron_left);
        switch (position){
            case 0:
                builder.setTitle("Details");
                break;
            case 1:
                builder.setTitle("Intro Video");

                break;
            case 2:
                builder.setTitle("Syllabus");
                break;
            case 3:
                builder.setTitle("Quiz");
                break;
            case 4:
                builder.setTitle("Invite");
                break;
            case 5:
                builder.setTitle("Review");
                break;
        }
        return builder.create();
    }


}
