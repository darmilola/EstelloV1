package com.estello.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;


import com.deltastream.example.edittextcontroller.HorizontalRTToolbar;
import com.deltastream.example.edittextcontroller.MentionHashTagListener;
import com.deltastream.example.edittextcontroller.RTManager;
import com.deltastream.example.edittextcontroller.RTextView;
import com.deltastream.example.edittextcontroller.api.RTApi;
import com.deltastream.example.edittextcontroller.api.RTProxyImpl;
import com.deltastream.example.edittextcontroller.api.format.RTFormat;
import com.estello.android.Adapter.ForumPostAdapter;
import com.estello.android.Adapter.HashTagsSelectionAdapter;
import com.estello.android.Adapter.MentionSelectionAdapter;
import com.estello.android.Adapter.MessagingAreaAttachmentsAdapter;
import com.estello.android.Adapter.MessagingAreaFileSelectAdapter;
import com.estello.android.Adapter.MessagingAreaPictureSelectAdapter;
import com.estello.android.AudioRecordView.AudioRecordViewBottomSheetType1;
import com.estello.android.AudioRecordView.AudioRecordViewTypeActivity;
import com.estello.android.Fragments.userProfileBottomSheet;
import com.estello.android.ViewModel.ForumPostAttachmentsModel;
import com.estello.android.ViewModel.ForumPostModel;
import com.estello.android.ViewModel.HashTagsSelectionModel;
import com.estello.android.ViewModel.LockableBottomSheetBehavior;
import com.estello.android.ViewModel.MentionSelectionModel;
import com.estello.android.ViewModel.MessagingAreaAttachmentModel;
import com.estello.android.ViewModel.MessagingAreaFileSelectModel;
import com.estello.android.ViewModel.MessagingAreaPictureSelectModel;
import com.estello.android.ViewModel.RtEdittextScrollView;
import com.estello.android.ViewModel.User;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rd.utils.DensityUtils;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

public abstract class ForumBaseActivity extends AppCompatActivity {

    boolean isLayoutChangingFromMaxHeightChange = false;
    RtEdittextScrollView rtEditText;
    HorizontalRTToolbar QandAformatToolbar;
    RTManager QandArtManager;
    LinearLayout edittextFullScreen;
    LinearLayout edittextCameraFileLayout;
    LinearLayout QandAQuestionSelectLayout;
    LinearLayout QandASuggetsionSelectLayout;
    LinearLayout QandAToolbarDisplayLayout;
    LinearLayout QandAattachmentLayout;
    LinearLayout QandAformattingAreaLayout;
    LinearLayout fullScreenReverseLayout;
    ImageView QandAdisplayFormattingToolIcon;
    ImageView QandAsendIcon;
    ImageView QandAremoveToolbarIcon;
    LinearLayout QandArtToolbarLayout;
    LinearLayout QandAattachmentsPicturesLayout;
    ImageView QandAattchmentsFile;

    HorizontalRTToolbar bottomSheetformatToolbar;
    LinearLayout bottomSheetformatArea;
    LinearLayout bottomSheetToolbarDisplayLayout;
    LinearLayout bottomSheetAttachmentLayout;
    LinearLayout bottomSheetFormattingAreaLayout;
    ImageView bottomSheetDisplayFormattingToolIcon;
    ImageView bottomSheetSendIcon;
    ImageView bottomSheetRemoveToolbarIcon;
    LinearLayout bottomSheetRtToolbarLayout;
    RTManager bottomSheetRtManager;
    FrameLayout bottomSheetRecordView;
    ScrollView rtScrollView;
    BottomSheetDialog dialog;
    LinearLayout bottomSheetRecordIconLayout1;

    int changingPeekHeight;
    boolean isScrollStopped = false;
    private BottomSheetBehavior bottomSheetBehavior;
    LinearLayout bottom_sheet;
    LinearLayout bottomSheetInnerLayout;
    ImageView bottomSheetFileSelect1;
    ImageView bottomSheetFileSelect2;
    int initialEdittextHeight = 0;
    int hiddenRealInitialEdittextHeight = DensityUtils.dpToPx(150);
    ArrayList<MessagingAreaPictureSelectModel> pictureDisplayModelArrayList;
    ArrayList<MessagingAreaFileSelectModel> messagingAreaFileSelectModelArrayList;
    MessagingAreaPictureSelectAdapter messagingAreaPictureSelectAdapter;
    RecyclerView MessagingAreaAttachmentsSelectRecyclerView;
    NestedScrollView MessagingAreaAttachmentsSelectNestedScroll;
    Thread thread;
    CoordinatorLayout QandAForumRoot;
    int softInputHeight = 0;
    boolean isSoftInputShown = false;
    FloatingActionButton cameraSnap,cameraSelect;
    ImageView messagingAreaPictureSelect1Bottomsheet, messagingAreaPictureSelect2QandA,cameraSelectPictureIcon2BottomSheet;
    int mSoftInputHeight;
    boolean isPictureGalleryShown = false;
    ViewTreeObserver innerBottomSheetViewTreeObserver;
    RTApi rtApi;
    RecyclerView bottomSheetAttachmentRecyclerView,QandAattachmentsRecyclerView;
    int edittextHeightWithKeyboard = 0;
    PopupWindow hashTagsMentionPopUpwindow;

    int mEdittextSizeWithKeyBoardShown = 0;
    ArrayList<MessagingAreaAttachmentModel> bottomSheetAttachmentList;
    MessagingAreaAttachmentsAdapter AttachmentsAdapter;
    boolean isSoftInputRetreived = false;
    boolean isEdittextWithKeyboardShownSizeRetreived = false;
    boolean isFocusFromPictureNestedScroll = false;

    LinearLayout bottomsheet_picture_select_layout;
    FrameLayout bottomSheetRecordView1Root;
    FrameLayout deleteAnimLayoutTypeActivity,deleteAnimLayoutTypeBottomsheet;
    LinearLayout recordBubbleLayoutRootBottomSheetType1;
    FrameLayout audiorecordViewTypeActivityRoot;
    LinearLayout recordBubbleLayoutRootActivity;
    ImageView messagingAreaPictureSelect2BottomSheet;
    RecyclerView QandAForumPostRecyclerView;
    boolean isSelectionChangedFromBottomsheetStateChange = false;
    ForumPostAdapter forumAdapter;
    boolean authBflag = false,authCFlag = false;
    boolean isBottomSheetUpdatedFromB = false;
    MentionSelectionAdapter mentionSelectionAdapter;
    HashTagsSelectionAdapter hashTagsSelectionAdapter;
    ArrayList<MentionSelectionModel> mentionSelectionModelArrayList;
    ArrayList<HashTagsSelectionModel> hashTagsSelectionModelArrayList;
    LinearLayout mentionHashtagsRoot;
    int selectionInStartMentioning;
    int selectionInStartHashTagging;
    boolean selectionChangeFromMentioning  = false;
    RecyclerView mentionHashTagSelectionRecyclerView;
    int i  = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeBaseFeaturesLayout();
        populateView();
        initView();
        setUpAttachmentsView();
        setUpQandAattachmentsView();

    }

    private void initializeBaseFeaturesLayout(){
        setContentView(R.layout.activity_forum_base);
    }


    private void initMentions(){

        RecyclerView mentionHashTagSelectionRecyclerView;
        mentionHashTagSelectionRecyclerView = findViewById(R.id.mention_hashtags_selection_recyclerview);
        mentionSelectionModelArrayList = new ArrayList<>();
        MentionSelectionModel mentionSelectionModel = new MentionSelectionModel();
        for(int i = 0; i < 20; i++){

            mentionSelectionModelArrayList.add(mentionSelectionModel);
        }
        mentionSelectionAdapter = new MentionSelectionAdapter(mentionSelectionModelArrayList, new MentionSelectionAdapter.MentionItemClickListener() {
            @Override
            public void onMentionItemClick() {

                if(selectionInStartMentioning > rtEditText.getSelectionStart()) selectionInStartMentioning = rtEditText.getSelectionStart();
                selectionChangeFromMentioning = true;
                rtEditText.getText().replace(selectionInStartMentioning,rtEditText.getSelectionStart(),"");
                rtEditText.getText().insert(rtEditText.getSelectionStart(),"Damilola"+"\u00A0"+"Akinterinwa");
                rtEditText.getText().insert(rtEditText.getSelectionStart()," ");

            }
        }, ForumBaseActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ForumBaseActivity.this,LinearLayoutManager.VERTICAL,false);

        mentionHashTagSelectionRecyclerView.setLayoutManager(linearLayoutManager);
        mentionHashTagSelectionRecyclerView.setAdapter(mentionSelectionAdapter);

    }


    private void initHashTags(){


        hashTagsSelectionModelArrayList = new ArrayList<>();
        HashTagsSelectionModel hashTagsSelectionModel = new HashTagsSelectionModel();
        for(int i = 0; i < 20; i++){

            hashTagsSelectionModelArrayList.add(hashTagsSelectionModel);
        }

        hashTagsSelectionAdapter = new HashTagsSelectionAdapter(hashTagsSelectionModelArrayList, new HashTagsSelectionAdapter.HashtagsItemClickListener() {
            @Override
            public void onHashTagItemClick() {

                if(selectionInStartHashTagging > rtEditText.getSelectionStart()) selectionInStartHashTagging = rtEditText.getSelectionStart();
                selectionChangeFromMentioning = true;
                rtEditText.getText().replace(selectionInStartHashTagging,rtEditText.getSelectionStart(),"");
                rtEditText.getText().insert(rtEditText.getSelectionStart(),"NewFaceOfTechnology");
                rtEditText.getText().insert(rtEditText.getSelectionStart()," ");
            }
        },ForumBaseActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ForumBaseActivity.this,LinearLayoutManager.VERTICAL,false);
        mentionHashTagSelectionRecyclerView.setLayoutManager(linearLayoutManager);
        mentionHashTagSelectionRecyclerView.setAdapter(hashTagsSelectionAdapter);

    }



    public void showMentionHashTagsPopup(boolean isHashTags) {


        mentionHashtagsRoot.setVisibility(View.VISIBLE);
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT,DensityUtils.dpToPx(280));
        if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
            //DensityUtils.dpToPx(60);
            params.gravity = Gravity.BOTTOM;
        }
        else{

            params.gravity = Gravity.TOP;
        }
        mentionHashtagsRoot.setLayoutParams(params);
        mentionHashtagsRoot.requestLayout();

        if(isHashTags){

            initHashTags();
        }
        else{
            initMentions();
        }


    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView(){

        initializeAudioRecordView();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ForumBaseActivity.this);
        mSoftInputHeight = preferences.getInt("softInputHeight",0);
        edittextHeightWithKeyboard = preferences.getInt("edittextSize",0);
        softInputHeight = preferences.getInt("softInputHeight",0);
        isSoftInputRetreived = preferences.getBoolean("softInputRetrieved",false);
        isEdittextWithKeyboardShownSizeRetreived = preferences.getBoolean("edittextRetrieved",false);
        mentionHashTagSelectionRecyclerView = findViewById(R.id.mention_hashtags_selection_recyclerview);

        bottom_sheet = findViewById(R.id.messaging_area_bottomsheet);
        bottomsheet_picture_select_layout = findViewById(R.id.messaging_area_picture_select_layout_bottomsheet);
        QandAForumPostRecyclerView = findViewById(R.id.QandAForumPostRecyclerView);
        bottomSheetFileSelect1 = findViewById(R.id.messaging_area_bottomsheet_file_select1);
        bottomSheetFileSelect2 = findViewById(R.id.messaging_area_bottomsheet_file_select2);

        rtScrollView = findViewById(R.id.rtEdittextScrollview);
        bottomSheetInnerLayout = findViewById(R.id.bottomsheet_inner_layout);
        bottomSheetSendIcon = findViewById(R.id.BottomSheetSendIcon);

        rtEditText = findViewById(R.id.bottom_sheet_edittext);
        rtEditText.getParent().requestDisallowInterceptTouchEvent(true);
        rtEditText.setMovementMethod(new ScrollingMovementMethod());
        fullScreenReverseLayout = findViewById(R.id.messaging_fullscreen_reverse_layout);
        initialEdittextHeight = rtEditText.getMaxHeight();
        rtApi = new RTApi(ForumBaseActivity.this, new RTProxyImpl(ForumBaseActivity.this));
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);

        cameraSelect = findViewById(R.id.QandACameraSelect);
        cameraSnap = findViewById(R.id.QandACameraSnap);
        messagingAreaPictureSelect1Bottomsheet = findViewById(R.id.messaging_area_picture_select_bottomsheet);
        messagingAreaPictureSelect2QandA = findViewById(R.id.QandAattachmentsPictures);
        cameraSnap.setVisibility(View.GONE);
        cameraSelect.setVisibility(View.GONE);
        MessagingAreaAttachmentsSelectRecyclerView = findViewById(R.id.messaging_area_picture_layout_recyclerview);
        MessagingAreaAttachmentsSelectNestedScroll = findViewById(R.id.messaging_area_pictures_layout_nested_scroll);
        MessagingAreaAttachmentsSelectNestedScroll.setVisibility(View.GONE);
        messagingAreaPictureSelect2BottomSheet = findViewById(R.id.messaging_area_picture_select2_bottomsheet);
        QandAForumRoot = findViewById(R.id.QandAForumRoot);
        //bottom_sheet = findViewById(R.id.messaging_area_bottomsheet);

        QandAQuestionSelectLayout = findViewById(R.id.QandAQuestionSelectLayout);
        QandASuggetsionSelectLayout = findViewById(R.id.QandASuggestionSelectLayout);
        QandAattachmentsPicturesLayout = findViewById(R.id.QandAattachmentsPicturesLayout);
        QandAattchmentsFile = findViewById(R.id.QandAAttachmentFileSelect);
        QandAattachmentsRecyclerView = findViewById(R.id.q_and_a_attachments_recyclerview);
        QandArtToolbarLayout = findViewById(R.id.QandARtToolbarLayout);
        QandAformatToolbar = findViewById(R.id.QandAFormatToolbar);
        QandAformatToolbar.setVisibility(View.VISIBLE);
        edittextFullScreen = findViewById(R.id.QandAEdittextFullScreen);
        edittextCameraFileLayout = findViewById(R.id.QandAEdittextCameraFileIconsLayout);
        //QandAformatArea = findViewById(R.id.QandAFormatAreaLayout);
        QandAsendIcon = findViewById(R.id.QandASendIcon);
        QandAToolbarDisplayLayout = findViewById(R.id.QandAFormatToolBarDisplayLayout);
        QandAattachmentLayout = findViewById(R.id.QandAAttachmentLayout);
        QandAdisplayFormattingToolIcon = findViewById(R.id.QandADisplayFormatToolbarIcon);
        QandAformattingAreaLayout = findViewById(R.id.QandAFormatAreaLayout);
        QandAremoveToolbarIcon = findViewById(R.id.QandAFormatToolbarRemove);
        QandArtManager = new RTManager(rtApi);
        QandArtManager.registerToolbar(QandArtToolbarLayout, QandAformatToolbar);
        QandArtManager.registerEditor(rtEditText,true);
        rtEditText.setRichTextEditing(true,true);
        bottomSheetFormattingAreaLayout = findViewById(R.id.FormatAreaLayout);
        bottomSheetDisplayFormattingToolIcon = findViewById(R.id.DisplayFormatToolbarIcon);
        bottomSheetToolbarDisplayLayout = findViewById(R.id.FormatToolBarDisplayLayout);
        bottomSheetRemoveToolbarIcon = findViewById(R.id.FormatToolbarRemove);
        bottomSheetformatToolbar = findViewById(R.id.FormatToolbar);
        bottomSheetRtManager = new RTManager(rtApi);
        mentionHashtagsRoot = findViewById(R.id.mention_hashtags_selection_layout_root);
        ((LockableBottomSheetBehavior)bottomSheetBehavior).setLocked(true);






        rtEditText.setMentionHashTagListener(new MentionHashTagListener() {
            @Override
            public void onMentioning(CharSequence sequence) {


            }

            @Override
            public void onHashTagging(CharSequence sequence) {


            }

            @Override
            public void onStopMentioning() {


                selectionChangeFromMentioning = false;
                ((LockableBottomSheetBehavior)bottomSheetBehavior).setLocked(false);
                mentionHashtagsRoot.setVisibility(View.GONE);
                bottom_sheet.requestLayout();

            }

            @Override
            public void onStopHashTags() {

                selectionChangeFromMentioning = false;
                ((LockableBottomSheetBehavior)bottomSheetBehavior).setLocked(false);
                mentionHashtagsRoot.setVisibility(View.GONE);
                bottom_sheet.requestLayout();


            }

            @Override
            public void onMentionStarted() {

                selectionChangeFromMentioning = true;
                ((LockableBottomSheetBehavior)bottomSheetBehavior).setLocked(true);
                bottom_sheet.requestLayout();
                selectionInStartMentioning = rtEditText.getSelectionStart();
                showMentionHashTagsPopup(false);


            }

            @Override
            public void onHashTagsStarted() {


                selectionChangeFromMentioning = true;
                ((LockableBottomSheetBehavior)bottomSheetBehavior).setLocked(true);
                bottom_sheet.requestLayout();
                selectionInStartHashTagging = rtEditText.getSelectionStart();
                showMentionHashTagsPopup(true);

            }
        });




        bottomSheetFileSelect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(MessagingAreaAttachmentsSelectNestedScroll.getVisibility() == View.VISIBLE){

                    initFileSelectRecyclerView();
                }
                else {

                    initFileSelectRecyclerView();
                    hideSoftKeyboard(rtEditText);
                    MessagingAreaAttachmentsSelectNestedScroll.setVisibility(View.VISIBLE);
                    QandAattachmentsRecyclerView.setVisibility(View.GONE);
                    if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {

                        initExpandedUi();

                    } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {

                        initCollapsedUi();

                    }

                }


            }

        });

        bottomSheetFileSelect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(MessagingAreaAttachmentsSelectNestedScroll.getVisibility() == View.VISIBLE){

                    initFileSelectRecyclerView();
                }
                else {

                    initFileSelectRecyclerView();
                    hideSoftKeyboard(rtEditText);
                    MessagingAreaAttachmentsSelectNestedScroll.setVisibility(View.VISIBLE);
                    QandAattachmentsRecyclerView.setVisibility(View.GONE);
                    if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {

                        initExpandedUi();

                    } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {

                        initCollapsedUi();

                    }

                }


            }

        });

        QandAattchmentsFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(MessagingAreaAttachmentsSelectNestedScroll.getVisibility() == View.VISIBLE){

                    initFileSelectRecyclerView();
                }
                else {

                    initFileSelectRecyclerView();
                    hideSoftKeyboard(rtEditText);
                    MessagingAreaAttachmentsSelectNestedScroll.setVisibility(View.VISIBLE);
                    QandAattachmentsRecyclerView.setVisibility(View.GONE);
                    if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {

                        initExpandedUi();

                    } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {

                        initCollapsedUi();

                    }

                }


            }
        });




        rtEditText.setOnTopReachedListener(new RtEdittextScrollView.OnTopReachedListener() {
            @Override
            public void onTopReached() {

                rtEditText.removeOnTopReachedListener();
                if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){

                    rtEditText.setClickable(true);
                }
                else {

                    if (!rtEditText.canScrollVertically(-1) && rtEditText.isClickable()) {

                        rtEditText.setClickable(false);
                        isSelectionChangedFromBottomsheetStateChange = true;

                    } else {

                        rtEditText.setClickable(true);
                    }
                }
                rtEditText.setOnTopReachedListener(this);


            }
        });



        rtEditText.setOnBottomReachedListener(new RtEdittextScrollView.OnBottomReachedListener() {

            @Override
            public void onBottomReached() {

                rtEditText.setClickable(true);
                rtEditText.removeOnBottomReachedListener();
                if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){


                }
                else {
                    isSelectionChangedFromBottomsheetStateChange = true;
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                }

                rtEditText.setOnBottomReachedListener(this);



            }
        });









        bottomSheetDisplayFormattingToolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                animateFormattingToolbarFromBottomSheetToActivity();
                cameraSnap.setVisibility(View.GONE);
                cameraSelect.setVisibility(View.GONE);
                MessagingAreaAttachmentsSelectNestedScroll.setVisibility(View.GONE);
                bottomSheetAttachmentRecyclerView.setVisibility(View.GONE);

                if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){

                    isFocusFromPictureNestedScroll = true;
                    initExpandedUi();

                }
                else if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){
                    isFocusFromPictureNestedScroll = true;
                    initCollapsedUi();

                }



            }
        });


        messagingAreaPictureSelect1Bottomsheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(MessagingAreaAttachmentsSelectNestedScroll.getVisibility() == View.VISIBLE){

                    initPictureSelectRecyclerView();
                }
                else {

                    initPictureSelectRecyclerView();
                    hideSoftKeyboard(rtEditText);
                    MessagingAreaAttachmentsSelectNestedScroll.setVisibility(View.VISIBLE);
                    QandAattachmentsRecyclerView.setVisibility(View.GONE);
                    if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {

                        initExpandedUi();

                    } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {

                        initCollapsedUi();

                    }

                }

            }

        });
        messagingAreaPictureSelect2QandA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (MessagingAreaAttachmentsSelectNestedScroll.getVisibility() == View.VISIBLE) {

                    initPictureSelectRecyclerView();

                } else {

                    initPictureSelectRecyclerView();
                    hideSoftKeyboard(rtEditText);
                    MessagingAreaAttachmentsSelectNestedScroll.setVisibility(View.VISIBLE);
                    QandAattachmentsRecyclerView.setVisibility(View.GONE);
                    if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {

                        initExpandedUi();
                    } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {

                        initCollapsedUi();
                    }
                }
            }

        });

        messagingAreaPictureSelect2BottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(MessagingAreaAttachmentsSelectNestedScroll.getVisibility() == View.VISIBLE){

                    initPictureSelectRecyclerView();
                }
                else {

                    initPictureSelectRecyclerView();
                    hideSoftKeyboard(rtEditText);
                    MessagingAreaAttachmentsSelectNestedScroll.setVisibility(View.VISIBLE);
                    QandAattachmentsRecyclerView.setVisibility(View.GONE);
                    if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {

                        initExpandedUi();

                    } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {

                        initCollapsedUi();

                    }

                }

            }
        });
        cameraSnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveTextToSharedPref();
                //                bottomSheetAttachmentRecyclerView.setVisibility(View.VISIBLE);
                //               QandAattachmentsRecyclerView.setVisibility(View.GONE);
                //bottomSheetBehavior.setPeekHeight(mSoftInputHeight + 120);
                //((LockableBottomSheetBehavior)bottomSheetBehavior).setLocked(false);
            }
        });

        QandAsendIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                QandAattachmentsRecyclerView.setVisibility(View.VISIBLE);
                bottomSheetAttachmentRecyclerView.setVisibility(View.GONE);
                authBottomSheetPeekHeight();
            }
        });






        edittextFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        fullScreenReverseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });



        bottomSheetInnerLayout.getViewTreeObserver().addOnGlobalLayoutListener( new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                bottomSheetInnerLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                if(!authBflag){

                    authBottomSheetPeekHeight();
                }
                else{

                    authChangingPeekHeight();
                }

                bottomSheetInnerLayout.getViewTreeObserver().addOnGlobalLayoutListener(this);

            }


        });





        rtEditText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

            }
        });



        QandAForumRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Log.e("layout changing", "onGlobalLayout: ");
                QandAForumRoot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int approxSoftInputHeight = QandAForumRoot.getRootView().getHeight() - QandAForumRoot.getHeight();

                if(!authBflag){


                }
                else if(isLayoutChangingFromMaxHeightChange){

                    isLayoutChangingFromMaxHeightChange = false;

                }
                else {

                    if (approxSoftInputHeight > 150) {


                        isSoftInputShown = true;

                        if(isSoftInputRetreived){


                        }
                        if(isEdittextWithKeyboardShownSizeRetreived){

                        }
                        else {



                            softInputHeight = QandAForumRoot.getRootView().getHeight() - QandAForumRoot.getHeight();
                            edittextHeightWithKeyboard = QandAForumRoot.getRootView().getHeight() - softInputHeight - 70;
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ForumBaseActivity.this);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putBoolean("softInputRetrieved", true);
                            editor.putBoolean("edittextRetrieved", true);
                            editor.putInt("edittextSize", edittextHeightWithKeyboard);
                            editor.putInt("softInputHeight", softInputHeight);
                            editor.apply();

                        }

                        if(MessagingAreaAttachmentsSelectNestedScroll.getVisibility() == View.VISIBLE){

                        }
                        else {
                            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {

                                isSelectionChangedFromBottomsheetStateChange = true;
                                rtEditText.onSelectionChanged(rtEditText.getSelectionStart(), rtEditText.getSelectionEnd());

                            }

                        }


                    } else {

                        isSoftInputShown = false;
                        mentionHashtagsRoot.setVisibility(View.GONE);
                        if(MessagingAreaAttachmentsSelectNestedScroll.getVisibility() == View.VISIBLE){

                        }
                        else {
                            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {

                                isSelectionChangedFromBottomsheetStateChange = true;
                                rtEditText.onSelectionChanged(rtEditText.getSelectionStart(), rtEditText.getSelectionEnd());

                            }
                        }


                    }
                }

                QandAForumRoot.getViewTreeObserver().addOnGlobalLayoutListener(this);

            }

        });


        rtEditText.setmOnselectionChangedListener(new RtEdittextScrollView.mOnselectionChangedListener() {
            @Override
            public void mOnSelectionChanged(int selStart, int selEnd) {

                Log.e("select change  ", "mOnSelectionChanged: ");
                rtEditText.setClickable(true);

                if (MessagingAreaAttachmentsSelectNestedScroll.getVisibility() == View.VISIBLE) {

                }
                else if(selectionChangeFromMentioning);//selectionChangeFromMentioning = false;
                else {


                    if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {

                        if(isSelectionChangedFromBottomsheetStateChange){

                            initExpandedUi();
                            isSelectionChangedFromBottomsheetStateChange = false;
                        }




                    }
                    else if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){

                        if(isSelectionChangedFromBottomsheetStateChange){

                            initCollapsedUi();
                            isSelectionChangedFromBottomsheetStateChange = false;
                        }

                    }


                }
            }
        });

        rtEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                if(hasFocus){


                    ShowSoftKeyboard(rtEditText);
                    if(MessagingAreaAttachmentsSelectNestedScroll.getVisibility() == View.VISIBLE){

                        bottomSheetAttachmentRecyclerView.setVisibility(View.GONE);
                        MessagingAreaAttachmentsSelectNestedScroll.setVisibility(View.GONE);
                        if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){

                            initExpandedUi();

                        }
                        else if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){

                            initCollapsedUi();
                        }

                    }

                    else{


                        if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){

                            initExpandedUi();
                        }
                        else if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){

                            initCollapsedUi();
                        }
                    }




                }
                else {


                    hideSoftKeyboard(rtEditText);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.bottomMargin = 0;
                    lp.weight = 4.3f;
                    lp.width = 0;
                    rtScrollView.setLayoutParams(lp);
                    rtEditText.setPadding(20,0,10,0);
                    rtEditText.requestLayout();

                }
            }
        });



        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {


                if(newState == BottomSheetBehavior.STATE_COLLAPSED){


                    rtEditText.setClickable(true);
                    initCollapsedUi();
                }
                else if(newState == BottomSheetBehavior.STATE_EXPANDED){


                    rtEditText.setClickable(true);
                    initExpandedUi();
                }

                else if(newState == BottomSheetBehavior.STATE_DRAGGING){

                    rtEditText.setIsTopReachedFromDragging(true);
                    rtEditText.setClickable(false);

                }





            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {


            }
        });

        QandAdisplayFormattingToolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animateFormattingToolbarDisplay();


            }
        });

        QandAremoveToolbarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                QandAToolbarDisplayLayout.setVisibility(View.GONE);
                QandAattachmentLayout.setVisibility(View.VISIBLE);
                QandAformattingAreaLayout.setBackgroundColor(Color.parseColor("#ffffff"));

            }
        });

    }


    @Override
    public void onResume() {

        super.onResume();
        if(forumAdapter != null){

            forumAdapter.resumePlayBack();
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            getWindow().setNavigationBarColor(ContextCompat.getColor(ForumBaseActivity.this,R.color.white));
            getWindow().setStatusBarColor(ContextCompat.getColor(ForumBaseActivity.this,R.color.white));
            //getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        }


    }

    private void initExpandedUi(){



        Log.e("iniTing", "initExpandedUi: ");
        mentionHashtagsRoot.setVisibility(View.GONE);
        rtEditText.setFocusClearedFromTouch(false);
        isLayoutChangingFromMaxHeightChange = true;
        if(MessagingAreaAttachmentsSelectNestedScroll.getVisibility() == View.VISIBLE){

            isPictureGalleryShown = true;
            rtEditText.setFocusClearedFromTouch(false);
            rtEditText.setPadding(20, 0, 10, 0);
            rtEditText.setMaxHeight(50);
            rtEditText.requestLayout();
            cameraSelect.setVisibility(View.VISIBLE);
            cameraSnap.setVisibility(View.VISIBLE);
            QandAformattingAreaLayout.setVisibility(View.GONE);
            edittextCameraFileLayout.setVisibility(View.GONE);
            bottomSheetBehavior.setPeekHeight(softInputHeight);
            bottomSheetFormattingAreaLayout.setVisibility(View.VISIBLE);
            rtEditText.clearFocus();


        }
        else {

            MessagingAreaAttachmentsSelectRecyclerView.setNestedScrollingEnabled(false);
            MessagingAreaAttachmentsSelectNestedScroll.setNestedScrollingEnabled(false);
            QandAattachmentsRecyclerView.setNestedScrollingEnabled(false);
            bottomSheetAttachmentRecyclerView.setNestedScrollingEnabled(false);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.bottomMargin = 10;
            lp.weight = 4.3f;
            lp.width = 0;
            rtScrollView.setLayoutParams(lp);
            rtEditText.requestLayout();
            rtScrollView.requestLayout();


            isPictureGalleryShown = false;
            edittextCameraFileLayout.setVisibility(View.GONE);
            MessagingAreaAttachmentsSelectNestedScroll.setVisibility(View.GONE);
            cameraSelect.setVisibility(View.GONE);
            cameraSnap.setVisibility(View.GONE);
            ((LockableBottomSheetBehavior)bottomSheetBehavior).setLocked(false);
            Log.e("setting here", "initExpandedUi: ");
            if(QandAattachmentsRecyclerView.getVisibility() == View.VISIBLE){
                rtEditText.setPadding(20, 20, 3, 180);
            }
            else {

                rtEditText.setPadding(20, 50, 3, 70);
            }
            QandAformattingAreaLayout.setVisibility(View.VISIBLE);
            edittextCameraFileLayout.setVisibility(View.GONE);
            bottomSheetFormattingAreaLayout.setVisibility(View.GONE);
            rtEditText.requestLayout();
            rtScrollView.requestLayout();
            bottom_sheet.requestLayout();

            if (isSoftInputShown) {

                if(QandAattachmentsRecyclerView.getVisibility() == View.VISIBLE){

                    rtEditText.setMaxHeight(edittextHeightWithKeyboard - 70);
                }
                else {
                    rtEditText.setMaxHeight(edittextHeightWithKeyboard);
                }
                rtEditText.requestLayout();
                rtScrollView.requestLayout();
                bottom_sheet.requestLayout();
                Log.e("setting here soft", "initExpandedUi: ");


            }
            else {
                if(QandAattachmentsRecyclerView.getVisibility() == View.VISIBLE){

                    rtEditText.setMaxHeight(QandAForumRoot.getRootView().getHeight() - 300);
                }
                else{
                    rtEditText.setMaxHeight(QandAForumRoot.getRootView().getHeight() - 200);
                }

                rtEditText.requestLayout();
                rtScrollView.requestLayout();
                bottom_sheet.requestLayout();

            }

        }

        fullScreenReverseLayout.setVisibility(View.VISIBLE);
        edittextFullScreen.setVisibility(View.GONE);
        edittextCameraFileLayout.setVisibility(View.GONE);
        //((LockableBottomSheetBehavior) bottomSheetBehavior).setLocked(false);

    }

    private void initCollapsedUi(){


        Log.e("select initing", "initCollapsedUi: ");
        mentionHashtagsRoot.setVisibility(View.GONE);
        rtEditText.setFocusClearedFromTouch(false);
        isLayoutChangingFromMaxHeightChange = true;
        fullScreenReverseLayout.setVisibility(View.GONE);
        edittextFullScreen.setVisibility(View.VISIBLE);
        edittextCameraFileLayout.setVisibility(View.GONE);
        isLayoutChangingFromMaxHeightChange = true;
        ((LockableBottomSheetBehavior)bottomSheetBehavior).setLocked(false);

        if(MessagingAreaAttachmentsSelectNestedScroll.getVisibility() == View.VISIBLE){

            MessagingAreaAttachmentsSelectRecyclerView.setNestedScrollingEnabled(false);
            MessagingAreaAttachmentsSelectNestedScroll.setNestedScrollingEnabled(true);
            isPictureGalleryShown = true;
            rtEditText.setPadding(20, 0, 10, 0);
            QandAformattingAreaLayout.setVisibility(View.GONE);
            edittextCameraFileLayout.setVisibility(View.GONE);
            bottomSheetFormattingAreaLayout.setVisibility(View.VISIBLE);
            bottomSheetBehavior.setPeekHeight(softInputHeight);
            cameraSelect.setVisibility(View.VISIBLE);
            cameraSnap.setVisibility(View.VISIBLE);
            rtEditText.setMaxHeight(50);
            rtEditText.clearFocus();
            //rtEditText.requestLayout();


        }

        else{




            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

            lp.bottomMargin = 50;
            lp.weight = 4.3f;
            lp.width = 0;
            rtScrollView.setLayoutParams(lp);
            rtScrollView.requestLayout();
            rtEditText.requestLayout();

            isPictureGalleryShown = false;
            cameraSelect.setVisibility(View.GONE);
            cameraSnap.setVisibility(View.GONE);
            MessagingAreaAttachmentsSelectRecyclerView.setNestedScrollingEnabled(false);
            MessagingAreaAttachmentsSelectNestedScroll.setNestedScrollingEnabled(false);

            Log.e("welcome ", "initCollapsedUi: ");
            if(QandAattachmentsRecyclerView.getVisibility() == View.VISIBLE){

                rtEditText.setPadding(20, 0, 10, 70);
            }
            else {

                rtEditText.setPadding(20, 0, 10, 90);
            }

            rtEditText.setMaxHeight(hiddenRealInitialEdittextHeight);
            //authBottomSheetPeekHeight();
            QandAformattingAreaLayout.setVisibility(View.VISIBLE);
            edittextCameraFileLayout.setVisibility(View.GONE);
            bottomSheetFormattingAreaLayout.setVisibility(View.GONE);
            rtEditText.requestLayout();
            bottom_sheet.requestLayout();

        }

        ((LockableBottomSheetBehavior) bottomSheetBehavior).setLocked(false);

        //edittextCameraFileLayout.setVisibility(View.GONE);
    }


    private void hideSoftKeyboard(View view){

        isSoftInputShown = false;
        isLayoutChangingFromMaxHeightChange = false;
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rtEditText.getWindowToken(),0);

    }
    private void ShowSoftKeyboard(View view){


        isSoftInputShown = true;
        isLayoutChangingFromMaxHeightChange = false;
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

    }


    private void animateFormattingToolbarDisplay(){

        rtEditText.requestFocus();
        Transition transition = new Slide(Gravity.BOTTOM);
        transition.setDuration(250);
        transition.addTarget(QandAToolbarDisplayLayout);
        transition.addTarget(QandAformattingAreaLayout);
        TransitionManager.beginDelayedTransition(QandAformattingAreaLayout,transition);
        QandAattachmentLayout.setVisibility(View.GONE);
        QandAToolbarDisplayLayout.setVisibility(View.VISIBLE);
        QandAformattingAreaLayout.setBackgroundColor(Color.parseColor("#f9f9f9"));

    }


    private void animateFormattingToolbarFromBottomSheetToActivity(){

        rtEditText.requestFocus();
        Transition transition = new Slide(Gravity.BOTTOM);
        transition.setDuration(250);
        transition.addTarget(QandAToolbarDisplayLayout);
        transition.addTarget(QandAformattingAreaLayout);
        TransitionManager.beginDelayedTransition(QandAformattingAreaLayout,transition);
        bottomSheetFormattingAreaLayout.setVisibility(View.GONE);
        QandAattachmentLayout.setVisibility(View.GONE);
        QandAToolbarDisplayLayout.setVisibility(View.VISIBLE);
        QandAformattingAreaLayout.setBackgroundColor(Color.parseColor("#f9f9f9"));

    }


    private void initPictureSelectRecyclerView() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(ForumBaseActivity.this, 3);
        messagingAreaPictureSelectAdapter = new MessagingAreaPictureSelectAdapter(pictureDisplayModelArrayList, this);
        MessagingAreaAttachmentsSelectRecyclerView.setLayoutManager(gridLayoutManager);
        MessagingAreaAttachmentsSelectRecyclerView.setAdapter(messagingAreaPictureSelectAdapter);

    }

    private void initFileSelectRecyclerView() {

        MessagingAreaFileSelectModel model = new MessagingAreaFileSelectModel();
        messagingAreaFileSelectModelArrayList = new ArrayList<>();
        for(int i = 0; i < 20; i++){

            messagingAreaFileSelectModelArrayList.add(model);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        MessagingAreaFileSelectAdapter messagingAreaFileSelectAdapter = new MessagingAreaFileSelectAdapter(messagingAreaFileSelectModelArrayList,ForumBaseActivity.this);
        MessagingAreaAttachmentsSelectRecyclerView.setLayoutManager(linearLayoutManager);
        MessagingAreaAttachmentsSelectRecyclerView.setAdapter(messagingAreaFileSelectAdapter);

    }

    @Override
    public void onBackPressed() {

        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {

            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        else {
            super.onBackPressed();

        }
    }

    @Override
    public void onPause(){

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        super.onPause();
        if(forumAdapter != null){

            forumAdapter.pausePlayBack();
        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (forumAdapter != null) {

            forumAdapter.destroyPlayer();
        }
    }

    private void populateView(){

        MessagingAreaPictureSelectModel messagingAreaPictureSelectModel = new MessagingAreaPictureSelectModel();
        pictureDisplayModelArrayList = new ArrayList<>();

        for(int i = 0; i < 27; i++){

            pictureDisplayModelArrayList.add(messagingAreaPictureSelectModel);
        }
    }




    private void setUpAttachmentsView(){

        bottomSheetAttachmentList = new ArrayList<>();
        MessagingAreaAttachmentModel attachmentModel = new MessagingAreaAttachmentModel(0);
        MessagingAreaAttachmentModel attachmentModel1 = new MessagingAreaAttachmentModel(1);

        for(int i = 0; i < 10; i++){

            bottomSheetAttachmentList.add(attachmentModel);
            bottomSheetAttachmentList.add(attachmentModel1);
        }

        bottomSheetAttachmentRecyclerView = findViewById(R.id.bottom_sheet_attachments_recyclerview);
        AttachmentsAdapter = new MessagingAreaAttachmentsAdapter(bottomSheetAttachmentList,ForumBaseActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ForumBaseActivity.this, LinearLayoutManager.HORIZONTAL,false);
        bottomSheetAttachmentRecyclerView.setLayoutManager(linearLayoutManager);
        bottomSheetAttachmentRecyclerView.setAdapter(AttachmentsAdapter);

    }


    private void setUpQandAattachmentsView(){

        bottomSheetAttachmentList = new ArrayList<>();
        MessagingAreaAttachmentModel attachmentModel = new MessagingAreaAttachmentModel(0);
        MessagingAreaAttachmentModel attachmentModel1 = new MessagingAreaAttachmentModel(1);

        for(int i = 0; i < 10; i++){

            bottomSheetAttachmentList.add(attachmentModel);
            bottomSheetAttachmentList.add(attachmentModel1);
        }

        QandAattachmentsRecyclerView = findViewById(R.id.q_and_a_attachments_recyclerview);
        AttachmentsAdapter = new MessagingAreaAttachmentsAdapter(bottomSheetAttachmentList,ForumBaseActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ForumBaseActivity.this, LinearLayoutManager.HORIZONTAL,false);
        QandAattachmentsRecyclerView.setLayoutManager(linearLayoutManager);
        QandAattachmentsRecyclerView.setAdapter(AttachmentsAdapter);

    }



    private void authChangingPeekHeight(){

        changingPeekHeight = rtEditText.getHeight() + 30;
        authBflag = true;
        if(isBottomSheetUpdatedFromB){

            isBottomSheetUpdatedFromB = false;
        }
        else{

            if(bottomSheetBehavior.getPeekHeight() >= hiddenRealInitialEdittextHeight) {

                if (QandAattachmentsRecyclerView.getVisibility() == View.VISIBLE) {

                    if (rtEditText.getHeight() < hiddenRealInitialEdittextHeight) {
                        bottomSheetBehavior.setPeekHeight(rtEditText.getHeight() + 170);
                    } else {
                        bottomSheetBehavior.setPeekHeight(hiddenRealInitialEdittextHeight + 170);
                    }
                }
                else if(MessagingAreaAttachmentsSelectNestedScroll.getVisibility() == View.VISIBLE) {
                    Log.e("auth ch 1", "authChangingPeekHeight: ");
                    //bottomSheetBehavior.setPeekHeight(200 + softInputHeight);
                }
                else{
                    if(rtEditText.getHeight() > hiddenRealInitialEdittextHeight){

                        bottomSheetBehavior.setPeekHeight(hiddenRealInitialEdittextHeight);
                    }
                    else{

                        bottomSheetBehavior.setPeekHeight(rtEditText.getHeight() + 70);

                    }

                }
            }
            else{

                Log.e("auth ch 2", "authChangingPeekHeight: ");

                bottomSheetBehavior.setPeekHeight(changingPeekHeight);
            }

        }

    }

    private void authBottomSheetPeekHeight() {

        authBflag = true;
        isBottomSheetUpdatedFromB = true;
        changingPeekHeight = rtEditText.getHeight()+30;

        if (bottomSheetBehavior.getPeekHeight() >= hiddenRealInitialEdittextHeight) {

            if (MessagingAreaAttachmentsSelectNestedScroll.getVisibility() == View.VISIBLE) {

            } else {

                if (rtEditText.getText(RTFormat.PLAIN_TEXT).equalsIgnoreCase("") && !(QandAattachmentsRecyclerView.getVisibility() == View.VISIBLE)) {

                    Log.e("here 4", "authBottomSheetPeekHeight: ");
                    bottomSheetBehavior.setPeekHeight(150);

                } else if (!(rtEditText.getText(RTFormat.PLAIN_TEXT).equalsIgnoreCase("")) && !(QandAattachmentsRecyclerView.getVisibility() == View.VISIBLE)) {

                    Log.e("here 3", "authBottomSheetPeekHeight: ");
                    bottomSheetBehavior.setPeekHeight(hiddenRealInitialEdittextHeight);

                } else if (rtEditText.getText(RTFormat.PLAIN_TEXT).equalsIgnoreCase("") && (QandAattachmentsRecyclerView.getVisibility() == View.VISIBLE)) {

                    Log.e("here 2", "authBottomSheetPeekHeight: ");
                    bottomSheetBehavior.setPeekHeight(310);
                } else if (!(rtEditText.getText(RTFormat.PLAIN_TEXT).equalsIgnoreCase("")) && (QandAattachmentsRecyclerView.getVisibility() == View.VISIBLE)) {


                    if (changingPeekHeight > hiddenRealInitialEdittextHeight + 100) {

                        //currently in expanded state
                        Log.e("here 1", "authBottomSheetPeekHeight: ");
                    } else {

                        //in collapsed
                        Log.e("here 0", "authBottomSheetPeekHeight: ");
                        bottomSheetBehavior.setPeekHeight(changingPeekHeight + 150);

                    }



                }
            }

        } else {


            if (rtEditText.getText(RTFormat.PLAIN_TEXT).equalsIgnoreCase("")) {

                if (rtEditText.hasFocus()) {
                    //authBflag = true;
                    if(!(QandAattachmentsRecyclerView.getVisibility() == View.VISIBLE)) {

                        //focus empty attachments not visible
                        bottomSheetBehavior.setPeekHeight(150);
                        Log.e("here -2", "authBottomSheetPeekHeight: ");

                    }
                    else if (QandAattachmentsRecyclerView.getVisibility() == View.VISIBLE) {

                        //focused empty attachment visible
                        bottomSheetBehavior.setPeekHeight(310);
                        Log.e("here -3", "authBottomSheetPeekHeight: ");

                    }

                } else {


                    //no focus, empty
                    bottomSheetBehavior.setPeekHeight(100);
                    Log.e("here -4", "authBottomSheetPeekHeight: ");


                }
            } else {

                if (!(QandAattachmentsRecyclerView.getVisibility() == View.VISIBLE)) {

                    //automating height changing at this point


                } else {

                    //has focus, height not max, not empty
                    Log.e("here -6", "authBottomSheetPeekHeight: ");
                    bottomSheetBehavior.setPeekHeight(changingPeekHeight + 150);

                }
            }


        }

        //rtEditText.requestLayout();
    }

    public void initBottomSheetRecordView1(){

        rtScrollView.setVisibility(View.GONE);
        bottomsheet_picture_select_layout.setVisibility(View.GONE);
        bottomSheetFileSelect1.setVisibility(View.GONE);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,100);
        lp1.leftMargin = 30;
        lp1.rightMargin = 10;
        bottomSheetRecordView1Root.setLayoutParams(lp1);
        bottomSheetRecordView1Root.requestLayout();

    }

    public void initActivityRecordView(){

        QandAdisplayFormattingToolIcon.setVisibility(View.GONE);
        QandAattchmentsFile.setVisibility(View.GONE);
        QandAattachmentsPicturesLayout.setVisibility(View.GONE);
        QandAQuestionSelectLayout.setVisibility(View.GONE);
        QandASuggetsionSelectLayout.setVisibility(View.GONE);

        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,100);
        lp1.leftMargin = 30;
        lp1.rightMargin = 10;
        audiorecordViewTypeActivityRoot.setLayoutParams(lp1);
        audiorecordViewTypeActivityRoot.requestLayout();

    }


    public void resetActivityFormatView(){


        QandAdisplayFormattingToolIcon.setVisibility(View.VISIBLE);
        QandAattchmentsFile.setVisibility(View.VISIBLE);
        QandAattachmentsPicturesLayout.setVisibility(View.VISIBLE);
        QandAQuestionSelectLayout.setVisibility(View.VISIBLE);
        QandASuggetsionSelectLayout.setVisibility(View.VISIBLE);

        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(65,65);
        audiorecordViewTypeActivityRoot.setLayoutParams(lp1);
        audiorecordViewTypeActivityRoot.requestLayout();

    }

    public void resetBottomSheetCameraFileSelect1View(){


        bottomsheet_picture_select_layout.setVisibility(View.VISIBLE);
        bottomSheetFileSelect1.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(65,65);
        lp1.gravity = Gravity.CENTER;
        bottomSheetRecordView1Root.setLayoutParams(lp1);
        bottomSheetRecordView1Root.requestLayout();

    }

    private void animateBottomSheetType1RecordNotifyBubble() {


        rtScrollView.setVisibility(View.VISIBLE);
        recordBubbleLayoutRootBottomSheetType1.setVisibility(View.VISIBLE);
        AlphaAnimation alphaAnim = new AlphaAnimation(0.0f, 1.0f);
        alphaAnim.setDuration(400);
        alphaAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        recordBubbleLayoutRootBottomSheetType1.setAnimation(alphaAnim);


        AlphaAnimation alphaAnim2 = new AlphaAnimation(1.0f, 0.0f);
        alphaAnim2.setStartOffset(4000);
        alphaAnim2.setDuration(400);
        alphaAnim2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {

                recordBubbleLayoutRootBottomSheetType1.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        recordBubbleLayoutRootBottomSheetType1.setAnimation(alphaAnim2);

    }


    private void animateActivityRecordNotifyBubble() {

        recordBubbleLayoutRootActivity.setVisibility(View.VISIBLE);
        AlphaAnimation alphaAnim = new AlphaAnimation(0.0f, 1.0f);
        alphaAnim.setDuration(400);
        alphaAnim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        recordBubbleLayoutRootActivity.setAnimation(alphaAnim);


        AlphaAnimation alphaAnim2 = new AlphaAnimation(1.0f, 0.0f);
        alphaAnim2.setStartOffset(4000);
        alphaAnim2.setDuration(400);
        alphaAnim2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {

                recordBubbleLayoutRootActivity.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        recordBubbleLayoutRootActivity.setAnimation(alphaAnim2);

    }


    private void initializeAudioRecordView(){


        recordBubbleLayoutRootBottomSheetType1 = findViewById(R.id.record_audio_bubble_root_bottomsheet_type1);
        deleteAnimLayoutTypeActivity = findViewById(R.id.record_delete_anim_root_type_activity);
        deleteAnimLayoutTypeBottomsheet = findViewById(R.id.record_delete_anim_root_type1_bottomsheet);
        bottomSheetRecordView1Root = findViewById(R.id.bottom_sheet_record_view1_root);
        AudioRecordViewBottomSheetType1 audioRecordViewBottomSheetType1 = new AudioRecordViewBottomSheetType1();
        audioRecordViewBottomSheetType1.initDeleteAnimView(deleteAnimLayoutTypeBottomsheet);
        audioRecordViewBottomSheetType1.initView(bottomSheetRecordView1Root);
        audiorecordViewTypeActivityRoot = findViewById(R.id.activity_record_view_root);
        recordBubbleLayoutRootActivity = findViewById(R.id.record_audio_bubble_root_type_activity);
        AudioRecordViewTypeActivity audiorecordViewTypeActivity = new AudioRecordViewTypeActivity();
        audiorecordViewTypeActivity.initDeleteAnimView(deleteAnimLayoutTypeActivity);
        audiorecordViewTypeActivity.initView(audiorecordViewTypeActivityRoot);


        audiorecordViewTypeActivity.setTouchReceivedListener(new AudioRecordViewTypeActivity.onRecordTouchReceivedListener() {
            @Override
            public void onTouchReceived() {

                initActivityRecordView();
            }
        });

        audiorecordViewTypeActivity.setRecordClickReceivedListener(new AudioRecordViewTypeActivity.onRecordClickReceivedListener() {
            @Override
            public void onClickReceived() {

                animateActivityRecordNotifyBubble();
            }
        });

        audioRecordViewBottomSheetType1.setTouchReceivedListener(new AudioRecordViewBottomSheetType1.onRecordTouchReceivedListener() {
            @Override
            public void onTouchReceived() {

                initBottomSheetRecordView1();
            }
        });
        audiorecordViewTypeActivity.setRecordingListener(new AudioRecordViewTypeActivity.RecordingListener() {
            @Override
            public void onRecordingStarted() {


                ((LockableBottomSheetBehavior)bottomSheetBehavior).setLocked(true);
                deleteAnimLayoutTypeActivity.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRecordingLocked() {

            }

            @Override
            public void onRecordingCompleted() {

                resetActivityFormatView();
                ((LockableBottomSheetBehavior)bottomSheetBehavior).setLocked(false);
            }

            @Override
            public void onRecordingCanceled() {

                resetActivityFormatView();
                ((LockableBottomSheetBehavior)bottomSheetBehavior).setLocked(false);
            }

            @Override
            public void onDeleteAnimationStart() {

                resetActivityFormatView();
            }
        });

        audioRecordViewBottomSheetType1.setRecordClickReceivedListener(new AudioRecordViewBottomSheetType1.onRecordClickReceivedListener() {
            @Override
            public void onClickReceived() {

                animateBottomSheetType1RecordNotifyBubble();

            }
        });

        audioRecordViewBottomSheetType1.setRecordingListener(new AudioRecordViewBottomSheetType1.RecordingListener() {
            @Override
            public void onRecordingStarted() {

                deleteAnimLayoutTypeBottomsheet.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRecordingLocked() {

            }

            @Override
            public void onRecordingCompleted() {

                rtScrollView.setVisibility(View.VISIBLE);
                resetBottomSheetCameraFileSelect1View();
            }

            @Override
            public void onRecordingCanceled() {

                rtScrollView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onDeleteAnimationStart() {
                resetBottomSheetCameraFileSelect1View();
            }
        });

    }

    private void saveTextToSharedPref(){

        i = i + 1;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ForumBaseActivity.this);
        if(i == 1) preferences.edit().putString("1",rtEditText.getText(RTFormat.HTML)).apply();
        if(i == 2) preferences.edit().putString("2",rtEditText.getText(RTFormat.HTML)).apply();
        if(i == 3) preferences.edit().putString("3",rtEditText.getText(RTFormat.HTML)).apply();

    }


    public void setForumAdapter(ForumPostAdapter adapter){

        LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(ForumBaseActivity.this, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,false);
        QandAForumPostRecyclerView.setLayoutManager(LinearLayoutManager);
        QandAForumPostRecyclerView.setAdapter(adapter);
    }
}