package com.estello.android.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.estello.android.Adapter.learningFragmentAdapter;

import com.estello.android.ViewModel.learningModel;
import com.estello.android.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class learnFragment extends Fragment {


    View view;
    RecyclerView recyclerView;
    ArrayList<learningModel> learningModelArrayList = new ArrayList<>();


    public learnFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

               view =  inflater.inflate(R.layout.fragment_learn, container, false);
              populateList();
              initRecyclerView();

            return  view;
    }



    private void initRecyclerView(){
        recyclerView = view.findViewById(R.id.learning_fragment_recyclerview);
        learningFragmentAdapter adapter = new learningFragmentAdapter(learningModelArrayList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }


    private void populateList(){

        learningModel model = new learningModel();

        for(int i = 0; i < 20; i++){
            learningModelArrayList.add(model);
        }

    }

}
