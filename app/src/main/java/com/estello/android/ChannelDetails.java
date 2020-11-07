package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.estello.android.Adapter.MessagingAreaAttachmentsAdapter;
import com.estello.android.Adapter.PinnedItemsAdapter;
import com.estello.android.ViewModel.MessagingAreaAttachmentModel;
import com.estello.android.ViewModel.PinnedItemsModel;

import java.util.ArrayList;

public class ChannelDetails extends AppCompatActivity {

    RecyclerView pinnedItemsRecyclerView;
    PinnedItemsAdapter pinnedItemsAdapter;
    ArrayList<PinnedItemsModel> pinnedItemsModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_details);
        setPinnedItemsView();
    }



    @Override
    public void onResume() {

        super.onResume();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            getWindow().setNavigationBarColor(ContextCompat.getColor(ChannelDetails.this,R.color.white));
            getWindow().setStatusBarColor(ContextCompat.getColor(ChannelDetails.this,R.color.white));
            //getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        }


    }

    private void setPinnedItemsView(){


        pinnedItemsModelArrayList = new ArrayList<>();
        PinnedItemsModel pinnedItemsModel = new PinnedItemsModel();
        for(int i = 0; i < 10; i++){

            pinnedItemsModelArrayList.add(pinnedItemsModel);
        }
        pinnedItemsRecyclerView = findViewById(R.id.channel_details_pinned_items_recyclerview);
        pinnedItemsAdapter = new PinnedItemsAdapter(pinnedItemsModelArrayList, ChannelDetails.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChannelDetails.this, LinearLayoutManager.VERTICAL,false);
        pinnedItemsRecyclerView.setLayoutManager(linearLayoutManager);
        pinnedItemsRecyclerView.setAdapter(pinnedItemsAdapter);

    }

}
