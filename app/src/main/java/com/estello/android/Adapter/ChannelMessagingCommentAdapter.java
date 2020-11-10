package com.estello.android.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arthurivanets.arvi.Config;
import com.arthurivanets.arvi.util.misc.ExoPlayerUtils;
import com.arthurivanets.arvi.widget.PlayableItemsContainer;
import com.arthurivanets.arvi.widget.PlayableItemsRecyclerView;
import com.deltastream.example.edittextcontroller.RTextView;
import com.deltastream.example.edittextcontroller.api.format.RTHtml;
import com.estello.android.R;
import com.estello.android.ViewModel.ForumPostModel;
import com.estello.android.ViewModel.RichLinkView.RichLinkView;
import com.estello.android.ViewModel.RichLinkView.ViewListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChannelMessagingCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ForumPostModel> forumPostModelArrayList;
    Context context;
    private static int typeDate = 0;
    private static int typePost = 1;
    private PostViewHolder postViewHolder;


   MentionClickedListener mentionClickedListener;
   ProfilePictureClickedListener profilePictureClickedListener;
   hashTagClickListener hashTagClickedListener;
   PostLongClickedListener postLongClickedListener;


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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {



        if (forumPostModelArrayList.get(position).getType() == typePost) {

            postViewHolder = (PostViewHolder) holder;

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            String text =  preferences.getString("2","");
            RTHtml rtHtml = new RTHtml(text);
            postViewHolder.textView.setText(rtHtml);

            LinearLayoutManager LinearLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            postViewHolder.attachmentsRecyclerView.setLayoutManager(LinearLayoutManager2);
            postViewHolder.attachmentsRecyclerView.setPlaybackTriggeringStates(PlayableItemsContainer.PlaybackTriggeringState.SETTLING, PlayableItemsContainer.PlaybackTriggeringState.DRAGGING);
            postViewHolder.attachmentsRecyclerView.setAutoplayEnabled(false);
            postViewHolder.attachmentsRecyclerView.setAutoplayMode(PlayableItemsContainer.AutoplayMode.ONE_AT_A_TIME);
            Config config = new Config.Builder().cache(ExoPlayerUtils.getCache(context)).build();
            ForumPostAttachmentsAdapter forumPostAttachmentsAdapter = new ForumPostAttachmentsAdapter(context, forumPostModelArrayList.get(position).getPostAttachmentList(), config);
            postViewHolder.attachmentsRecyclerView.setAdapter(forumPostAttachmentsAdapter);
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

    public class PostViewHolder extends RecyclerView.ViewHolder implements RTextView.MentionClickedListener, RTextView.HashTagClickedListener,View.OnLongClickListener {


        PlayableItemsRecyclerView attachmentsRecyclerView;
        RTextView textView;
        RichLinkView richLinkView;
        ImageView profilePicture;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            attachmentsRecyclerView = itemView.findViewById(R.id.forum_post_attachments_recyclerview);
            textView = itemView.findViewById(R.id.forum_post_recycler_item_textview);
            textView.setMentionClickedListener(this);
            textView.setHashTagClickedListener(this);
            textView.setOnLongClickListener(this);
            itemView.setOnLongClickListener(this);
            richLinkView = itemView.findViewById(R.id.Commentsrichlinkview);
            profilePicture = itemView.findViewById(R.id.forum_post_profile_picture_type_post);
            profilePicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    profilePictureClickedListener.onProfilePictureClicked(getAdapterPosition());
                }
            });



        }

        @Override
        public void onMentionClicked(int clickedPosition) {

            mentionClickedListener.onMentionClicked(clickedPosition);
        }

        @Override
        public void onHashTagClicked(int position) {

            hashTagClickedListener.onHashTagClicked(position);
        }


        @Override
        public boolean onLongClick(View v) {

            postLongClickedListener.onPostLongClicked(getAdapterPosition());
            return false;
        }
    }
}

