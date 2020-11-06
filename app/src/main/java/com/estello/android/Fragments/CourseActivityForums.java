package com.estello.android.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.estello.android.QandAChannel;
import com.estello.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseActivityForums extends Fragment {


    View view;
    CardView qandaCard;
    public CourseActivityForums() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_course_activity_forums, container, false);

         initView();
         return view;
    }

    private void initView(){
        qandaCard = view.findViewById(R.id.qandaforumcard);

        qandaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), QandAChannel.class));
            }
        });
    }

}
