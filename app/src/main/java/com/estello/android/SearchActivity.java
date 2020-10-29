package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.estello.android.Adapter.ExploreSearchCoursesAdapter;
import com.estello.android.Adapter.ExploreSearchInstructorsAdapter;
import com.estello.android.Adapter.ExploreSearchOrganizationAdapter;
import com.estello.android.Adapter.SearchHistoryAdapter;
import com.estello.android.ViewModel.CoursesModel;
import com.estello.android.ViewModel.InstructorsModel;
import com.estello.android.ViewModel.OrganizationModel;
import com.estello.android.ViewModel.SearchHistoryModel;
import com.estello.android.Utils.KeyboardUtils;



import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    EditText searchField;
    NestedScrollView nestedScrollView;
    RecyclerView seeAllRecyclerView,historyRecyclerView;
    Toolbar toolbar;
    int previousNestedScrollVisibility = View.GONE;
    int previousSeeAllVisibility = View.GONE;
    int shortAnimationDuration;
    SearchHistoryAdapter searchHistoryAdapter;
    ExploreSearchCoursesAdapter exploreSearchCoursesAdapter;
    ExploreSearchInstructorsAdapter exploreSearchInstructorsAdapter;
    ExploreSearchOrganizationAdapter exploreSearchOrganizationAdapter;
    RecyclerView coursesRecyclerView,organizationRecyclerView,instructorsRecyclerView;
    TextView coursesSeeAll,organizationSeeAll,instructorsSeeAll;
    ArrayList<SearchHistoryModel> searchHistoryModelArrayList = new ArrayList<>();
    ArrayList<CoursesModel> coursesModelArrayList = new ArrayList<>();
    ArrayList<InstructorsModel> instructorsModelArrayList = new ArrayList<>();
    ArrayList<OrganizationModel> organizationModelArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        populateList();
        initView();
    }

    private void initView(){



        shortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);


        seeAllRecyclerView = findViewById(R.id.search_see_all_recyclerview);
        nestedScrollView = findViewById(R.id.search_nested_scrollview);
        historyRecyclerView = findViewById(R.id.search_previous_search_recyclerview);
        searchField = findViewById(R.id.search_edittext);
        new KeyboardUtils(SearchActivity.this).showKeyboard();
        coursesRecyclerView = findViewById(R.id.search_courses_courses_recycler_view);
        organizationRecyclerView = findViewById(R.id.search_courses_organization_recyclerview);
        instructorsRecyclerView = findViewById(R.id.search_courses_instructors_recycler_view);
        coursesSeeAll = findViewById(R.id.search_courses_see_all);
        organizationSeeAll = findViewById(R.id.search_courses_organization_see_all);
        instructorsSeeAll = findViewById(R.id.search_courses_instructors_see_all);


        nestedScrollView.setVisibility(View.GONE);
        seeAllRecyclerView.setVisibility(View.GONE);
        historyRecyclerView.setVisibility(View.VISIBLE);
        toolbar = findViewById(R.id.search_course_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(SearchActivity.this,R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager linearLayoutManager5 = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL,false);



        exploreSearchOrganizationAdapter = new ExploreSearchOrganizationAdapter(organizationModelArrayList,SearchActivity.this);
        exploreSearchCoursesAdapter = new ExploreSearchCoursesAdapter(coursesModelArrayList,SearchActivity.this);
        exploreSearchInstructorsAdapter = new ExploreSearchInstructorsAdapter(instructorsModelArrayList,SearchActivity.this);
        searchHistoryAdapter = new SearchHistoryAdapter(searchHistoryModelArrayList, new SearchHistoryAdapter.ListItemClickListener() {
            @Override
            public void onListItemClick(int clickedItemIndex) {

                previousNestedScrollVisibility = nestedScrollView.getVisibility();
                previousSeeAllVisibility = seeAllRecyclerView.getVisibility();

                nestedScrollView.setVisibility(View.VISIBLE);
                seeAllRecyclerView.setVisibility(View.GONE);
                historyRecyclerView.setVisibility(View.GONE);

                searchField.setText(searchHistoryModelArrayList.get(clickedItemIndex).getSearchQuery());
            }
        });

        coursesRecyclerView.setLayoutManager(linearLayoutManager);
        instructorsRecyclerView.setLayoutManager(linearLayoutManager2);
        organizationRecyclerView.setLayoutManager(linearLayoutManager3);
        seeAllRecyclerView.setLayoutManager(linearLayoutManager4);
        historyRecyclerView.setLayoutManager(linearLayoutManager5);

        coursesRecyclerView.setAdapter(exploreSearchCoursesAdapter);
        instructorsRecyclerView.setAdapter(exploreSearchInstructorsAdapter);
        organizationRecyclerView.setAdapter(exploreSearchOrganizationAdapter);
        historyRecyclerView.setAdapter(searchHistoryAdapter);


        instructorsSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSeeAllRecyclerView();
                nestedScrollView.setVisibility(View.GONE);
                seeAllRecyclerView.setVisibility(View.VISIBLE);
                seeAllRecyclerView.setAdapter(exploreSearchInstructorsAdapter);
            }
        });

        coursesSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSeeAllRecyclerView();
                nestedScrollView.setVisibility(View.GONE);
                seeAllRecyclerView.setVisibility(View.VISIBLE);
                seeAllRecyclerView.setAdapter(exploreSearchCoursesAdapter);
            }
        });

        organizationSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSeeAllRecyclerView();
                nestedScrollView.setVisibility(View.GONE);
                seeAllRecyclerView.setVisibility(View.VISIBLE);
                seeAllRecyclerView.setAdapter(exploreSearchOrganizationAdapter);
            }
        });


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        if (menuItem.getItemId() == android.R.id.home) {

            this.onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }


    private void populateList(){
        CoursesModel coursesModel = new CoursesModel();
        OrganizationModel organizationModel = new OrganizationModel();
        InstructorsModel instructorsModel = new InstructorsModel();
        SearchHistoryModel searchHistoryModel = new SearchHistoryModel("Science");
        SearchHistoryModel searchHistoryModel2 = new SearchHistoryModel("Politics");
        SearchHistoryModel searchHistoryModel3 = new SearchHistoryModel("Delta Stream is here to take over the place so watch out for it");


        for(int i = 0; i < 5; i++){

            searchHistoryModelArrayList.add(searchHistoryModel);
            searchHistoryModelArrayList.add(searchHistoryModel2);
            searchHistoryModelArrayList.add(searchHistoryModel3);
            coursesModelArrayList.add(coursesModel);
            organizationModelArrayList.add(organizationModel);
            instructorsModelArrayList.add(instructorsModel);
        }

    }

    @Override
    public void onBackPressed() {

        if(historyRecyclerView.getVisibility() == View.VISIBLE){

            clearText();
            super.onBackPressed();

        }
        else {

            if (seeAllRecyclerView.getVisibility() == View.VISIBLE) {

                hideSeeAllRecyclerView();
                nestedScrollView.setVisibility(View.VISIBLE);
                seeAllRecyclerView.setVisibility(View.GONE);
            } else {

                clearText();
                super.onBackPressed();
            }
        }
    }

    public void clearText() {
        searchField.setText(null);
    }


    private void showSeeAllRecyclerView() {

        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        seeAllRecyclerView.setAlpha(0f);
        seeAllRecyclerView.setVisibility(View.VISIBLE);

        // Animate the content view to 100% opacity, and clear any animation
        // listener set on the view.
        seeAllRecyclerView.animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration)
                .setListener(null);

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        nestedScrollView.animate()
                .alpha(0f)
                .setDuration(shortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        nestedScrollView.setVisibility(View.GONE);
                    }
                });
    }



    private void showNestedScrollFromHistoryRecyclerView() {

        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        nestedScrollView.setAlpha(0f);
        nestedScrollView.setVisibility(View.VISIBLE);

        // Animate the content view to 100% opacity, and clear any animation
        // listener set on the view.
        nestedScrollView.animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration)
                .setListener(null);

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        historyRecyclerView.animate()
                .alpha(0f)
                .setDuration(shortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        historyRecyclerView.setVisibility(View.GONE);
                    }
                });
    }

    private void showSeeAllFromHistoryRecyclerView() {

        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        seeAllRecyclerView.setAlpha(0f);
        seeAllRecyclerView.setVisibility(View.VISIBLE);

        // Animate the content view to 100% opacity, and clear any animation
        // listener set on the view.
        seeAllRecyclerView.animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration)
                .setListener(null);

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        historyRecyclerView.animate()
                .alpha(0f)
                .setDuration(shortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        historyRecyclerView.setVisibility(View.GONE);
                    }
                });
    }

    private void hideSeeAllRecyclerView() {

        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        nestedScrollView.setAlpha(0f);
        nestedScrollView.setVisibility(View.VISIBLE);

        // Animate the content view to 100% opacity, and clear any animation
        // listener set on the view.
        nestedScrollView.animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration)
                .setListener(null);

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        seeAllRecyclerView.animate()
                .alpha(0f)
                .setDuration(shortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        seeAllRecyclerView.setVisibility(View.GONE);
                    }
                });
    }




}
