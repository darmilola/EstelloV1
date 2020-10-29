package com.estello.android.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.Adapter.TrainingAnnouncementAdapter;

import com.estello.android.ViewModel.TrainingPostModel;
import com.estello.android.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class training_announcement_fragment extends Fragment {


    View view;
    RecyclerView recyclerView;
    TrainingAnnouncementAdapter adapter;
    ArrayList<TrainingPostModel> trainingPostModelArrayList = new ArrayList<>();

    public training_announcement_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

               view =  inflater.inflate(R.layout.fragment_training_announcement_fragment, container, false);

               initRecyclerView();
               return  view;

    }


    private void initRecyclerView(){
        recyclerView = view.findViewById(R.id.training_anouncement_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        TrainingPostModel trainingPostModel = new TrainingPostModel();

        for(int i = 0; i < 10; i++){
            trainingPostModelArrayList.add(trainingPostModel);
        }
        adapter = new TrainingAnnouncementAdapter(getContext(),trainingPostModelArrayList);

        recyclerView.setAdapter(adapter);

    }

}
