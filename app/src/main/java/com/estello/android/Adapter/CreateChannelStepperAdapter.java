package com.estello.android.Adapter;

import android.content.Context;
import android.os.Bundle;

import com.estello.android.Fragments.CreateCourseDetails;
import com.estello.android.Fragments.CreateNewChanneChannelPrivacy;
import com.estello.android.Fragments.CreateNewChannelChannelName;
import com.estello.android.Fragments.CreateNewChannelPurpose;
import com.estello.android.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

public class CreateChannelStepperAdapter extends AbstractFragmentStepAdapter {

    private static final String CURRENT_STEP_POSITION_KEY = "position";

    public CreateChannelStepperAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {

        if(position == 0){

            final CreateNewChannelChannelName step1 = new CreateNewChannelChannelName();
            Bundle b = new Bundle();
            b.putInt(CURRENT_STEP_POSITION_KEY, position);
            step1.setArguments(b);
            return step1;

        }
        else if(position == 1){

           final CreateNewChannelPurpose step2 = new CreateNewChannelPurpose();
            Bundle b = new Bundle();
            b.putInt(CURRENT_STEP_POSITION_KEY, position);
            step2.setArguments(b);
            return step2;

        }
        else if(position == 2){

            final CreateNewChanneChannelPrivacy step3 = new CreateNewChanneChannelPrivacy();
            Bundle b = new Bundle();
            b.putInt(CURRENT_STEP_POSITION_KEY, position);
            step3.setArguments(b);
            return step3;

        }
        else{

            final CreateNewChannelChannelName step1 = new CreateNewChannelChannelName();
            Bundle b = new Bundle();
            b.putInt(CURRENT_STEP_POSITION_KEY, position);
            step1.setArguments(b);
            return step1;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        StepViewModel.Builder builder = new StepViewModel.Builder(context);
        builder.setBackButtonStartDrawableResId(R.drawable.leftarrow);
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
