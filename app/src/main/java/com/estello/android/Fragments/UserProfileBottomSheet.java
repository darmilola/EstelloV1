package com.estello.android.Fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.estello.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileBottomSheet extends BottomSheetDialogFragment {


    View view;
    private  BottomSheetBehavior bottomSheetBehavior;
    FrameLayout bottom_sheet;
    BottomSheetDialog dialog;
    public UserProfileBottomSheet userProfileBottomSheet() {
        return new UserProfileBottomSheet();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        dialog = (BottomSheetDialog)super.onCreateDialog(savedInstanceState);

        dialog.setContentView(R.layout.user_profile_view_profile_bottomsheet);
        //view =  View.inflate(getContext(),R.layout.fragment_user_profile_bottom_sheet,null);
        initView();
        return  dialog;

    }



    private void initView(){


        bottom_sheet = (FrameLayout)dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);

        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
        bottomSheetBehavior.setHalfExpandedRatio(0.75f);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);



        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {


                setupFullHeight();


                if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){


                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);



                }
                else if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HALF_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                }
                else{
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

    }

    private void setupFullHeight() {

        ViewGroup.LayoutParams layoutParams = bottom_sheet.getLayoutParams();

        int windowHeight = getWindowHeight();
        if (layoutParams != null) {
            layoutParams.height = windowHeight;
        }
        bottom_sheet.setLayoutParams(layoutParams);

    }

    private int getWindowHeight() {
        // Calculate window height for fullscreen use
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }




}
