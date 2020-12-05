package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
//import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;


import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;


import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class Welcome extends AppCompatActivity {


    ImageView background;
    LinearLayout emailSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        background = findViewById(R.id.welcome_background_image);
        DrawableImageViewTarget imageViewTarget = new DrawableImageViewTarget(background);
        Glide.with(Welcome.this).load(R.drawable.collegegif).into(imageViewTarget);
        emailSignIn = findViewById(R.id.signin_with_email_layout);

        emailSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this,MainActivity.class));
            }
        });
    }
}
