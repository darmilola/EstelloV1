package com.estello.android.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.deltastream.example.edittextcontroller.RTextView;
import com.deltastream.example.edittextcontroller.api.format.RTHtml;
import com.estello.android.Arvi.Config;
import com.estello.android.Arvi.util.misc.ExoPlayerUtils;
import com.estello.android.Arvi.widget.PlayableItemsContainer;
import com.estello.android.Arvi.widget.PlayableItemsRecyclerView;
import com.estello.android.ChannelBaseActivity;
import com.estello.android.ChannelPostDetails;
import com.estello.android.R;
import com.estello.android.ViewModel.ForumPostModel;
import com.estello.android.ViewModel.RichLinkView.RichLinkView;
import com.estello.android.ViewModel.RichLinkView.ViewListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChannelMessagingCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ForumPostModel> forumPostModelArrayList;
    Context context;
    private static int typeDate = 0;
    private static int typePost = 1;
    private MentionClickedListener mentionClickedListener;
    private ProfilePictureClickedListener profilePictureClickedListener;
    private hashTagClickListener hashTagClickedListener;
    private PostLongClickedListener postLongClickedListener;
    private Queue<PostViewHolder> postViewHolderQueue = new LinkedList<>();


    public interface hashTagClickListener{
        public void onHashTagClicked(int position);
    }

    public interface MentionClickedListener{

        public void onMentionClicked(int position);
    }
    public interface ProfilePictureClickedListener{

        public void onProfilePictureClicked(int position);
    }
    public interface PostLongClickedListener{

        public void onPostLongClicked(int position);
    }


    public ChannelMessagingCommentAdapter(ArrayList<ForumPostModel> forumPostModelArrayList, Context context, MentionClickedListener mentionClickedListener, ProfilePictureClickedListener profilePictureClickedListener, hashTagClickListener hashTagClickedListener, PostLongClickedListener postLongClickedListener) {

        this.context = context;
        this.forumPostModelArrayList = forumPostModelArrayList;
        this.mentionClickedListener = mentionClickedListener;
        this.profilePictureClickedListener = profilePictureClickedListener;
        this.hashTagClickedListener = hashTagClickedListener;
        this.postLongClickedListener = postLongClickedListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == typeDate) {

            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_post_date_item, parent, false);
            return new DateViewHolder(view2);

        } else if (viewType == typePost) {

            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_message_comment_recycler_item, parent, false);
            return new PostViewHolder(view2);
        }
        return null;
    }




    @Override
    public void onViewAttachedToWindow(@NotNull RecyclerView.ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);

        if (viewHolder instanceof PostViewHolder) {
            if (!postViewHolderQueue.contains(viewHolder)) {
                LinearLayoutManager LinearLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                PostViewHolder postViewHolder = (PostViewHolder) viewHolder;
                postViewHolder.attachmentsRecyclerView.setLayoutManager(LinearLayoutManager2);
                Config config = new Config.Builder().cache(ExoPlayerUtils.getCache(context)).build();
                ForumPostAttachmentsAdapter forumPostAttachmentsAdapter = new ForumPostAttachmentsAdapter(context, forumPostModelArrayList.get(viewHolder.getAdapterPosition()).getPostAttachmentList(), config, new ForumPostAttachmentsAdapter.NewPlayerStarted() {
                    @Override
                    public void onNewPlayerStarted() {
                        for (PostViewHolder postViewHolder1 : postViewHolderQueue) {
                            if (!postViewHolder1.attachmentsRecyclerView.isPlayBackPlaying() || viewHolder != postViewHolder1) {
                                postViewHolder1.attachmentsRecyclerView.stopPlayback();
                            }
                        }
                    }
                });

                 postViewHolder.attachmentsRecyclerView.setAdapter(forumPostAttachmentsAdapter);
                 ((ChannelPostDetails) postViewHolder.itemView.getContext()).setActivityPausedListener(ChannelMessagingCommentAdapter.this::pausePlayBackFromActivityOnPause);
                 ((ChannelPostDetails) postViewHolder.itemView.getContext()).setActivityDestroyedListener(ChannelMessagingCommentAdapter.this::destroyPlayBackFromActivity);
                 ((ChannelPostDetails) postViewHolder.itemView.getContext()).setActivityResumedListener(ChannelMessagingCommentAdapter.this::resumePlayBackFromActivity);
                 postViewHolderQueue.add((PostViewHolder) viewHolder);
            }


        }

    }


    @Override
    public void onViewRecycled(@NotNull RecyclerView.ViewHolder viewHolder){
        if(viewHolder instanceof PostViewHolder){
            boolean isRemoved = postViewHolderQueue.remove(viewHolder);
            ((PostViewHolder) viewHolder).attachmentsRecyclerView.stopPlayback();
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NotNull RecyclerView.ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        if(viewHolder instanceof PostViewHolder){
            PostViewHolder postViewHolder = postViewHolderQueue.peek();
            if(postViewHolder != null)postViewHolder.attachmentsRecyclerView.stopPlayback();
        }
    }





    private void resumePlayBackFromActivity(){
        PostViewHolder PostViewHolderCache;
        for (PostViewHolder postViewHolder: postViewHolderQueue) {
            PostViewHolderCache = postViewHolder;
            if (PostViewHolderCache != null && PostViewHolderCache.attachmentsRecyclerView.isPlayBackPlaying())
                PostViewHolderCache.attachmentsRecyclerView.onResume();
        }
    }

    private void pausePlayBackFromActivityOnPause(){
        for (PostViewHolder videoCache: postViewHolderQueue){
            videoCache.attachmentsRecyclerView.onPause(true);
        }
    }
    private void destroyPlayBackFromActivity(){
        for (PostViewHolder videoCache: postViewHolderQueue) {
            videoCache.attachmentsRecyclerView.onDestroy(true);
        }
    }







    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {



        if (forumPostModelArrayList.get(position).getType() == typePost) {

             PostViewHolder postViewHolder = (PostViewHolder) holder;
             SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
             String text =  preferences.getString("2","");
             RTHtml rtHtml = new RTHtml(text);
             postViewHolder.textView.setText(rtHtml);
             String link = "";
             if(postViewHolder.richLinkView != null)
                 link = "https://www.github.com/darmilola";
                 postViewHolder.richLinkView.setLink(link, new ViewListener() {
                     @Override
                    public void onSuccess(boolean status) {
                        try {
                            if(postViewHolder.richLinkView != null) {

                                postViewHolder.richLinkView.setLinkFromMeta(postViewHolder.richLinkView.getMetaData());
                            }
                        } catch (IndexOutOfBoundsException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Exception e) {

                        if(postViewHolder.richLinkView != null) {

                            //postViewHolder.richLinkView.setVisibility(View.GONE);
                        }
                    }
                });

        } else if (forumPostModelArrayList.get(position).getType() == typeDate) {

            DateViewHolder dateViewHolder = (DateViewHolder) holder;
            dateViewHolder.forumPostDate.setText(forumPostModelArrayList.get(position).getPostGroupDate());
        }


    }

    @Override
    public int getItemCount() {
        return forumPostModelArrayList.size();
    }


    @Override
    public int getItemViewType(int position) {

        if (forumPostModelArrayList.get(position).getType() == typeDate) {

            return typeDate;
        } else if (forumPostModelArrayList.get(position).getType() == typePost) {

            return typePost;
        }
        return typePost;
    }



    public class DateViewHolder extends RecyclerView.ViewHolder {


        TextView forumPostDate;

        public DateViewHolder(@NonNull View itemView) {

            super(itemView);
            forumPostDate = itemView.findViewById(R.id.forum_post_date);


        }
    }

    public class PostViewHolder extends RecyclerView.ViewHolder implements RTextView.MentionClickedListener, RTextView.HashTagClickedListener,View.OnLongClickListener,View.OnClickListener {

        PlayableItemsRecyclerView attachmentsRecyclerView;
        RTextView textView;
        RichLinkView richLinkView;
        ImageView profilePicture;
        boolean isMentionClicked = false;
        boolean isHashTagClicked = false;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            attachmentsRecyclerView = itemView.findViewById(R.id.forum_post_attachments_recyclerview);
            textView = itemView.findViewById(R.id.forum_post_recycler_item_textview);
            textView.setMentionClickedListener(this);
            textView.setHashTagClickedListener(this);
            textView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            richLinkView = itemView.findViewById(R.id.commentsrichlinkview);
            profilePicture = itemView.findViewById(R.id.forum_post_profile_picture_type_post);
            profilePicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    profilePictureClickedListener.onProfilePictureClicked(getAdapterPosition());
                }
            });


            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (isMentionClicked) {
                        isMentionClicked = false;
                    }
                    else if(isHashTagClicked){
                        isHashTagClicked = false;
                    }else {

                    }
                }
            });

        }

        @Override
        public void onMentionClicked(int clickedPosition) {
            pausePlayBackFromActivityOnPause();
            isMentionClicked = true;
            mentionClickedListener.onMentionClicked(clickedPosition);
        }

        @Override
        public void onHashTagClicked(int position) {
            pausePlayBackFromActivityOnPause();
            isHashTagClicked = true;
            hashTagClickedListener.onHashTagClicked(position);
        }

        @Override
        public boolean onLongClick(View v) {
            pausePlayBackFromActivityOnPause();
            postLongClickedListener.onPostLongClicked(getAdapterPosition());
            return true;
        }

        @Override
        public void onClick(View v) {

        }
    }

}

