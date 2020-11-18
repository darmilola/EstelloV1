package com.estello.android.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.estello.android.Arvi.Config;
import com.estello.android.Arvi.player.Player;
import com.estello.android.Arvi.widget.PlayableItemViewHolder;
import com.estello.android.R;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ForumPostAttachmentsVideosAdapter extends RecyclerView.Adapter<ForumPostAttachmentsVideosAdapter.VideosViewHolder> {


    String videoUrl = "";
    Context context;
    Config config;
    public ForumPostAttachmentsVideosAdapter(Context context, String videoUrl,Config config){

        this.context = context;
        this.videoUrl = videoUrl;
        this.config = config;
    }

    @NonNull
    @Override
    public VideosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_post_item_attachments_type_video, parent, false);
        return new VideosViewHolder(parent,view3,config);

    }


    @Override
    public void onBindViewHolder(@NonNull VideosViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class  VideosViewHolder extends PlayableItemViewHolder {

        Config config;
        private Player player;

        public VideosViewHolder(ViewGroup parentViewGroup, View itemView, Config config) {
            super(parentViewGroup, itemView,0);
            this.config = config;
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
            return "http://techslides.com/demos/sample-videos/small.mp4"; //attachmentsList.get(getAdapterPosition()).;
        }

        @NotNull
        @Override
        public  Config getConfig(){
            return config;
        }
    }
}
