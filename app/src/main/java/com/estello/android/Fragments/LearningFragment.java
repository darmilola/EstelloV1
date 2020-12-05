package com.estello.android.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.estello.android.Adapter.LearningClassesAdapter;
import com.estello.android.Adapter.LearningLiveClassesAdapter;
import com.estello.android.Adapter.MostPopularCertifiedCourseAdapter;
import com.estello.android.Adapter.MostPopularCourseAdapter;
import com.estello.android.Adapter.MostPopularCourseWithPracticeLabAdapter;
import com.estello.android.Adapter.MostPopularOneToOneCourseAdapter;
import com.estello.android.Adapter.RecommendedCourseAdapter;
import com.estello.android.Adapter.TopRatedCourseAdapter;
import com.estello.android.Adapter.TopRatedOneToOneCourseAdapter;
import com.estello.android.Adapter.explore_chip_adapter;
import com.estello.android.ExploreDisplaySelectedTopic;
import com.estello.android.ViewModel.CoursesModel;
import com.estello.android.ViewModel.explore_category_chip_model;
import com.estello.android.SearchActivity;
import com.google.android.material.card.MaterialCardView;


import com.estello.android.R;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearningFragment extends Fragment {


    View view;
    RecyclerView savedClassesRecyclerview,recommendedRecyclerview,liveClassesRecyclerview,trendingCategoryRecyclerview;
    ArrayList<CoursesModel> coursesList = new ArrayList<>();
    public LearningFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view =  inflater.inflate(R.layout.fragment_learning, container, false);

         initView();

         return  view;

    }

    private void initView() {

        liveClassesRecyclerview = view.findViewById(R.id.learning_live_recyclerview);
        recommendedRecyclerview = view.findViewById(R.id.learning_recommended_class_recyclerview);
        savedClassesRecyclerview = view.findViewById(R.id.learning_saved_class_recyclerview);
        trendingCategoryRecyclerview = view.findViewById(R.id.learning_trending_class_recyclerview);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        liveClassesRecyclerview.setLayoutManager(linearLayoutManager1);
        recommendedRecyclerview.setLayoutManager(linearLayoutManager2);
        savedClassesRecyclerview.setLayoutManager(linearLayoutManager3);
        trendingCategoryRecyclerview.setLayoutManager(linearLayoutManager4);

        CoursesModel coursesModel = new CoursesModel();
        for(int i = 0; i < 10; i++){
            coursesList.add(coursesModel);
        }
        LearningClassesAdapter learningClassesAdapter = new LearningClassesAdapter(coursesList,getContext());
        LearningLiveClassesAdapter learningLiveClassesAdapter = new LearningLiveClassesAdapter(coursesList,getContext());
        liveClassesRecyclerview.setAdapter(learningLiveClassesAdapter);
        recommendedRecyclerview.setAdapter(learningClassesAdapter);
        savedClassesRecyclerview.setAdapter(learningClassesAdapter);
        trendingCategoryRecyclerview.setAdapter(learningClassesAdapter);

    }

}
