package com.estello.android.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.estello.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class course_detail_instructor_fragment extends Fragment {


    TextView instructorsname;
    String id;
    View view;
    TextView weblink;
    TextView profileName,profession,about;

    public course_detail_instructor_fragment() {
        // Required empty public constructor
    }

    public course_detail_instructor_fragment(String id){

        this.id = id;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

               view  =  inflater.inflate(R.layout.fragment_course_detail_instructor_fragment, container, false);
               initView();
               return  view;
    }

    private void initView(){

        profileName = view.findViewById(R.id.course_detail_fragment_instructor_profile_profile_name);
        profession = view.findViewById(R.id.course_detail_fragment_instructor_profile_profession);



    }

}
