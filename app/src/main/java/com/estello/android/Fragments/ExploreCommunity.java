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
import com.estello.android.ViewModel.RecyclerViewPagerIndicator;
import com.rd.utils.DensityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreCommunity extends Fragment {


    private ArrayList<ExploreCommuntyBillboardItem> exploreCommunityBillboardItemArrayList = new ArrayList<>();
    private RecyclerView billboardRecyclerview;
    private ExploreCommunityBillboardAdapter exploreCommunityBillboardAdapter;
    View view;
    final int duration = 8000;
    private ArrayList<Bitmap> blurrExtractList = new ArrayList<>();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private  Runnable SCROLLING_RUNNABLE;
    private UpdateStatusAndToolbarBackgroundListener updateStatusAndToolbarBackgroundListener;
    private List<Map<CommunityViewMetadata, List<Object>>> communityViewList = new ArrayList<>();
    private RecyclerView communityRecyclerView;
    public ExploreCommunity() {
        // Required empty public constructor
    }

    public interface UpdateStatusAndToolbarBackgroundListener{
        public void onUpdate(Bitmap bitmap);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_explore_community, container, false);
        initView();
        return  view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        updateStatusAndToolbarBackgroundListener = (UpdateStatusAndToolbarBackgroundListener)context;
    }

    private void initView() {

        billboardRecyclerview = view.findViewById(R.id.explore_community_billboard_recyclerview);
        communityRecyclerView = view.findViewById(R.id.explore_community_main_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        communityRecyclerView.setLayoutManager(layoutManager);



        ExploreCommuntyBillboardItem exploreCommuntyBillboardItem = new ExploreCommuntyBillboardItem(R.drawable.ucas);
        ExploreCommuntyBillboardItem exploreCommuntyBillboardItem2 = new ExploreCommuntyBillboardItem(R.drawable.ucas1);
        ExploreCommuntyBillboardItem exploreCommuntyBillboardItem3 = new ExploreCommuntyBillboardItem(R.drawable.ucasdeadline);
        ExploreCommuntyBillboardItem exploreCommuntyBillboardItem4 = new ExploreCommuntyBillboardItem(R.drawable.ucasdeadline);

        for (int i = 0; i < 1; i++) {

            exploreCommunityBillboardItemArrayList.add(exploreCommuntyBillboardItem);
            exploreCommunityBillboardItemArrayList.add(exploreCommuntyBillboardItem2);
            exploreCommunityBillboardItemArrayList.add(exploreCommuntyBillboardItem3);
            exploreCommunityBillboardItemArrayList.add(exploreCommuntyBillboardItem4);

        }

        for(int i = 0; i < exploreCommunityBillboardItemArrayList.size(); i++){

            Bitmap image = BitmapFactory.decodeResource(getResources(), exploreCommunityBillboardItemArrayList.get(i).getImageUrl());
            Bitmap mBitmap = BlurBuilder.blur(getContext(),image);
            blurrExtractList.add(mBitmap);
        }
         updateStatusAndToolbarBackgroundListener.onUpdate(blurrExtractList.get(0));

        SCROLLING_RUNNABLE = new Runnable() {

            @Override
            public void run() {
                billboardRecyclerview.smoothScrollBy(getContext().getResources().getDisplayMetrics().widthPixels, 0);
               // mHandler.postDelayed(this, duration);
            }
        };

        exploreCommunityBillboardAdapter = new ExploreCommunityBillboardAdapter(exploreCommunityBillboardItemArrayList,getContext());
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        billboardRecyclerview.setLayoutManager(layoutManager1);

        CommunityViewMetadata communityViewMetadata = new CommunityViewMetadata("Welcome to Estello Scholars Community for all Students in the Globe",0);
        CommunityViewMetadata communityViewMetadata2 = new CommunityViewMetadata("#Hello Estello",1);
        CommunityViewMetadata communityViewMetadata3= new CommunityViewMetadata("#Hello Estello",2);

        ArrayList<Object> channelsList = new ArrayList<>();
        ArrayList<Object> hashtagsList = new ArrayList<>();
        ArrayList<Object> videosList = new ArrayList<>();
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

        for(int i = 0; i < 5; i++){

            communityViewList.add(channlesMap);
            communityViewList.add(hashtagsMap);
            communityViewList.add(videosMap);
            communityViewList.add(channlesMap1);
            communityViewList.add(hashtagsMap1);
            communityViewList.add(videosMap1);
            communityViewList.add(channlesMap2);
            communityViewList.add(hashtagsMap2);
            communityViewList.add(videosMap2);
            communityViewList.add(channlesMap3);
            communityViewList.add(hashtagsMap3);
            communityViewList.add(videosMap3);
        }


        ExploreCommunityRecyclerAdapter exploreCommunityRecyclerAdapter = new ExploreCommunityRecyclerAdapter(communityViewList,getContext());
        communityRecyclerView.setAdapter(exploreCommunityRecyclerAdapter);


        new PagerSnapHelper().attachToRecyclerView(billboardRecyclerview);
        billboardRecyclerview.addItemDecoration(new RecyclerViewPagerIndicator(DensityUtils.dpToPx(3),DensityUtils.dpToPx(5),DensityUtils.dpToPx(30), ContextCompat.getColor(getContext(),R.color.white),ContextCompat.getColor(getContext(),R.color.pinkypinky)));
        billboardRecyclerview.setAdapter(exploreCommunityBillboardAdapter);

        billboardRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItem = layoutManager.findLastCompletelyVisibleItemPosition();
                int currentposition = layoutManager.findFirstVisibleItemPosition();
                //if(currentposition >= 0)updateStatusAndToolbarBackgroundListener.onUpdate(blurrExtractList.get(currentposition));
                if(lastItem == layoutManager.getItemCount()-1){
                    mHandler.removeCallbacks(SCROLLING_RUNNABLE);
                    Handler postHandler = new Handler();
                    postHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            billboardRecyclerview.setAdapter(null);
                            billboardRecyclerview.setAdapter(exploreCommunityBillboardAdapter);
                         //   mHandler.postDelayed(SCROLLING_RUNNABLE, 2000);
                        }
                    }, 2000);
                }
            }
        });
        mHandler.postDelayed(SCROLLING_RUNNABLE, 2000);
    }

}
