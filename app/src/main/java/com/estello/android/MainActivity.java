package com.estello.android;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.estello.android.Fragments.DirectMessages;
import com.estello.android.Fragments.ExploreFragment;
import com.estello.android.Fragments.MyCourses;
import com.estello.android.Fragments.UserProfile;
import com.estello.android.Fragments.UserProfileBottomSheet;
import com.estello.android.ViewModel.NoSwipeableViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;




import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());
    NoSwipeableViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        initView();
        setupViewPager(viewPager);

    }


    private void initView() {


        bottomNavigationView = findViewById(R.id.chip_navigation);
        viewPager = findViewById(R.id.content_frame);


        viewPager.setOffscreenPageLimit(4);
        viewPager.setCurrentItem(0, false);
        bottomNavigationView.setSelectedItemId(R.id.explore);
        bottomNavigationView.setSelected(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId();
                if (i == R.id.explore) {
                    viewPager.setCurrentItem(0, false);

                } else if (i == R.id.dm) {

                    viewPager.setCurrentItem(1, false);
                } else if (i == R.id.learn) {
                    viewPager.setCurrentItem(2, false);
                }

                if (i == R.id.userprofile) {

                    viewPager.setCurrentItem(3, false);
                }

                return true;
            }
        });


    }


     public void displayUserProfile(){
         UserProfileBottomSheet bottomSheet = new UserProfileBottomSheet();
         bottomSheet.show(getSupportFragmentManager(),"userbottomsheetprofile");
     }

    /*private void ChangeToExploreFragment(){
        Fragment fragment = new ExploreFragment();
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

                    return new ExploreFragment();

                case 1:
                    return new DirectMessages();

                case 2:
                    return new com.estello.android.Fragments.MyCourses();
                case 3:
                    return new UserProfile();


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

        adapter.addFragment(new ExploreFragment(), "first");
        adapter.addFragment(new DirectMessages(), "second");
        adapter.addFragment(new MyCourses(), "third");
        adapter.addFragment(new UserProfile(),"fourth");
        viewPager.setAdapter(adapter);
    }



       @Override
        public void onResume() {

           super.onResume();
           getWindow().setNavigationBarColor(ContextCompat.getColor(MainActivity.this,R.color.white));
           getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
           getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

    }



}
