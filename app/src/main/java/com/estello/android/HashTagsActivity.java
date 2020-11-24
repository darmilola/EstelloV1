package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.estello.android.Adapter.ChannelPostAdapter;
import com.estello.android.Adapter.HashTagPostAdapter;
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
    RecyclerView HashTagsPostPostRecyclerView;
    HashTagPostAdapter adapter;
    ActivityPausedListener activityPausedListener;
    ActivityResumedListener activityResumedListener;
    ActivityDestroyedListener activityDestroyedListener;


    public interface ActivityPausedListener{

        public void onActivityPaused();
    }
    public interface ActivityResumedListener{
        public void onActivityResumed();
    }
    public interface ActivityDestroyedListener{
        public void onActivityDestroyed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hash_tags);
        initSlidr();
        Slidr.attach(this, config);
        initView();
    }

    private void initView(){
        HashTagsPostPostRecyclerView = findViewById(R.id.channel_post_recyclerview);
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

      public void setActivityPausedListener(ActivityPausedListener activityPausedListener){
        this.activityPausedListener = activityPausedListener;
    }
    public void setActivityDestroyedListener(ActivityDestroyedListener activityDestroyedListener){
        this.activityDestroyedListener = activityDestroyedListener;
     }
     public void setActivityResumedListener(ActivityResumedListener activityResumedListener){
        this.activityResumedListener = activityResumedListener;
     }

    @Override
    public void onResume() {

        super.onResume();
        if(activityResumedListener != null)activityResumedListener.onActivityResumed();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setNavigationBarColor(ContextCompat.getColor(this,R.color.transparent));
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.white));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        }
    }


     @Override
    public void onPause() {
        activityPausedListener.onActivityPaused();
        super.onPause();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        activityDestroyedListener.onActivityDestroyed();
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
            ForumPostAttachmentsModel forumPostAttachmentsModel1 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel2 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel3 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel4 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel5 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel6 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel7 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel8 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel9 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            //ForumPostAttachmentsModel forumPostAttachmentsModel10 = new ForumPostAttachmentsModel(2,"https://images.unsplash.com/photo-1582719471384-894fbb16e074?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60");


            ArrayList<ForumPostAttachmentsModel> forumPostAttachmentsModelArrayList = new ArrayList<>();
            for(int i = 0; i < 1; i++){

                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel1);
                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel2);
                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel3);
                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel4);
                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel5);
                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel6);
                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel7);
                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel8);
                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel9);
            }

            ForumPostModel postModel = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,0);
            postModel.setPostGroupDate("October 15 2020");
            ForumPostModel postModel2 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,1);
            ForumPostModel postModel3 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,2);
            ForumPostModel postModel4 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,3);
            forumPostModelArrayList = new ArrayList<>();
            ForumPostModel postModel0 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,4);
            forumPostModelArrayList.add(postModel0);
            for(int i = 0; i < 10; i++){
                forumPostModelArrayList.add(postModel);
                for(int j = 0; j < 50; j++){
                    forumPostModelArrayList.add(postModel2);
                    forumPostModelArrayList.add(postModel3);
                    forumPostModelArrayList.add(postModel4);
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object result){

            adapter = new HashTagPostAdapter(HashTagsActivity.this, forumPostModelArrayList, new HashTagPostAdapter.MentionClickedListener() {
                @Override
                public void onMentionClicked(String mentionJson) {

                    UserProfileBottomSheet bottomSheet = new UserProfileBottomSheet();
                    bottomSheet.show(getSupportFragmentManager(), "userprofile");

                }
            }, new HashTagPostAdapter.ProfilePictureClickedListener() {
                @Override
                public void onProfilePictureClicked(int position) {

                    UserProfileBottomSheet bottomSheet = new UserProfileBottomSheet();
                    bottomSheet.show(getSupportFragmentManager(), "userprofile");

                }
            }, new HashTagPostAdapter.hashTagClickedListener() {
                @Override
                public void onHashTagClicked(String hashtagText) {

                    startActivity(new Intent(HashTagsActivity.this,HashTagsActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }, position -> {

            }, new HashTagPostAdapter.ItemClickedListener() {
                @Override
                public void onItemClicked() {

                }
            });
            HashTagsPostPostRecyclerView.setLayoutManager(LinearLayoutManager);
            HashTagsPostPostRecyclerView.setAdapter(adapter);

        }
    }

}
