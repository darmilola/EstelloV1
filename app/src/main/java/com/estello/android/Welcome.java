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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.pchmn.materialchips.R2.id.image;


public class Welcome extends AppCompatActivity {


    ImageView background;
    LinearLayout facebookSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        background = findViewById(R.id.welcome_background_image);
        DrawableImageViewTarget imageViewTarget = new DrawableImageViewTarget(background);
        Glide.with(Welcome.this).load(R.drawable.collegegif).into(imageViewTarget);
        facebookSignIn = findViewById(R.id.signin_with_facebook_layout);

        facebookSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this,login.class));
            }
        });



    }

}
