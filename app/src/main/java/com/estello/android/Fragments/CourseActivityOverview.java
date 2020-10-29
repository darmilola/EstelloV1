package com.estello.android.Fragments;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.estello.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseActivityOverview extends Fragment {


    View view;
    TabLayout tabLayout;
    ViewPager viewPager;
    Context context;
    WeeksAdapter adapter;

    public CourseActivityOverview() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        this.context = context;
        adapter = new WeeksAdapter(getChildFragmentManager());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view =  inflater.inflate(R.layout.fragment_course_activity_overview, container, false);
         initView();
         return  view;
    }

    private void initView(){

        tabLayout = view.findViewById(R.id.course_activity_overview_tabs);
        viewPager = view.findViewById(R.id.course_activity_overview_pager);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        LinearLayout tabsLinearLayout = (LinearLayout)((ViewGroup)tabLayout.getChildAt(0)).getChildAt(0);

        TextView tabTextView = (TextView)tabsLinearLayout.getChildAt(1);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)tabsLinearLayout.getLayoutParams();
        params.leftMargin = 30;
        tabLayout.requestLayout();
        Typeface customfont= Typeface.createFromAsset(getActivity().getAssets(), "opensansbold.ttf");
        tabTextView.setTypeface(customfont);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {Typeface customfont= Typeface.createFromAsset(getActivity().getAssets(), "opensansbold.ttf");


            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Typeface customfont= Typeface.createFromAsset(getActivity().getAssets(), "opensansbold.ttf");
                LinearLayout tabsLinearLayout = (LinearLayout)((ViewGroup)tabLayout.getChildAt(0)).getChildAt(tab.getPosition());
                TextView tabTextView = (TextView)tabsLinearLayout.getChildAt(1);
                tabTextView.setTypeface(customfont);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                Typeface customfont= Typeface.createFromAsset(getActivity().getAssets(), "opensansreg.ttf");
                LinearLayout tabsLinearLayout = (LinearLayout)((ViewGroup)tabLayout.getChildAt(0)).getChildAt(tab.getPosition());
                TextView tabTextView = (TextView)tabsLinearLayout.getChildAt(1);
                tabTextView.setTypeface(customfont);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    public class WeeksAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public WeeksAdapter(FragmentManager manager) {
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

        public void addFragment(Fragment fragment,String title) {

            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }

    private void setupViewPager(ViewPager viewPager) {

        for(int i = 0; i < 10; i++){

            adapter.addFragment(new CourseOverViewWeeksFragment(),"WEEK "+Integer.toString(i));
        }
        viewPager.setAdapter(adapter);


    }



}
