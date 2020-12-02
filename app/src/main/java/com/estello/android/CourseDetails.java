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
import android.widget.TextView;


import com.adroitandroid.chipcloud.ChipCloud;
import com.estello.android.Adapter.FaqAdapter;
import com.estello.android.Adapter.SyllabusAdapter;
import com.estello.android.Fragments.course_detail_instructor_fragment;
import com.estello.android.ViewModel.AutoScrollViewPager;
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

public class CourseDetails extends AppCompatActivity implements Player.EventListener {

    PlayerView intro_video_view;
    SimpleExoPlayer simpleExoPlayer;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    TextView course_title,skills_you_will_gain,syllabus;
    MaterialButton enrollmentButton;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    TextView whatToExpect;
    RecyclerView recyclerView;
    TextView about_course,toolbar_title;
    private AutoScrollViewPager mViewPager;
    instructor_adapter adapter = new instructor_adapter(getSupportFragmentManager());
    TextView instructor_title;
    List<SyllabusSection> syllabusSectionList = new ArrayList<>();
    PageIndicatorView pageIndicatorView;
    ArrayList<String> skillsToAcquire = new ArrayList<>();
    ArrayList<FaqModel> faqList = new ArrayList<>();
    ChipCloud skillToAcquireCloud;
    RecyclerView faqRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        initView();
        initRecyclerView();
        setupViewPager(mViewPager);

    }

    private void initView(){
        skillToAcquireCloud = findViewById(R.id.course_details_skill_to_acquire_cloud);
        about_course = findViewById(R.id.course_details_about_course);
        intro_video_view = findViewById(R.id.course_details_intro_video);
        appBarLayout = findViewById(R.id.course_details_app_bar);
        whatToExpect = findViewById(R.id.course_details_preview);
        collapsingToolbarLayout = findViewById(R.id.course_detail_collapsing_toolbar_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.course_detail_toolbar);
        course_title = findViewById(R.id.course_detail_course_title);
        skills_you_will_gain = findViewById(R.id.course_details_skills_you_will_gain);
        syllabus = findViewById(R.id.course_details_what_you_will_learn);
        enrollmentButton = findViewById(R.id.course_details_enrollment_button);
        mViewPager = findViewById(R.id.course_detail_instructor_pager);
        instructor_title = findViewById(R.id.course_detail_instructor_title);
        toolbar_title = findViewById(R.id.course_details_toolbar_title);
        faqRecyclerView = findViewById(R.id.course_details_faq_recyclerview);
        mViewPager.startAutoScroll(5000);
        mViewPager.setInterval(3000);
        mViewPager.setAutoScrollDurationFactor(5d);
        Typeface customfont= Typeface.createFromAsset(getAssets(), "font/latoreg.ttf");
        Typeface customfont2= Typeface.createFromAsset(getAssets(),"montsemi.ttf");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(CourseDetails.this,R.color.White), PorterDuff.Mode.SRC_ATOP);
        pageIndicatorView = findViewById(R.id.course_detail_instructor_pageIndicatorView);



        skillsToAcquire.add("Modelling");
        skillsToAcquire.add("Normal Attitude");
        skillsToAcquire.add("Badass Software Development");
        skillsToAcquire.add("Lets Go A fishing");
        skillsToAcquire.add("Cookability");

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

                        toolbar_title.setText("The Science Of WellBeing by Yale University to be animated to its position");
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

    private void initRecyclerView(){

        recyclerView = findViewById(R.id.course_details_syllabus_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CourseDetails.this, LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(CourseDetails.this, LinearLayoutManager.VERTICAL,false);


        SyllabusSectionDetails syllabusSectionDetails = new SyllabusSectionDetails("This is going to be the place where the section description will be so thats fantastic okay yeah thank you mehn good to go","5 readings, 4 videos","3 hours live");
        SyllabusSectionDetails syllabusSectionDetails2 = new SyllabusSectionDetails("This is going to be the place where the secti good to go","8 readings, 10 videos","5 hours live");

        SyllabusSection section = new SyllabusSection("Day 1","This is My First Title", Arrays.asList(syllabusSectionDetails,syllabusSectionDetails2,syllabusSectionDetails,syllabusSectionDetails2));

        SyllabusSection section2 = new SyllabusSection("Day 2","This is My Second Title", Arrays.asList(syllabusSectionDetails2));

        syllabusSectionList.add(section);
        syllabusSectionList.add(section2);

        SyllabusAdapter adapter  = new SyllabusAdapter(CourseDetails.this,syllabusSectionList);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);


        FaqAnswerModel faqAnswerModel = new FaqAnswerModel();
        FaqModel faqModel = new FaqModel(faqAnswerModel);
        faqList.add(faqModel);
        faqList.add(faqModel);
        faqList.add(faqModel);
        faqList.add(faqModel);
        faqList.add(faqModel);
        faqList.add(faqModel);
        faqList.add(faqModel);
        FaqAdapter faqAdapter = new FaqAdapter(CourseDetails.this,faqList);

        faqRecyclerView.setLayoutManager(linearLayoutManager2);
        faqRecyclerView.setAdapter(faqAdapter);
        recyclerView.setNestedScrollingEnabled(false);

    }

    private void initPlayer(){

        simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();
        intro_video_view.setPlayer(simpleExoPlayer);
        MediaSource mediaSource = buildMediaSource(Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        simpleExoPlayer.setPlayWhenReady(playWhenReady);
        simpleExoPlayer.seekTo(currentWindow, playbackPosition);
        simpleExoPlayer.prepare(mediaSource, false, false);
    }



    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initPlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if ((Util.SDK_INT < 24 || simpleExoPlayer == null)) {
            initPlayer();
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(this, "Vhoola");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }


    private void releasePlayer() {
        if (simpleExoPlayer != null) {
            playWhenReady = simpleExoPlayer.getPlayWhenReady();
            playbackPosition = simpleExoPlayer.getCurrentPosition();
            currentWindow = simpleExoPlayer.getCurrentWindowIndex();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }


    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
    }



    public class instructor_adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public instructor_adapter(FragmentManager manager) {
            super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {

            mFragmentList.add(fragment);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }

    private void setupViewPager(ViewPager viewPager) {

        for(int i = 0; i < 5; i++){

            adapter.addFragment(new course_detail_instructor_fragment(Integer.toString(i)));
        }

        viewPager.setAdapter(adapter);
        pageIndicatorView.setCount(5); // specify total count of indicators
        pageIndicatorView.setSelection(0);
        pageIndicatorView.setViewPager(viewPager);

    }


}
