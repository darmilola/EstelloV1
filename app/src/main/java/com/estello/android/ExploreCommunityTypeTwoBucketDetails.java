package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.estello.android.Adapter.CommunityViewHashtagsAdapter;
import com.estello.android.Adapter.CommunityViewHashtagsDetailsAdapter;

import java.util.ArrayList;

public class ExploreCommunityTypeTwoBucketDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Object> hashtagsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_community_type_two_bucket_details);
        initView();
    }

    private void initView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ExploreCommunityTypeTwoBucketDetails.this,2);
        recyclerView = findViewById(R.id.activity_explore_community_hashtags_bucket_details_recyclerview);
        recyclerView.setLayoutManager(gridLayoutManager);
        for(int i = 0; i < 50; i++){
            Object object = new Object();
            hashtagsList.add(object);
        }
        CommunityViewHashtagsDetailsAdapter communityViewHashtagsDetailsAdapter = new CommunityViewHashtagsDetailsAdapter(hashtagsList,ExploreCommunityTypeTwoBucketDetails.this);
        recyclerView.setAdapter(communityViewHashtagsDetailsAdapter);
    }
}
