package com.estello.android.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.estello.android.R;

import androidx.fragment.app.Fragment;


public class sign_up_secondpage extends Fragment {

    TextView hello,fullname,whattodo;
    MaterialCheckBox acquire,train;
    View view;

    public sign_up_secondpage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

                view =  inflater.inflate(R.layout.fragment_sign_up_secondpage, container, false);
                initView();
                return view;

    }

    private void initView(){
        hello = view.findViewById(R.id.sign_up_second_page_hello);
        fullname = view.findViewById(R.id.sign_up_second_page_firstname_lastname);
        whattodo = view.findViewById(R.id.sign_up_second_page_whattodo);
        acquire = view.findViewById(R.id.acquire_new_skills);
        train = view.findViewById(R.id.organise_trainings);

        Typeface customfont= Typeface.createFromAsset(getActivity().getAssets(),"clanbook.ttf");
        hello.setTypeface(customfont);
        fullname.setTypeface(customfont);
        whattodo.setTypeface(customfont);
        acquire.setTypeface(customfont);
        train.setTypeface(customfont);
    }

}
