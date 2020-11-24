package com.estello.android;


import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.estello.android.Fragments.DirectMessages;
import com.estello.android.Fragments.LearningFragment;
import com.estello.android.Fragments.MyCourses;
import com.estello.android.Fragments.UserProfileBottomSheet;
import com.estello.android.ViewModel.NoSwipeableViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.rd.utils.DensityUtils;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());
    NoSwipeableViewPager viewPager;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    LinearLayout userProfileImageLayout;
    LinearLayout viewProfileLayout;
    ActionBarDrawerToggle drawerToggle;
    LinearLayout mainView;
    LinearLayout bottom_nav_root_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setupViewPager(viewPager);
    }


    private void initView() {

        bottom_nav_root_layout = findViewById(R.id.bottomnav_root_layout);
        toolbar = findViewById(R.id.activity_main_toolbar);
        viewProfileLayout = findViewById(R.id.activity_main_drawer_user_profile_layout);
        drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        mainView = findViewById(R.id.activity_main_main_view);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.app_name,R.string.app_name){

            @Override
            public void onDrawerSlide(View drawerView,float slideOffset){
                super.onDrawerSlide(drawerView,slideOffset);
                //setTransluscentNavFlag(true);
                //getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                mainView.setTranslationX(-slideOffset * drawerView.getWidth());
                drawerLayout.bringChildToFront(drawerView);
                drawerLayout.requestLayout();
            }
            @Override
            public void onDrawerOpened(View drawerView){

            }
            @Override
            public void onDrawerClosed(View drawerView){
                      // setTransluscentNavFlag(false);
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);
        bottomNavigationView = findViewById(R.id.chip_navigation);
        viewPager = findViewById(R.id.content_frame);
        userProfileImageLayout = findViewById(R.id.activity_main_user_profile_profile_image_layout);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setCurrentItem(0, false);
        bottomNavigationView.setSelectedItemId(R.id.learn);
        bottomNavigationView.setSelected(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId();
                if (i == R.id.learn) {
                    viewPager.setCurrentItem(0, false);

                }  else if (i == R.id.my_dm) {
                    viewPager.setCurrentItem(1, false);
                }
                return true;
            }
        });


        viewProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfileBottomSheet userProfileBottomSheet = new UserProfileBottomSheet();
                userProfileBottomSheet.show(getSupportFragmentManager(),"userprofile");

            }
        });

        userProfileImageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout.closeDrawer(GravityCompat.END);
                }
                else{
                    drawerLayout.openDrawer(GravityCompat.END);
                }
            }
        });


    }



    /*private void ChangeToExploreFragment(){
        Fragment fragment = new LearningFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }

    private void ChangeToNetworkFragment(){
        Fragment fragment = new NetworkFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }

    private void ChangeToLearningFragment(){
        Fragment fragment = new learnFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }
    private void ChangeToNofifFragment(){
        Fragment fragment = new NotificationFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }

    private void ChangeToMyCoursesFragment(){
        Fragment fragment = new MyCourses();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }

    private void ChangeToDirectMessagesFragment(){
        Fragment fragment = new DirectMessages();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }


    private void ChangeToDownloadsFragment(){
        Fragment fragment = new downloadsFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }*/




    public class viewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        public viewPagerAdapter(FragmentManager fm) {
            super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position) {

                  case 0:

                    return new LearningFragment();
                  case 1:
                    return new DirectMessages();



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

        adapter.addFragment(new LearningFragment(), "first");
        adapter.addFragment(new DirectMessages(), "second");
        viewPager.setAdapter(adapter);
    }




       @Override
        public void onResume() {

           super.onResume();
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

               getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.full_transparency));
               getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
               getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
               getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
               getWindow().setFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS,WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);



           }
    }

    private void setTransluscentNavFlag(boolean on){
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
        //final int light = WindowManager.LayoutParams.
        if(on){
            layoutParams.flags |= bits;
            bottom_nav_root_layout.setPadding(0,0,0, DensityUtils.dpToPx(50));
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
        else{
            layoutParams.flags &= -bits;
            bottom_nav_root_layout.setPadding(0,0,0, 0);
        }
        getWindow().setAttributes(layoutParams);
    }



}
