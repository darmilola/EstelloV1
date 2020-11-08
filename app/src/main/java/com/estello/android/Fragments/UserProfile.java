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
public class UserProfile extends Fragment {



    private View view;
    TextView viewProfile;
    public UserProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_user_profile, container, false);
         initView();
         return view;

    }

    private void initView(){
        viewProfile = view.findViewById(R.id.user_profile_view_profile);

        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserProfileBottomSheet bottomSheet = new UserProfileBottomSheet();
                bottomSheet.show(getChildFragmentManager(),"userprofile");
            }
        });

    }

}
