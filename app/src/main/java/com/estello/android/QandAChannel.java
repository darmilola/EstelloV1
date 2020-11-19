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

            ForumPostAttachmentsModel forumPostAttachmentsModel1 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel2 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel3 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel4 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel5 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel6 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel7 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel8 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel9 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel10 = new ForumPostAttachmentsModel(1,"https://images.unsplash.com/photo-1582719471384-894fbb16e074?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60");


            ArrayList<ForumPostAttachmentsModel> forumPostAttachmentsModelArrayList = new ArrayList<>();
            ArrayList<ForumPostAttachmentsModel> forumPostAttachmentsModelArrayList2 = new ArrayList<>();
            ArrayList<ForumPostAttachmentsModel> forumPostAttachmentsModelArrayList3 = new ArrayList<>();
            ArrayList<ForumPostAttachmentsModel> forumPostAttachmentsModelArrayList4 = new ArrayList<>();
            ArrayList<ForumPostAttachmentsModel> forumPostAttachmentsModelArrayList5 = new ArrayList<>();
            ArrayList<ForumPostAttachmentsModel> forumPostAttachmentsModelArrayList6 = new ArrayList<>();
            ArrayList<ForumPostAttachmentsModel> forumPostAttachmentsModelArrayList7 = new ArrayList<>();
            ArrayList<ForumPostAttachmentsModel> forumPostAttachmentsModelArrayList8 = new ArrayList<>();
            ArrayList<ForumPostAttachmentsModel> forumPostAttachmentsModelArrayList9 = new ArrayList<>();
            for(int i = 0; i < 1; i++){

                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel1);
                forumPostAttachmentsModelArrayList2.add(forumPostAttachmentsModel2);
                forumPostAttachmentsModelArrayList3.add(forumPostAttachmentsModel3);
                forumPostAttachmentsModelArrayList4.add(forumPostAttachmentsModel4);
                forumPostAttachmentsModelArrayList5.add(forumPostAttachmentsModel5);
                forumPostAttachmentsModelArrayList6.add(forumPostAttachmentsModel6);
                forumPostAttachmentsModelArrayList7.add(forumPostAttachmentsModel7);
                forumPostAttachmentsModelArrayList8.add(forumPostAttachmentsModel8);
                forumPostAttachmentsModelArrayList9.add(forumPostAttachmentsModel9);


            }
            ForumPostModel postModel0 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,4);
            ForumPostModel postModel = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,0);
            postModel.setPostGroupDate("October 15 2020");
            ForumPostModel postModel2 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,1);
            ForumPostModel postModel3 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList2,1);
            ForumPostModel postModel4 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList3,1);
            ForumPostModel postModel5 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList4,1);
            ForumPostModel postModel6 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList5,1);
            ForumPostModel postModel7 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList6,1);
            ForumPostModel postModel8 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList7,1);
            ForumPostModel postModel9 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList8,1);
            ForumPostModel postModel10 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList9,1);
            //ForumPostModel postModel4 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,3);
            forumPostModelArrayList = new ArrayList<>();
            forumPostModelArrayList.add(postModel0);
            for(int i = 0; i < 1; i++){
                forumPostModelArrayList.add(postModel);
                for(int j = 0; j < 1; j++){

                    forumPostModelArrayList.add(postModel2);
                    forumPostModelArrayList.add(postModel3);
                    forumPostModelArrayList.add(postModel4);
                    forumPostModelArrayList.add(postModel5);
                    forumPostModelArrayList.add(postModel6);
                    forumPostModelArrayList.add(postModel7);
                    forumPostModelArrayList.add(postModel8);
                    forumPostModelArrayList.add(postModel9);
                    forumPostModelArrayList.add(postModel10);

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
