package com.estello.android.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allattentionhere.autoplayvideos.AAH_CustomRecyclerView;

import com.estello.android.Adapter.NetworkAdapter;

import com.estello.android.ViewModel.NetworkPostImageModel;
import com.estello.android.ViewModel.NetworkPostModel;
import com.estello.android.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NetworkFragment extends Fragment {


    AAH_CustomRecyclerView recyclerView;
    View view;
    ArrayList<NetworkPostModel> postModelArrayList = new ArrayList<>();
    public NetworkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

               view =  inflater.inflate(R.layout.fragment_network, container, false);
               populatePost();
               initRecyclerView();
               return view;
    }

    private void initView(){

    }

    private void initRecyclerView(){

        recyclerView = view.findViewById(R.id.network_recycler_view);
        NetworkAdapter adapter = new NetworkAdapter(postModelArrayList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setActivity(getActivity());
        recyclerView.setVisiblePercent(70);
        recyclerView.setAdapter(adapter);

    }
    private void populatePost(){

        NetworkPostImageModel networkPostImageModel = new NetworkPostImageModel("","https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
        NetworkPostImageModel networkPostImageModel1 = new NetworkPostImageModel("","https://homepages.cae.wisc.edu/~ece533/images/baboon.png");

        NetworkPostImageModel networkPostImageMode2 = new NetworkPostImageModel("","https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
        NetworkPostImageModel networkPostImageModel3 = new NetworkPostImageModel("","https://homepages.cae.wisc.edu/~ece533/images/peppers.png");


        ArrayList<NetworkPostImageModel> networkPostImageModels = new ArrayList<>();

       networkPostImageModels.add(networkPostImageModel);
       networkPostImageModels.add(networkPostImageModel1);
        ArrayList<NetworkPostImageModel> networkPostImageModels2 = new ArrayList<>();
        networkPostImageModels2.add(networkPostImageMode2);

        ArrayList<NetworkPostImageModel> networkPostImageModels3 = new ArrayList<>();
        networkPostImageModels3.add(networkPostImageMode2);
        networkPostImageModels3.add(networkPostImageModel1);
        networkPostImageModels3.add(networkPostImageModel3);
        networkPostImageModels3.add(networkPostImageModel);





        NetworkPostModel postModel = new NetworkPostModel();
        NetworkPostModel postModel1 = new NetworkPostModel();
        NetworkPostModel postModel3 = new NetworkPostModel();
        NetworkPostModel postModel4 = new NetworkPostModel();
        NetworkPostModel postModel5 = new NetworkPostModel();
        postModel4.setNetworkPostImageModelArrayList(networkPostImageModels2);
        postModel3.setNetworkPostImageModelArrayList(networkPostImageModels);
        postModel5.setNetworkPostImageModelArrayList(networkPostImageModels3);
        postModel.setViewType(1);
        postModel1.setViewType(2);
        postModel3.setViewType(3);
        postModel4.setViewType(3);
        postModel5.setViewType(3);
        for(int i = 0; i < 1; i++){
            postModelArrayList.add(postModel);
            postModelArrayList.add(postModel1);
            postModelArrayList.add(postModel3);
            postModelArrayList.add(postModel4);
            postModelArrayList.add(postModel5);
        }
    }
}
