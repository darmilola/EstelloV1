package com.estello.android.Fragments;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.sulek.currencyfield.CurrencyField;
import ru.slybeaver.slycalendarview.SlyCalendarDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.adroitandroid.chipcloud.ChipCloud;
import com.estello.android.ViewModel.SkillsAndTagsModel;
import com.estello.android.SelectSkillsActivity;
import com.estello.android.SelectTagsActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.estello.android.R;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateCourseDetails extends Fragment implements Step{




    View view;
    MaterialButton manageSkills,manageTags;
    ArrayList<SkillsAndTagsModel> skillsArrayList = new ArrayList<>();
    ArrayList<SkillsAndTagsModel> tagsArrayList = new ArrayList<>();
    ChipCloud skillsCloud,tagsCloud;
    TextView courseTitleTitle,skillsToAcquireTitle,maximumParticipantsTitle,tagsTitle;
    TextInputLayout courseTitleLayout;
    TextInputEditText courseTitleEdittext;
    TextView fee_title,setDateTitle,minFeeError,courseImageText,coursePrivacyText,uploadImage;
    MaterialCheckBox sponsoredCourse;
    MaterialButton setDate;
    CurrencyField courseFeeValue;
    SlyCalendarDialog.Callback callback;
    TextView courseDate;

    String[] daysOfWeek = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    String[] monthOfYear = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    public CreateCourseDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view =   inflater.inflate(R.layout.fragment_create_course_details, container, false);

         initView();
         return  view;
    }


    private void initView(){

        manageSkills = view.findViewById(R.id.create_course_details_manage_skills);
        skillsCloud = view.findViewById(R.id.selected_skill_chip_cloud);
        courseTitleTitle = view.findViewById(R.id.create_course_details_course_title);
        courseTitleLayout = view.findViewById(R.id.create_course_details_course_title_layout);
        courseTitleEdittext = view.findViewById(R.id.create_course_details_course_title_edittext);
        skillsToAcquireTitle = view.findViewById(R.id.create_course_details_skills_to_acquire_title);
        maximumParticipantsTitle = view.findViewById(R.id.create_course_details_maximum_participants_title);
        tagsTitle = view.findViewById(R.id.create_course_details_tags_title);
        manageTags = view.findViewById(R.id.create_course_details_manage_tags);
        tagsCloud = view.findViewById(R.id.selected_tags_chip_cloud);
        fee_title = view.findViewById(R.id.create_course_details_course_fee_title);
        setDateTitle = view.findViewById(R.id.create_course_details_set_date_title);
        minFeeError = view.findViewById(R.id.create_course_details_minimum_fee_error);
        sponsoredCourse = view.findViewById(R.id.create_course_details_sponsored_checkbox);
        setDate = view.findViewById(R.id.create_course_details_set_date_button);
        courseDate = view.findViewById(R.id.create_course_details_course_date_value);
        courseImageText = view.findViewById(R.id.create_course_details_course_image_title);
        coursePrivacyText = view.findViewById(R.id.course_privacy_text);
        uploadImage = view.findViewById(R.id.create_course_details_upload_image_text);
        courseFeeValue = view.findViewById(R.id.create_course_details_course_fee_value);
        Typeface customfont = Typeface.createFromAsset(getContext().getAssets(), "font/latoreg.ttf");

        fee_title.setTypeface(customfont);
        coursePrivacyText.setTypeface(customfont);
        courseImageText.setTypeface(customfont);
        uploadImage.setTypeface(customfont);
        courseDate.setTypeface(customfont);
        setDateTitle.setTypeface(customfont);
        setDate.setTypeface(customfont);
        courseFeeValue.setTypeface(customfont);
        sponsoredCourse.setTypeface(customfont);
        minFeeError.setTypeface(customfont);
        manageTags.setTypeface(customfont);
        courseTitleTitle.setTypeface(customfont);
        courseTitleLayout.setTypeface(customfont);
        courseTitleEdittext.setTypeface(customfont);
        skillsToAcquireTitle.setTypeface(customfont);
        maximumParticipantsTitle.setTypeface(customfont);
        tagsTitle.setTypeface(customfont);
        manageSkills.setTypeface(customfont);
        skillsCloud.setTypeface(customfont);
        sponsoredCourse.setTypeface(customfont);



        callback = new SlyCalendarDialog.Callback() {
            @Override
            public void onCancelled() {

            }

            @Override
            public void onDataSelected(Calendar firstDate, Calendar secondDate, int hours, int minutes) {

                if (firstDate != null) {

                    if (secondDate == null) {

                        courseDate.setText(firstDate.get(Calendar.DAY_OF_MONTH)+" "+monthOfYear[firstDate.get(Calendar.MONTH)]+", "+firstDate.get(Calendar.YEAR));
                    }
                    else{

                        courseDate.setText(firstDate.get(Calendar.DAY_OF_MONTH)+" "+monthOfYear[firstDate.get(Calendar.MONTH)]+", "+firstDate.get(Calendar.YEAR) +" to "+ secondDate.get(Calendar.DAY_OF_MONTH)+" "+monthOfYear[secondDate.get(Calendar.MONTH)]+", "+secondDate.get(Calendar.YEAR));

                    }
                }
            }
        };


        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SlyCalendarDialog()
                        .setSingle(false)
                        .setFirstMonday(false)
                        .setCallback(callback)
                        .setHeaderColor(R.color.colorPrimary)
                        .show(getFragmentManager(), "Choose Date");
            }
        });

        manageSkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SelectSkillsActivity.class);
                startActivityForResult(intent,0);
            }
        });

        manageTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SelectTagsActivity.class);
                startActivityForResult(intent,0);
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == 1) {
             skillsArrayList.clear();
             skillsArrayList = data.getParcelableArrayListExtra("skilllist");

             for(int i = 0; i < skillsArrayList.size(); i++){

                 skillsCloud.addChip(skillsArrayList.get(i).getLabel());
             }
             skillsCloud.setVisibility(View.VISIBLE);
        }

        if (requestCode == 0 && resultCode == 2) {
            tagsArrayList.clear();
            tagsArrayList = data.getParcelableArrayListExtra("tagslist");

            for(int i = 0; i < tagsArrayList.size(); i++){

                tagsCloud.addChip(tagsArrayList.get(i).getLabel());
            }
            tagsCloud.setVisibility(View.VISIBLE);
        }
    }






    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }





}
