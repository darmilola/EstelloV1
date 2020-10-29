package com.estello.android.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.Adapter.DirectMessageAdapter;

import com.estello.android.ViewModel.DirectMessageModel;
import com.estello.android.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DirectMessages extends Fragment {

    RecyclerView recyclerView;
    View view;
    DirectMessageAdapter directMessageAdapter;
    ArrayList<DirectMessageModel> messageModelArrayList = new ArrayList<>();
    public DirectMessages() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view =  inflater.inflate(R.layout.fragment_direct_messages, container, false);

         populateList();
         initRecyclerView();
         return view;
    }

    private void initRecyclerView(){
        recyclerView = view.findViewById(R.id.direct_message_recyclerview);
        directMessageAdapter = new DirectMessageAdapter(messageModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(directMessageAdapter);

    }

    public void populateList(){

        DirectMessageModel directMessageModel = new DirectMessageModel();

        for(int i = 0; i < 20; i++){
            messageModelArrayList.add(directMessageModel);
        }

    }

}
