package com.estello.android.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.Adapter.ExploreChannelAdapter;
import com.estello.android.R;
import com.estello.android.ViewModel.ChannelsModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreChannelUnreadChannel extends Fragment {


    View view;
    RecyclerView recyclerView;
    ArrayList<ChannelsModel> channelsModelArrayList = new ArrayList<>();
    public ExploreChannelUnreadChannel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_explore_channel_unread_channel, container, false);

        initView();
        return view;
    }


    private void initView(){
        ChannelsModel channelsModel = new ChannelsModel();
        recyclerView = view.findViewById(R.id.unread_channel_recyclerview);
        for(int i = 0; i < 10; i++){
            channelsModelArrayList.add(channelsModel);
        }
        ExploreChannelAdapter adapter = new ExploreChannelAdapter(channelsModelArrayList,getContext());
        LinearLayoutManager layoutManager  = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
