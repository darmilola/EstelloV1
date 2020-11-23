package com.estello.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.estello.android.Arvi.Config;
import com.estello.android.Arvi.util.misc.ExoPlayerUtils;
import com.estello.android.Arvi.widget.PlaybackState;
import com.estello.android.Fragments.UserProfileBottomSheet;
import com.estello.android.ViewModel.OnSwipeTouchListener;
import com.estello.android.ViewModel.VideoModel;
import com.estello.android.ViewModel.VideoPlayerActivityController;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class VideoPlayerBaseActivity extends AppCompatActivity {

    LinearLayout transparentOverlay;
    FrameLayout touchArea;
    LinearLayout progressToolLayout;
    AppCompatSeekBar videoProgress;
    TextView duration;
    LinearLayout fullScreen,playPauseLayout;
    LottieAnimationView loader;
    SimpleDraweeView thumbnail;
    Config config;
    String url = "";
    PlayerView player;
    LinearLayout controller;
    PlaybackState playbackState;
    AlphaAnimation alphaAnim2;
    LottieAnimationView playPauseView;
    String playbackCacheID = "";
    VideoModel videoModel;
    private Handler mHandler = new Handler();
    boolean isCancelled = false;
    boolean isPaused = false;
    boolean isReady = false;
    boolean isStarted = true;
    boolean isLooping = false;
    boolean isNewFullscreenPlayer = true;
    String transitionName;
    ImageView profilePicture;
    VideoPlayerActivityController videoPlayerActivityController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player_base_activity);
        initView();
    }

    public void setTransitionName(String transitionName) {
        this.transitionName = transitionName;
    }

    private void initView(){
        isNewFullscreenPlayer = true;
        player = findViewById(R.id.player_view);
        profilePicture = findViewById(R.id.video_player_base_user_profile_picture);
        playPauseView = findViewById(R.id.attachment_video_play_pause_view);
        playPauseLayout = findViewById(R.id.attachment_play_pause_layout);
        loader = findViewById(R.id.attachment_video_loader_view);
        thumbnail = findViewById(R.id.attachment_video_thumbnail);
        touchArea = findViewById(R.id.video_player_base_frame);
        transparentOverlay = findViewById(R.id.video_player_fullscreen_overlay);
        videoProgress = findViewById(R.id.video_attachments_progressbar);
        progressToolLayout = findViewById(R.id.video_attachments_progress_layout);
        duration = findViewById(R.id.video_attachments_playing_duration);
        fullScreen = findViewById(R.id.video_attachments_fullscreen_layout);
        controller = findViewById(R.id.video_attachments_controller);
        touchArea.setTransitionName(transitionName);
        downloadVideoThumbnail();
        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isReady && videoPlayerActivityController.isPlaying() && videoPlayerActivityController.isTrulyPlayable()) {
                    if(alphaAnim2 != null && !alphaAnim2.hasEnded()){
                        isCancelled = true;
                        alphaAnim2.cancel();
                    }
                    isPaused = true;
                    videoPlayerActivityController.setPausedByUser(true);
                    videoPlayerActivityController.pause();
                }
                UserProfileBottomSheet userProfileBottomSheet = new UserProfileBottomSheet();
                userProfileBottomSheet.show(getSupportFragmentManager(),"userinfo_from_video_base");
            }
        });
         Config config = new Config.Builder().cache(ExoPlayerUtils.getCache(this)).build();
         videoPlayerActivityController = new VideoPlayerActivityController(this,player,config) {
            @Override
            public ViewParent getParent() {
                return null;
            }

            @NonNull
            @Override
            public String getUrl() {
                return getVideotUrl();
            }
        };
         videoPlayerActivityController.setPlayBackCacheID(generatePlaybackCacheID());
         videoPlayerActivityController.start();
         videoPlayerActivityController.setPlayBackStateListener(new VideoPlayerActivityController.PlayBackStateListener() {
            @Override
            public void OnStart() {
                onStartedState();
            }
            @Override
            public void onBuffering() {
                onBufferingState();
            }

            @Override
            public void onReady() {
                onReadyState();
            }

            @Override
            public void onPause() {
                onPausedState();
            }

            @Override
            public void onStop() {
                 onStoppedState();
            }

            @Override
            public void onError() {
                 onErrorState();
            }
        });

        playPauseView.setMinAndMaxFrame(0,45);
        setOnGestureListeners();
        playPauseView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }
            @Override
            public void onAnimationEnd(Animator animation) {
                playPauseView.pauseAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });



        playPauseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (videoPlayerActivityController.isNewPlayer) {
                    playbackCacheID = generatePlaybackCacheID();
                }*/
                //videoPlayerActivityController.setPlayBackCacheID(videoModel.getPlaybackCacheID());

                if (isReady && videoPlayerActivityController.isPlaying() && videoPlayerActivityController.isTrulyPlayable()) {
                    if(alphaAnim2 != null && !alphaAnim2.hasEnded()){
                        isCancelled = true;
                        alphaAnim2.cancel();
                    }
                    isPaused = true;
                    videoPlayerActivityController.setPausedByUser(true);
                    videoPlayerActivityController.pause();
                }
                else {
                    if(alphaAnim2 != null && !alphaAnim2.hasEnded()){
                        isCancelled = true;
                        alphaAnim2.cancel();
                    }
                    videoPlayerActivityController.start();
                }
            }
        });

    }

    String generatePlaybackCacheID(){
        String SALTCHARS = "ABCDEFGHIJLMNOPQRSTUVWXYZ123456890";
        StringBuilder salt = new StringBuilder();
        Random random = new Random();
        while (salt.length() < 18){
            int index = (int)(random.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltr = salt.toString();
        return  saltr;
    }


    @SuppressLint("ClickableViewAccessibility")
    private void setOnGestureListeners() {
        videoPlayerActivityController.mPlayerView.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Log.e("right", "onSwipeRight: ");

            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Log.e("left", "onSwipeLeft: ");

            }

            @Override
            public void onClick() {
                super.onClick();
                if(isReady && videoPlayerActivityController.isPlaying() && videoPlayerActivityController.isTrulyPlayable()){

                    if(alphaAnim2 != null && !alphaAnim2.hasEnded()){
                        isCancelled = true;
                        alphaAnim2.cancel();
                    }
                    transparentOverlay.setVisibility(View.VISIBLE);
                    progressToolLayout.setVisibility(View.VISIBLE);
                    playPauseView.setVisibility(View.VISIBLE);
                    playPauseLayout.setVisibility(View.VISIBLE);
                    scheduleVideoProgressToolDisappearance();

                }


            }
            @Override
            public void onDoubleClick() {
                super.onDoubleClick();
                if (isReady && videoPlayerActivityController.isPlaying() && videoPlayerActivityController.isTrulyPlayable()) {
                    if(alphaAnim2 != null && !alphaAnim2.hasEnded()){
                        isCancelled = true;
                        alphaAnim2.cancel();
                    }
                    isPaused = true;
                    videoPlayerActivityController.setPausedByUser(true);
                    videoPlayerActivityController.pause();
                }

            }
        });
    }

    public void setVideoUrl(String url) {
        this.url = url;
    }

    public String getVideotUrl(){
        return videoModel.getVideoUrl();
    }

    @Override
    public void onPause() {
        super.onPause();
       // videoPlayerActivityController.setMuted(true);
    }

    private void onStartedState() {
        Log.e("started", "onStartedState: ");
        loader.setVisibility(View.VISIBLE);
        transparentOverlay.setVisibility(View.VISIBLE);
        isStarted = true;
        isReady = false;
        videoPlayerActivityController.setInReadyState(false);
        isLooping = true;
        if(isPaused){

            playPauseView.setMaxFrame(89);
            playPauseView.resumeAnimation();
            isPaused = false;


        }
        else{

            progressToolLayout.setVisibility(View.GONE);
            thumbnail.setVisibility(View.VISIBLE);
            loader.setVisibility(View.VISIBLE);
            playPauseView.setVisibility(View.GONE);
            playPauseLayout.setVisibility(View.GONE);

        }

    }

    private void onBufferingState() {
        Log.e("buffering", "onBufferingState: ");
        isReady = true;
        loader.setVisibility(View.VISIBLE);
        thumbnail.setVisibility(View.GONE);
        transparentOverlay.setVisibility(View.VISIBLE);
        progressToolLayout.setVisibility(View.VISIBLE);

    }


    private void onReadyState() {
        videoProgress.setMax((int) videoPlayerActivityController.getDuration());
        if(isNewFullscreenPlayer) {
            isNewFullscreenPlayer = false;
            videoPlayerActivityController.seekTo(videoModel.getPlaybackPosition());
        }
        if(isStarted && !isPaused){
            isStarted = false;
            scheduleVideoProgressToolDisappearance();
        }
        Log.e("ready", "onReadyState: ");
        isReady = true;
        videoPlayerActivityController.setInReadyState(isReady);
        loader.setVisibility(View.GONE);
        thumbnail.setVisibility(View.GONE);
        transparentOverlay.setVisibility(View.GONE);
        progressToolLayout.setVisibility(View.GONE);
        playPauseView.setMaxFrame(89);
        playPauseView.resumeAnimation();
        (VideoPlayerBaseActivity.this).runOnUiThread(new Runnable() {

            @Override
            public void run() {

                if (videoPlayerActivityController.mPlayerView != null) {
                    int mCurrentPosition = (int) (videoPlayerActivityController.getPlaybackPosition());
                    videoProgress.setProgress(mCurrentPosition);
                    int formattedSecPostion = (int) TimeUnit.MILLISECONDS.toSeconds(videoPlayerActivityController.getPlaybackPosition()) % 60;
                    int unformattedSecPostion = (int) TimeUnit.MILLISECONDS.toSeconds(videoPlayerActivityController.getPlaybackPosition());
                    int formattedMinutePostion = (int) TimeUnit.MILLISECONDS.toMinutes(videoPlayerActivityController.getPlaybackPosition()) % 60;
                    int unformattedMinutePostion = (int) TimeUnit.MILLISECONDS.toMinutes(videoPlayerActivityController.getPlaybackPosition());
                    int secs = formattedSecPostion >= 0 ? formattedSecPostion : unformattedSecPostion;
                    int minutes = formattedMinutePostion >= 0 ? formattedMinutePostion : unformattedMinutePostion;
                    int hours = (int) TimeUnit.MILLISECONDS.toHours(videoPlayerActivityController.getPlaybackPosition());


                    if (hours <= 0) {
                        if (minutes < 10 && secs < 10) {
                            duration.setText(minutes + ":0" + secs);
                        } else if (minutes < 10 && secs >= 10) {
                            duration.setText(minutes + ":" + secs);
                        } else if (minutes >= 10 && secs < 10) {
                            duration.setText(minutes + ":0" + secs);
                        } else {
                            duration.setText(minutes + ":" + secs);
                        }
                    } else {

                        if (secs == 60) secs = 0;
                        if (minutes == 60) minutes = 0;

                        if (minutes < 10 && secs < 10) {
                            duration.setText(hours + ":0" + minutes + ":0" + secs);
                        } else if (minutes < 10 && secs >= 10) {
                            duration.setText(hours + ":0" + minutes + ":" + secs);
                        } else if (minutes >= 10 && secs < 10) {
                            duration.setText(hours + ":" + minutes + ":0" + secs);
                        } else {
                            duration.setText(hours + ":" + minutes + ":" + secs);
                        }
                    }

                }
                mHandler.postDelayed(this, 1000);
            }
        });

        videoProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (videoPlayerActivityController.mPlayerView != null && fromUser) {
                    if(alphaAnim2 != null && !alphaAnim2.hasEnded()){
                        isCancelled = true;
                        alphaAnim2.cancel();

                    }
                    videoPlayerActivityController.seekTo(progress);
                    videoProgress.setProgress(progress);
                    isStarted = false;
                    scheduleVideoProgressToolDisappearance();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    private void onPausedState() {

        Log.e("pause", "onPausedState: ");
        if(alphaAnim2 != null && !alphaAnim2.hasEnded()){
            isCancelled = true;
            alphaAnim2.cancel();
        }
        transparentOverlay.setVisibility(View.VISIBLE);
        progressToolLayout.setVisibility(View.VISIBLE);
        playPauseView.setVisibility(View.VISIBLE);
        playPauseLayout.setVisibility(View.VISIBLE);
        loader.setVisibility(View.GONE);
        thumbnail.setVisibility(View.GONE);
        isPaused = true;
        isLooping = false;
        playPauseView.setMinAndMaxFrame(0,45);
        playPauseView.resumeAnimation();


    }


    private void onStoppedState() {

        videoPlayerActivityController.setPausedByUser(false);
        Log.e("stopped", "onStoppedState: ");
        isReady = false;
        videoPlayerActivityController.setInReadyState(isReady);
        isLooping = false;
        if(alphaAnim2 != null && !alphaAnim2.hasEnded()){
            isCancelled = true;
            alphaAnim2.cancel();
        }
        playPauseView.setMinAndMaxFrame(0,45);
        playPauseView.resumeAnimation();
        playPauseView.setVisibility(View.VISIBLE);
        playPauseLayout.setVisibility(View.VISIBLE);
        thumbnail.setVisibility(View.VISIBLE);
        loader.setVisibility(View.GONE);
        transparentOverlay.setVisibility(View.VISIBLE);
        progressToolLayout.setVisibility(View.GONE);

    }


    private void onErrorState() {

        videoPlayerActivityController.setPausedByUser(false);
        Log.e("started", "onErrorState: ");
        isReady = false;
        videoPlayerActivityController.setInReadyState(isReady);
        isLooping = false;
        if(alphaAnim2 != null && !alphaAnim2.hasEnded()){
            isCancelled = true;
            alphaAnim2.cancel();
        }
        playPauseView.setMinAndMaxFrame(0,45);
        playPauseView.resumeAnimation();
        loader.setVisibility(View.GONE);
        playPauseView.setVisibility(View.VISIBLE);
        transparentOverlay.setVisibility(View.VISIBLE);
        progressToolLayout.setVisibility(View.GONE);
        thumbnail.setVisibility(View.VISIBLE);

    }

    public void scheduleVideoProgressToolDisappearance(){

        alphaAnim2 = new AlphaAnimation(1.0f, 0.0f);
        alphaAnim2.setStartOffset(4000);
        alphaAnim2.setDuration(400);
        alphaAnim2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {


                if(isCancelled){
                    transparentOverlay.setVisibility(View.VISIBLE);
                    playPauseView.setVisibility(View.VISIBLE);
                    playPauseLayout.setVisibility(View.VISIBLE);
                    isCancelled = false;
                }
                else {
                    playPauseView.setVisibility(View.GONE);
                    playPauseLayout.setVisibility(View.GONE);
                    transparentOverlay.setVisibility(View.GONE);

                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        transparentOverlay.setAnimation(alphaAnim2);

    }


    private void downloadVideoThumbnail(){

        ImageRequest request = ImageRequest.fromUri(videoModel.getVideoThumbnailUrl());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(thumbnail.getController()).build();
                 thumbnail.setController(controller);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        videoPlayerActivityController.release();
    }

    public void setVideoModel(VideoModel videoModel) {
        this.videoModel = videoModel;
    }
}


