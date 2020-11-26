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
    RecyclerView mostPopularCourseRecyclerView,RecommendedCourseRecyclerView,praticeLabRecyclerView;
    RecyclerView mostPopularOneToOneRecyclerView,topRatedCourseRecyclerview,mostPopularCertificateRecyclerview,topRatedOneToOneRecyclerView;
    ArrayList<CoursesModel> coursesList = new ArrayList<>();
    RecyclerView categoryRecycler;
    TextView exploreMostPopularCourseSeeAll;
    MaterialCardView cardView;
    ArrayList<explore_category_chip_model> explore_category_chip_models = new ArrayList<>();
    public LearningFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view =  inflater.inflate(R.layout.fragment_learning, container, false);

         initView();
         setUpMostPopularCourse();
         setUpRecommendedCourse();
         setUpPracticeLabCourse();
         setUpMostPopularOneToOneCourse();
         setUpTopRatedCourse();
         setUpTopMostPopularCertifiedCourse();
         setUpTopRatedOneToOneCourse();
         setUpCategoryRecycler();
         return  view;

    }






    private void setUpMostPopularCourse(){
        mostPopularCourseRecyclerView = view.findViewById(R.id.explore_most_popular_course_recyclerview);
        MostPopularCourseAdapter adapter = new MostPopularCourseAdapter(coursesList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        mostPopularCourseRecyclerView.setLayoutManager(linearLayoutManager);
        mostPopularCourseRecyclerView.setAdapter(adapter);
    }

    private void setUpRecommendedCourse(){
        RecommendedCourseRecyclerView = view.findViewById(R.id.explore_recommended_course_recyclerview);
        RecommendedCourseAdapter adapter = new RecommendedCourseAdapter(coursesList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        RecommendedCourseRecyclerView.setLayoutManager(linearLayoutManager);
        RecommendedCourseRecyclerView.setAdapter(adapter);
    }

    private void setUpPracticeLabCourse(){
        praticeLabRecyclerView = view.findViewById(R.id.explore_most_popular_with_practice_lab_recyclerview);
        MostPopularCourseWithPracticeLabAdapter adapter = new MostPopularCourseWithPracticeLabAdapter(coursesList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        praticeLabRecyclerView.setLayoutManager(linearLayoutManager);
        praticeLabRecyclerView.setAdapter(adapter);

    }

    private void setUpMostPopularOneToOneCourse(){

        mostPopularOneToOneRecyclerView = view.findViewById(R.id.explore_most_popular_one_to_one_recyclerview);
        MostPopularOneToOneCourseAdapter adapter = new MostPopularOneToOneCourseAdapter(coursesList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        mostPopularOneToOneRecyclerView.setLayoutManager(linearLayoutManager);
        mostPopularOneToOneRecyclerView.setAdapter(adapter);
    }

    private void setUpTopRatedCourse(){

        topRatedCourseRecyclerview = view.findViewById(R.id.explore_top_rated_course_recycler_view);
        TopRatedCourseAdapter adapter = new TopRatedCourseAdapter(coursesList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        topRatedCourseRecyclerview.setLayoutManager(linearLayoutManager);
        topRatedCourseRecyclerview.setAdapter(adapter);
    }


    private void setUpTopMostPopularCertifiedCourse(){

        mostPopularCertificateRecyclerview = view.findViewById(R.id.explore_most_popular_certificate_recyclerview);
        MostPopularCertifiedCourseAdapter adapter = new MostPopularCertifiedCourseAdapter(coursesList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        mostPopularCertificateRecyclerview.setLayoutManager(linearLayoutManager);
        mostPopularCertificateRecyclerview.setAdapter(adapter);
    }

    private void setUpTopRatedOneToOneCourse(){

        topRatedCourseRecyclerview = view.findViewById(R.id.explore_top_rated_one_to_one_recyclerview);
        TopRatedOneToOneCourseAdapter adapter = new TopRatedOneToOneCourseAdapter(coursesList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        topRatedCourseRecyclerview.setLayoutManager(linearLayoutManager);
        topRatedCourseRecyclerview.setAdapter(adapter);
    }




    private void initView(){


        CoursesModel courses_model = new CoursesModel();

        for(int i = 0; i < 10; i++){
            coursesList.add(courses_model);
        }
    }

    private void setUpCategoryRecycler(){
        explore_category_chip_model explore_category_chip_model = new explore_category_chip_model("Art and Humanities");
        explore_category_chip_model explore_category_chip_model1 = new explore_category_chip_model("Business");
        explore_category_chip_model explore_category_chip_model2 = new explore_category_chip_model("Computer Science");
        explore_category_chip_model explore_category_chip_model3 = new explore_category_chip_model("Data Science");
        explore_category_chip_model explore_category_chip_model4 = new explore_category_chip_model("Information Technology");
        explore_category_chip_model explore_category_chip_model5 = new explore_category_chip_model("Health");
        explore_category_chip_model explore_category_chip_model6 = new explore_category_chip_model("Math and Logic");
        explore_category_chip_model explore_category_chip_model7 = new explore_category_chip_model("Personal Development");
        explore_category_chip_model explore_category_chip_model8 = new explore_category_chip_model("Physical Science and Engineering ");
        explore_category_chip_model explore_category_chip_model9 = new explore_category_chip_model("Social Science");
        explore_category_chip_model explore_category_chip_model10 = new explore_category_chip_model("Language Learning");
        explore_category_chip_model explore_category_chip_model11 = new explore_category_chip_model("Religion");

            explore_category_chip_models.add(explore_category_chip_model);
            explore_category_chip_models.add(explore_category_chip_model1);
            explore_category_chip_models.add(explore_category_chip_model2);
            explore_category_chip_models.add(explore_category_chip_model3);
            explore_category_chip_models.add(explore_category_chip_model4);
            explore_category_chip_models.add(explore_category_chip_model5);
            explore_category_chip_models.add(explore_category_chip_model6);
            explore_category_chip_models.add(explore_category_chip_model7);
            explore_category_chip_models.add(explore_category_chip_model8);
            explore_category_chip_models.add(explore_category_chip_model9);
            explore_category_chip_models.add(explore_category_chip_model10);
            explore_category_chip_models.add(explore_category_chip_model11);



        categoryRecycler = view.findViewById(R.id.category_recyler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        explore_chip_adapter explore_chip_adapter = new explore_chip_adapter(explore_category_chip_models,getContext());
        categoryRecycler.setLayoutManager(linearLayoutManager);
        categoryRecycler.setAdapter(explore_chip_adapter);
    }







}
