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
import androidx.recyclerview.widget.SnapHelper;

import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Handler;

import com.estello.android.Adapter.ExploreCommunityBillboardAdapter;
import com.estello.android.R;
import com.estello.android.ViewModel.BlurBuilder;
import com.estello.android.ViewModel.ExploreCommuntyBillboardItem;
import com.estello.android.ViewModel.RecyclerViewPagerIndicator;
import com.rd.utils.DensityUtils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreCommunity extends Fragment {


    ArrayList<ExploreCommuntyBillboardItem> exploreCommuntyBillboardItemArrayList = new ArrayList<>();
    RecyclerView billboardRecyclerview;
    ExploreCommunityBillboardAdapter exploreCommunityBillboardAdapter;
    View view;
    final int duration = 8000;
    private ArrayList<Bitmap> blurrExtractList = new ArrayList<>();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private  Runnable SCROLLING_RUNNABLE;
    UpdateStatusAndToolbarBackgroundListener updateStatusAndToolbarBackgroundListener;
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


        ExploreCommuntyBillboardItem exploreCommuntyBillboardItem = new ExploreCommuntyBillboardItem(R.drawable.ucas);
        ExploreCommuntyBillboardItem exploreCommuntyBillboardItem2 = new ExploreCommuntyBillboardItem(R.drawable.ucas1);
        ExploreCommuntyBillboardItem exploreCommuntyBillboardItem3 = new ExploreCommuntyBillboardItem(R.drawable.ucasdeadline);
        ExploreCommuntyBillboardItem exploreCommuntyBillboardItem4 = new ExploreCommuntyBillboardItem(R.drawable.ucasdeadline);

        for (int i = 0; i < 1; i++) {

            exploreCommuntyBillboardItemArrayList.add(exploreCommuntyBillboardItem);
            exploreCommuntyBillboardItemArrayList.add(exploreCommuntyBillboardItem2);
            exploreCommuntyBillboardItemArrayList.add(exploreCommuntyBillboardItem3);
            exploreCommuntyBillboardItemArrayList.add(exploreCommuntyBillboardItem4);

        }

        for(int i = 0; i < exploreCommuntyBillboardItemArrayList.size(); i++){

            Bitmap image = BitmapFactory.decodeResource(getResources(),exploreCommuntyBillboardItemArrayList.get(i).getImageUrl());
            Bitmap mBitmap = BlurBuilder.blur(getContext(),image);
            blurrExtractList.add(mBitmap);
        }
         updateStatusAndToolbarBackgroundListener.onUpdate(blurrExtractList.get(0));

        SCROLLING_RUNNABLE = new Runnable() {

            @Override
            public void run() {
                billboardRecyclerview.smoothScrollBy(getContext().getResources().getDisplayMetrics().widthPixels, 0);
                mHandler.postDelayed(this, duration);
            }
        };

        exploreCommunityBillboardAdapter = new ExploreCommunityBillboardAdapter(exploreCommuntyBillboardItemArrayList,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        billboardRecyclerview.setLayoutManager(layoutManager);
        new PagerSnapHelper().attachToRecyclerView(billboardRecyclerview);
        billboardRecyclerview.addItemDecoration(new RecyclerViewPagerIndicator(DensityUtils.dpToPx(3),DensityUtils.dpToPx(5),DensityUtils.dpToPx(30), ContextCompat.getColor(getContext(),R.color.white),ContextCompat.getColor(getContext(),R.color.pinkypinky)));
        billboardRecyclerview.setAdapter(exploreCommunityBillboardAdapter);

        billboardRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItem = layoutManager.findLastCompletelyVisibleItemPosition();
                int currentposition = layoutManager.findFirstVisibleItemPosition();
                updateStatusAndToolbarBackgroundListener.onUpdate(blurrExtractList.get(currentposition));
                if(lastItem == layoutManager.getItemCount()-1){
                    mHandler.removeCallbacks(SCROLLING_RUNNABLE);
                    Handler postHandler = new Handler();
                    postHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            billboardRecyclerview.setAdapter(null);
                            billboardRecyclerview.setAdapter(exploreCommunityBillboardAdapter);
                            mHandler.postDelayed(SCROLLING_RUNNABLE, 2000);
                        }
                    }, 2000);
                }
            }
        });
        mHandler.postDelayed(SCROLLING_RUNNABLE, 2000);
    }

}
