package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.deltastream.example.edittextcontroller.RTextView;
import com.estello.android.Adapter.ChannelPostAdapter;
import com.estello.android.Fragments.UserProfileBottomSheet;
import com.estello.android.ViewModel.ForumPostAttachmentsModel;
import com.estello.android.ViewModel.ForumPostModel;
import com.estello.android.ViewModel.User;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrListener;

import java.util.ArrayList;

public class HashTagsActivity extends AppCompatActivity {

    SlidrConfig config;
    RecyclerView QandAForumPostRecyclerView;
    ChannelPostAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hash_tags);
        initSlidr();
        Slidr.attach(this, config);
        initView();
    }

    private void initView(){
        QandAForumPostRecyclerView = findViewById(R.id.channel_post_recyclerview);
        new populateTask().execute();
    }

    private void initSlidr(){
                 config = new SlidrConfig.Builder()
                .primaryColor(ContextCompat.getColor(this,R.color.full_transparency))
                .secondaryColor(ContextCompat.getColor(this,R.color.full_transparency))
                         .scrimColor(ContextCompat.getColor(this,R.color.black))
                .listener(new SlidrListener() {
                    @Override
                    public void onSlideStateChanged(int state) {

                    }

                    @Override
                    public void onSlideChange(float percent) {

                    }

                    @Override
                    public void onSlideOpened() {

                        //getWindow().setNavigationBarColor(ContextCompat.getColor(HashTagsActivity.this,R.color.transparent));
                    }

                    @Override
                    public boolean onSlideClosed() {

                        //getWindow().setNavigationBarColor(ContextCompat.getColor(HashTagsActivity.this,R.color.white));
                        return false;
                    }
                }).build();
    }

    @Override
    public void onResume() {

        super.onResume();
        if(adapter != null){

            adapter.resumePlayBack();

        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            getWindow().setNavigationBarColor(ContextCompat.getColor(this,R.color.transparent));
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.white));
            //getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        }


    }

    @SuppressLint("UseSparseArrays")
    public class populateTask extends AsyncTask {

        ArrayList<ForumPostModel> forumPostModelArrayList;
        androidx.recyclerview.widget.LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(HashTagsActivity.this, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,false);

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


            ArrayList<ForumPostAttachmentsModel> forumPostAttachmentsModelArrayList = new ArrayList<>();
            for(int i = 0; i < 1; i++){

                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel3);
                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel4);
                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel5);

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

            adapter = new ChannelPostAdapter(HashTagsActivity.this, forumPostModelArrayList, new ChannelPostAdapter.MentionClickedListener() {
                @Override
                public void onMentionClicked(int position) {

                    UserProfileBottomSheet bottomSheet = new UserProfileBottomSheet();
                    bottomSheet.show(getSupportFragmentManager(), "userprofile");

                }
            }, new ChannelPostAdapter.ProfilePictureClickedListener() {
                @Override
                public void onProfilePictureClicked(int position) {

                    UserProfileBottomSheet bottomSheet = new UserProfileBottomSheet();
                    bottomSheet.show(getSupportFragmentManager(), "userprofile");

                }
            }, new RTextView.HashTagClickedListener() {
                @Override
                public void onHashTagClicked(int position) {


                }
            }, position -> {

            });
            QandAForumPostRecyclerView.setLayoutManager(LinearLayoutManager);
            QandAForumPostRecyclerView.setAdapter(adapter);


        }
    }

}
