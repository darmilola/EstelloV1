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
import com.estello.android.ViewModel.ForumPostModel;

import com.estello.android.R;
import com.estello.android.ViewModel.RichLinkView.RichLinkView;
import com.estello.android.ViewModel.RichLinkView.ViewListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


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
    private static int typeSuggestion = 3;
    private static int typeInfo = 4;
    private MentionClickedListener mentionClickedListener;
    private ProfilePictureClickedListener profilePictureClickedListener;
    private hashTagClickedListener hashTagClickedListener;
    private PostLongClickedListener postLongClickedListener;
    private ItemClickedListener itemClickedListener;
    private boolean onGoingToFullscreen = false;
    private Queue<PostViewHolder> postViewHolderQueue = new LinkedList<>();
    private Queue<QuestionPostViewHolder> questionPostViewHolderQueue = new LinkedList<>();
    private Queue<SuggestionsPostViewHolder> suggestionsPostViewHolderQueue = new LinkedList<>();



    public interface hashTagClickedListener {
        public void onHashTagClicked(String hashTagId);
    }

    public interface ItemClickedListener{
        public void onItemClicked();
    }

    public interface MentionClickedListener{

        public void onMentionClicked(String mentionJson);
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
        else if(viewType == typeSuggestion){
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
              if(!postViewHolderQueue.contains(viewHolder)){
                  LinearLayoutManager LinearLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                  PostViewHolder postViewHolder = (PostViewHolder) viewHolder;
                  postViewHolder.attachmentsRecyclerView.setLayoutManager(LinearLayoutManager2);
                  Config config = new Config.Builder().cache(ExoPlayerUtils.getCache(context)).build();
                  ForumPostAttachmentsAdapter forumPostAttachmentsAdapter = new ForumPostAttachmentsAdapter(context, forumPostList.get(viewHolder.getAdapterPosition()).getPostAttachmentList(), config, new ForumPostAttachmentsAdapter.NewPlayerStarted() {
                      @Override
                      public void onNewPlayerStarted() {
                          for (PostViewHolder postViewHolder1:postViewHolderQueue) {
                              if(!postViewHolder1.attachmentsRecyclerView.isPlayBackPlaying() || viewHolder != postViewHolder1){
                                  postViewHolder1.attachmentsRecyclerView.stopPlayback();
                              }
                          }
                      }
                  }, new ForumPostAttachmentsAdapter.GoingToFullScreen() {
                      @Override
                      public void onGoingTofullscreen() {
                        onGoingToFullscreen = true;
                      }
                  });

                  postViewHolder.attachmentsRecyclerView.setAdapter(forumPostAttachmentsAdapter);
                  ((ChannelBaseActivity)postViewHolder.itemView.getContext()).setActivityPausedListener(ChannelPostAdapter.this::pausePlayBackFromActivityOnPause);
                  ((ChannelBaseActivity)postViewHolder.itemView.getContext()).setActivityDestroyedListener(ChannelPostAdapter.this::destroyPlayBackFromActivity);
                  ((ChannelBaseActivity)postViewHolder.itemView.getContext()).setActivityResumedListener(ChannelPostAdapter.this::resumePlayBackFromActivity);
                   postViewHolderQueue.add((PostViewHolder) viewHolder);
              }


          }


          if(viewHolder instanceof QuestionPostViewHolder){

              if(!questionPostViewHolderQueue.contains(viewHolder)){
                  LinearLayoutManager LinearLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                  QuestionPostViewHolder questionPostViewHolder = (QuestionPostViewHolder) viewHolder;
                  questionPostViewHolder.attachmentsRecyclerView.setLayoutManager(LinearLayoutManager2);
                  Config config = new Config.Builder().cache(ExoPlayerUtils.getCache(context)).build();
                  ForumPostAttachmentsAdapter forumPostAttachmentsAdapter = new ForumPostAttachmentsAdapter(context, forumPostList.get(viewHolder.getAdapterPosition()).getPostAttachmentList(), config, new ForumPostAttachmentsAdapter.NewPlayerStarted() {
                      @Override
                      public void onNewPlayerStarted() {
                          for (QuestionPostViewHolder questionPostViewHolder1:questionPostViewHolderQueue) {
                              if(!questionPostViewHolder1.attachmentsRecyclerView.isPlayBackPlaying() || viewHolder != questionPostViewHolder1){
                                  questionPostViewHolder1.attachmentsRecyclerView.stopPlayback();
                              }
                          }
                      }
                  }, new ForumPostAttachmentsAdapter.GoingToFullScreen() {
                      @Override
                      public void onGoingTofullscreen() {
                          onGoingToFullscreen = true;
                      }
                  });

                  questionPostViewHolder.attachmentsRecyclerView.setAdapter(forumPostAttachmentsAdapter);
                  ((ChannelBaseActivity)questionPostViewHolder.itemView.getContext()).setActivityPausedListener(ChannelPostAdapter.this::pausePlayBackFromActivityOnPause);
                  ((ChannelBaseActivity)questionPostViewHolder.itemView.getContext()).setActivityDestroyedListener(ChannelPostAdapter.this::destroyPlayBackFromActivity);
                  ((ChannelBaseActivity)questionPostViewHolder.itemView.getContext()).setActivityResumedListener(ChannelPostAdapter.this::resumePlayBackFromActivity);
                   questionPostViewHolderQueue.add((QuestionPostViewHolder) viewHolder);
              }

          }
          if(viewHolder instanceof SuggestionsPostViewHolder){

              if(!suggestionsPostViewHolderQueue.contains(viewHolder)){
                  LinearLayoutManager LinearLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                  SuggestionsPostViewHolder suggestionsPostViewHolder = (SuggestionsPostViewHolder) viewHolder;
                  suggestionsPostViewHolder .attachmentsRecyclerView.setLayoutManager(LinearLayoutManager2);
                  Config config = new Config.Builder().cache(ExoPlayerUtils.getCache(context)).build();
                  ForumPostAttachmentsAdapter forumPostAttachmentsAdapter = new ForumPostAttachmentsAdapter(context, forumPostList.get(viewHolder.getAdapterPosition()).getPostAttachmentList(), config, new ForumPostAttachmentsAdapter.NewPlayerStarted() {
                      @Override
                      public void onNewPlayerStarted() {
                          for (SuggestionsPostViewHolder suggestionsPostViewHolder1:suggestionsPostViewHolderQueue) {
                              if(!suggestionsPostViewHolder1.attachmentsRecyclerView.isPlayBackPlaying() || viewHolder != suggestionsPostViewHolder1){
                                  suggestionsPostViewHolder1.attachmentsRecyclerView.stopPlayback();
                              }
                          }
                      }
                  }, new ForumPostAttachmentsAdapter.GoingToFullScreen() {
                      @Override
                      public void onGoingTofullscreen() {
                          onGoingToFullscreen = true;
                      }
                  });

                  suggestionsPostViewHolder.attachmentsRecyclerView.setAdapter(forumPostAttachmentsAdapter);
                  ((ChannelBaseActivity)suggestionsPostViewHolder.itemView.getContext()).setActivityPausedListener(ChannelPostAdapter.this::pausePlayBackFromActivityOnPause);
                  ((ChannelBaseActivity)suggestionsPostViewHolder.itemView.getContext()).setActivityDestroyedListener(ChannelPostAdapter.this::destroyPlayBackFromActivity);
                  ((ChannelBaseActivity)suggestionsPostViewHolder.itemView.getContext()).setActivityResumedListener(ChannelPostAdapter.this::resumePlayBackFromActivity);
                  suggestionsPostViewHolderQueue.add((SuggestionsPostViewHolder) viewHolder);
              }
          }


      }

      @Override
      public void onViewRecycled(@NotNull RecyclerView.ViewHolder viewHolder){
        if(viewHolder instanceof PostViewHolder){
               boolean isRemoved = postViewHolderQueue.remove(viewHolder);
              ((PostViewHolder) viewHolder).attachmentsRecyclerView.stopPlayback();
        }
          if(viewHolder instanceof QuestionPostViewHolder){
              boolean isRemoved = questionPostViewHolderQueue.remove(viewHolder);
              ((QuestionPostViewHolder) viewHolder).attachmentsRecyclerView.stopPlayback();

          }
          if(viewHolder instanceof SuggestionsPostViewHolder){
              boolean isRemoved = suggestionsPostViewHolderQueue.remove(viewHolder);
              ((SuggestionsPostViewHolder)viewHolder).attachmentsRecyclerView.stopPlayback();

          }


      }

        @Override
        public void onViewDetachedFromWindow(@NotNull RecyclerView.ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
         if(viewHolder instanceof PostViewHolder){
               PostViewHolder  postViewHolder = postViewHolderQueue.peek();
               if(postViewHolder != null)postViewHolder.attachmentsRecyclerView.stopPlayback();
         }
         if(viewHolder instanceof QuestionPostViewHolder){
             QuestionPostViewHolder  questionPostViewHolder = questionPostViewHolderQueue.peek();
             if(questionPostViewHolder != null)questionPostViewHolder.attachmentsRecyclerView.stopPlayback();
        }
        if(viewHolder instanceof SuggestionsPostViewHolder){
             SuggestionsPostViewHolder  suggestionsPostViewHolder = suggestionsPostViewHolderQueue.peek();
             if(suggestionsPostViewHolder != null)suggestionsPostViewHolder.attachmentsRecyclerView.stopPlayback();
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (forumPostList.get(position).getType() == typePost) {
            PostViewHolder postViewHolder = (PostViewHolder) holder;
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            String text =  preferences.getString("2","");
            RTHtml rtHtml = new RTHtml(text);
            postViewHolder.textView.setText(rtHtml);
            if(text.equals(""))postViewHolder.textView.setVisibility(View.GONE);
            LinearLayoutManager recentCommentLinearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            postViewHolder.recentCommentsRecyclerView.setLayoutManager(recentCommentLinearLayoutManager);
            ForumPostRecentCommentAdapter forumPostRecentCommentAdapter = new ForumPostRecentCommentAdapter(context, forumPostList.get(position).getRecentReplyingUsersList());
            postViewHolder.recentCommentsRecyclerView.setAdapter(forumPostRecentCommentAdapter);
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
            if(text.equals(""))questionPostViewHolder.textView.setVisibility(View.GONE);
            LinearLayoutManager CommentsLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            questionPostViewHolder.recentCommentsRecyclerView.setLayoutManager(CommentsLayoutManager);
            ForumPostRecentCommentAdapter forumPostRecentCommentAdapter = new ForumPostRecentCommentAdapter(context, forumPostList.get(position).getRecentReplyingUsersList());
            questionPostViewHolder.recentCommentsRecyclerView.setAdapter(forumPostRecentCommentAdapter);

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
        if (forumPostList.get(position).getType() == typeSuggestion) {

            SuggestionsPostViewHolder suggestionsPostViewHolder = (SuggestionsPostViewHolder) holder;
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            String text = preferences.getString("2", "");
            RTHtml rtHtml = new RTHtml(text);
            suggestionsPostViewHolder.textView.setText(rtHtml);
            if(text.equals(""))suggestionsPostViewHolder.textView.setVisibility(View.GONE);
            LinearLayoutManager CommentLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            suggestionsPostViewHolder.recentCommentsRecyclerView.setLayoutManager(CommentLayoutManager);
            ForumPostRecentCommentAdapter forumPostRecentCommentAdapter = new ForumPostRecentCommentAdapter(context, forumPostList.get(position).getRecentReplyingUsersList());
            suggestionsPostViewHolder.recentCommentsRecyclerView.setAdapter(forumPostRecentCommentAdapter);
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
        else if(forumPostList.get(position).getType() == typeSuggestion){

            return typeSuggestion;
        }
        else if(forumPostList.get(position).getType() == typeInfo){

            return  typeInfo;
        }
        return typeInfo;
    }

    private void resumePlayBackFromActivity(){
        PostViewHolder PostViewHolderCache;
        QuestionPostViewHolder QuestionPostViewHolderCache;
        SuggestionsPostViewHolder SuggestionPostViewHolderCache;
        for (PostViewHolder postViewHolder: postViewHolderQueue) {
            PostViewHolderCache = postViewHolder;
            if (PostViewHolderCache != null && PostViewHolderCache.attachmentsRecyclerView.isPlayBackPlaying())
                PostViewHolderCache.attachmentsRecyclerView.onResume();
        }
        for (QuestionPostViewHolder questionPostViewHolder: questionPostViewHolderQueue) {
            QuestionPostViewHolderCache = questionPostViewHolder;
            if (QuestionPostViewHolderCache != null && QuestionPostViewHolderCache.attachmentsRecyclerView.isPlayBackPlaying())
                QuestionPostViewHolderCache.attachmentsRecyclerView.onResume();
        }
        for (SuggestionsPostViewHolder suggestionsPostViewHolder: suggestionsPostViewHolderQueue) {
            SuggestionPostViewHolderCache = suggestionsPostViewHolder;
            if (SuggestionPostViewHolderCache != null && SuggestionPostViewHolderCache.attachmentsRecyclerView.isPlayBackPlaying())
                SuggestionPostViewHolderCache.attachmentsRecyclerView.onResume();
        }
    }

    private void pausePlayBackFromActivityOnPause(){
        for (PostViewHolder videoCache: postViewHolderQueue) {
            if (onGoingToFullscreen) {
            } else {
                videoCache.attachmentsRecyclerView.onPause(true);
            }
        }
        for (QuestionPostViewHolder videoCache: questionPostViewHolderQueue){
            if (onGoingToFullscreen) {
            } else {
                videoCache.attachmentsRecyclerView.onPause(true);
            }
        }
        for (SuggestionsPostViewHolder videoCache: suggestionsPostViewHolderQueue){
            if (onGoingToFullscreen) {
            } else {
                videoCache.attachmentsRecyclerView.onPause(true);
            }
        }
        onGoingToFullscreen = false;
    }

    private void destroyPlayBackFromActivity(){
        for (PostViewHolder videoCache: postViewHolderQueue) {
            videoCache.attachmentsRecyclerView.onDestroy(true);
        }
        for (QuestionPostViewHolder videoCache: questionPostViewHolderQueue) {
            videoCache.attachmentsRecyclerView.onDestroy(true);
        }
        for (SuggestionsPostViewHolder videoCache: suggestionsPostViewHolderQueue) {
            videoCache.attachmentsRecyclerView.onDestroy(true);
        }
    }



    public class PostViewHolder extends RecyclerView.ViewHolder implements RTextView.MentionClickedListener, RTextView.HashTagClickedListener,View.OnLongClickListener,View.OnClickListener {

        RecyclerView recentCommentsRecyclerView;
        PlayableItemsRecyclerView attachmentsRecyclerView;
        RTextView textView;
        RichLinkView richLinkView;
        ImageView profilePicture;
        boolean isMentionClicked = false;
        boolean isHashTagClicked = false;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            attachmentsRecyclerView = itemView.findViewById(R.id.forum_post_attachments_recyclerview);
            recentCommentsRecyclerView = itemView.findViewById(R.id.forum_post_recent_comments_recylerview);
            textView = itemView.findViewById(R.id.forum_post_recycler_item_textview);
            textView.setMentionClickedListener(this);
            textView.setHashTagClickedListener(this);
            textView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            richLinkView = itemView.findViewById(R.id.richlinkview);
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
                        itemClickedListener.onItemClicked();
                    }
                }
            });

        }


        @Override
        public void onMentionClicked(String mentionJson) {
            pausePlayBackFromActivityOnPause();
            isMentionClicked = true;
            mentionClickedListener.onMentionClicked(mentionJson);
        }

        @Override
        public void onHashTagClicked(String hashTagId) {
            pausePlayBackFromActivityOnPause();
            isHashTagClicked = true;
            hashTagClickedListener.onHashTagClicked(hashTagId);
        }

        @Override
        public boolean onLongClick(View v) {
            pausePlayBackFromActivityOnPause();
            postLongClickedListener.onPostLongClicked(getAdapterPosition());
            return true;
        }

        @Override
        public void onClick(View v) {

            pausePlayBackFromActivityOnPause();
            itemClickedListener.onItemClicked();


        }
    }

    public class QuestionPostViewHolder extends RecyclerView.ViewHolder  implements RTextView.MentionClickedListener, RTextView.HashTagClickedListener,View.OnLongClickListener,View.OnClickListener  {

        RecyclerView recentCommentsRecyclerView;
        PlayableItemsRecyclerView attachmentsRecyclerView;
        RTextView textView;
        RichLinkView richLinkView;
        ImageView profilePicture;
        boolean isMentionClicked = false;
        boolean isHashTagClicked = false;

        public QuestionPostViewHolder(@NonNull View itemView) {
            super(itemView);
            attachmentsRecyclerView = itemView.findViewById(R.id.forum_post_attachments_recyclerview);
            recentCommentsRecyclerView = itemView.findViewById(R.id.forum_post_recent_comments_recylerview);
            textView = itemView.findViewById(R.id.forum_post_recycler_item_question_textview);
            richLinkView = itemView.findViewById(R.id.richlinkview);
            textView.setMentionClickedListener(this);
            textView.setHashTagClickedListener(this);
            textView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            profilePicture = itemView.findViewById(R.id.forum_post_profile_picture_type_question);
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
                        itemClickedListener.onItemClicked();
                    }
                }
            });

        }


        @Override
        public void onMentionClicked(String mentionJson) {
            pausePlayBackFromActivityOnPause();
            isMentionClicked = true;
            mentionClickedListener.onMentionClicked(mentionJson);
        }

        @Override
        public void onHashTagClicked(String hashTagId) {
            pausePlayBackFromActivityOnPause();
            isHashTagClicked = true;
            hashTagClickedListener.onHashTagClicked(hashTagId);
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



    public class SuggestionsPostViewHolder extends RecyclerView.ViewHolder  implements RTextView.MentionClickedListener, RTextView.HashTagClickedListener,View.OnLongClickListener,View.OnClickListener  {

        RecyclerView recentCommentsRecyclerView;
        PlayableItemsRecyclerView attachmentsRecyclerView;
        RTextView textView;
        RichLinkView richLinkView;
        ImageView profilePicture;
        boolean isMentionClicked = false;
        boolean isHashTagClicked = false;

        SuggestionsPostViewHolder(@NonNull View itemView) {
            super(itemView);
            attachmentsRecyclerView = itemView.findViewById(R.id.forum_post_attachments_recyclerview);
            recentCommentsRecyclerView = itemView.findViewById(R.id.forum_post_recent_comments_recylerview);
            textView = itemView.findViewById(R.id.forum_post_recycler_item_suggestion_textview);
            richLinkView = itemView.findViewById(R.id.richlinkview);
            textView.setMentionClickedListener(this);
            textView.setHashTagClickedListener(this);
            textView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            profilePicture = itemView.findViewById(R.id.forum_post_profile_picture_type_suggestion);
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
                        itemClickedListener.onItemClicked();
                    }
                }
            });

        }
        @Override
        public void onMentionClicked(String mentionJson) {
            pausePlayBackFromActivityOnPause();
            isMentionClicked = true;
            mentionClickedListener.onMentionClicked(mentionJson);
        }
        @Override
        public void onHashTagClicked(String hashTagId) {
            pausePlayBackFromActivityOnPause();
            isHashTagClicked = true;
            hashTagClickedListener.onHashTagClicked(hashTagId);
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
    public class DateViewHolder extends RecyclerView.ViewHolder {


        TextView forumPostDate;

        public DateViewHolder(@NonNull View itemView) {

            super(itemView);
            forumPostDate = itemView.findViewById(R.id.forum_post_date);
        }
    }
    public class InfoViewHolder extends RecyclerView.ViewHolder {
        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}


