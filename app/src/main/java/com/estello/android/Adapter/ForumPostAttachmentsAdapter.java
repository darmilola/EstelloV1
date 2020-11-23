package com.estello.android.Adapter;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.os.Handler;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import com.estello.android.Arvi.Config;
import com.estello.android.Arvi.widget.PlayableItemViewHolder;
import com.estello.android.Arvi.widget.PlaybackState;
import com.estello.android.ChannelVideoPlayerFullScreen;
import com.estello.android.ViewModel.ForumPostAttachmentsModel;
import com.estello.android.Utils.BitmapScaler;
import com.estello.android.ViewModel.OnSwipeTouchListener;
import com.estello.android.ViewModel.VideoModel;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import com.estello.android.R;
import com.google.android.exoplayer2.ui.PlayerView;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ForumPostAttachmentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    private ArrayList<ForumPostAttachmentsModel> attachmentsList;
    private static int typeFile = 0;
    private static int typeImage = 1;
    private static int typeVideo = 2;
    private static int typeAudio = 3;
    private Config config;
    private NewPlayerStarted newPlayerStarted;
    private GoingToFullScreen goingToFullScreen;


    public interface GoingToFullScreen{
        public void onGoingTofullscreen();
    }
    public interface NewPlayerStarted{
        public void onNewPlayerStarted();
    }
    public ForumPostAttachmentsAdapter(Context context, ArrayList<ForumPostAttachmentsModel> attachmentsList,Config config,NewPlayerStarted newPlayerStarted,GoingToFullScreen goingToFullScreen){
        this.context = context;
        this.attachmentsList = attachmentsList;
        this.config = config;
        this.newPlayerStarted = newPlayerStarted;
        this.goingToFullScreen = goingToFullScreen;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == typeImage) {

            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_post_item_attachments_image, parent, false);
            return new ImageViewHolder(view2);

        } else if (viewType == typeFile) {

            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_post_item_attachments_type_file, parent, false);
            return new FileViewHolder(view2);

        }
        else if(viewType == typeVideo){

            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_post_item_attachments_type_video, parent, false);
            return new VideosViewHolder(parent,view2,config);

        }
        else if(viewType == typeAudio){

            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_post_attachments_item_type_audio, parent, false);
            return new AudioViewHolder(view2);

        }
        return  null;
    }
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            if (holder.getItemViewType() == typeImage) {


               setDataSubscriber(context,Uri.parse(attachmentsList.get(position).getAttachmentImageUrl()),holder);

            }
            else if (holder.getItemViewType() == typeVideo) {
                ((VideosViewHolder) holder).setUrl(attachmentsList.get(position).getAttachmentsVideoUrl());
                //((VideosViewHolder)holder).videoProgress.setMax((int) ((VideosViewHolder)holder).getDuration());
                ImageRequest request = ImageRequest.fromUri(attachmentsList.get(position).getAttachmentVideoThumbnailUrl());
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(((VideosViewHolder)holder).thumbnail.getController()).build();
                ((VideosViewHolder)holder).thumbnail.setController(controller);

            }
        }

        public void getBitmap(Context context, Uri uri, DataSubscriber dataSubscriber){

            ImagePipeline imagePipeline = Fresco.getImagePipeline();
            ImageRequestBuilder builder = ImageRequestBuilder.newBuilderWithSource(uri);
            ImageRequest request = builder.build();
            DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(request,context);
            dataSource.subscribe(dataSubscriber, UiThreadImmediateExecutorService.getInstance());
        }

        public void setDataSubscriber(Context context, Uri uri, RecyclerView.ViewHolder holder){

            DataSubscriber dataSubscriber = new BaseDataSubscriber() {
                @Override
                protected void onNewResultImpl(DataSource dataSource) {

                    if(!dataSource.isFinished()){

                        return;
                    }
                    CloseableReference<CloseableBitmap> imageReference = (CloseableReference<CloseableBitmap>) dataSource.getResult();
                    if(imageReference != null){

                        final  CloseableReference<CloseableBitmap> closeableReference = imageReference.clone();

                        try{
                            CloseableBitmap closeableBitmap = closeableReference.get();
                            Bitmap bitmap = closeableBitmap.getUnderlyingBitmap();
                            if(bitmap != null && !bitmap.isRecycled()){

                                Bitmap bitmap1 = new BitmapScaler().scaleBitmap(bitmap,0.5f);

                                ((ImageViewHolder)holder).imageView.setImageBitmap(bitmap1);
                            }
                        }finally {
                            imageReference.close();
                            closeableReference.close();
                        }
                    }


                }

                @Override
                protected void onFailureImpl(DataSource dataSource) {

                    Throwable throwable = dataSource.getFailureCause();
                }
            };
            getBitmap(context,uri,dataSubscriber);
        }
    @Override
    public int getItemCount() {
        return attachmentsList.size();
    }


    public int getItemViewType(int position){

        if(attachmentsList.get(position).getAttachmentTypeCode() == typeFile){

            return typeFile;
        }
        else if(attachmentsList.get(position).getAttachmentTypeCode() == typeImage){

            return  typeImage;
        }
        else if(attachmentsList.get(position).getAttachmentTypeCode() == typeVideo){

            return  typeVideo;
        }
        else if(attachmentsList.get(position).getAttachmentTypeCode() == typeAudio){

            return  typeAudio;
        }

        return typeImage;
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder{


        ImageView imageView;
        public ImageViewHolder(@NonNull View itemView) {

            super(itemView);
            imageView = itemView.findViewById(R.id.forum_post_attachments_imageview);



        }
    }

    public class FileViewHolder extends RecyclerView.ViewHolder{



        public FileViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }

    public class AudioViewHolder extends RecyclerView.ViewHolder{



        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public class  VideosViewHolder extends PlayableItemViewHolder {

        RelativeLayout transparentOverlay;
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
        String transitionName = "";
        private Handler mHandler = new Handler();
        boolean isCancelled = false;
        boolean isPaused = false;
        boolean isReady = false;
        boolean isStarted = false;
        boolean isLooping = false;
        private int playerProgress = 0;
        private VideosViewHolder(ViewGroup parentViewGroup, View itemView, Config config) {

            super(parentViewGroup, itemView);
            this.config = config;
            player = itemView.findViewById(R.id.player_view);
            playPauseView = itemView.findViewById(R.id.attachment_video_play_pause_view);
            playPauseLayout = itemView.findViewById(R.id.attachment_play_pause_layout);
            loader = itemView.findViewById(R.id.attachment_video_loader_view);
            thumbnail = itemView.findViewById(R.id.attachment_video_thumbnail);
            touchArea = itemView.findViewById(R.id.forum_post_item_attachments_video_frame);
            transparentOverlay = itemView.findViewById(R.id.video_attachments_transparent_overlay);
            videoProgress = itemView.findViewById(R.id.video_attachments_progressbar);
            progressToolLayout = itemView.findViewById(R.id.video_attachments_progress_layout);
            duration = itemView.findViewById(R.id.video_attachments_playing_duration);
            fullScreen = itemView.findViewById(R.id.video_attachments_fullscreen_layout);
            controller = itemView.findViewById(R.id.video_attachments_controller);
            playPauseView.setMinAndMaxFrame(0,45);




            fullScreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goingToFullScreen.onGoingTofullscreen();
                    setMuted(true);
                    VideoModel videoModel = new VideoModel();
                    videoModel.setPlaybackCacheID(getPlayBackCacheID());
                    videoModel.setPlaybackPosition(playerProgress);
                    videoModel.setVideoThumbnailUrl(attachmentsList.get(getAdapterPosition()).getAttachmentVideoThumbnailUrl());
                    videoModel.setVideoUrl(attachmentsList.get(getAdapterPosition()).getAttachmentsVideoUrl());
                    ArrayList<VideoModel> videoModelArrayList = new ArrayList<>();
                    videoModelArrayList.add(videoModel);
                    Intent intent = new Intent(context, ChannelVideoPlayerFullScreen.class);
                    intent.putParcelableArrayListExtra("videomodel",videoModelArrayList);
                    transitionName = generatePlaybackCacheID();
                    touchArea.setTransitionName(transitionName);
                    ViewCompat.setTransitionName(touchArea,transitionName);
                    intent.putExtra("transition_name",transitionName);
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,touchArea, Objects.requireNonNull(ViewCompat.getTransitionName(touchArea)));
                    context.startActivity(intent,optionsCompat.toBundle());
                }
            });
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
                    if (isNewPlayer) {
                        playbackCacheID = generatePlaybackCacheID();
                    }
                    setPlayBackCacheID(playbackCacheID);

                    if (isReady && isPlaying() && isTrulyPlayable()) {
                        if(alphaAnim2 != null && !alphaAnim2.hasEnded()){
                            isCancelled = true;
                            alphaAnim2.cancel();
                        }
                         isPaused = true;
                         setPausedByUser(true);
                         pause();
                    }
                    else {
                        if(alphaAnim2 != null && !alphaAnim2.hasEnded()){
                            isCancelled = true;
                            alphaAnim2.cancel();
                        }
                        start();
                    }
                }
            });

        }

            @SuppressLint("ClickableViewAccessibility")
            private void setOnGestureListeners() {
            mPlayerView.setOnTouchListener(new OnSwipeTouchListener(context){
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
                    if(isReady && isPlaying() && isTrulyPlayable()){

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
                    if (isReady && isPlaying() && isTrulyPlayable()) {
                        if(alphaAnim2 != null && !alphaAnim2.hasEnded()){
                            isCancelled = true;
                            alphaAnim2.cancel();
                        }
                        isPaused = true;
                        setPausedByUser(true);
                        pause();
                    }

                }
            });
        }

        @Override
        protected float getTriggerOffset() {
            return 0.999f;
        }


        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public boolean isLooping(){
            return  true;
        }


        /**
         * Retrieves the media Url associated with this item.
         *
         * @return the media url
         */
        @NonNull
        @Override
        public String getUrl() {
            return url;
        }

        @NotNull
        @Override
        public  Config getConfig(){
            return config;
        }

        @Override
        public long getDuration() {
           return super.getDuration();
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

        @Override
        public  void onStateChanged(@NonNull PlaybackState playbackState){
              super.onStateChanged(playbackState);
              this.playbackState = playbackState;
              if(playbackState == PlaybackState.BUFFERING) onBufferingState();
              if(playbackState == PlaybackState.ERROR) onErrorState();
              if(playbackState == PlaybackState.PAUSED) onPausedState();
              if(playbackState == PlaybackState.READY) onReadyState();
              if(playbackState == PlaybackState.STOPPED) onStoppedState();
              if(playbackState == PlaybackState.STARTED) onStartedState();


        }

        private void onStartedState() {
                Log.e("started", "onStartedState: ");
                loader.setVisibility(View.VISIBLE);
                transparentOverlay.setVisibility(View.VISIBLE);
                isStarted = true;
                isReady = false;
                setInReadyState(false);
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
            newPlayerStarted.onNewPlayerStarted();
            loader.setVisibility(View.VISIBLE);
            thumbnail.setVisibility(View.GONE);
            transparentOverlay.setVisibility(View.VISIBLE);
            progressToolLayout.setVisibility(View.VISIBLE);

        }


        private void onReadyState() {
            if(isStarted && !isPaused){
                isStarted = false;
                scheduleVideoProgressToolDisappearance();
            }
            Log.e("ready", "onReadyState: ");
            isReady = true;
            setInReadyState(isReady);
            loader.setVisibility(View.GONE);
            thumbnail.setVisibility(View.GONE);
            transparentOverlay.setVisibility(View.GONE);
            progressToolLayout.setVisibility(View.GONE);
            playPauseView.setMaxFrame(89);
            playPauseView.resumeAnimation();

            videoProgress.setMax((int) getDuration());
            ((Activity) context).runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    if (mPlayerView != null) {
                        int mCurrentPosition = (int) (getPlaybackPosition());
                        videoProgress.setProgress(mCurrentPosition);
                        int formattedSecPostion = (int) TimeUnit.MILLISECONDS.toSeconds(getPlaybackPosition()) % 60;
                        int unformattedSecPostion = (int) TimeUnit.MILLISECONDS.toSeconds(getPlaybackPosition());
                        int formattedMinutePostion = (int) TimeUnit.MILLISECONDS.toMinutes(getPlaybackPosition()) % 60;
                        int unformattedMinutePostion = (int) TimeUnit.MILLISECONDS.toMinutes(getPlaybackPosition());
                        int secs = formattedSecPostion >= 0 ? formattedSecPostion : unformattedSecPostion;
                        int minutes = formattedMinutePostion >= 0 ? formattedMinutePostion : unformattedMinutePostion;
                        int hours = (int) TimeUnit.MILLISECONDS.toHours(getPlaybackPosition());


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
                    playerProgress = progress;
                    if (mPlayerView != null && fromUser) {
                        if(alphaAnim2 != null && !alphaAnim2.hasEnded()){
                            isCancelled = true;
                            alphaAnim2.cancel();

                        }
                        seekTo(progress);
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

            setPausedByUser(false);
            Log.e("stopped", "onStoppedState: ");
            isReady = false;
            setInReadyState(isReady);
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

            setPausedByUser(false);
            Log.e("started", "onErrorState: ");
            isReady = false;
            setInReadyState(isReady);
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

        }

    }





