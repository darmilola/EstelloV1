package com.estello.android.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arthurivanets.arvi.widget.PlayableItemsRecyclerView;
import com.deltastream.example.edittextcontroller.RTextView;
import com.estello.android.R;
import com.estello.android.ViewModel.CoursesModel;
import com.estello.android.ViewModel.ForumPostModel;
import com.estello.android.ViewModel.RichLinkView.RichLinkView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChannelMessagingCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ForumPostModel> forumPostModelArrayList;
    Context context;
    private static int typeDate = 0;
    private static int typePost = 1;


    ChannelPostAdapter.MentionClickedListener mentionClickedListener;
    ChannelPostAdapter.ProfilePictureClickedListener profilePictureClickedListener;
    RTextView.HashTagClickedListener hashTagClickedListener;
    ChannelPostAdapter.PostLongClickedListener postLongClickedListener;


    public interface HashTagClickListener{
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


    public ChannelMessagingCommentAdapter(ArrayList<ForumPostModel> forumPostModelArrayList, Context context, ChannelPostAdapter.MentionClickedListener mentionClickedListener, ChannelPostAdapter.ProfilePictureClickedListener profilePictureClickedListener, RTextView.HashTagClickedListener hashTagClickedListener, ChannelPostAdapter.PostLongClickedListener postLongClickedListener) {

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
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_message_comment_recycler_item, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return forumPostModelArrayList.size();
    }



    public class DateViewHolder extends RecyclerView.ViewHolder {


        TextView forumPostDate;

        public DateViewHolder(@NonNull View itemView) {

            super(itemView);
            forumPostDate = itemView.findViewById(R.id.forum_post_date);


        }
    }

    public class PostViewHolder extends RecyclerView.ViewHolder implements RTextView.MentionClickedListener, RTextView.HashTagClickedListener,View.OnLongClickListener {

        RecyclerView recentCommentsRecyclerView;
        PlayableItemsRecyclerView attachmentsRecyclerView;
        RTextView textView;
        RichLinkView richLinkView;
        ImageView profilePicture;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            attachmentsRecyclerView = itemView.findViewById(R.id.forum_post_attachments_recyclerview);
            recentCommentsRecyclerView = itemView.findViewById(R.id.forum_post_recent_comments_recylerview);
            textView = itemView.findViewById(R.id.forum_post_recycler_item_textview);
            textView.setMentionClickedListener(this);
            textView.setHashTagClickedListener(this);
            textView.setOnLongClickListener(this);
            itemView.setOnLongClickListener(this);
            richLinkView = itemView.findViewById(R.id.richlinkview);
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

