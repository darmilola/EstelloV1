package com.estello.android;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.estello.android.Fragments.UserProfileBottomSheet;
import com.estello.android.Adapter.ChannelPostAdapter;

import com.estello.android.ViewModel.ForumPostAttachmentsModel;
import com.estello.android.ViewModel.ForumPostModel;
import com.estello.android.ViewModel.User;
import java.util.ArrayList;

public class QandAChannel extends ChannelBaseActivity {


    ChannelPostAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_channel_base);

        new populateTask().execute();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("destroyed", "onDestroy: ");
    }

    @Override public void onStop(){
        super.onStop();
        Log.e("stopped", "onStop: ");
    }

    @Override public void onPause(){
        super.onPause();
        Log.e("paused", "onPause: ");
    }

    @SuppressLint("UseSparseArrays")
    public class populateTask extends AsyncTask{

        ArrayList<ForumPostModel> forumPostModelArrayList;
        LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(QandAChannel.this, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,false);


        @Override
        protected Object doInBackground(Object[] objects) {

            User user = new User();
            ArrayList<User> userArrayList = new ArrayList<>();
            for(int i = 0; i < 3; i++){

                userArrayList.add(user);
            }
            ForumPostAttachmentsModel forumPostAttachmentsModel3 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel4 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel5 = new ForumPostAttachmentsModel(1,"https://images.unsplash.com/photo-1582719471384-894fbb16e074?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60");
            ForumPostAttachmentsModel forumPostAttachmentsModel6 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4","https://images.unsplash.com/photo-1582719471384-894fbb16e074?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60");
            ForumPostAttachmentsModel forumPostAttachmentsModel7 = new ForumPostAttachmentsModel(0,"https://images.unsplash.com/photo-1582719471384-894fbb16e074?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60");
            ForumPostAttachmentsModel forumPostAttachmentsModel8 = new ForumPostAttachmentsModel(3,"https://images.unsplash.com/photo-1582719471384-894fbb16e074?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60");
            ArrayList<ForumPostAttachmentsModel> forumPostAttachmentsModelArrayList = new ArrayList<>();
            for(int i = 0; i < 1; i++){

                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel4);

            }
            ForumPostModel postModel0 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,4);
            ForumPostModel postModel = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,0);
            postModel.setPostGroupDate("October 15 2020");
            ForumPostModel postModel2 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,1);
            //ForumPostModel postModel3 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,2);
            //ForumPostModel postModel4 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,3);
            forumPostModelArrayList = new ArrayList<>();
            forumPostModelArrayList.add(postModel0);
            for(int i = 0; i < 1; i++){
                forumPostModelArrayList.add(postModel);
                for(int j = 0; j < 5; j++){

                    forumPostModelArrayList.add(postModel2);
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object result){

            adapter = new ChannelPostAdapter(QandAChannel.this, forumPostModelArrayList, position -> {

                UserProfileBottomSheet bottomSheet = new UserProfileBottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "userprofile");

            }, position -> {

                UserProfileBottomSheet bottomSheet = new UserProfileBottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "userprofile");

            }, position -> {

                Intent intent = new Intent(QandAChannel.this, HashTagsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }, QandAChannel.this::showPostToolsBottomSheet, new ChannelPostAdapter.ItemClickedListener() {
                @Override
                public void onItemClicked() {
                    startActivity(new Intent(QandAChannel.this, ChannelPostDetails.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });
            setChannelPostAdapter(adapter);
        }
    }

    @Override
    public void setChannelPostAdapter(ChannelPostAdapter adapter){
        adapter = this.adapter;
        super.setChannelPostAdapter(adapter);
    }

    @Override
    public void showPostToolsBottomSheet(int position){
        super.showPostToolsBottomSheet(position);
    }

}
