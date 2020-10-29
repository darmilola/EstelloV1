package com.estello.android.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.estello.android.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class CreateCourseUploadIntroVideo extends Fragment  implements Step {


    View view;
    TextView videoName,uploadText;
    public CreateCourseUploadIntroVideo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_create_course_upload_intro_video, container, false);

        initView();
        return  view;
    }

    private void initView(){

        videoName = view.findViewById(R.id.course_video_name);
        uploadText = view.findViewById(R.id.upload_course_video_text);
        Typeface customfont= Typeface.createFromAsset(getActivity().getAssets(), "font/latoreg.ttf");

        videoName.setTypeface(customfont);
        uploadText.setTypeface(customfont);




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
