package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.estello.android.Adapter.ChannelPostAdapter;
import com.estello.android.Fragments.DirectMessages;
import com.estello.android.Fragments.UserProfileBottomSheet;
import com.estello.android.ViewModel.ForumPostAttachmentsModel;
import com.estello.android.ViewModel.ForumPostModel;
import com.estello.android.ViewModel.User;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrListener;
import com.rd.utils.DensityUtils;

import java.util.ArrayList;

public class DirectMessagesDetails extends ChannelBaseActivity {

    ChannelPostAdapter adapter;
    SlidrConfig config;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSlidr();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.topMargin = DensityUtils.dpToPx(20);
       // super.toolbar.setLayoutParams(lp);
        //super.toolbar.requestLayout();
        Slidr.attach(this, config);
        new populateTask().execute();

    }

    private void initSlidr(){
        config = new SlidrConfig.Builder()
                .primaryColor(ContextCompat.getColor(this,R.color.white))
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

    @SuppressLint("UseSparseArrays")
    public class populateTask extends AsyncTask {

        ArrayList<ForumPostModel> forumPostModelArrayList;
        androidx.recyclerview.widget.LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(DirectMessagesDetails.this, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,false);


        @Override
        protected Object doInBackground(Object[] objects) {

            User user = new User();
            ArrayList<User> userArrayList = new ArrayList<>();
            for(int i = 0; i < 3; i++){

                userArrayList.add(user);
            }

            ForumPostAttachmentsModel forumPostAttachmentsModel1 = new ForumPostAttachmentsModel(0,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel2 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel3 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel4 = new ForumPostAttachmentsModel(3,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel5 = new ForumPostAttachmentsModel(0,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel6 = new ForumPostAttachmentsModel(1,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel7 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel8 = new ForumPostAttachmentsModel(3,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel9 = new ForumPostAttachmentsModel(0,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
            ForumPostAttachmentsModel forumPostAttachmentsModel10 = new ForumPostAttachmentsModel(1,"https://images.unsplash.com/photo-1582719471384-894fbb16e074?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60");


            ArrayList<ForumPostAttachmentsModel> forumPostAttachmentsModelArrayList = new ArrayList<>();
            ArrayList<ForumPostAttachmentsModel> forumPostAttachmentsModelArrayList2 = new ArrayList<>();
            ArrayList<ForumPostAttachmentsModel> forumPostAttachmentsModelArrayList3 = new ArrayList<>();
            for(int i = 0; i < 1; i++){

                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel7);
                forumPostAttachmentsModelArrayList2.add(forumPostAttachmentsModel2);
                forumPostAttachmentsModelArrayList3.add(forumPostAttachmentsModel3);
                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel10);
                forumPostAttachmentsModelArrayList2.add(forumPostAttachmentsModel10);
                forumPostAttachmentsModelArrayList3.add(forumPostAttachmentsModel10);
                forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel8);
                forumPostAttachmentsModelArrayList2.add(forumPostAttachmentsModel8);
                forumPostAttachmentsModelArrayList3.add(forumPostAttachmentsModel8);

            }
            ForumPostModel postModel0 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,4);
            ForumPostModel postModel = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,0);
            postModel.setPostGroupDate("October 15 2020");
            ForumPostModel postModel2 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList,1);
            ForumPostModel postModel3 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList2,2);
            ForumPostModel postModel4 = new ForumPostModel(userArrayList,forumPostAttachmentsModelArrayList3,3);
            forumPostModelArrayList = new ArrayList<>();
            forumPostModelArrayList.add(postModel0);
            for(int i = 0; i < 5; i++){
                forumPostModelArrayList.add(postModel);
                for(int j = 0; j < 3; j++){

                    forumPostModelArrayList.add(postModel2);
                    forumPostModelArrayList.add(postModel3);
                    forumPostModelArrayList.add(postModel4);
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object result){

            adapter = new ChannelPostAdapter(DirectMessagesDetails.this, forumPostModelArrayList, position -> {

                UserProfileBottomSheet bottomSheet = new UserProfileBottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "userprofile");

            }, (int mentionJson) -> {

                UserProfileBottomSheet bottomSheet = new UserProfileBottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "userprofile");

            }, (String hashtagText) -> {

                Intent intent = new Intent(DirectMessagesDetails.this, HashTagsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }, DirectMessagesDetails.this::showPostToolsBottomSheet, new ChannelPostAdapter.ItemClickedListener() {
                @Override
                public void onItemClicked() {
                    startActivity(new Intent(DirectMessagesDetails.this, ChannelPostDetails.class));
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



    @Override
    public void onResume() {

        super.onResume();
        if(activityResumedListener != null)activityResumedListener.onActivityResumed();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setNavigationBarColor(ContextCompat.getColor(this,R.color.transparent));
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.transparent));
            //getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
            //getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS,WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        }
    }
}
