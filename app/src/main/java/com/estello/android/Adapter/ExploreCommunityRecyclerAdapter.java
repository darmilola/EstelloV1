package com.estello.android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.deltastream.example.edittextcontroller.RTextView;
import com.estello.android.Arvi.Config;
import com.estello.android.Arvi.util.misc.ExoPlayerUtils;
import com.estello.android.Arvi.widget.PlayableItemsRecyclerView;
import com.estello.android.ChannelPostDetails;
import com.estello.android.ExploreCommunityTypeOneBucketDetails;
import com.estello.android.ExploreCommunityTypeThreeBucketDetails;
import com.estello.android.ExploreCommunityTypeTwoBucketDetails;
import com.estello.android.MainActivity;
import com.estello.android.R;
import com.estello.android.ViewModel.AutoScrollLinearLayoutManager;
import com.estello.android.ViewModel.CommunityViewMetadata;
import com.estello.android.ViewModel.ForumPostModel;
import com.estello.android.ViewModel.RecyclerViewPagerIndicator;
import com.estello.android.ViewModel.RichLinkView.RichLinkView;
import com.rd.utils.DensityUtils;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ExploreCommunityRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Map<CommunityViewMetadata,List<Object>>> communityViewList;
    private static int typeChannel = 0;
    private static int typeHashtags = 1;
    private static int typeVideos = 2;
    private static int typeFeaturedPost = 3;
    private static int typeBillboard = 4;
    private static int typeOneAd = 5;
    private static int typeTwoAd = 6;
    Context context;
    private Queue<ExploreFeaturedPostViewHolder> exploreFeaturedPostViewHolderQueue = new LinkedList<>();
    boolean onGoingToFullscreen  = false;
    private Timer billboardTimer;

    public ExploreCommunityRecyclerAdapter(List<Map<CommunityViewMetadata,List<Object>>> communityViewList,Context context){
           this.communityViewList = communityViewList;
           this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == typeChannel) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_community_recyclerview_type1_bucket, parent, false);
            return new ExploreChannelViewHolder(view);
        }
        else if (viewType == typeHashtags) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_community_recyclerview_type2_bucket, parent, false);
            return new ExploreHashtagsViewHolder(view);
        }
        else if (viewType == typeVideos) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_community_recyclerview_type3_bucket, parent, false);
            return new ExploreVideosViewHolder(view);
        }
        else if (viewType == typeFeaturedPost) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_community_recyler_type4_item, parent, false);
            return new ExploreFeaturedPostViewHolder(view);
        }
        else if (viewType == typeBillboard) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_community_recyclerview_type5_bucket_billboard, parent, false);
            return new ExploreBillboardViewholder(view);
        }
        else if (viewType == typeOneAd) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_community_recyclerview_type6_item, parent, false);
            return new ExploreType1Ads(view);
        }
        else if (viewType == typeTwoAd) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_community_recyclerview_type6_item_type2, parent, false);
            return new ExploreType2Ads(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

         if(holder instanceof ExploreBillboardViewholder){



         }
    }



    @Override
    public void onViewAttachedToWindow(@NotNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        Log.e("attached ", "onViewAttachedToWindow: ");
       int position = holder.getAdapterPosition();
       if(holder instanceof ExploreChannelViewHolder){

            Map<CommunityViewMetadata,List<Object>> channelMap = communityViewList.get(position);
            Set<CommunityViewMetadata> communityViewMetadataHashSet = channelMap.keySet();
            List<Object>channelsModelListObj = null;
            for (CommunityViewMetadata communityViewMetadata: communityViewMetadataHashSet) {
                channelsModelListObj = channelMap.get(communityViewMetadata);
            }
            ExploreChannelViewHolder channelViewHolder = (ExploreChannelViewHolder) holder;
            GridLayoutManager layoutManager = new GridLayoutManager(context,3);
            channelViewHolder.recyclerView.setLayoutManager(layoutManager);
            CommunityViewChannelsAdapter adapter = new CommunityViewChannelsAdapter(channelsModelListObj,context);
            channelViewHolder.recyclerView.setAdapter(adapter);


        }
        else if(holder instanceof ExploreHashtagsViewHolder){

            Map<CommunityViewMetadata,List<Object>> hashtagsMap = communityViewList.get(position);
            Set<CommunityViewMetadata> communityViewMetadataHashSet = hashtagsMap.keySet();
            List<Object> hashtagsModelListObj = null;
            for (CommunityViewMetadata communityViewMetadata: communityViewMetadataHashSet) {
                hashtagsModelListObj = hashtagsMap.get(communityViewMetadata);
            }
            ExploreHashtagsViewHolder hashtagsViewHolder = (ExploreHashtagsViewHolder) holder;
            GridLayoutManager layoutManager = new GridLayoutManager(context,3);
            hashtagsViewHolder.recyclerView.setLayoutManager(layoutManager);
            CommunityViewHashtagsAdapter adapter = new CommunityViewHashtagsAdapter(hashtagsModelListObj,context);
            hashtagsViewHolder.recyclerView.setAdapter(adapter);
        }

        else if(holder instanceof ExploreVideosViewHolder){

            Map<CommunityViewMetadata,List<Object>> videosMap = communityViewList.get(position);
            Set<CommunityViewMetadata> communityViewMetadataHashSet = videosMap.keySet();
            List<Object> videosModelListObj = null;
            for (CommunityViewMetadata communityViewMetadata: communityViewMetadataHashSet) {
                videosModelListObj = videosMap.get(communityViewMetadata);
            }
            ExploreVideosViewHolder videosViewHolder = (ExploreVideosViewHolder) holder;
            GridLayoutManager layoutManager = new GridLayoutManager(context,2);
            videosViewHolder.recyclerView.setLayoutManager(layoutManager);
            CommunityViewVideosAdapter adapter = new CommunityViewVideosAdapter(videosModelListObj,context);
            videosViewHolder.recyclerView.setAdapter(adapter);

        }

       else if(holder instanceof ExploreFeaturedPostViewHolder){

           Map<CommunityViewMetadata,List<Object>> featuredMap = communityViewList.get(position);
           Set<CommunityViewMetadata> communityViewMetadataHashSet = featuredMap.keySet();
           List<Object> featuredModelListObj = null;
           for (CommunityViewMetadata communityViewMetadata: communityViewMetadataHashSet) {
               featuredModelListObj = featuredMap.get(communityViewMetadata);
           }
           ExploreFeaturedPostViewHolder featuredPostViewHolder = (ExploreFeaturedPostViewHolder) holder;
           ForumPostModel forumPostModel = (ForumPostModel) featuredModelListObj.get(0);
           LinearLayoutManager LinearLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
           featuredPostViewHolder.attachmentsRecyclerView.setLayoutManager(LinearLayoutManager2);
           Config config = new Config.Builder().cache(ExoPlayerUtils.getCache(context)).build();
           ForumPostAttachmentsAdapter forumPostAttachmentsAdapter = new ForumPostAttachmentsAdapter(context, forumPostModel.getPostAttachmentList(), config, new ForumPostAttachmentsAdapter.NewPlayerStarted() {
               @Override
               public void onNewPlayerStarted() {
                   for (ExploreFeaturedPostViewHolder mexploreFeaturedPostViewHolder:exploreFeaturedPostViewHolderQueue) {
                       if(!mexploreFeaturedPostViewHolder.attachmentsRecyclerView.isPlayBackPlaying() || mexploreFeaturedPostViewHolder != featuredPostViewHolder){
                           mexploreFeaturedPostViewHolder.attachmentsRecyclerView.stopPlayback();
                       }
                   }
               }
           }, new ForumPostAttachmentsAdapter.GoingToFullScreen() {
               @Override
               public void onGoingTofullscreen() {
                   onGoingToFullscreen = true;
               }
           });

           featuredPostViewHolder.attachmentsRecyclerView.setAdapter(forumPostAttachmentsAdapter);
           ((MainActivity)context).setActivityPausedListener(ExploreCommunityRecyclerAdapter.this::pausePlayBackFromActivityOnPause);
           ((MainActivity)context).setActivityDestroyedListener(ExploreCommunityRecyclerAdapter.this::destroyPlayBackFromActivity);
           ((MainActivity)context).setActivityResumedListener(ExploreCommunityRecyclerAdapter.this::resumePlayBackFromActivity);
           exploreFeaturedPostViewHolderQueue.add(featuredPostViewHolder);
       }

    }


    @Override
    public void onViewRecycled(@NotNull RecyclerView.ViewHolder viewHolder) {
           super.onViewRecycled(viewHolder);
        if (viewHolder instanceof ExploreFeaturedPostViewHolder) {
            ExploreFeaturedPostViewHolder exploreFeaturedPostViewHolder = (ExploreFeaturedPostViewHolder) viewHolder;
            exploreFeaturedPostViewHolderQueue.remove(exploreFeaturedPostViewHolder);
            exploreFeaturedPostViewHolder.attachmentsRecyclerView.stopPlayback();
        }
    }


    @Override
    public void onViewDetachedFromWindow(@NotNull RecyclerView.ViewHolder viewHolder){
           super.onViewDetachedFromWindow(viewHolder);
            Log.e("explore detach", "onViewDetachedFromWindow: ");
            if (viewHolder instanceof ExploreFeaturedPostViewHolder) {
                Log.e("explore 1 detach", "onViewDetachedFromWindow: ");
                ExploreFeaturedPostViewHolder exploreFeaturedPostViewHolder = (ExploreFeaturedPostViewHolder) viewHolder;
                exploreFeaturedPostViewHolderQueue.remove(exploreFeaturedPostViewHolder);
                exploreFeaturedPostViewHolder.attachmentsRecyclerView.stopPlayback();
            }
        }


    private void resumePlayBackFromActivity(){
        ExploreFeaturedPostViewHolder exploreFeaturedPostViewHolderCache;
        for (ExploreFeaturedPostViewHolder exploreFeaturedPostViewHolder:exploreFeaturedPostViewHolderQueue) {
            exploreFeaturedPostViewHolderCache = exploreFeaturedPostViewHolder;
            if (exploreFeaturedPostViewHolderCache != null && exploreFeaturedPostViewHolderCache.attachmentsRecyclerView.isPlayBackPlaying())
                exploreFeaturedPostViewHolderCache.attachmentsRecyclerView.onResume();
        }
    }

    private void pausePlayBackFromActivityOnPause(){
        for (ExploreFeaturedPostViewHolder videoCache:exploreFeaturedPostViewHolderQueue) {
            if (onGoingToFullscreen) {

            } else {
                videoCache.attachmentsRecyclerView.onPause(true);
            }
        }
        onGoingToFullscreen = false;
    }

    private void destroyPlayBackFromActivity(){
        for (ExploreFeaturedPostViewHolder videoCache:exploreFeaturedPostViewHolderQueue) {
            videoCache.attachmentsRecyclerView.onDestroy(false);
        }
    }

    @Override
    public int getItemCount() {
        return communityViewList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Set<CommunityViewMetadata> communityViewMetadataHashSet = communityViewList.get(position).keySet();
        CommunityViewMetadata communityViewMetadata1 = null;
        for (CommunityViewMetadata communityViewMetadata: communityViewMetadataHashSet) {
            communityViewMetadata1 = communityViewMetadata;
        }
        if (communityViewMetadata1.getViewType() == typeChannel) {
            return typeChannel;
        } else if (communityViewMetadata1.getViewType() == typeHashtags) {
            return typeHashtags;
        } else if (communityViewMetadata1.getViewType() == typeVideos) {
            return typeVideos;
        }
        else if (communityViewMetadata1.getViewType() == typeFeaturedPost) {
            return typeFeaturedPost;
        }
        else if (communityViewMetadata1.getViewType() == typeBillboard) {
            return typeBillboard;
        }
        else if(communityViewMetadata1.getViewType() == typeOneAd){
            return  typeOneAd;
        }
        else if(communityViewMetadata1.getViewType() == typeTwoAd){
            return  typeTwoAd;
        }
        return typeChannel;
    }


    public class ExploreChannelViewHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        LinearLayout more;
        public ExploreChannelViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.explore_community_type1_recyclerview);
            more = itemView.findViewById(R.id.explore_community_type1_more);
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ExploreCommunityTypeOneBucketDetails.class));
                }
            });
        }
    }

    public class ExploreHashtagsViewHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        LinearLayout more;
        public ExploreHashtagsViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.explore_community_type2_recyclerview);
            more = itemView.findViewById(R.id.explore_community_type2_more);
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ExploreCommunityTypeTwoBucketDetails.class));
                }
            });
        }
    }

    public class ExploreVideosViewHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        LinearLayout more;
        public ExploreVideosViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.explore_community_type3_recyclerview);
            more = itemView.findViewById(R.id.explore_community_type_three_more);
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ExploreCommunityTypeThreeBucketDetails.class));
                }
            });
        }
    }

    public class ExploreBillboardViewholder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;

        public ExploreBillboardViewholder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.explore_community_billboard_recyclerview);
            new PagerSnapHelper().attachToRecyclerView(recyclerView);

            Map<CommunityViewMetadata,List<Object>> billboardMap = communityViewList.get(0);//billboard static position zero
            Set<CommunityViewMetadata> communityViewMetadataHashSet = billboardMap.keySet();
            List<Object> billboardModelListObj = null;
            for (CommunityViewMetadata communityViewMetadata: communityViewMetadataHashSet) {
                billboardModelListObj = billboardMap.get(communityViewMetadata);
            }

            ExploreCommunityBillboardAdapter exploreCommunityBillboardAdapter = new ExploreCommunityBillboardAdapter(billboardModelListObj,context);
            AutoScrollLinearLayoutManager layoutManager = new AutoScrollLinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false,250);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(exploreCommunityBillboardAdapter);
            recyclerView.addItemDecoration(new RecyclerViewPagerIndicator(DensityUtils.dpToPx(3),DensityUtils.dpToPx(5),DensityUtils.dpToPx(30), ContextCompat.getColor(context,R.color.white),ContextCompat.getColor(context,R.color.pinkypinky)));

            billboardTimer = new Timer();
            TimerTask timerTask = new TimerTask() {
                public void run() {
                    recyclerView.post(() -> {
                        int nextPage = (layoutManager.findFirstVisibleItemPosition() + 1) % exploreCommunityBillboardAdapter.getItemCount();
                        recyclerView.smoothScrollToPosition(nextPage);
                    });
                }
            };
            billboardTimer.schedule(timerTask, 5000, 8000);
        }
    }

    public class ExploreType1Ads extends RecyclerView.ViewHolder{


        public ExploreType1Ads(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class ExploreType2Ads extends RecyclerView.ViewHolder{


        public ExploreType2Ads(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class ExploreFeaturedPostViewHolder extends RecyclerView.ViewHolder implements RTextView.MentionClickedListener, RTextView.HashTagClickedListener,View.OnLongClickListener,View.OnClickListener{

        PlayableItemsRecyclerView attachmentsRecyclerView;
        RTextView textView;
        RichLinkView richLinkView;
        ImageView profilePicture;
        boolean isMentionClicked = false;
        boolean isHashTagClicked = false;

        public ExploreFeaturedPostViewHolder(View ItemView){
            super(ItemView);
            attachmentsRecyclerView = ItemView.findViewById(R.id.forum_post_attachments_recyclerview);
            textView = itemView.findViewById(R.id.forum_post_recycler_item_textview);
            textView.setMentionClickedListener(this);
            textView.setHashTagClickedListener(this);
            textView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
            textView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            richLinkView = itemView.findViewById(R.id.richlinkview);
            profilePicture = itemView.findViewById(R.id.forum_post_profile_picture_type_post);
        }
        @Override
        public void onClick(View view) {

            context.startActivity(new Intent(context, ChannelPostDetails.class));
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
