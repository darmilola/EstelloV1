package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.estello.android.Adapter.CommunityViewVideosDetailsAdapter;

import java.util.ArrayList;

public class ExploreCommunityTypeThreeBucketDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Object> videosList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_community_type_three_bucket_details);
        initView();
    }

    private void initView(){
         GridLayoutManager gridLayoutManager = new GridLayoutManager(ExploreCommunityTypeThreeBucketDetails.this,2);
         recyclerView = findViewById(R.id.activity_explore_community_videos_bucket_details_recyclerview);
         recyclerView.setLayoutManager(gridLayoutManager);
         for(int i = 0; i < 50; i++){
            Object object = new Object();
            videosList.add(object);
         }
         CommunityViewVideosDetailsAdapter communityViewVideosDetailsAdapter = new CommunityViewVideosDetailsAdapter(videosList,ExploreCommunityTypeThreeBucketDetails.this);
         recyclerView.setAdapter(communityViewVideosDetailsAdapter);
    }
}
