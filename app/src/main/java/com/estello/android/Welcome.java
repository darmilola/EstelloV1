package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
//import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;

import com.estello.android.Fragments.welcome_firstpage;
import com.estello.android.Fragments.welcome_secondpage;
import com.estello.android.Fragments.welcome_thirdpage;
import com.estello.android.ViewModel.AutoScrollViewPager;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.rd.PageIndicatorView;


import com.zhpan.indicator.IndicatorView;

import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public class Welcome extends AppCompatActivity {

    private viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());
    private AutoScrollViewPager mViewPager;
    IndicatorView indicatorView;
    MaterialCardView materialCardView;
    Handler handler;
    static TextView welcome_title,welcome_exp,login_text;
    Button create_new_account;
    MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
    ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel();
    MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable();
    ShapeAppearanceModel shapeAppearanceModel2 = new ShapeAppearanceModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcome);

        mViewPager = findViewById(R.id.welcome_pager);
        mViewPager.setOffscreenPageLimit(2);
        setupViewPager(mViewPager);
        materialCardView = findViewById(R.id.welcome_card);
        mViewPager.startAutoScroll(8000);
        mViewPager.setInterval(4000);
        welcome_title = findViewById(R.id.welcome_slide_title);
        welcome_exp = findViewById(R.id.welcome_slide_exp);
        create_new_account = findViewById(R.id.welcome_slide_create_account);
        login_text = findViewById(R.id.welcome_slide_login);
        PageIndicatorView pageIndicatorView = findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setCount(3); // specify total count of indicators
        pageIndicatorView.setSelection(0);


        materialShapeDrawable.setFillColor(ColorStateList.valueOf(ContextCompat.getColor(Welcome.this,R.color.White)));
        shapeAppearanceModel.setTopLeftCorner(CornerFamily.ROUNDED, 55);
        shapeAppearanceModel.setTopRightCorner(CornerFamily.ROUNDED,55);
        materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel);


        materialShapeDrawable2.setFillColor(ColorStateList.valueOf(ContextCompat.getColor(Welcome.this,R.color.colorPrimary)));
        shapeAppearanceModel2.setTopLeftCorner(CornerFamily.ROUNDED, 40);
        shapeAppearanceModel2.setBottomRightCorner(CornerFamily.ROUNDED,40);
        materialShapeDrawable2.setShapeAppearanceModel(shapeAppearanceModel2);



        materialCardView.setBackground(materialShapeDrawable);
        create_new_account.setBackground(materialShapeDrawable2);

        //indicatorView.setupWithViewPager(mViewPager);
        //mViewPager.startAutoScroll(2000);

        create_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Welcome.this,Sign_Up.class));
            }
        });

        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Welcome.this,login.class));
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

                    return new welcome_firstpage();

                case 1:
                    return new welcome_secondpage();
                case 2:
                    return  new welcome_thirdpage();

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

        adapter.addFragment(new welcome_firstpage(), "first");
        adapter.addFragment(new welcome_secondpage(), "second");
        adapter.addFragment(new welcome_thirdpage(), "third");
        viewPager.setAdapter(adapter);
    }

    public static void AuthPage(String title,String text){
        welcome_title.setText(title);
        welcome_exp.setText(text);

    }



}
