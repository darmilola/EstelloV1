package com.estello.android.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.arthurivanets.arvi.Config;
import com.arthurivanets.arvi.widget.PlayableItemViewHolder;
import com.arthurivanets.arvi.widget.PlaybackState;
import com.estello.android.ViewModel.ForumPostAttachmentsModel;
import com.estello.android.Utils.BitmapScaler;
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
import com.google.android.exoplayer2.SimpleExoPlayer;

import com.estello.android.R;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ForumPostAttachmentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    private ArrayList<ForumPostAttachmentsModel> attachmentsList;
    private static int typeFile = 0;
    private static int typeImage = 1;
    private static int typeVideo = 2;
    private static int typeAudio = 3;
    Config config;
    long playBackPosition = 0;
    public List<VideosViewHolder> videosViewHolderList = new ArrayList<>();
    boolean isPlayAfterPaused = false;



    public ForumPostAttachmentsAdapter(Context context, ArrayList<ForumPostAttachmentsModel> attachmentsList,Config config){

        this.context = context;
        this.attachmentsList = attachmentsList;
        this.config = config;
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

   /*@Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder vh){

        if(vh instanceof VideosViewHolder){

            ((VideosViewHolder)vh).stop();

        }
    }*/

        @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            if (holder.getItemViewType() == typeImage) {


               setDataSubscriber(context,Uri.parse(attachmentsList.get(position).getAttachmentImageUrl()),holder);

            }
            else if (holder.getItemViewType() == typeVideo) {


                videosViewHolderList.add((VideosViewHolder) holder);
                ((VideosViewHolder) holder).setUrl(attachmentsList.get(position).getAttachmentsVideoUrl());

                //Glide.with(context).load(Uri.parse(attachmentsList.get(position).getAttachmentVideoThumbnailUrl())).into(((VideosViewHolder)holder).thumbnail);

                ImageRequest request = ImageRequest.fromUri(attachmentsList.get(position).getAttachmentVideoThumbnailUrl());
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(((VideosViewHolder)holder).thumbnail.getController()).build();
                ((VideosViewHolder)holder).thumbnail.setController(controller);

                ((VideosViewHolder) holder).playVideoLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        //playBackPosition = 0;
                        //((VideosViewHolder)holder).getPlaybackInfo().setPlaybackPosition(playBackPosition);
                        if(((VideosViewHolder)holder).playbackState == PlaybackState.PAUSED){

                            isPlayAfterPaused = true;
                        }

                        ((VideosViewHolder) holder).start();

                    }


                });


                ((VideosViewHolder) holder).videoFrameLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        playBackPosition = ((VideosViewHolder) holder).getPlaybackInfo().getPlaybackPosition();
                        if (((VideosViewHolder) holder).isPlaying()) {

                            ((VideosViewHolder) holder).pause();
                        }
                    }
                });

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

        LottieAnimationView loader;
        SimpleDraweeView thumbnail;
        LinearLayout playVideoLayout;
        Config config;
        private SimpleExoPlayer player;
        FrameLayout videoFrameLayout;
        String url = "";
        PlaybackState playbackState;

        public VideosViewHolder(ViewGroup parentViewGroup, View itemView, Config config) {
            super(parentViewGroup, itemView);
            this.config = config;
            playVideoLayout = itemView.findViewById(R.id.attachment_video_play_circle_layout);
            loader = itemView.findViewById(R.id.attachment_video_loader_view);
            thumbnail = itemView.findViewById(R.id.attachment_video_thumbnail);
            videoFrameLayout = itemView.findViewById(R.id.forum_post_item_attachments_video_frame);






        }

        @Override
        protected float getTriggerOffset() {
            return 0.1f;
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
            return url; //attachmentsList.get(getAdapterPosition()).;
        }

        @NotNull
        @Override
        public  Config getConfig(){
            return config;
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


                //onAttach(player);
                loader.setVisibility(View.VISIBLE);
                playVideoLayout.setVisibility(View.GONE);
                //thumbnail.setVisibility(View.VISIBLE);
                if(isPlayAfterPaused){

                    thumbnail.setVisibility(View.GONE);
                    isPlayAfterPaused = false;
                }
                else{
                    thumbnail.setVisibility(View.VISIBLE);
                }

            }


        private void onBufferingState() {

            loader.setVisibility(View.VISIBLE);
            playVideoLayout.setVisibility(View.GONE);
            thumbnail.setVisibility(View.GONE);
        }


        private void onReadyState() {

                loader.setVisibility(View.GONE);
                playVideoLayout.setVisibility(View.GONE);
                thumbnail.setVisibility(View.GONE);

        }


        private void onPausedState() {
            playVideoLayout.setVisibility(View.VISIBLE);
        }


        private void onStoppedState() {

            playVideoLayout.setVisibility(View.VISIBLE);
            thumbnail.setVisibility(View.VISIBLE);

        }


        private void onErrorState() {

            playVideoLayout.setVisibility(View.VISIBLE);
            loader.setVisibility(View.GONE);

        }

    }


    }


