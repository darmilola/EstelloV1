package com.estello.android.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.R;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;
import org.mockito.internal.matchers.ContainsExtraTypeInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreChannelsFragment extends Fragment {


    ViewPager viewPager;
    TabLayout tabLayout;
    ChannelsPagerAdapter adapter;
    View view;
    public ExploreChannelsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_explore_channels, container, false);
        initView();
        return  view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

    private void initView(){
        adapter  = new ChannelsPagerAdapter(getChildFragmentManager());
        tabLayout = view.findViewById(R.id.explore_channels_tabs);
        viewPager = view.findViewById(R.id.explore_channel_viewpager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(2);

    }

      public class ChannelsPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        public ChannelsPagerAdapter(FragmentManager fm) {
            super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NotNull
        @Override
        public Fragment getItem(int position) {

            switch (position) {

                  case 0:
                      return new ExploreChannelFavouriteChannel();
                  case 1:
                    return new ExploreChannelPrivateChannel();
                  case 2:
                      return new ExploreChannelAllChannel();
                  case 3:
                      return new ExploreChannelUnreadChannel();
                case 4:
                    return  new ExploreChannelOwnedChannel();



            }
            return null;
        }

        @Override
        public int getCount() {

            return 5;
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

        adapter.addFragment(new ExploreChannelFavouriteChannel(), "Favourite");
        adapter.addFragment(new ExploreChannelPrivateChannel(), "Private");
        adapter.addFragment(new ExploreChannelAllChannel(), "All");
        adapter.addFragment(new ExploreChannelUnreadChannel(), "Unread");
        adapter.addFragment(new ExploreChannelOwnedChannel(), "Owned");
        viewPager.setAdapter(adapter);
    }



}
