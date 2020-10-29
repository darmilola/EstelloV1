package com.estello.android.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.Adapter.TrainingParticipantAdapter;

import com.estello.android.ViewModel.TrainingParticipantsModel;
import com.estello.android.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class training_participant_fragment extends Fragment {


    RecyclerView recyclerView;
    View view;
    TrainingParticipantAdapter adapter;
    ArrayList<TrainingParticipantsModel> trainingParticipantsModelArrayList = new ArrayList<>();
    public training_participant_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_training_participant_fragment, container, false);

        initRecyclerView();
        return  view;

    }

    private void initRecyclerView(){
        recyclerView = view.findViewById(R.id.training_participants_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        TrainingParticipantsModel model = new TrainingParticipantsModel();
        for(int i = 0; i < 20; i++){

            trainingParticipantsModelArrayList.add(model);
        }
        adapter = new TrainingParticipantAdapter(trainingParticipantsModelArrayList);
        recyclerView.setAdapter(adapter);

    }





}
