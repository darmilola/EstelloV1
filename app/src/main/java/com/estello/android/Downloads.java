package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;


import com.estello.android.Adapter.DownloadFragmentAdapter;
import com.estello.android.ViewModel.downloads_model;

import java.util.ArrayList;

public class Downloads extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<downloads_model> downloads_modelArrayList = new ArrayList<>();
    DownloadFragmentAdapter downloadFragmentAdapter;
    Toolbar toolbar;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloads);

        initView();
        populateList();
        initRecyclerView();
    }

    private void initRecyclerView(){
        recyclerView = findViewById(R.id.download_fragment_recycler);
        downloadFragmentAdapter = new DownloadFragmentAdapter(downloads_modelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(downloadFragmentAdapter);


    }

    public void populateList(){


        downloads_model downloads_model = new downloads_model();

        for(int i = 0; i < 5; i++){
            downloads_modelArrayList.add(downloads_model);
        }

    }

    private void initView(){

        toolbar = findViewById(R.id.mydownloads_toolbar);
        title = findViewById(R.id.mydownloads_toolbar_title);
        Typeface customfont2= Typeface.createFromAsset(getAssets(), "font/latoreg.ttf");
        title.setTypeface(customfont2);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(this,R.color.White), PorterDuff.Mode.SRC_ATOP);

    }
}
