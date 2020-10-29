package com.estello.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arthurivanets.arvi.Config;
import com.arthurivanets.arvi.util.misc.ExoPlayerUtils;
import com.arthurivanets.arvi.widget.PlayableItemsContainer;
import com.arthurivanets.arvi.widget.PlayableItemsRecyclerView;
import com.estello.android.ViewModel.ForumPostModel;

import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;


public class ForumPostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Context context;
    private ArrayList<ForumPostModel> forumPostList;
    private static int typeDate = 0;
    private static int typePost = 1;
    private static int typeQuestion = 2;
    private PostViewHolder postViewHolder;
    private QuestionPostViewHolder questionPostViewHolder;

    public ForumPostAdapter(Context context, ArrayList<ForumPostModel> forumPostList) {

        this.context = context;
        this.forumPostList = forumPostList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == typeDate) {

            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_post_date_item, parent, false);
            return new DateViewHolder(view2);
        } else if (viewType == typePost) {
            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_post_recyler_item, parent, false);
            return new PostViewHolder(view2);
        }
        else if(viewType == typeQuestion){

            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_post_recycler_item_type_question, parent, false);
            return new QuestionPostViewHolder(view2);
        }
        return null;


    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if (forumPostList.get(position).getType() == typePost) {

            postViewHolder = (PostViewHolder) holder;

            LinearLayoutManager uroraLinearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            LinearLayoutManager LinearLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            //LinearLayoutManager2.setInitialPrefetchItemCount(forumPostList.get(position).getPostAttachmentList().size());
            postViewHolder.recentCommentsRecyclerView.setLayoutManager(uroraLinearLayoutManager);
            postViewHolder.attachmentsRecyclerView.setLayoutManager(LinearLayoutManager2);
            postViewHolder.attachmentsRecyclerView.setPlaybackTriggeringStates(PlayableItemsContainer.PlaybackTriggeringState.SETTLING, PlayableItemsContainer.PlaybackTriggeringState.DRAGGING);
            postViewHolder.attachmentsRecyclerView.setAutoplayEnabled(false);
            postViewHolder.attachmentsRecyclerView.setAutoplayMode(PlayableItemsContainer.AutoplayMode.ONE_AT_A_TIME);
            ForumPostRecentCommentAdapter forumPostRecentCommentAdapter = new ForumPostRecentCommentAdapter(context, forumPostList.get(position).getRecentReplyingUsersList());

            Config config = new Config.Builder().cache(ExoPlayerUtils.getCache(context)).build();
            ForumPostAttachmentsAdapter forumPostAttachmentsAdapter = new ForumPostAttachmentsAdapter(context, forumPostList.get(position).getPostAttachmentList(), config);

            postViewHolder.attachmentsRecyclerView.setAdapter(forumPostAttachmentsAdapter);
            postViewHolder.recentCommentsRecyclerView.setAdapter(forumPostRecentCommentAdapter);
            postViewHolder.attachmentsRecyclerView.onResume();
            //postViewHolder.attachmentsRecyclerView.onResume();
            //postViewHolder.attachmentsRecyclerView.setCacheManager(CacheManager.DEFAULT);


        } else if (forumPostList.get(position).getType() == typeDate) {

            DateViewHolder dateViewHolder = (DateViewHolder) holder;
            dateViewHolder.forumPostDate.setText(forumPostList.get(position).getPostGroupDate());
        }





        if (forumPostList.get(position).getType() == typeQuestion) {

            questionPostViewHolder = (QuestionPostViewHolder) holder;

            LinearLayoutManager uroraLinearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            LinearLayoutManager LinearLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            //LinearLayoutManager2.setInitialPrefetchItemCount(forumPostList.get(position).getPostAttachmentList().size());
            questionPostViewHolder.recentCommentsRecyclerView.setLayoutManager(uroraLinearLayoutManager);
            questionPostViewHolder.attachmentsRecyclerView.setLayoutManager(LinearLayoutManager2);
            questionPostViewHolder.attachmentsRecyclerView.setPlaybackTriggeringStates(PlayableItemsContainer.PlaybackTriggeringState.SETTLING, PlayableItemsContainer.PlaybackTriggeringState.DRAGGING);
            questionPostViewHolder.attachmentsRecyclerView.setAutoplayEnabled(false);
            questionPostViewHolder.attachmentsRecyclerView.setAutoplayMode(PlayableItemsContainer.AutoplayMode.ONE_AT_A_TIME);
            ForumPostRecentCommentAdapter forumPostRecentCommentAdapter = new ForumPostRecentCommentAdapter(context, forumPostList.get(position).getRecentReplyingUsersList());

            Config config = new Config.Builder().cache(ExoPlayerUtils.getCache(context)).build();
            ForumPostAttachmentsAdapter forumPostAttachmentsAdapter = new ForumPostAttachmentsAdapter(context, forumPostList.get(position).getPostAttachmentList(), config);

            questionPostViewHolder.attachmentsRecyclerView.setAdapter(forumPostAttachmentsAdapter);
            questionPostViewHolder.recentCommentsRecyclerView.setAdapter(forumPostRecentCommentAdapter);
            questionPostViewHolder.attachmentsRecyclerView.onResume();
            //postViewHolder.attachmentsRecyclerView.onResume();
            //postViewHolder.attachmentsRecyclerView.setCacheManager(CacheManager.DEFAULT);


        }


    }

    @Override
    public int getItemCount() {
        return forumPostList.size();
    }


    @Override
    public int getItemViewType(int position) {

        if (forumPostList.get(position).getType() == typeDate) {

            return typeDate;
        } else if (forumPostList.get(position).getType() == typePost) {

            return typePost;
        } else if (forumPostList.get(position).getType() == typeQuestion) {

            return typeQuestion;
        }
        return typePost;
    }

    public void pausePlayBack(){

        if(questionPostViewHolder != null){
            questionPostViewHolder.attachmentsRecyclerView.pausePlayback();
        }
        if(postViewHolder != null){

            postViewHolder.attachmentsRecyclerView.pausePlayback();
        }
    }
    public void resumePlayBack(){

        if(questionPostViewHolder != null){

            questionPostViewHolder.attachmentsRecyclerView.onResume();
        }
        if(postViewHolder != null){

            postViewHolder.attachmentsRecyclerView.onResume();
        }
    }
    public void destroyPlayer(){

        if(questionPostViewHolder != null){

            questionPostViewHolder.attachmentsRecyclerView.onDestroy();
        }
        if(postViewHolder != null){

            postViewHolder.attachmentsRecyclerView.onDestroy();
        }
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recentCommentsRecyclerView;
        PlayableItemsRecyclerView attachmentsRecyclerView;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            attachmentsRecyclerView = itemView.findViewById(R.id.forum_post_attachments_recyclerview);
            recentCommentsRecyclerView = itemView.findViewById(R.id.forum_post_recent_comments_recylerview);


        }
    }

    public class QuestionPostViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recentCommentsRecyclerView;
        PlayableItemsRecyclerView attachmentsRecyclerView;

        public QuestionPostViewHolder(@NonNull View itemView) {
            super(itemView);

            attachmentsRecyclerView = itemView.findViewById(R.id.forum_post_attachments_recyclerview);
            recentCommentsRecyclerView = itemView.findViewById(R.id.forum_post_recent_comments_recylerview);


        }

    }


    public class DateViewHolder extends RecyclerView.ViewHolder {


        TextView forumPostDate;

        public DateViewHolder(@NonNull View itemView) {

            super(itemView);
            forumPostDate = itemView.findViewById(R.id.forum_post_date);


        }
    }
}


