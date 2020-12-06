package com.estello.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.deltastream.example.edittextcontroller.RTextView;
import com.estello.android.Arvi.Config;
import com.estello.android.Arvi.util.misc.ExoPlayerUtils;
import com.estello.android.Arvi.widget.PlayableItemsRecyclerView;
import com.estello.android.MainActivity;
import com.estello.android.R;
import com.estello.android.ViewModel.ForumPostModel;
import com.estello.android.ViewModel.RichLinkView.RichLinkView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CommunityFeaturedAdapter extends RecyclerView.Adapter<CommunityFeaturedAdapter.itemViewHolder> {

    List<Object> communityFeaturedArraylist;
    private Queue<itemViewHolder> itemViewHolderQueue = new LinkedList<>();
    Context context;
    boolean onGoingToFullscreen  = false;

    public CommunityFeaturedAdapter(List<Object> communityFeaturedArraylist, Context context){
        this.communityFeaturedArraylist = communityFeaturedArraylist;
        this.context = context;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_community_recyler_type4_item, parent, false);
        return new itemViewHolder(view2);
    }

    @Override
    public void onViewAttachedToWindow(@NotNull itemViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        ForumPostModel forumPostModel = (ForumPostModel) communityFeaturedArraylist.get(viewHolder.getAdapterPosition());
        LinearLayoutManager LinearLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            viewHolder.attachmentsRecyclerView.setLayoutManager(LinearLayoutManager2);
                Config config = new Config.Builder().cache(ExoPlayerUtils.getCache(context)).build();
                ForumPostAttachmentsAdapter forumPostAttachmentsAdapter = new ForumPostAttachmentsAdapter(context, forumPostModel.getPostAttachmentList(), config, new ForumPostAttachmentsAdapter.NewPlayerStarted() {
                    @Override
                    public void onNewPlayerStarted() {
                        for (itemViewHolder itemViewHolder:itemViewHolderQueue) {
                            if(!itemViewHolder.attachmentsRecyclerView.isPlayBackPlaying() || viewHolder != itemViewHolder){
                                itemViewHolder.attachmentsRecyclerView.stopPlayback();
                            }
                        }
                    }
                }, new ForumPostAttachmentsAdapter.GoingToFullScreen() {
                    @Override
                    public void onGoingTofullscreen() {
                        onGoingToFullscreen = true;
                    }
                });

                  viewHolder.attachmentsRecyclerView.setAdapter(forumPostAttachmentsAdapter);
                  ((MainActivity)context).setActivityPausedListener(CommunityFeaturedAdapter.this::pausePlayBackFromActivityOnPause);
                  ((MainActivity)context).setActivityDestroyedListener(CommunityFeaturedAdapter.this::destroyPlayBackFromActivity);
                  ((MainActivity)context).setActivityResumedListener(CommunityFeaturedAdapter.this::resumePlayBackFromActivity);
                   itemViewHolderQueue.add(viewHolder);
    }


    @Override
    public void onViewRecycled(@NotNull itemViewHolder viewHolder){
        for (itemViewHolder viewHolder1 : itemViewHolderQueue) {
            itemViewHolderQueue.remove(viewHolder1);
            if (viewHolder1 != null) viewHolder1.attachmentsRecyclerView.stopPlayback();
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NotNull itemViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);

        for (itemViewHolder viewHolder1 : itemViewHolderQueue) {
            itemViewHolderQueue.remove(viewHolder1);
            if (viewHolder1 != null) viewHolder1.attachmentsRecyclerView.stopPlayback();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) { }


    private void resumePlayBackFromActivity(){
        itemViewHolder itemViewHolderCache;
        for (itemViewHolder itemViewHolder: itemViewHolderQueue) {
            itemViewHolderCache = itemViewHolder;
            if (itemViewHolderCache != null && itemViewHolderCache.attachmentsRecyclerView.isPlayBackPlaying())
                itemViewHolderCache.attachmentsRecyclerView.onResume();
        }
    }

    private void pausePlayBackFromActivityOnPause(){
        for (itemViewHolder videoCache: itemViewHolderQueue) {
            if (onGoingToFullscreen) {

            } else {
                videoCache.attachmentsRecyclerView.onPause(true);
            }
        }
        onGoingToFullscreen = false;

    }
    private void destroyPlayBackFromActivity(){
        for (itemViewHolder videoCache: itemViewHolderQueue) {
            videoCache.attachmentsRecyclerView.onDestroy(false);
        }
    }




    @Override
    public int getItemCount() {
        return communityFeaturedArraylist.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder implements RTextView.MentionClickedListener, RTextView.HashTagClickedListener,View.OnLongClickListener,View.OnClickListener{

        PlayableItemsRecyclerView attachmentsRecyclerView;
        RTextView textView;
        RichLinkView richLinkView;
        ImageView profilePicture;
        boolean isMentionClicked = false;
        boolean isHashTagClicked = false;
        public itemViewHolder(View ItemView){
            super(ItemView);
            attachmentsRecyclerView = ItemView.findViewById(R.id.forum_post_attachments_recyclerview);
            textView = itemView.findViewById(R.id.forum_post_recycler_item_textview);
            textView.setMentionClickedListener(this);
            textView.setHashTagClickedListener(this);
            textView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            richLinkView = itemView.findViewById(R.id.richlinkview);
            profilePicture = itemView.findViewById(R.id.forum_post_profile_picture_type_post);
        }
        @Override
        public void onClick(View view) {

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }

        @Override
        public void onMentionClicked(String mentionJson) {

        }

        @Override
        public void onHashTagClicked(String hashtagText) {

        }
    }
}

