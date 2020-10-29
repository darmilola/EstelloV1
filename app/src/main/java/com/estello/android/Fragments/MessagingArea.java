package com.estello.android.Fragments;


import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.estello.android.Adapter.MessagingAreaPictureSelectAdapter;
import com.estello.android.ViewModel.MessagingAreaPictureSelectModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;


import com.estello.android.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessagingArea extends Fragment {


    View view;
    private BottomSheetBehavior bottomSheetBehavior;
    FrameLayout bottom_sheet;
    BottomSheetDialog dialog;

    int initialEdittextHeight = 0;
    ArrayList<MessagingAreaPictureSelectModel> pictureDisplayModelArrayList;
    MessagingAreaPictureSelectAdapter messagingAreaPictureSelectAdapter;
    RecyclerView messaging_pictures_recyclerview;
    NestedScrollView messagingPicturesNestedScroll;
    Thread thread;

    int softInputHeight = 0;
    boolean isSoftInputShown = false;

    ImageView cameraSelectPictureIcon1,cameraSelectPictureIcon2QandA,cameraSelectPictureIcon2BottomSheet;
    int mSoftInputHeight;
    boolean isPictureGalleryShown = false;

    MessagingArea messagingArea = new MessagingArea();

    public MessagingArea() {

        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view =  inflater.inflate(R.layout.fragment_messaging_area, container, false);

         return  view;
    }

}
