package com.estello.android.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.Adapter.NotificationAdapter;

import com.estello.android.ViewModel.notification_model;
import com.estello.android.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {


    RecyclerView recyclerView;
    View view;
    ArrayList<notification_model> notificationModelArrayList = new ArrayList<>();
    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

               view =  inflater.inflate(R.layout.fragment_notification, container, false);

               populateList();
               initRecyclerView();
               return  view;
    }

    private void initRecyclerView(){
        recyclerView = view.findViewById(R.id.notification_recyclerview);
        NotificationAdapter adapter = new NotificationAdapter(notificationModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }


    private void populateList(){

        notification_model notification_model = new notification_model();
        notification_model notification_model2 = new notification_model();
        notification_model notification_model3 = new notification_model();


        for(int i = 0; i < 3; i++){
            notificationModelArrayList.add(notification_model);

        }

    }



}
