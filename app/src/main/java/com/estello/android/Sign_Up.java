package com.estello.android;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import com.estello.android.Fragments.sign_up_firstpage;
import com.estello.android.Fragments.sign_up_secondpage;
import com.estello.android.Fragments.sign_up_thirdpage;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class Sign_Up extends AppCompatActivity {

    viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());
    ViewPager sign_up_viewPager;
    ImageButton nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        sign_up_viewPager = findViewById(R.id.sign_up_pager);
        nextButton = findViewById(R.id.sign_up_next_page_button);
        setupViewPager(sign_up_viewPager);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sign_up_viewPager.setCurrentItem;
            }
        });
    }




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

                    return new sign_up_firstpage();

                case 1:
                    return new sign_up_secondpage();
                case 2:
                    return new sign_up_thirdpage();

            }
            return null;
        }

        @Override
        public int getCount() {

            return 3;
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

        adapter.addFragment(new sign_up_firstpage(), "first");
        adapter.addFragment(new sign_up_secondpage(), "second");
        adapter.addFragment(new sign_up_thirdpage(),"third");
        viewPager.setAdapter(adapter);
    }
}
