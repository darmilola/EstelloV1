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



public class sign_up_firstpage extends Fragment {


    TextView hello,introduce;
    TextInputEditText fullname,email;
    View view;
    TextInputLayout fullname_layout,email_layout;
    public sign_up_firstpage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_sign_up_firstpage, container, false);
        initView();
        return view;
    }

    private void initView(){
        fullname = view.findViewById(R.id.sign_up_fullname);
        email = view.findViewById(R.id.sign_up_email);
        hello = view.findViewById(R.id.sign_up_firstpage_hello);
        introduce = view.findViewById(R.id.sign_up_firstpage_lets_introduce);
        fullname_layout = view.findViewById(R.id.sign_up_fullname_layout);
        email_layout = view.findViewById(R.id.sign_up_email_layout);
        Typeface customfont= Typeface.createFromAsset(getActivity().getAssets(),"clanbook.ttf");
        fullname.setTypeface(customfont);
        email.setTypeface(customfont);
        hello.setTypeface(customfont);
        introduce.setTypeface(customfont);
        email_layout.setTypeface(customfont);
        fullname_layout.setTypeface(customfont);
    }

}
