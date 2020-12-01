package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.estello.android.Adapter.CommunityViewChannelsAdapter;
import com.estello.android.Adapter.CommunityViewChannelsDetailsAdapter;

import java.util.ArrayList;

public class ExploreCommunityTypeOneBucketDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Object> channelList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_community_type_one_bucket_details);
        initView();
    }

    private void initView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ExploreCommunityTypeOneBucketDetails.this,2);
        recyclerView = findViewById(R.id.activity_explore_community_channel_bucket_details_recyclerview);
        recyclerView.setLayoutManager(gridLayoutManager);
        for(int i = 0; i < 50; i++){
            Object object = new Object();
            channelList.add(object);
        }
        CommunityViewChannelsDetailsAdapter communityViewChannelsDetailsAdapter = new CommunityViewChannelsDetailsAdapter(channelList,ExploreCommunityTypeOneBucketDetails.this);
        recyclerView.setAdapter(communityViewChannelsDetailsAdapter);
    }
}
