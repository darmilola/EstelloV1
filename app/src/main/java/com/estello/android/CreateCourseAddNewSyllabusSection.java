package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.estello.android.Adapter.AddNewSectionMaterialAdapter;
import com.estello.android.ViewModel.AddNewSectionMaterials;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;



import java.util.ArrayList;

public class CreateCourseAddNewSyllabusSection extends AppCompatActivity {

    TextView changeSessionDivision,title,description,liveSession,sessionDivision;
    TextInputLayout sessionTitleLayout,sessionDescriptionLayout;
    TextInputEditText sessionTitleEdittext,sessionDescriptionEdittext;
    RecyclerView recyclerView;
    Toolbar toolbar;
    TextView toolbarTitle;
    ArrayList<AddNewSectionMaterials> addNewSectionMaterials = new ArrayList<>();
    private BottomSheetBehavior bottomSheetBehavior;
    private LinearLayout bottom_sheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course_add_new_syllabus_section);

        initView();
        initRecyclerView();

    }


    private void initView(){

        changeSessionDivision = findViewById(R.id.create_section_add_or_edit_section_change_division);
        sessionDivision = findViewById(R.id.create_section_add_or_edit_session_division_tag);
        title = findViewById(R.id.create_section_add_or_edit_section_title);
        description = findViewById(R.id.create_section_add_or_edit_description_title);
        liveSession = findViewById(R.id.create_section_add_or_edit_live_session_title);
        sessionTitleLayout = findViewById(R.id.create_section_add_or_edit_section_title_value_layout);
        sessionDescriptionLayout = findViewById(R.id.create_section_add_or_edit_description_value_layout);
        sessionTitleEdittext = findViewById(R.id.create_section_add_or_edit_section_title_value);
        sessionDescriptionEdittext = findViewById(R.id.create_section_add_or_edit_description_value);
        recyclerView = findViewById(R.id.add_new_section_material_recyclerview);
        toolbar = findViewById(R.id.add_new_section_toolbar);
        bottom_sheet = findViewById(R.id.add_new_section_bottom_sheet_layout);
        toolbarTitle = findViewById(R.id.create_course_add_or_edit_section_toolbar_title);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(this,R.color.White), PorterDuff.Mode.SRC_ATOP);


        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        changeSessionDivision.setOnClickListener(new View.OnClickListener() {
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

    }

    private void initRecyclerView(){

        recyclerView = findViewById(R.id.add_new_section_material_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CreateCourseAddNewSyllabusSection.this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        AddNewSectionMaterials materials = new AddNewSectionMaterials(R.drawable.course_video_icon,"Introduction to Mathematical modelling of the world system.mp4");
        AddNewSectionMaterials materials2 = new AddNewSectionMaterials(R.drawable.bookicon,"Introduction to Mathematical modelling of the world system.mp4");
        AddNewSectionMaterials materials3 = new AddNewSectionMaterials(R.drawable.course_video_icon,"Introduction to Mathematical modelling of the world system.mp4");
        AddNewSectionMaterials materials4 = new AddNewSectionMaterials(R.drawable.bookicon,"Introduction to Mathematical modelling of the world system.mp4");

        addNewSectionMaterials.add(materials);
        addNewSectionMaterials.add(materials2);
        addNewSectionMaterials.add(materials3);
        addNewSectionMaterials.add(materials4);

        AddNewSectionMaterialAdapter adapter = new AddNewSectionMaterialAdapter(CreateCourseAddNewSyllabusSection.this,addNewSectionMaterials);

        recyclerView.setAdapter(adapter);


    }
}
