package com.estello.android;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.adroitandroid.chipcloud.ChipCloud;
import com.estello.android.Adapter.CourseDetailsRelatedClassesAdapter;
import com.estello.android.Adapter.FaqAdapter;
import com.estello.android.Adapter.SyllabusAdapter;
import com.estello.android.Fragments.course_detail_instructor_fragment;
import com.estello.android.ViewModel.AutoScrollViewPager;
import com.estello.android.ViewModel.CourseVideoPlayerMinView;
import com.estello.android.ViewModel.CoursesModel;
import com.estello.android.ViewModel.FaqAnswerModel;
import com.estello.android.ViewModel.FaqModel;
import com.estello.android.ViewModel.SyllabusSection;
import com.estello.android.ViewModel.SyllabusSectionDetails;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;



import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;

import com.google.android.exoplayer2.ui.PlayerView;

import com.google.android.exoplayer2.upstream.DataSource;

import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import com.google.android.exoplayer2.util.Util;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.button.MaterialButton;
import com.rd.PageIndicatorView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseDetails extends AppCompatActivity {


    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    TextView course_title,skills_you_will_gain;
    MaterialButton enrollmentButton;
    TextView toolbar_title;
    ArrayList<String> skillsToAcquire = new ArrayList<>();
    ArrayList<CoursesModel> coursesModelArrayList = new ArrayList<>();
    ChipCloud skillToAcquireCloud;
    ViewGroup viewGroup;
    CourseVideoPlayerMinView courseVideoPlayerMinView;
    RecyclerView relatedCoursesRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        initView();
    }

    private void initView(){

        skillToAcquireCloud = findViewById(R.id.course_details_skill_to_acquire_cloud);
        appBarLayout = findViewById(R.id.course_details_app_bar);
        collapsingToolbarLayout = findViewById(R.id.course_detail_collapsing_toolbar_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.course_detail_toolbar);
        course_title = findViewById(R.id.course_detail_course_title);
        skills_you_will_gain = findViewById(R.id.course_details_skills_you_will_gain);
        enrollmentButton = findViewById(R.id.course_details_enrollment_button);
        toolbar_title = findViewById(R.id.course_details_toolbar_title);
        viewGroup = findViewById(R.id.video_min_view_video_frame);
        relatedCoursesRecyclerview = findViewById(R.id.related_courses_recyclerview);
        courseVideoPlayerMinView = new CourseVideoPlayerMinView(CourseDetails.this,viewGroup);
        courseVideoPlayerMinView.setVideoUrl("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4");
        courseVideoPlayerMinView.setVideoThumbnailUrl("https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
        courseVideoPlayerMinView.setUpPlayer();
        initRelatedCourse();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(CourseDetails.this,R.color.White), PorterDuff.Mode.SRC_ATOP);




        skillsToAcquire.add("Data Structure and Algorithms");
        skillsToAcquire.add("Continuous Deployment");
        skillsToAcquire.add("Bubble.io");
        skillsToAcquire.add("Software Testing");
        skillsToAcquire.add("Programming in Assembly Language");

        for(int i = 0; i < skillsToAcquire.size(); i++){

            skillToAcquireCloud.addChip(skillsToAcquire.get(i));
        }


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {

                boolean isShown = true;
                int scrollRange = -1;
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if(scrollRange == -1){
                        scrollRange = appBarLayout.getTotalScrollRange();
                    }
                    if(scrollRange + verticalOffset == 0){

                        toolbar_title.setText("Android Reversing and Malware Analysis for Software");
                        isShown = true;
                        return;
                    }
                    else if(isShown){


                        isShown = false;
                        toolbar_title.setText("");
                        return;
                    }
                }
        });

        enrollmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CourseDetails.this,CourseActivity.class));
            }
        });
    }
    @Override
    public void onPause() {
        super.onPause();
        if (courseVideoPlayerMinView != null) courseVideoPlayerMinView.pausePlayer();
    }
    @Override
    public void onDestroy() {

        super.onDestroy();
        if(courseVideoPlayerMinView != null) courseVideoPlayerMinView.destroyPlayer();
    }

    private void initRelatedCourse(){
        CoursesModel coursesModel = new CoursesModel();
        for(int i = 0; i < 4; i++){
            coursesModelArrayList.add(coursesModel);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(CourseDetails.this,LinearLayoutManager.VERTICAL,false);
        relatedCoursesRecyclerview.setLayoutManager(layoutManager);
        CourseDetailsRelatedClassesAdapter classesAdapter = new CourseDetailsRelatedClassesAdapter(coursesModelArrayList,CourseDetails.this);
        relatedCoursesRecyclerview.setAdapter(classesAdapter);
    }
}
