package com.estello.android.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseActivityInfo extends Fragment {


    public CourseActivityInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_activity_info, container, false);
    }

}
