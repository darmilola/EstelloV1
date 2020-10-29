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
import android.widget.TextView;

import com.estello.android.Fragments.CourseActivityForums;
import com.estello.android.Fragments.CourseActivityGrades;
import com.estello.android.Fragments.CourseActivityInfo;
import com.estello.android.Fragments.CourseActivityOverview;
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
    TextView courseActivityToolbarTitle;
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
        courseActivityToolbarTitle = findViewById(R.id.course_activity_toolbar_title);

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

                    courseActivityToolbarTitle.setText("The Science Of WellBeing");
                    tabLayout.setVisibility(View.GONE);
                    isShown = true;
                    return;
                }
                else if(isShown){


                    isShown = false;
                    courseActivityToolbarTitle.setText("");
                    tabLayout.setVisibility(View.VISIBLE);
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
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position){

                case 0:

                    return new CourseActivityOverview();

                case 1:
                    return new CourseActivityGrades();


                case 2:

                    return new CourseActivityForums();

                case 3:

                    return  new CourseActivityInfo();



            }
            return null;
        }

        @Override
        public int getCount() {

            return 4;
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

        adapter.addFragment(new CourseActivityOverview(), "Overview");
        adapter.addFragment(new CourseActivityGrades(), "Grades");
        adapter.addFragment(new CourseActivityForums(), "Forums");
        adapter.addFragment(new CourseActivityInfo(), "Info");
        viewPager.setAdapter(adapter);
    }


}
