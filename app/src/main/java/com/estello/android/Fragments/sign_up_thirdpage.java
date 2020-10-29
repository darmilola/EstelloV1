package com.estello.android.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.estello.android.R;

import androidx.fragment.app.Fragment;


public class sign_up_thirdpage extends Fragment {

    TextInputEditText password,retyped;
    TextView hello,fullname;
    View view;
    TextInputLayout password_layout,retype_layout;
    public sign_up_thirdpage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

               view =  inflater.inflate(R.layout.fragment_sign_up_thirdpage, container, false);
               initView();
               return view;
    }


    private void initView(){
        hello = view.findViewById(R.id.sign_up_third_page_hello);
        fullname = view.findViewById(R.id.sign_up_third_page_firstname_lastname);
        retyped = view.findViewById(R.id.retype_password);
        password = view.findViewById(R.id.create_password);
        password_layout = view.findViewById(R.id.create_password_layout);
        retype_layout = view.findViewById(R.id.retype_password_layout);
        Typeface customfont= Typeface.createFromAsset(getActivity().getAssets(),"clanbook.ttf");
        hello.setTypeface(customfont);
        fullname.setTypeface(customfont);
        retyped.setTypeface(customfont);
        retype_layout.setTypeface(customfont);
        password_layout.setTypeface(customfont);
        password.setTypeface(customfont);


    }

}
