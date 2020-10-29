package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.allattentionhere.autoplayvideos.AAH_CustomRecyclerView;
import com.estello.android.Adapter.PersonalProfilePostAdapter;
import com.estello.android.ViewModel.NetworkPostImageModel;
import com.estello.android.ViewModel.NetworkPostModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;


import java.util.ArrayList;

public class PersonalProfilePage extends AppCompatActivity {

    AAH_CustomRecyclerView recyclerView;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ArrayList<NetworkPostModel> postModelArrayList = new ArrayList<>();
    Toolbar toolbar;
    TextView profileName,profileProfession,profileAbout,webLink,followersTitle,followersValue,followingTitle,followingValue,tutoredTitle,TutoredValue;
    TextView tutoredValue,topProfileName;
    MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
    ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel();
    private BottomSheetBehavior bottomSheetBehavior;
    private LinearLayout bottom_sheet;
    ImageView more;
    TextView edit,settings,earnings,verification,invite,help,downloads,mycourses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile_page);
        populatePost();
        initView();
        initRecyclerView();
        setOnclickListener();
    }


    private void initRecyclerView(){
        recyclerView = findViewById(R.id.personal_profile_post_recycler_view);
        toolbar = findViewById(R.id.personal_profile_toolbar);
        PersonalProfilePostAdapter personalProfilePostAdapter = new PersonalProfilePostAdapter(postModelArrayList,PersonalProfilePage.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PersonalProfilePage.this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setActivity(PersonalProfilePage.this);
        recyclerView.setVisiblePercent(40);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(personalProfilePostAdapter);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(PersonalProfilePage.this,R.color.White), PorterDuff.Mode.SRC_ATOP);

    }

    private void initView(){
        //topProfileName = findViewById(R.id.personal_profile_page_toolbar_name);

        appBarLayout = findViewById(R.id.profile_page_app_bar);
        collapsingToolbarLayout = findViewById(R.id.profile_page_collapsing_toolbar_layout);
        profileName = findViewById(R.id.personal_profile_profile_name);
        profileProfession = findViewById(R.id.personal_profile_profession);
        profileAbout = findViewById(R.id.personal_profile_about);
        webLink = findViewById(R.id.personal_profile_web_link);
        followersTitle = findViewById(R.id.personal_profile_followers_title);
        followingValue = findViewById(R.id.personal_profile_following_value);
        followersValue = findViewById(R.id.personal_profile_followers_value);
        followingTitle = findViewById(R.id.personal_profile_following_title);
        tutoredValue = findViewById(R.id.personal_profile_tutored_value);
        tutoredTitle = findViewById(R.id.personal_profile_tutored_title);
        bottom_sheet = findViewById(R.id.profile_bottom_sheet_layout);
        downloads = findViewById(R.id.personal_profile_downloaded_materials);
        mycourses = findViewById(R.id.personal_profile_my_courses);
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        more = findViewById(R.id.persnal_profile_more);
        edit = findViewById(R.id.editprofile);
        settings = findViewById(R.id.profile_settings);
        earnings = findViewById(R.id.profile_earning);
        verification = findViewById(R.id.profile_verification);
        invite = findViewById(R.id.profile_invite_a_friend);
        help = findViewById(R.id.profile__help);

        Typeface customfont= Typeface.createFromAsset(getAssets(), "font/latoreg.ttf");
        Typeface customfont2= Typeface.createFromAsset(getAssets(),"montsemi.ttf");


        edit.setTypeface(customfont);
        collapsingToolbarLayout.setCollapsedTitleTypeface(customfont2);
        settings.setTypeface(customfont);
        earnings.setTypeface(customfont);
        mycourses.setTypeface(customfont);
        downloads.setTypeface(customfont);
        verification.setTypeface(customfont);
        invite.setTypeface(customfont);
        help.setTypeface(customfont);
       // topProfileName.setTypeface(customfont);
        profileName.setTypeface(customfont2);
        profileProfession.setTypeface(customfont2);
        profileAbout.setTypeface(customfont);
        webLink.setTypeface(customfont);
        followingTitle.setTypeface(customfont);
        followingValue.setTypeface(customfont);
        followersValue.setTypeface(customfont);
        followersTitle.setTypeface(customfont);
        tutoredTitle.setTypeface(customfont);
        tutoredValue.setTypeface(customfont);


        materialShapeDrawable.setFillColor(ColorStateList.valueOf(ContextCompat.getColor(PersonalProfilePage.this,R.color.light_primary)));
       // shapeAppearanceModel.setTopLeftCorner(CornerFamily.ROUNDED, 55);
        //shapeAppearanceModel.setTopRightCorner(CornerFamily.ROUNDED,55);

        materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        bottom_sheet.setBackground(materialShapeDrawable);


    }

    private void setOnclickListener(){

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                else{
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        mycourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalProfilePage.this,MyCourses.class));
            }
        });

        downloads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalProfilePage.this,Downloads.class));
            }
        });

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {

            boolean isShown = true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if(scrollRange + verticalOffset == 0){


                    collapsingToolbarLayout.setTitle("Introduction to Computer Programming");
                    isShown = true;
                    return;
                }
                else if(isShown){


                    isShown = false;
                    collapsingToolbarLayout.setTitle("");
                    return;
                }
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        if (menuItem.getItemId() == android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
    private void populatePost(){

        NetworkPostImageModel networkPostImageModel = new NetworkPostImageModel("","https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
        NetworkPostImageModel networkPostImageModel1 = new NetworkPostImageModel("","https://homepages.cae.wisc.edu/~ece533/images/baboon.png");

        NetworkPostImageModel networkPostImageMode2 = new NetworkPostImageModel("","https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
        NetworkPostImageModel networkPostImageModel3 = new NetworkPostImageModel("","https://homepages.cae.wisc.edu/~ece533/images/peppers.png");


        ArrayList<NetworkPostImageModel> networkPostImageModels = new ArrayList<>();

        networkPostImageModels.add(networkPostImageModel);
        networkPostImageModels.add(networkPostImageModel1);
        ArrayList<NetworkPostImageModel> networkPostImageModels2 = new ArrayList<>();
        networkPostImageModels2.add(networkPostImageMode2);

        ArrayList<NetworkPostImageModel> networkPostImageModels3 = new ArrayList<>();
        networkPostImageModels3.add(networkPostImageMode2);
        networkPostImageModels3.add(networkPostImageModel1);
        networkPostImageModels3.add(networkPostImageModel3);
        networkPostImageModels3.add(networkPostImageModel);





        NetworkPostModel postModel = new NetworkPostModel();
        NetworkPostModel postModel1 = new NetworkPostModel();
        NetworkPostModel postModel3 = new NetworkPostModel();
        NetworkPostModel postModel4 = new NetworkPostModel();
        NetworkPostModel postModel5 = new NetworkPostModel();
        postModel4.setNetworkPostImageModelArrayList(networkPostImageModels2);
        postModel3.setNetworkPostImageModelArrayList(networkPostImageModels);
        postModel5.setNetworkPostImageModelArrayList(networkPostImageModels3);
        postModel.setViewType(1);
        postModel1.setViewType(2);
        postModel3.setViewType(3);
        postModel4.setViewType(3);
        postModel5.setViewType(3);
        for(int i = 0; i < 1; i++){
            postModelArrayList.add(postModel);
            postModelArrayList.add(postModel1);
            postModelArrayList.add(postModel3);
            postModelArrayList.add(postModel4);
            postModelArrayList.add(postModel5);
        }
    }
}
