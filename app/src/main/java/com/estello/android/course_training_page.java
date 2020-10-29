package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.estello.android.Fragments.training_announcement_fragment;
import com.estello.android.Fragments.training_live_fragment;
import com.estello.android.Fragments.training_material_fragment;
import com.estello.android.Fragments.training_participant_fragment;
import com.estello.android.Fragments.training_qanda_fragment;
import com.estello.android.ViewModel.NoSwipeableViewPager;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;


import java.util.ArrayList;
import java.util.List;

public class course_training_page extends AppCompatActivity {



    viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());
    NoSwipeableViewPager viewPager;
    LinearLayout search_root;
    MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
    ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel();
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_training_page);

        search = findViewById(R.id.coursee_training_search_class_text);

        viewPager = findViewById(R.id.content_frame);
        search_root = findViewById(R.id.course_training_search_course_root);
        Typeface customfont= Typeface.createFromAsset(getAssets(), "font/latoreg.ttf");
        search.setTypeface(customfont);

        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setCurrentItem(0,false);



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

                    return new training_announcement_fragment();

                case 1:
                    return new training_qanda_fragment();
                case 2:
                    return new training_live_fragment();
                case 3:
                    return new training_material_fragment();
                case 4:
                    return new training_participant_fragment();


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

        adapter.addFragment(new training_announcement_fragment(), "first");
        adapter.addFragment(new training_qanda_fragment(), "second");
        adapter.addFragment(new training_live_fragment(),"third");
        adapter.addFragment(new training_material_fragment(), "fourth");
        adapter.addFragment(new training_participant_fragment(),"fifth");
        viewPager.setAdapter(adapter);
    }
}
