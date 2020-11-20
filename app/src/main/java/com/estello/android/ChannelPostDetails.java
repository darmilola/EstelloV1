package com.estello.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import android.widget.ScrollView;


import com.deltastream.example.edittextcontroller.HorizontalRTToolbar;
import com.deltastream.example.edittextcontroller.MentionHashTagListener;
import com.deltastream.example.edittextcontroller.RTManager;
import com.deltastream.example.edittextcontroller.RTextView;
import com.deltastream.example.edittextcontroller.api.RTApi;
import com.deltastream.example.edittextcontroller.api.RTProxyImpl;
import com.deltastream.example.edittextcontroller.api.format.RTFormat;
import com.deltastream.example.edittextcontroller.api.format.RTHtml;
import com.estello.android.Adapter.ChannelMessagingCommentAdapter;
import com.estello.android.Adapter.ChannelPostAdapter;
import com.estello.android.Adapter.ForumPostAttachmentsAdapter;
import com.estello.android.Adapter.HashTagsSelectionAdapter;
import com.estello.android.Adapter.MentionSelectionAdapter;
import com.estello.android.Adapter.MessagingAreaAttachmentsAdapter;
import com.estello.android.Adapter.MessagingAreaFileSelectAdapter;
import com.estello.android.Adapter.MessagingAreaPictureSelectAdapter;
import com.estello.android.Arvi.Config;
import com.estello.android.Arvi.util.misc.ExoPlayerUtils;
import com.estello.android.Arvi.widget.PlayableItemsContainer;
import com.estello.android.Arvi.widget.PlayableItemsRecyclerView;
import com.estello.android.AudioRecordView.AudioRecordViewBottomSheetType1;
import com.estello.android.AudioRecordView.AudioRecordViewTypeActivity;
import com.estello.android.Fragments.UserProfileBottomSheet;
import com.estello.android.ViewModel.ForumPostAttachmentsModel;
import com.estello.android.ViewModel.ForumPostModel;
import com.estello.android.ViewModel.HashTagsSelectionModel;
import com.estello.android.ViewModel.LockableBottomSheetBehavior;
import com.estello.android.ViewModel.MentionSelectionModel;
import com.estello.android.ViewModel.MessagingAreaAttachmentModel;
import com.estello.android.ViewModel.MessagingAreaFileSelectModel;
import com.estello.android.ViewModel.MessagingAreaPictureSelectModel;
import com.estello.android.ViewModel.RichLinkView.RichLinkView;
import com.estello.android.ViewModel.RichLinkView.ViewListener;
import com.estello.android.ViewModel.RtEdittextScrollView;
import com.estello.android.ViewModel.User;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrListener;
import com.rd.utils.DensityUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ChannelPostDetails extends AppCompatActivity {

    boolean isLayoutChangingFromMaxHeightChange = false;
    RtEdittextScrollView rtEditText;
    HorizontalRTToolbar ChannelMessageDetailsFormatToolbar;
    RTManager ChannelPostDetailsArtManager;
    LinearLayout edittextFullScreen;
    LinearLayout edittextCameraFileLayout;
    LinearLayout ChannelPostDetailsToolbarDisplayLayout;
    LinearLayout ChannelMessageDetailsAttachmentsLayout;
    LinearLayout ChannelMessageDetailsFormattingAreaLayout;
    LinearLayout fullScreenReverseLayout;
    ImageView ChannelMessageDetailsDisplayFormattingToolIcon;
    ImageView ChannelMessageDetailsCommentSendIcon;
    ImageView ChannelMessageDetailsRemoveToolbarIcon;
    LinearLayout ChannelMessageDetailsrtToolbarLayout;
    LinearLayout ChannelMessageDetailsAttachmentsPicturesLayout;
    ImageView ChannelMessageDetailsAttchmentsFile;
    NestedScrollView channelMessageDetailsNestedScroll;

    HorizontalRTToolbar bottomSheetformatToolbar;
    LinearLayout bottomSheetToolbarDisplayLayout;
    LinearLayout bottomSheetFormattingAreaLayout;
    ImageView bottomSheetDisplayFormattingToolIcon;
    ImageView bottomSheetSendIcon;
    ImageView bottomSheetRemoveToolbarIcon;
    RTManager bottomSheetRtManager;
    ScrollView rtScrollView;
    int changingPeekHeight;
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
    CoordinatorLayout ChannelMessageDetailsRoot;
    int softInputHeight = 0;
    boolean isSoftInputShown = false;
    FloatingActionButton cameraSnap,cameraSelect;
    ImageView messagingAreaPictureSelect1Bottomsheet, messagingAreaPictureSelect2Channel;
    int mSoftInputHeight;
    ImageView moreIcon;
    Toolbar toolbar;
    boolean isPictureGalleryShown = false;
    RTApi rtApi;
    RecyclerView bottomSheetAttachmentRecyclerView, ChannelAttachmentsRecyclerView;
    int edittextHeightWithKeyboard = 0;

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
    boolean isSelectionChangedFromBottomsheetStateChange = false;
    ChannelPostAdapter forumAdapter;
    boolean authBflag = false;
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
    ChannelMessagingCommentAdapter commentAdapter;
    RecyclerView commentsRecyclerview;
    int i  = 0;

    RichLinkView richLinkView;
    RTextView rTextView;
    PlayableItemsRecyclerView playableItemsRecyclerView;
    SlidrConfig config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_post_details);
        initSlidr();
        Slidr.attach(this, config);
        initView();
        setUpAttachmentsView();
        setUpActivityAttachmentsView();
        new populateTask().execute();
        initRtTextView();
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
        }, ChannelPostDetails.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChannelPostDetails.this,LinearLayoutManager.VERTICAL,false);

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
        }, ChannelPostDetails.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChannelPostDetails.this,LinearLayoutManager.VERTICAL,false);
        mentionHashTagSelectionRecyclerView.setLayoutManager(linearLayoutManager);
        mentionHashTagSelectionRecyclerView.setAdapter(hashTagsSelectionAdapter);

    }



    public void showMentionHashTagsPopup(boolean isHashTags) {


        mentionHashtagsRoot.setVisibility(View.VISIBLE);
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT,DensityUtils.dpToPx(280));
        if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){

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


    private void initSlidr(){
        config = new SlidrConfig.Builder()
                .primaryColor(ContextCompat.getColor(this,R.color.full_transparency))
                .secondaryColor(ContextCompat.getColor(this,R.color.full_transparency))
                .scrimColor(ContextCompat.getColor(this,R.color.black))
                .listener(new SlidrListener() {
                    @Override
                    public void onSlideStateChanged(int state) {

                    }

                    @Override
                    public void onSlideChange(float percent) {

                    }

                    @Override
                    public void onSlideOpened() {

                        //getWindow().setNavigationBarColor(ContextCompat.getColor(HashTagsActivity.this,R.color.transparent));
                    }

                    @Override
                    public boolean onSlideClosed() {

                        Log.e("closed", "onPause:");

                        //getWindow().setNavigationBarColor(ContextCompat.getColor(HashTagsActivity.this,R.color.white));
                        return false;
                    }
                }).build();
    }
    @SuppressLint("ClickableViewAccessibility")
    private void initView() {

        initializeAudioRecordView();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ChannelPostDetails.this);
        mSoftInputHeight = preferences.getInt("softInputHeight", 0);
        edittextHeightWithKeyboard = preferences.getInt("edittextSize", 0);
        softInputHeight = preferences.getInt("softInputHeight", 0);
        isSoftInputRetreived = preferences.getBoolean("softInputRetrieved", false);
        isEdittextWithKeyboardShownSizeRetreived = preferences.getBoolean("edittextRetrieved", false);
        mentionHashTagSelectionRecyclerView = findViewById(R.id.mention_hashtags_selection_recyclerview);
        bottom_sheet = findViewById(R.id.post_details_messaging_area_bottomsheet);
        bottomsheet_picture_select_layout = findViewById(R.id.messaging_area_picture_select_layout_bottomsheet);
        bottomSheetFileSelect1 = findViewById(R.id.messaging_area_bottomsheet_file_select1);
        bottomSheetFileSelect2 = findViewById(R.id.messaging_area_bottomsheet_file_select2);

        rtScrollView = findViewById(R.id.Message_Details_rtEdittextScrollview);
        bottomSheetInnerLayout = findViewById(R.id.bottomsheet_inner_layout);
        bottomSheetSendIcon = findViewById(R.id.BottomSheetSendIcon);

        moreIcon = findViewById(R.id.channel_details_more_icon);
        rtEditText = findViewById(R.id.message_details_bottom_sheet_edittext);
        rtEditText.getParent().requestDisallowInterceptTouchEvent(true);
        rtEditText.setMovementMethod(new ScrollingMovementMethod());
        fullScreenReverseLayout = findViewById(R.id.messaging_fullscreen_reverse_layout);
        initialEdittextHeight = rtEditText.getMaxHeight();
        rtApi = new RTApi(ChannelPostDetails.this, new RTProxyImpl(ChannelPostDetails.this));
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);

        commentsRecyclerview = findViewById(R.id.channel_post_details_comments_recyclerview);
        cameraSelect = findViewById(R.id.channel_base_camera_select);
        cameraSnap = findViewById(R.id.channel_base_camera_snap);
        messagingAreaPictureSelect1Bottomsheet = findViewById(R.id.messaging_area_picture_select_bottomsheet);
        messagingAreaPictureSelect2Channel = findViewById(R.id.channel_base_attachments_pictures);
        cameraSnap.setVisibility(View.GONE);
        cameraSelect.setVisibility(View.GONE);
        MessagingAreaAttachmentsSelectRecyclerView = findViewById(R.id.messaging_area_picture_layout_recyclerview);
        MessagingAreaAttachmentsSelectNestedScroll = findViewById(R.id.messaging_area_pictures_layout_nested_scroll);
        MessagingAreaAttachmentsSelectNestedScroll.setVisibility(View.GONE);
        messagingAreaPictureSelect2BottomSheet = findViewById(R.id.messaging_area_picture_select2_bottomsheet);
        ChannelMessageDetailsRoot = findViewById(R.id.channel_message_details_root);
        ChannelMessageDetailsAttachmentsPicturesLayout = findViewById(R.id.channel_base_attachments_picture_layout);
        ChannelMessageDetailsAttchmentsFile = findViewById(R.id.channel_base_attachments_file_select);
        channelMessageDetailsNestedScroll  = findViewById(R.id.channel_detail_nested_scroll);
        ChannelAttachmentsRecyclerView = findViewById(R.id.channel_attachments_recyclerview);
        ChannelMessageDetailsrtToolbarLayout = findViewById(R.id.message_details_rt_toobar_layout);
        ChannelMessageDetailsFormatToolbar = findViewById(R.id.message_details_rt_toobar);
        ChannelMessageDetailsFormatToolbar.setVisibility(View.VISIBLE);
        edittextFullScreen = findViewById(R.id.BottomSheetEdittextFullScreen);
        edittextCameraFileLayout = findViewById(R.id.ChannelEdittextCameraFileIconsLayout);
        ChannelMessageDetailsCommentSendIcon = findViewById(R.id.channel_base_send_icon);
        ChannelPostDetailsToolbarDisplayLayout = findViewById(R.id.channel_base_format_toolbar_display_layout);
        ChannelMessageDetailsAttachmentsLayout = findViewById(R.id.channel_base_attachments_layout);
        ChannelMessageDetailsDisplayFormattingToolIcon = findViewById(R.id.channel_base_display_format_toolbar_icon);
        ChannelMessageDetailsFormattingAreaLayout = findViewById(R.id.channel_base_format_area_layout);
        ChannelMessageDetailsRemoveToolbarIcon = findViewById(R.id.channel_base_format_toolbar_remove);
        ChannelPostDetailsArtManager = new RTManager(rtApi);
        ChannelPostDetailsArtManager.registerToolbar(ChannelMessageDetailsrtToolbarLayout, ChannelMessageDetailsFormatToolbar);
        ChannelPostDetailsArtManager.registerEditor(rtEditText, true);
        rtEditText.setRichTextEditing(true, true);
        bottomSheetFormattingAreaLayout = findViewById(R.id.FormatAreaLayout);
        bottomSheetDisplayFormattingToolIcon = findViewById(R.id.DisplayFormatToolbarIcon);
        bottomSheetToolbarDisplayLayout = findViewById(R.id.FormatToolBarDisplayLayout);
        bottomSheetRemoveToolbarIcon = findViewById(R.id.FormatToolbarRemove);
        bottomSheetformatToolbar = findViewById(R.id.FormatToolbar);
        mentionHashtagsRoot = findViewById(R.id.mention_hashtags_selection_layout_root);
        richLinkView = findViewById(R.id.richlinkview);
        playableItemsRecyclerView = findViewById(R.id.channel_details_message_attachments_recyclerview);
        rTextView = findViewById(R.id.channel_post_details_textview);


        toolbar = findViewById(R.id.channel_message_details_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(ChannelPostDetails.this,R.color.black), PorterDuff.Mode.SRC_ATOP);


        ((LockableBottomSheetBehavior) bottomSheetBehavior).setLocked(true);


        rTextView.setHashTagClickedListener(new RTextView.HashTagClickedListener() {
            @Override
            public void onHashTagClicked(int position) {

                Intent intent = new Intent(ChannelPostDetails.this, HashTagsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        rTextView.setMentionClickedListener(new RTextView.MentionClickedListener() {
            @Override
            public void onMentionClicked(int clickedPosition) {

                UserProfileBottomSheet bottomSheet = new UserProfileBottomSheet();
                bottomSheet.show(getSupportFragmentManager(), "userprofile");
            }
        });



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
                ((LockableBottomSheetBehavior) bottomSheetBehavior).setLocked(false);
                mentionHashtagsRoot.setVisibility(View.GONE);
                bottom_sheet.requestLayout();

            }

            @Override
            public void onStopHashTags() {

                selectionChangeFromMentioning = false;
                ((LockableBottomSheetBehavior) bottomSheetBehavior).setLocked(false);
                mentionHashtagsRoot.setVisibility(View.GONE);
                bottom_sheet.requestLayout();


            }

            @Override
            public void onMentionStarted() {

                selectionChangeFromMentioning = true;
                ((LockableBottomSheetBehavior) bottomSheetBehavior).setLocked(true);
                bottom_sheet.requestLayout();
                selectionInStartMentioning = rtEditText.getSelectionStart();
                showMentionHashTagsPopup(false);


            }

            @Override
            public void onHashTagsStarted() {


                selectionChangeFromMentioning = true;
                ((LockableBottomSheetBehavior) bottomSheetBehavior).setLocked(true);
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
                    ChannelAttachmentsRecyclerView.setVisibility(View.GONE);
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
                    ChannelAttachmentsRecyclerView.setVisibility(View.GONE);
                    if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {

                        initExpandedUi();

                    } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {

                        initCollapsedUi();

                    }

                }


            }

        });

        ChannelMessageDetailsAttchmentsFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(MessagingAreaAttachmentsSelectNestedScroll.getVisibility() == View.VISIBLE){

                    initFileSelectRecyclerView();
                }
                else {

                    initFileSelectRecyclerView();
                    hideSoftKeyboard(rtEditText);
                    MessagingAreaAttachmentsSelectNestedScroll.setVisibility(View.VISIBLE);
                    ChannelAttachmentsRecyclerView.setVisibility(View.GONE);
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
                    ChannelAttachmentsRecyclerView.setVisibility(View.GONE);
                    if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {

                        initExpandedUi();

                    } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {

                        initCollapsedUi();

                    }

                }

            }

        });
        messagingAreaPictureSelect2Channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (MessagingAreaAttachmentsSelectNestedScroll.getVisibility() == View.VISIBLE) {

                    initPictureSelectRecyclerView();

                } else {

                    initPictureSelectRecyclerView();
                    hideSoftKeyboard(rtEditText);
                    MessagingAreaAttachmentsSelectNestedScroll.setVisibility(View.VISIBLE);
                    ChannelAttachmentsRecyclerView.setVisibility(View.GONE);
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
                    ChannelAttachmentsRecyclerView.setVisibility(View.GONE);
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
                //               ChannelAttachmentsRecyclerView.setVisibility(View.GONE);
                //bottomSheetBehavior.setPeekHeight(mSoftInputHeight + 120);
                //((LockableBottomSheetBehavior)bottomSheetBehavior).setLocked(false);
            }
        });

        moreIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPostToolsBottomSheet(1);
            }
        });

        ChannelMessageDetailsCommentSendIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChannelAttachmentsRecyclerView.setVisibility(View.VISIBLE);
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

                if(authBflag){

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



        ChannelMessageDetailsRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Log.e("layout changing", "onGlobalLayout: ");
                ChannelMessageDetailsRoot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int approxSoftInputHeight = ChannelMessageDetailsRoot.getRootView().getHeight() - ChannelMessageDetailsRoot.getHeight();

                 if(isLayoutChangingFromMaxHeightChange){

                    isLayoutChangingFromMaxHeightChange = false;

                 }
                 else {

                    if (approxSoftInputHeight > DensityUtils.dpToPx(250)){


                        isSoftInputShown = true;

                        if(isSoftInputRetreived){


                        }
                        if(isEdittextWithKeyboardShownSizeRetreived){

                        }
                        else {



                            softInputHeight = ChannelMessageDetailsRoot.getRootView().getHeight() - ChannelMessageDetailsRoot.getHeight();
                            edittextHeightWithKeyboard = ChannelMessageDetailsRoot.getRootView().getHeight() - softInputHeight - 70;
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ChannelPostDetails.this);
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

                ChannelMessageDetailsRoot.getViewTreeObserver().addOnGlobalLayoutListener(this);

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
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        lp.bottomMargin = 0;
                        lp.weight = 4.3f;
                        lp.width = 0;
                        rtScrollView.setLayoutParams(lp);
                        rtEditText.setPadding(20, 0, 10, 0);
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

        ChannelMessageDetailsDisplayFormattingToolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animateFormattingToolbarDisplay();


            }
        });

        ChannelMessageDetailsRemoveToolbarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChannelPostDetailsToolbarDisplayLayout.setVisibility(View.GONE);
                ChannelMessageDetailsAttachmentsLayout.setVisibility(View.VISIBLE);
                ChannelMessageDetailsFormattingAreaLayout.setBackgroundColor(Color.parseColor("#ffffff"));

            }
        });



    }


    @Override
    public void onResume() {

        super.onResume();
        if(forumAdapter != null){

            //forumAdapter.resumePlayBack();
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            getWindow().setNavigationBarColor(ContextCompat.getColor(this,R.color.transparent));
            getWindow().setStatusBarColor(ContextCompat.getColor(ChannelPostDetails.this,R.color.white));
            //getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        }


    }

    @Override
    public void onBackPressed() {

        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {

            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        else {
            super.onBackPressed();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }

    @Override
    public void onPause(){

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        super.onPause();
        Log.e("paused", "onPause: ");
        if(forumAdapter != null){

            //forumAdapter.pausePlayBack();
        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("destroyed", "onDestroy: ");

       /*   if (ChannelPostDetailsArtManager != null) {
              ChannelPostDetailsArtManager.onDestroy(true);
        }*/
        if (forumAdapter != null) {

            //forumAdapter.destroyPlayer();
        }

    }
    @Override public void onStop(){
        super.onStop();
        Log.e("stopped", "onStop: ");
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
            ChannelMessageDetailsFormattingAreaLayout.setVisibility(View.GONE);
            edittextCameraFileLayout.setVisibility(View.GONE);
            bottomSheetBehavior.setPeekHeight(softInputHeight);
            bottomSheetFormattingAreaLayout.setVisibility(View.VISIBLE);
            rtEditText.clearFocus();


        }
        else {

            MessagingAreaAttachmentsSelectRecyclerView.setNestedScrollingEnabled(false);
            MessagingAreaAttachmentsSelectNestedScroll.setNestedScrollingEnabled(false);
            ChannelAttachmentsRecyclerView.setNestedScrollingEnabled(false);
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
            if(ChannelAttachmentsRecyclerView.getVisibility() == View.VISIBLE){
                rtEditText.setPadding(20, 20, 3, 180);
            }
            else {

                rtEditText.setPadding(20, 50, 3, 70);
            }
            ChannelMessageDetailsFormattingAreaLayout.setVisibility(View.VISIBLE);
            edittextCameraFileLayout.setVisibility(View.GONE);
            bottomSheetFormattingAreaLayout.setVisibility(View.GONE);
            rtEditText.requestLayout();
            rtScrollView.requestLayout();
            bottom_sheet.requestLayout();

            if (isSoftInputShown) {

                if(ChannelAttachmentsRecyclerView.getVisibility() == View.VISIBLE){

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
                if(ChannelAttachmentsRecyclerView.getVisibility() == View.VISIBLE){

                    rtEditText.setMaxHeight(ChannelMessageDetailsRoot.getRootView().getHeight() - 300);
                }
                else{
                    rtEditText.setMaxHeight(ChannelMessageDetailsRoot.getRootView().getHeight() - 200);
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
        if(MessagingAreaAttachmentsSelectNestedScroll.getVisibility() == View.VISIBLE){

            MessagingAreaAttachmentsSelectRecyclerView.setNestedScrollingEnabled(false);
            MessagingAreaAttachmentsSelectNestedScroll.setNestedScrollingEnabled(true);
            isPictureGalleryShown = true;
            rtEditText.setPadding(20, 0, 10, 0);
            ChannelMessageDetailsFormattingAreaLayout.setVisibility(View.GONE);
            edittextCameraFileLayout.setVisibility(View.GONE);
            bottomSheetFormattingAreaLayout.setVisibility(View.VISIBLE);
            bottomSheetBehavior.setPeekHeight(softInputHeight);
            cameraSelect.setVisibility(View.VISIBLE);
            cameraSnap.setVisibility(View.VISIBLE);
            rtEditText.setMaxHeight(50);
            rtEditText.clearFocus();
            ((LockableBottomSheetBehavior) bottomSheetBehavior).setLocked(false);
            //rtEditText.requestLayout();


        }

        else {


            if (rtEditText.getText(RTFormat.PLAIN_TEXT).isEmpty() && !isSoftInputShown &&!(ChannelAttachmentsRecyclerView.getVisibility() == View.VISIBLE)) {

                resetBottomSheet();
            } else {


                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

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
                if (ChannelAttachmentsRecyclerView.getVisibility() == View.VISIBLE) {

                    rtEditText.setPadding(20, 0, 10, 70);
                } else {

                    rtEditText.setPadding(20, 0, 10, 90);
                }

                rtEditText.setMaxHeight(hiddenRealInitialEdittextHeight);
                //authBottomSheetPeekHeight();
                ChannelMessageDetailsFormattingAreaLayout.setVisibility(View.VISIBLE);
                edittextCameraFileLayout.setVisibility(View.GONE);
                bottomSheetFormattingAreaLayout.setVisibility(View.GONE);
                rtEditText.requestLayout();
                bottom_sheet.requestLayout();


                ((LockableBottomSheetBehavior) bottomSheetBehavior).setLocked(false);
            }


        }



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
        transition.addTarget(ChannelPostDetailsToolbarDisplayLayout);
        transition.addTarget(ChannelMessageDetailsFormattingAreaLayout);
        TransitionManager.beginDelayedTransition(ChannelMessageDetailsFormattingAreaLayout,transition);
        ChannelMessageDetailsAttachmentsLayout.setVisibility(View.GONE);
        ChannelPostDetailsToolbarDisplayLayout.setVisibility(View.VISIBLE);
        ChannelMessageDetailsFormattingAreaLayout.setBackgroundColor(Color.parseColor("#f9f9f9"));

    }


    private void animateFormattingToolbarFromBottomSheetToActivity(){

        rtEditText.requestFocus();
        Transition transition = new Slide(Gravity.BOTTOM);
        transition.setDuration(250);
        transition.addTarget(ChannelPostDetailsToolbarDisplayLayout);
        transition.addTarget(ChannelMessageDetailsFormattingAreaLayout);
        TransitionManager.beginDelayedTransition(ChannelMessageDetailsFormattingAreaLayout,transition);
        bottomSheetFormattingAreaLayout.setVisibility(View.GONE);
        ChannelMessageDetailsAttachmentsLayout.setVisibility(View.GONE);
        ChannelPostDetailsToolbarDisplayLayout.setVisibility(View.VISIBLE);
        ChannelMessageDetailsFormattingAreaLayout.setBackgroundColor(Color.parseColor("#f9f9f9"));

    }


    private void initPictureSelectRecyclerView() {

        MessagingAreaPictureSelectModel messagingAreaPictureSelectModel = new MessagingAreaPictureSelectModel();
        pictureDisplayModelArrayList = new ArrayList<>();

        for(int i = 0; i < 27; i++){

            pictureDisplayModelArrayList.add(messagingAreaPictureSelectModel);
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(ChannelPostDetails.this, 3);
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
        MessagingAreaFileSelectAdapter messagingAreaFileSelectAdapter = new MessagingAreaFileSelectAdapter(messagingAreaFileSelectModelArrayList, ChannelPostDetails.this);
        MessagingAreaAttachmentsSelectRecyclerView.setLayoutManager(linearLayoutManager);
        MessagingAreaAttachmentsSelectRecyclerView.setAdapter(messagingAreaFileSelectAdapter);

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
        AttachmentsAdapter = new MessagingAreaAttachmentsAdapter(bottomSheetAttachmentList, ChannelPostDetails.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChannelPostDetails.this, LinearLayoutManager.HORIZONTAL,false);
        bottomSheetAttachmentRecyclerView.setLayoutManager(linearLayoutManager);
        bottomSheetAttachmentRecyclerView.setAdapter(AttachmentsAdapter);

    }


    private void setUpActivityAttachmentsView(){

        bottomSheetAttachmentList = new ArrayList<>();
        MessagingAreaAttachmentModel attachmentModel = new MessagingAreaAttachmentModel(0);
        MessagingAreaAttachmentModel attachmentModel1 = new MessagingAreaAttachmentModel(1);

        for(int i = 0; i < 10; i++){

            bottomSheetAttachmentList.add(attachmentModel);
            bottomSheetAttachmentList.add(attachmentModel1);
        }

        ChannelAttachmentsRecyclerView = findViewById(R.id.channel_attachments_recyclerview);
        AttachmentsAdapter = new MessagingAreaAttachmentsAdapter(bottomSheetAttachmentList, ChannelPostDetails.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChannelPostDetails.this, LinearLayoutManager.HORIZONTAL,false);
        ChannelAttachmentsRecyclerView.setLayoutManager(linearLayoutManager);
        ChannelAttachmentsRecyclerView.setAdapter(AttachmentsAdapter);

    }



    private void authChangingPeekHeight() {

        changingPeekHeight = rtEditText.getHeight() + 50;
        authBflag = false;

        if (isBottomSheetUpdatedFromB) {

            isBottomSheetUpdatedFromB = false;

        } else {

            if(bottomSheetBehavior.getPeekHeight() >= hiddenRealInitialEdittextHeight) {

                if (ChannelAttachmentsRecyclerView.getVisibility() == View.VISIBLE) {

                    if (rtEditText.getHeight() < hiddenRealInitialEdittextHeight) {

                        bottomSheetBehavior.setPeekHeight(rtEditText.getHeight() + 190);
                    } else {
                        bottomSheetBehavior.setPeekHeight(hiddenRealInitialEdittextHeight + 190);
                    }
                } else if (MessagingAreaAttachmentsSelectNestedScroll.getVisibility() == View.VISIBLE) {
                    Log.e("auth ch 1", "authChangingPeekHeight: ");
                    //bottomSheetBehavior.setPeekHeight(200 + softInputHeight);
                } else {
                    if (rtEditText.getHeight() > hiddenRealInitialEdittextHeight) {

                        bottomSheetBehavior.setPeekHeight(hiddenRealInitialEdittextHeight);
                    } else {

                        bottomSheetBehavior.setPeekHeight(rtEditText.getHeight() + 70);

                    }

                }
            } else {

                Log.e("auth ch 2", "authChangingPeekHeight: ");
                if(ChannelAttachmentsRecyclerView.getVisibility() == View.VISIBLE){
                    bottomSheetBehavior.setPeekHeight(changingPeekHeight + 150);
                }
                else {
                    bottomSheetBehavior.setPeekHeight(changingPeekHeight);
                }
            }
            authNestedScrollViewHeightWithBottomSheetHeight(changingPeekHeight);
        }
    }

    private void authBottomSheetPeekHeight() {

        authBflag = false;
        isBottomSheetUpdatedFromB = true;
        changingPeekHeight = rtEditText.getHeight() + 50;
        bottomSheetBehavior.setPeekHeight(changingPeekHeight + 150);
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

        ChannelMessageDetailsDisplayFormattingToolIcon.setVisibility(View.GONE);
        ChannelMessageDetailsAttchmentsFile.setVisibility(View.GONE);
        ChannelMessageDetailsAttachmentsPicturesLayout.setVisibility(View.GONE);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,100);
        lp1.leftMargin = 30;
        lp1.rightMargin = 10;
        audiorecordViewTypeActivityRoot.setLayoutParams(lp1);
        audiorecordViewTypeActivityRoot.requestLayout();

    }


    public void resetActivityFormatView(){


        ChannelMessageDetailsDisplayFormattingToolIcon.setVisibility(View.VISIBLE);
        ChannelMessageDetailsAttchmentsFile.setVisibility(View.VISIBLE);
        ChannelMessageDetailsAttachmentsPicturesLayout.setVisibility(View.VISIBLE);
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
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ChannelPostDetails.this);
        if(i == 1) preferences.edit().putString("1",rtEditText.getText(RTFormat.HTML)).apply();
        if(i == 2) preferences.edit().putString("2",rtEditText.getText(RTFormat.HTML)).apply();
        if(i == 3) preferences.edit().putString("3",rtEditText.getText(RTFormat.HTML)).apply();

    }


    public void showPostToolsBottomSheet(int position){
        new PostToolsBottomSheet().show(getSupportFragmentManager(),"postTools");
    }




    public static class PostToolsBottomSheet extends BottomSheetDialogFragment {


        View view;
        private  BottomSheetBehavior bottomSheetBehavior;
        FrameLayout bottom_sheet;
        BottomSheetDialog dialog;
        public ChannelBaseActivity.PostToolsBottomSheet postToolsBottomSheet(){
            return new ChannelBaseActivity.PostToolsBottomSheet();
        }
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
        }

        @NotNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            dialog = (BottomSheetDialog)super.onCreateDialog(savedInstanceState);

            dialog.setContentView(R.layout.channel_post_item_tool_bottomsheet);
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

                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                }


            });

        }
    }

    private void initRtTextView(){


        ForumPostAttachmentsModel forumPostAttachmentsModel3 = new ForumPostAttachmentsModel(2, "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4", "https://i.pinimg.com/564x/df/10/f8/df10f827ca7e1a2eee027b1c0998475f.jpg");

        ArrayList<ForumPostAttachmentsModel> forumPostAttachmentsModelArrayList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {

            forumPostAttachmentsModelArrayList.add(forumPostAttachmentsModel3);

        }


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ChannelPostDetails.this);
        String text =  preferences.getString("2","");
        RTHtml rtHtml = new RTHtml(text);
        rTextView.setText(rtHtml);

         LinearLayoutManager LinearLayoutManager2 = new LinearLayoutManager(ChannelPostDetails.this, LinearLayoutManager.VERTICAL, false);
        //LinearLayoutManager2.setInitialPrefetchItemCount(forumPostList.get(position).getPostAttachmentList().size());
        playableItemsRecyclerView.setLayoutManager(LinearLayoutManager2);
        playableItemsRecyclerView.setPlaybackTriggeringStates(PlayableItemsContainer.PlaybackTriggeringState.SETTLING, PlayableItemsContainer.PlaybackTriggeringState.DRAGGING);
        playableItemsRecyclerView.setAutoplayEnabled(false);
        playableItemsRecyclerView.setAutoplayMode(PlayableItemsContainer.AutoplayMode.ONE_AT_A_TIME);
        Config config = new Config.Builder().cache(ExoPlayerUtils.getCache(ChannelPostDetails.this)).build();
        ForumPostAttachmentsAdapter forumPostAttachmentsAdapter = new ForumPostAttachmentsAdapter(ChannelPostDetails.this, forumPostAttachmentsModelArrayList, config, new ForumPostAttachmentsAdapter.NewPlayerStarted() {
            @Override
            public void onNewPlayerStarted() {

            }
        });
        playableItemsRecyclerView.setAdapter(forumPostAttachmentsAdapter);
        playableItemsRecyclerView.onResume();

        if(richLinkView != null)
            richLinkView.setLink("https://medium.com/@allisonmorgan/short-essay-on-web-crawling-scraping-8abf1b232b65", new ViewListener() {

                @Override
                public void onSuccess(boolean status) {
                    try {
                        if(richLinkView != null) {

                            richLinkView.setLinkFromMeta(richLinkView.getMetaData());
                        }
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Exception e) {

                    if(richLinkView != null) {

                        //postViewHolder.richLinkView.setVisibility(View.GONE);
                    }
                }
            });

    }






    @SuppressLint("UseSparseArrays")
    public class populateTask extends AsyncTask {

        ArrayList<ForumPostModel> forumPostModelArrayList;
        LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(ChannelPostDetails.this, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false);


        @Override
        protected Object doInBackground(Object[] objects) {

            User user = new User();
            ArrayList<User> userArrayList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {

                userArrayList.add(user);
            }


            ArrayList<ForumPostAttachmentsModel> forumPostAttachmentsModelArrayList = new ArrayList<>();
            for (int i = 0; i < 1; i++) {

            }
            ForumPostModel postModel = new ForumPostModel(userArrayList, forumPostAttachmentsModelArrayList, 0);
            postModel.setPostGroupDate("October 15 2020");
            ForumPostModel postModel3 = new ForumPostModel(userArrayList, forumPostAttachmentsModelArrayList, 1);
            ForumPostModel postModel4 = new ForumPostModel(userArrayList, forumPostAttachmentsModelArrayList, 1);
            forumPostModelArrayList = new ArrayList<>();
            for (int i = 0; i < 1; i++) {
                forumPostModelArrayList.add(postModel);
                for (int j = 0; j < 1; j++) {

                    forumPostModelArrayList.add(postModel3);
                    forumPostModelArrayList.add(postModel4);
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {

            commentAdapter = new ChannelMessagingCommentAdapter(forumPostModelArrayList, ChannelPostDetails.this, new ChannelMessagingCommentAdapter.MentionClickedListener() {
                @Override
                public void onMentionClicked(int position) {

                    UserProfileBottomSheet bottomSheet = new UserProfileBottomSheet();
                    bottomSheet.show(getSupportFragmentManager(), "userprofile");
                }
            }, new ChannelMessagingCommentAdapter.ProfilePictureClickedListener() {
                @Override
                public void onProfilePictureClicked(int position) {

                    UserProfileBottomSheet bottomSheet = new UserProfileBottomSheet();
                    bottomSheet.show(getSupportFragmentManager(), "userprofile");
                }
            }, new ChannelMessagingCommentAdapter.hashTagClickListener() {
                @Override
                public void onHashTagClicked(int position) {

                    Intent intent = new Intent(ChannelPostDetails.this, HashTagsActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }, new ChannelMessagingCommentAdapter.PostLongClickedListener() {
                @Override
                public void onPostLongClicked(int position) {
                    showPostToolsBottomSheet(position);
                }
            });

            commentsRecyclerview.setLayoutManager(LinearLayoutManager);
            commentsRecyclerview.setAdapter(commentAdapter);

        }


    }

    private void resetBottomSheet(){

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.bottomMargin = DensityUtils.dpToPx(10);
        lp.weight = 4.3f;
        lp.width = 0;
        rtScrollView.setLayoutParams(lp);
        rtScrollView.requestLayout();
        rtEditText.requestLayout();
        rtEditText.clearFocus();
        bottomSheetToolbarDisplayLayout.setVisibility(View.GONE);
        edittextCameraFileLayout.setVisibility(View.VISIBLE);
        edittextFullScreen.setVisibility(View.GONE);
        ChannelMessageDetailsFormattingAreaLayout.setVisibility(View.GONE);
        bottomSheetBehavior.setPeekHeight(DensityUtils.dpToPx(44));
        ((LockableBottomSheetBehavior) bottomSheetBehavior).setLocked(true);
    }

    private void authNestedScrollViewHeightWithBottomSheetHeight(int heightChange){

        commentsRecyclerview.setPadding(0,0,0,heightChange);

    }

}
