package com.estello.android.ViewModel;

import android.app.Application;
import android.content.Context;

import com.arthurivanets.arvi.PlayerProviderImpl;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.estello.android.R;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MyApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        Fresco.initialize(this);
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                                new CalligraphyConfig.Builder()
                                .setDefaultFontPath("font/latoreg.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                                .build());


        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this,Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);


    }
    @Override
    public void onTrimMemory(int level){
        super.onTrimMemory(level);

        if(level >= TRIM_MEMORY_BACKGROUND){
            PlayerProviderImpl.getInstance(this).release();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
