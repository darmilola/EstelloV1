package com.estello.android;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.deltastream.example.edittextcontroller.RTextView;
import com.estello.android.Fragments.userProfileBottomSheet;
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

                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel3);
                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel4);
                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel5);
                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel6);
                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel7);
                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel8);


            }
            ForumPostModel postModel = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,0);
            postModel.setPostGroupDate("October 15 2020");
            ForumPostModel postModel2 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,1);
            ForumPostModel postModel3 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,2);
            ForumPostModel postModel4 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,3);
            forumPostModelArrayList = new ArrayList<>();
            for(int i = 0; i < 1; i++){
                forumPostModelArrayList.add(postModel);
                for(int j = 0; j < 2; j++){

                    forumPostModelArrayList.add(postModel2);
                    forumPostModelArrayList.add(postModel3);
                    forumPostModelArrayList.add(postModel4);
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object result){

            adapter = new ChannelPostAdapter(QandAChannel.this, forumPostModelArrayList, position -> {

                userProfileBottomSheet bottomSheet = new userProfileBottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "userprofile");

            }, position -> {

                userProfileBottomSheet bottomSheet = new userProfileBottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "userprofile");

            }, position -> {

                Intent intent = new Intent(QandAChannel.this, HashTagsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            });
            setChannelPostAdapter(adapter);
        }
    }

    @Override
    public void setChannelPostAdapter(ChannelPostAdapter adapter){
        adapter = this.adapter;
        super.setChannelPostAdapter(adapter);

    }

}
