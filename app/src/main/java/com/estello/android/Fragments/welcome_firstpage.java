package com.estello.android.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.Welcome;
import com.estello.android.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class welcome_firstpage extends Fragment {


    public welcome_firstpage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome_firstpage, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();

      //  Welcome.AuthPage("Acquire new Skills","Learn new Skills from top independent expert trainers from all around the world");
    }

}
