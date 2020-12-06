package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import com.estello.android.Adapter.CreateChannelStepperAdapter;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class CreateNewChannel extends AppCompatActivity implements StepperLayout.StepperListener {

    Toolbar toolbar;
    private StepperLayout mStepperLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_channel);
        initView();
    }

    private void initView(){

        mStepperLayout = (StepperLayout) findViewById(R.id.create_new_channel_stepper_layout);
        mStepperLayout.setAdapter(new CreateChannelStepperAdapter(getSupportFragmentManager(), this));
        mStepperLayout.setListener(this);
        toolbar = findViewById(R.id.create_new_channel_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public void onCompleted(View completeButton) {

    }

    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int newStepPosition) {

    }

    @Override
    public void onReturn() {

    }
}
