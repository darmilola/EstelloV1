package com.estello.android.Fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Handler;

import com.estello.android.Adapter.ExploreCommunityBillboardAdapter;
import com.estello.android.Adapter.ExploreCommunityRecyclerAdapter;
import com.estello.android.R;
import com.estello.android.ViewModel.BlurBuilder;
import com.estello.android.ViewModel.CommunityViewChannelsModel;
import com.estello.android.ViewModel.CommunityViewHashtagsModel;
import com.estello.android.ViewModel.CommunityViewMetadata;
import com.estello.android.ViewModel.CommunityViewVideoModel;
import com.estello.android.ViewModel.ExploreCommuntyBillboardItem;
import com.estello.android.ViewModel.ForumPostAttachmentsModel;
import com.estello.android.ViewModel.ForumPostModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreCommunity extends Fragment {
    View view;
    final int duration = 8000;
    private ArrayList<Bitmap> blurrExtractList = new ArrayList<>();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private  Runnable SCROLLING_RUNNABLE;
    private List<Map<CommunityViewMetadata, List<Object>>> communityViewList = new ArrayList<>();
    private RecyclerView communityRecyclerView;
    public ExploreCommunity() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_explore_community, container, false);
        initView();
        return  view;
    }



    private void initView() {


        communityRecyclerView = view.findViewById(R.id.explore_community_main_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        communityRecyclerView.setLayoutManager(layoutManager);
        ExploreCommuntyBillboardItem exploreCommuntyBillboardItem = new ExploreCommuntyBillboardItem(R.drawable.ucas);
        ExploreCommuntyBillboardItem exploreCommuntyBillboardItem2 = new ExploreCommuntyBillboardItem(R.drawable.ucas1);
        ExploreCommuntyBillboardItem exploreCommuntyBillboardItem3 = new ExploreCommuntyBillboardItem(R.drawable.ucasdeadline);
        ExploreCommuntyBillboardItem exploreCommuntyBillboardItem4 = new ExploreCommuntyBillboardItem(R.drawable.ucasdeadline);
        ArrayList<Object> billboardList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {

            billboardList.add(exploreCommuntyBillboardItem);
            billboardList.add(exploreCommuntyBillboardItem2);
            billboardList.add(exploreCommuntyBillboardItem3);
            billboardList.add(exploreCommuntyBillboardItem4);
        }




        CommunityViewMetadata communityViewMetadata = new CommunityViewMetadata("Welcome to Estello Scholars Community for all Students in the Globe",0);
        CommunityViewMetadata communityViewMetadata2 = new CommunityViewMetadata("#Hello Estello",1);
        CommunityViewMetadata communityViewMetadata3= new CommunityViewMetadata("#Hello Estello",2);
        CommunityViewMetadata communityViewMetadata4= new CommunityViewMetadata("#Hello Estello",3);
        CommunityViewMetadata communityViewMetadata5= new CommunityViewMetadata("#Hello Estello",4);

        ArrayList<Object> channelsList = new ArrayList<>();
        ArrayList<Object> hashtagsList = new ArrayList<>();
        ArrayList<Object> videosList = new ArrayList<>();
        ArrayList<Object> featuredList = new ArrayList<>();
        ArrayList<Object> featuredList2 = new ArrayList<>();
        ArrayList<Object> featuredList3 = new ArrayList<>();
        ArrayList<ForumPostAttachmentsModel> forumPostAttachmentsModelArrayList = new ArrayList<>();
        ForumPostAttachmentsModel forumPostAttachmentsModel2 = new ForumPostAttachmentsModel(2,"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4","https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
        forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel2);
        ForumPostModel postModel4 = new ForumPostModel(new ArrayList<>(),forumPostAttachmentsModelArrayList,3);
        ForumPostModel postModel5 = new ForumPostModel(new ArrayList<>(),forumPostAttachmentsModelArrayList,3);
        featuredList.add(postModel4);
        featuredList2.add(postModel5);
        CommunityViewChannelsModel channelsModel;
        CommunityViewHashtagsModel communityViewHashtagsModel;
        CommunityViewVideoModel communityViewVideoModel;

        for(int i = 0; i < 6; ++i){
            channelsModel = new CommunityViewChannelsModel();
            channelsList.add(channelsModel);
            communityViewHashtagsModel = new CommunityViewHashtagsModel();
            hashtagsList.add(communityViewHashtagsModel);
            communityViewVideoModel = new CommunityViewVideoModel();
            videosList.add(communityViewVideoModel);
        }
        Map<CommunityViewMetadata,List<Object>> channlesMap = new HashMap<>();
        channlesMap.put(communityViewMetadata,channelsList);
        Map<CommunityViewMetadata,List<Object>> hashtagsMap = new HashMap<>();
        hashtagsMap.put(communityViewMetadata2,hashtagsList);
        Map<CommunityViewMetadata,List<Object>> videosMap = new HashMap<>();
        videosMap.put(communityViewMetadata3,videosList);
        Map<CommunityViewMetadata,List<Object>> channlesMap1 = new HashMap<>();
        channlesMap1.put(communityViewMetadata,channelsList);
        Map<CommunityViewMetadata,List<Object>> hashtagsMap1 = new HashMap<>();
        hashtagsMap1.put(communityViewMetadata2,hashtagsList);
        Map<CommunityViewMetadata,List<Object>> videosMap1 = new HashMap<>();
        videosMap1.put(communityViewMetadata3,videosList);
        Map<CommunityViewMetadata,List<Object>> channlesMap2 = new HashMap<>();
        channlesMap2.put(communityViewMetadata,channelsList);
        Map<CommunityViewMetadata,List<Object>> hashtagsMap2 = new HashMap<>();
        hashtagsMap2.put(communityViewMetadata2,hashtagsList);
        Map<CommunityViewMetadata,List<Object>> videosMap2 = new HashMap<>();
        videosMap2.put(communityViewMetadata3,videosList);
        Map<CommunityViewMetadata,List<Object>> channlesMap3 = new HashMap<>();
        channlesMap3.put(communityViewMetadata,channelsList);
        Map<CommunityViewMetadata,List<Object>> hashtagsMap3 = new HashMap<>();
        hashtagsMap3.put(communityViewMetadata2,hashtagsList);
        Map<CommunityViewMetadata,List<Object>> videosMap3 = new HashMap<>();
        videosMap3.put(communityViewMetadata3,videosList);
        Map<CommunityViewMetadata,List<Object>> featuredMap = new HashMap<>();
        featuredMap.put(communityViewMetadata4,featuredList);
        Map<CommunityViewMetadata,List<Object>> featuredMap2 = new HashMap<>();
        featuredMap2.put(communityViewMetadata4,featuredList2);
        Map<CommunityViewMetadata,List<Object>> BillboardMap = new HashMap<>();
        BillboardMap.put(communityViewMetadata5,billboardList);

        for(int i = 0; i < 3; i++){


            communityViewList.add(BillboardMap);
            communityViewList.add(channlesMap1);
            communityViewList.add(hashtagsMap1);
            communityViewList.add(videosMap1);
            communityViewList.add(featuredMap);
            communityViewList.add(featuredMap2);

        }
        ExploreCommunityRecyclerAdapter exploreCommunityRecyclerAdapter = new ExploreCommunityRecyclerAdapter(communityViewList,getContext());
        communityRecyclerView.setAdapter(exploreCommunityRecyclerAdapter);
    }

}
