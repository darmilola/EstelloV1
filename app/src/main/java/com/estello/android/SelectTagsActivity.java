package com.estello.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.estello.android.ViewModel.SkillsAndTagsModel;
import com.google.android.material.button.MaterialButton;
import com.pchmn.materialchips.ChipsInput;
import com.pchmn.materialchips.model.Chip;


import java.util.ArrayList;

public class SelectTagsActivity extends AppCompatActivity {


    ArrayList<Chip> chipArrayList = new ArrayList<>();
    ArrayList<SkillsAndTagsModel> skillsAndTagsModelArrayList = new ArrayList<>();
    ChipsInput skillsInputs;
    Toolbar toolbar;
    TextView title;
    MaterialButton doneButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tags);
        initView();
    }


    private void initView(){
        toolbar = findViewById(R.id.manageTagsToobar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(this,R.color.White), PorterDuff.Mode.SRC_ATOP);

        title = findViewById(R.id.manageTagsTitle);
        doneButton = findViewById(R.id.manageTagsDone);

        skillsInputs = findViewById(R.id.select_tags_input);

        Typeface customfont= Typeface.createFromAsset(getAssets(), "font/latoreg.ttf");


        doneButton.setTypeface(customfont);
        title.setTypeface(customfont);

        Chip chip = new Chip("Android","");
        Chip chip2 = new Chip("Python","");
        Chip chip3 = new Chip("Java","");
        Chip chip4 = new Chip("C++","");
        Chip chip5 = new Chip("Entrepreneurship","");
        Chip chip6 = new Chip("TechForAll","");
        Chip chip7 = new Chip("ZoomTraining","");
        Chip chip8 = new Chip("MeetingMatter","");
        Chip chip9 = new Chip("Mapow","");
        Chip chip10 = new Chip("okachi","");
        Chip chip11 = new Chip("gogogat","");
        Chip chip12 = new Chip("gabrolio","");




        chipArrayList.add(chip);
        chipArrayList.add(chip2);
        chipArrayList.add(chip3);
        chipArrayList.add(chip4);
        chipArrayList.add(chip5);
        chipArrayList.add(chip6);
        chipArrayList.add(chip7);
        chipArrayList.add(chip8);
        chipArrayList.add(chip9);
        chipArrayList.add(chip10);
        chipArrayList.add(chip11);
        chipArrayList.add(chip12);

        skillsInputs.setFilterableList(chipArrayList);


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i = 0; i < skillsInputs.getSelectedChipList().size(); i++){

                    skillsAndTagsModelArrayList.add(new SkillsAndTagsModel(skillsInputs.getSelectedChipList().get(i).getLabel(),skillsInputs.getSelectedChipList().get(i).getAvatarDrawable()));
                }



                Intent intent = new Intent();
                intent.putParcelableArrayListExtra("tagslist", skillsAndTagsModelArrayList);
                setResult(2,intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed(){
        setResult(0);
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){

        if(menuItem.getItemId() == android.R.id.home){

            this.onBackPressed();
        }


        return super.onOptionsItemSelected(menuItem);
    }
}
