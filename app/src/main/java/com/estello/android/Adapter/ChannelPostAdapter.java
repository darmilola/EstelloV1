package com.estello.android.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
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
import com.estello.android.ViewModel.ForumPostModel;

import com.estello.android.R;
import com.estello.android.ViewModel.RichLinkView.RichLinkView;
import com.estello.android.ViewModel.RichLinkView.ViewListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;


public class ChannelPostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Context context;
    private ArrayList<ForumPostModel> forumPostList;
    private static int typeDate = 0;
    private static int typePost = 1;
    private static int typeQuestion = 2;
    private static int typeIdea = 3;
    private static int typeInfo = 4;
    private PostViewHolder mPostViewHolder;
    private QuestionPostViewHolder mQuestionPostViewHolder;
    private SuggestionsPostViewHolder mSuggestionsPostViewHolder;
    MentionClickedListener mentionClickedListener;
    ProfilePictureClickedListener profilePictureClickedListener;
    hashTagClickedListener hashTagClickedListener;
    PostLongClickedListener postLongClickedListener;
    ItemClickedListener itemClickedListener;
    List<PostViewHolder> postViewHolderList = new ArrayList<>();
    List<PostViewHolder> attachedViewHolderList = new ArrayList<>();


    public interface hashTagClickedListener {
        public void onHashTagClicked(int position);
    }

    public interface ItemClickedListener{
        public void onItemClicked();
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



    public ChannelPostAdapter(Context context, ArrayList<ForumPostModel> forumPostList, MentionClickedListener mentionClickedListener, ProfilePictureClickedListener profilePictureClickedListener, ChannelPostAdapter.hashTagClickedListener hashTagClickedListener, PostLongClickedListener postLongClickedListener, ItemClickedListener itemClickedListener) {

        this.context = context;
        this.forumPostList = forumPostList;
        this.mentionClickedListener = mentionClickedListener;
        this.profilePictureClickedListener = profilePictureClickedListener;
        this.hashTagClickedListener = hashTagClickedListener;
        this.postLongClickedListener = postLongClickedListener;
        this.itemClickedListener = itemClickedListener;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == typeDate) {

            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_post_date_item, parent, false);
            return new DateViewHolder(view2);
        } else if (viewType == typePost) {
            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_post_recyler_item, parent, false);
            return new PostViewHolder(view2);
        }
        else if(viewType == typeQuestion){

            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_post_recycler_item_type_question, parent, false);
            return new QuestionPostViewHolder(view2);
        }
        else if(viewType == typeIdea){

            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_post_recycler_item_type_suggestion, parent, false);
            return new SuggestionsPostViewHolder(view2);
        }
        else if(viewType == typeInfo){

            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_post_item_channel_info, parent, false);
            return new InfoViewHolder(view2);
        }
        return null;

    }


      @Override
    public void onViewAttachedToWindow(@NotNull RecyclerView.ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
          if(viewHolder instanceof PostViewHolder){

              postViewHolderList.add((PostViewHolder) viewHolder);
              attachedViewHolderList.add((PostViewHolder) viewHolder);
          }
          if(viewHolder instanceof QuestionPostViewHolder){
              //((QuestionPostViewHolder)viewHolder).attachmentsRecyclerView.startPlayback();
          }
          if(viewHolder instanceof SuggestionsPostViewHolder){
              //((SuggestionsPostViewHolder)viewHolder).attachmentsRecyclerView.startPlayback();
          }
              Log.e("attached", "onViewAttachedToWindow: "+viewHolder.getAdapterPosition());

      }

    @Override
    public void onViewDetachedFromWindow(@NotNull RecyclerView.ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        if(viewHolder instanceof PostViewHolder){
            if(viewHolder.getAdapterPosition() < postViewHolderList.size())postViewHolderList.remove(viewHolder.getAdapterPosition()-1);
            //((PostViewHolder)viewHolder).attachmentsRecyclerView.stopPlayback();
        }
        if(viewHolder instanceof QuestionPostViewHolder){
            //((QuestionPostViewHolder)viewHolder).attachmentsRecyclerView.stopPlayback();
        }
        if(viewHolder instanceof SuggestionsPostViewHolder){
            //((SuggestionsPostViewHolder)viewHolder).attachmentsRecyclerView.stopPlayback();
        }

    }


    @Override
    public void onViewRecycled(@NotNull RecyclerView.ViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
        Log.e("are you reycling", "onViewRecycled: ");
        Log.e("detached", "onViewDetachedToWindow: ");
        if(viewHolder instanceof PostViewHolder){
            //((PostViewHolder)viewHolder).attachmentsRecyclerView.onDestroy();
        }
        if(viewHolder instanceof QuestionPostViewHolder){
            //((QuestionPostViewHolder)viewHolder).attachmentsRecyclerView.stopPlayback();
        }
        if(viewHolder instanceof SuggestionsPostViewHolder){
            //((SuggestionsPostViewHolder)viewHolder).attachmentsRecyclerView.stopPlayback();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Log.e("binding", "onBindViewHolder: ");

        if (forumPostList.get(position).getType() == typePost) {


            mPostViewHolder = ((PostViewHolder)holder);
            PostViewHolder postViewHolder = (PostViewHolder) holder;
            //postViewHolderList.add(postViewHolder);
            postViewHolder.setIsRecyclable(false);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            String text =  preferences.getString("2","");
            RTHtml rtHtml = new RTHtml(text);
            postViewHolder.textView.setText(rtHtml);
            postViewHolder.textView.setVisibility(View.GONE);
            LinearLayoutManager uroraLinearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            LinearLayoutManager LinearLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            //LinearLayoutManager2.setInitialPrefetchItemCount(forumPostList.get(position).getPostAttachmentList().size());
            postViewHolder.recentCommentsRecyclerView.setLayoutManager(uroraLinearLayoutManager);
            postViewHolder.attachmentsRecyclerView.setLayoutManager(LinearLayoutManager2);
            postViewHolder.attachmentsRecyclerView.setPlaybackTriggeringStates(PlayableItemsContainer.PlaybackTriggeringState.SETTLING, PlayableItemsContainer.PlaybackTriggeringState.DRAGGING);
            postViewHolder.attachmentsRecyclerView.setAutoplayEnabled(false);
            ForumPostRecentCommentAdapter forumPostRecentCommentAdapter = new ForumPostRecentCommentAdapter(context, forumPostList.get(position).getRecentReplyingUsersList());

            Config config = new Config.Builder().cache(ExoPlayerUtils.getCache(context)).build();
            ForumPostAttachmentsAdapter forumPostAttachmentsAdapter = new ForumPostAttachmentsAdapter(context, forumPostList.get(position).getPostAttachmentList(), config,position);
            postViewHolder.attachmentsRecyclerView.setAdapter(forumPostAttachmentsAdapter);
            postViewHolder.recentCommentsRecyclerView.setAdapter(forumPostRecentCommentAdapter);
            postViewHolder.attachmentsRecyclerView.onResume();

            if(postViewHolder.richLinkView != null)
            postViewHolder.richLinkView.setLink("https://medium.com/@allisonmorgan/short-essay-on-web-crawling-scraping-8abf1b232b65", new ViewListener() {

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

                        //mpostViewHolder.richLinkView.setVisibility(View.GONE);
                    }
                    }
            });




        } else if (forumPostList.get(position).getType() == typeDate) {

            DateViewHolder dateViewHolder = (DateViewHolder) holder;
            dateViewHolder.forumPostDate.setText(forumPostList.get(position).getPostGroupDate());
        }





        if (forumPostList.get(position).getType() == typeQuestion) {

            QuestionPostViewHolder questionPostViewHolder = (QuestionPostViewHolder) holder;


            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

            String text =  preferences.getString("2","");
            RTHtml rtHtml = new RTHtml(text);
            questionPostViewHolder.textView.setText(rtHtml);
            questionPostViewHolder.textView.setVisibility(View.GONE);
            LinearLayoutManager uroraLinearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            LinearLayoutManager LinearLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            //LinearLayoutManager2.setInitialPrefetchItemCount(forumPostList.get(position).getPostAttachmentList().size());
            questionPostViewHolder.recentCommentsRecyclerView.setLayoutManager(uroraLinearLayoutManager);
            questionPostViewHolder.attachmentsRecyclerView.setLayoutManager(LinearLayoutManager2);
            questionPostViewHolder.attachmentsRecyclerView.setPlaybackTriggeringStates(PlayableItemsContainer.PlaybackTriggeringState.SETTLING, PlayableItemsContainer.PlaybackTriggeringState.DRAGGING);
            questionPostViewHolder.attachmentsRecyclerView.setAutoplayEnabled(true);
            questionPostViewHolder.attachmentsRecyclerView.setAutoplayMode(PlayableItemsContainer.AutoplayMode.ONE_AT_A_TIME);
            ForumPostRecentCommentAdapter forumPostRecentCommentAdapter = new ForumPostRecentCommentAdapter(context, forumPostList.get(position).getRecentReplyingUsersList());

            Config config = new Config.Builder().cache(ExoPlayerUtils.getCache(context)).build();
            ForumPostAttachmentsAdapter forumPostAttachmentsAdapter = new ForumPostAttachmentsAdapter(context, forumPostList.get(position).getPostAttachmentList(), config,position);

            questionPostViewHolder.attachmentsRecyclerView.setAdapter(forumPostAttachmentsAdapter);
            questionPostViewHolder.recentCommentsRecyclerView.setAdapter(forumPostRecentCommentAdapter);
            questionPostViewHolder.attachmentsRecyclerView.onResume();

            if(questionPostViewHolder.richLinkView != null)
            questionPostViewHolder.richLinkView.setLink("https://www.github.com/darmilola", new ViewListener() {

                @Override
                public void onSuccess(boolean status) {





                    try {
                        if(questionPostViewHolder.richLinkView != null){

                            questionPostViewHolder.richLinkView.setLinkFromMeta(questionPostViewHolder.richLinkView.getMetaData());
                        }

                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Exception e) {

                    if (questionPostViewHolder.richLinkView != null) {

                        //questionPostViewHolder.richLinkView.setVisibility(View.GONE);
                    }
                }
            });

        }
        if (forumPostList.get(position).getType() == typeIdea) {

            SuggestionsPostViewHolder suggestionsPostViewHolder = (SuggestionsPostViewHolder) holder;

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            String text = preferences.getString("2", "");
            RTHtml rtHtml = new RTHtml(text);
            suggestionsPostViewHolder.textView.setText(rtHtml);
            suggestionsPostViewHolder.textView.setVisibility(View.GONE);
            LinearLayoutManager uroraLinearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            LinearLayoutManager LinearLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            //LinearLayoutManager2.setInitialPrefetchItemCount(forumPostList.get(position).getPostAttachmentList().size());
            suggestionsPostViewHolder.recentCommentsRecyclerView.setLayoutManager(uroraLinearLayoutManager);
            suggestionsPostViewHolder.attachmentsRecyclerView.setLayoutManager(LinearLayoutManager2);
            suggestionsPostViewHolder.attachmentsRecyclerView.setPlaybackTriggeringStates(PlayableItemsContainer.PlaybackTriggeringState.SETTLING, PlayableItemsContainer.PlaybackTriggeringState.DRAGGING);
            suggestionsPostViewHolder.attachmentsRecyclerView.setAutoplayEnabled(true);
            suggestionsPostViewHolder.attachmentsRecyclerView.setAutoplayMode(PlayableItemsContainer.AutoplayMode.ONE_AT_A_TIME);
            ForumPostRecentCommentAdapter forumPostRecentCommentAdapter = new ForumPostRecentCommentAdapter(context, forumPostList.get(position).getRecentReplyingUsersList());

            Config config = new Config.Builder().cache(ExoPlayerUtils.getCache(context)).build();
            ForumPostAttachmentsAdapter forumPostAttachmentsAdapter = new ForumPostAttachmentsAdapter(context, forumPostList.get(position).getPostAttachmentList(), config,position);

            suggestionsPostViewHolder.attachmentsRecyclerView.setAdapter(forumPostAttachmentsAdapter);
            suggestionsPostViewHolder.recentCommentsRecyclerView.setAdapter(forumPostRecentCommentAdapter);
            suggestionsPostViewHolder.attachmentsRecyclerView.onResume();

            if(suggestionsPostViewHolder.richLinkView != null)
            suggestionsPostViewHolder.richLinkView.setLink("https://www.linkedin.com/posts/louisberyl_thestartupstack-dadjokes-activity-6727661737484652544-5ybg", new ViewListener() {

                @Override
                public void onSuccess(boolean status) {


                    try {
                 if(suggestionsPostViewHolder.richLinkView != null) suggestionsPostViewHolder.richLinkView.setLinkFromMeta(suggestionsPostViewHolder.richLinkView.getMetaData());
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Exception e) {

                    if (suggestionsPostViewHolder.richLinkView != null) {

                        //suggestionsPostViewHolder.richLinkView.setVisibility(View.GONE);
                    }
                }
            });


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
        else if(forumPostList.get(position).getType() == typeIdea){

            return  typeIdea;
        }
        else if(forumPostList.get(position).getType() == typeInfo){

            return  typeInfo;
        }
        return typePost;
    }

    public void pausePlayBack(){

        if(mQuestionPostViewHolder != null){
           // mQuestionPostViewHolder.attachmentsRecyclerView.onPause();
        }
        if(mPostViewHolder != null){

       //     mPostViewHolder.attachmentsRecyclerView.onPause();

        }
        if(mSuggestionsPostViewHolder != null){
       //     mSuggestionsPostViewHolder.attachmentsRecyclerView.onPause();
        }
    }

    public void resumePlayBack(){

        if(mQuestionPostViewHolder != null){

         //   mQuestionPostViewHolder.attachmentsRecyclerView.onResume();
        }
        if(mPostViewHolder != null){

         //   mPostViewHolder.attachmentsRecyclerView.onResume();
        }
        if(mSuggestionsPostViewHolder != null){

           // mSuggestionsPostViewHolder.attachmentsRecyclerView.onResume();
        }
    }

    public void destroyPlayer(){

        if(mQuestionPostViewHolder != null){

            mQuestionPostViewHolder.attachmentsRecyclerView.onDestroy();
        }
        if(mPostViewHolder != null){

            mPostViewHolder.attachmentsRecyclerView.onDestroy();
        }
        if(mSuggestionsPostViewHolder != null){

            mSuggestionsPostViewHolder.attachmentsRecyclerView.onDestroy();
        }
    }

    private void pausePlayBackFromActivityOnPause(){
        for(int i = 0; i < postViewHolderList.size(); i++) {
            PostViewHolder postViewHolder = postViewHolderList.get(i);
            postViewHolder.attachmentsRecyclerView.onPause(true);
        }
    }
    private void destroyPlayBackFromActivity(){

        for(int i = 0; i < attachedViewHolderList.size(); i++) {
            PostViewHolder postViewHolder = attachedViewHolderList.get(i);
            postViewHolder.attachmentsRecyclerView.onDestroy();
        }
    }

    public class PostViewHolder extends RecyclerView.ViewHolder implements RTextView.MentionClickedListener, RTextView.HashTagClickedListener,View.OnLongClickListener,View.OnClickListener {

        RecyclerView recentCommentsRecyclerView;
        PlayableItemsRecyclerView attachmentsRecyclerView;
        RTextView textView;
        RichLinkView richLinkView;
        ImageView profilePicture;
        boolean isMentionClicked = false;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            setIsRecyclable(false);
            attachmentsRecyclerView = itemView.findViewById(R.id.forum_post_attachments_recyclerview);
            recentCommentsRecyclerView = itemView.findViewById(R.id.forum_post_recent_comments_recylerview);
            textView = itemView.findViewById(R.id.forum_post_recycler_item_textview);
            textView.setMentionClickedListener(this);
            textView.setHashTagClickedListener(this);
            textView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            richLinkView = itemView.findViewById(R.id.richlinkview);
            //attachmentsRecyclerView.pausePlayback(true);
            ((ChannelBaseActivity)itemView.getContext()).setActivityPausedListener(ChannelPostAdapter.this::pausePlayBackFromActivityOnPause);
            ((ChannelBaseActivity)itemView.getContext()).setActivityDestroyedListener(ChannelPostAdapter.this::destroyPlayBackFromActivity);
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
                    } else {
                        itemClickedListener.onItemClicked();
                    }
                }
            });

        }


        @Override
        public void onMentionClicked(int clickedPosition) {
            isMentionClicked = true;
            mentionClickedListener.onMentionClicked(clickedPosition);

        }

        @Override
        public void onHashTagClicked(int position) {

            hashTagClickedListener.onHashTagClicked(position);
        }

        @Override
        public boolean onLongClick(View v) {

            postLongClickedListener.onPostLongClicked(getAdapterPosition());
            return true;
        }

        @Override
        public void onClick(View v) {

            pausePlayBackFromActivityOnPause();
            itemClickedListener.onItemClicked();


        }
    }

    public class QuestionPostViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recentCommentsRecyclerView;
        PlayableItemsRecyclerView attachmentsRecyclerView;
        RTextView textView;
        RichLinkView richLinkView;

        public QuestionPostViewHolder(@NonNull View itemView) {
            super(itemView);

            attachmentsRecyclerView = itemView.findViewById(R.id.forum_post_attachments_recyclerview);
            recentCommentsRecyclerView = itemView.findViewById(R.id.forum_post_recent_comments_recylerview);
            textView = itemView.findViewById(R.id.forum_post_recycler_item_question_textview);
            richLinkView = itemView.findViewById(R.id.richlinkview);
           // richLinkView.setVisibility(View.GONE);



        }

    }

    public class SuggestionsPostViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recentCommentsRecyclerView;
        PlayableItemsRecyclerView attachmentsRecyclerView;
        RTextView textView;
        RichLinkView richLinkView;

        public SuggestionsPostViewHolder(@NonNull View itemView) {
            super(itemView);

            attachmentsRecyclerView = itemView.findViewById(R.id.forum_post_attachments_recyclerview);
            recentCommentsRecyclerView = itemView.findViewById(R.id.forum_post_recent_comments_recylerview);
            textView = itemView.findViewById(R.id.forum_post_recycler_item_suggestion_textview);

            richLinkView = itemView.findViewById(R.id.richlinkview);



        }

    }
    public class DateViewHolder extends RecyclerView.ViewHolder {


        TextView forumPostDate;

        public DateViewHolder(@NonNull View itemView) {

            super(itemView);
            forumPostDate = itemView.findViewById(R.id.forum_post_date);
        }
    }
    public class InfoViewHolder extends RecyclerView.ViewHolder {


       // TextView forumPostDate;

        public InfoViewHolder(@NonNull View itemView) {

            super(itemView);
            //forumPostDate = itemView.findViewById(R.id.forum_post_date);
        }
    }
}


