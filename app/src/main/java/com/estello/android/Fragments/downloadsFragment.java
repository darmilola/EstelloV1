package com.estello.android.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.estello.android.Adapter.DownloadFragmentAdapter;
import com.estello.android.ViewModel.downloads_model;
import com.estello.android.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class downloadsFragment extends Fragment {


    RecyclerView recyclerView;
    View view;
    ArrayList<downloads_model> downloads_modelArrayList = new ArrayList<>();
    DownloadFragmentAdapter downloadFragmentAdapter;
    public downloadsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view =  inflater.inflate(R.layout.fragment_downloads, container, false);
         populateList();
         initRecyclerView();

         return  view;
    }


    private void initRecyclerView(){
        recyclerView = view.findViewById(R.id.download_fragment_recycler);
        downloadFragmentAdapter = new DownloadFragmentAdapter(downloads_modelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(downloadFragmentAdapter);


    }

    public void populateList(){


        downloads_model downloads_model = new downloads_model();

        for(int i = 0; i < 5; i++){
            downloads_modelArrayList.add(downloads_model);
        }

    }


}
