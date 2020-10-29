package com.estello.android.Fragments;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.Welcome;
import com.estello.android.R;



public class welcome_secondpage extends Fragment {
   public welcome_secondpage() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome_secondpage, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        Welcome.AuthPage("Unlimited Access","Access ebooks,audios,videos and other content from trainers with no restrictions");
    }

}
