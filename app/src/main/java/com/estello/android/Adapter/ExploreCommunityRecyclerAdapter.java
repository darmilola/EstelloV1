package com.estello.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.R;
import com.estello.android.ViewModel.CommunityViewMetadata;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ExploreCommunityRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Map<CommunityViewMetadata,List<Object>>> communityViewList;
    private static int typeChannel = 0;
    private static int typeHashtags = 1;
    private static int typeVideos = 2;
    Context context;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {



    }

    @Override
    public void onViewAttachedToWindow(@NotNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
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
        return typeChannel;
    }


    public class ExploreChannelViewHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;

        public ExploreChannelViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.explore_community_type1_recyclerview);
        }
    }

    public class ExploreHashtagsViewHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        public ExploreHashtagsViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.explore_community_type2_recyclerview);
        }
    }

    public class ExploreVideosViewHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        public ExploreVideosViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.explore_community_type3_recyclerview);
        }
    }
}
