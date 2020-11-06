package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class ChannelDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_details);
    }



    @Override
    public void onResume() {

        super.onResume();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            getWindow().setNavigationBarColor(ContextCompat.getColor(ChannelDetails.this,R.color.white));
            getWindow().setStatusBarColor(ContextCompat.getColor(ChannelDetails.this,R.color.white));
            //getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        }


    }

}
