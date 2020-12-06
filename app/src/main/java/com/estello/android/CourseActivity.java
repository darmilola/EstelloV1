package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.estello.android.Fragments.CourseActivityForums;
import com.estello.android.Fragments.CourseActivityGrades;
import com.estello.android.Fragments.CourseActivityInfo;
import com.estello.android.Fragments.CourseActivityLesson;
import com.estello.android.ViewModel.CourseVideoPlayerMinView;
import com.estello.android.ViewModel.NoSwipeableViewPager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;



import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {


    private NoSwipeableViewPager mViewPager;
    private TabLayout tabLayout;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    ViewGroup viewGroup;
    CourseVideoPlayerMinView courseVideoPlayerMinView;
    CourseActivityPagerAdapter adapter = new CourseActivityPagerAdapter(getSupportFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        initView();
    }



    private void initView(){

        mViewPager = findViewById(R.id.course_activity_pager);
        mViewPager.setOffscreenPageLimit(3);
        setupViewPager(mViewPager);
        tabLayout = (TabLayout) findViewById(R.id.course_activity_tabs);
        tabLayout.setupWithViewPager(mViewPager);
        toolbar = findViewById(R.id.course_activity_toolbar);
        appBarLayout = findViewById(R.id.course_activity_app_bar);
        collapsingToolbarLayout = findViewById(R.id.course_activity_collapsing_toolbar_layout);
        viewGroup = findViewById(R.id.video_min_view_video_frame);
        courseVideoPlayerMinView = new CourseVideoPlayerMinView(CourseActivity.this,viewGroup);
        courseVideoPlayerMinView.setVideoUrl("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4");
        courseVideoPlayerMinView.setVideoThumbnailUrl("https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");
        courseVideoPlayerMinView.setUpPlayer();


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(CourseActivity.this,R.color.White), PorterDuff.Mode.SRC_ATOP);


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {

            boolean isShown = true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if(scrollRange + verticalOffset == 0){

                    isShown = true;
                    return;
                }
                else if(isShown){


                    isShown = false;
                    return;
                }
            }
        });


    }

    public class CourseActivityPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();



        public CourseActivityPagerAdapter(FragmentManager fm) {
            super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){

                case 0:

                    return new CourseActivityLesson();

                case 1:

                    return new CourseActivityForums();
            }
            return null;
        }

        @Override
        public int getCount() {

            return 2;
        }
        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }

    private void setupViewPager(ViewPager viewPager) {

        adapter.addFragment(new CourseActivityLesson(), "Lessons");
        adapter.addFragment(new CourseActivityForums(), "Forums");
        viewPager.setAdapter(adapter);
    }


}
