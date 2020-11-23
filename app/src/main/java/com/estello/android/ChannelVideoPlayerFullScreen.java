package com.estello.android;

import android.os.Bundle;

import com.estello.android.ViewModel.VideoModel;

import java.util.ArrayList;

public class ChannelVideoPlayerFullScreen extends VideoPlayerBaseActivity {

    ArrayList<VideoModel> videoModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        videoModelArrayList = getIntent().getParcelableArrayListExtra("videomodel");
        String transitioname = getIntent().getStringExtra("transition_name");
        setTransitionName(transitioname);
        setVideoModel(videoModelArrayList.get(0));
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setTransitionName(String transitionName) {
        super.setTransitionName(transitionName);
    }

    @Override
    public void setVideoUrl(String url) {
        url = videoModelArrayList.get(0).getVideoUrl();
        super.setVideoUrl(url);
    }

    @Override
    public void setVideoModel(VideoModel videoModel) {
        super.setVideoModel(videoModel);
    }
}
