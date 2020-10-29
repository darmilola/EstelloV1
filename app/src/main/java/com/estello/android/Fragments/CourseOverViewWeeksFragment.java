package com.estello.android.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.estello.android.Adapter.CourseActivityWeeksAdapter;
import com.estello.android.ViewModel.CourseWeekSectionDetailsModel;
import com.estello.android.ViewModel.CourseWeekSectionsModel;

import com.estello.android.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseOverViewWeeksFragment extends Fragment {



    View view;
    RecyclerView recyclerView;
    TextView weekTitle,downloadText,fileSize;
    ArrayList<CourseWeekSectionsModel> sectionsModelArrayList = new ArrayList<>();
    ArrayList<CourseWeekSectionDetailsModel> sectionDetailsModelArrayList = new ArrayList<>();


    public CourseOverViewWeeksFragment(){

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view =  inflater.inflate(R.layout.fragment_course_over_view_weeks, container, false);

         initView();
         populateList();
         initRecyclerView();
         return view;
    }


    private void initView(){

        recyclerView = view.findViewById(R.id.course_week_sections_recyclerview);
        weekTitle = view.findViewById(R.id.course_week_title);
        downloadText = view.findViewById(R.id.course_overview_week_download_text);
        fileSize = view.findViewById(R.id.course_overview_week_file_size);

    }

    private void initRecyclerView(){

        CourseActivityWeeksAdapter courseActivityWeeksAdapter = new CourseActivityWeeksAdapter(sectionsModelArrayList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(courseActivityWeeksAdapter);
    }


    public void populateList(){

        CourseWeekSectionDetailsModel courseWeekSectionDetailsModel = new CourseWeekSectionDetailsModel(1);
        CourseWeekSectionDetailsModel courseWeekSectionDetailsModel2 = new CourseWeekSectionDetailsModel(2);
        CourseWeekSectionDetailsModel courseWeekSectionDetailsModel3 = new CourseWeekSectionDetailsModel(3);

        for(int i = 0; i < 3; i++){

            sectionDetailsModelArrayList.add(courseWeekSectionDetailsModel);
            sectionDetailsModelArrayList.add(courseWeekSectionDetailsModel2);
            sectionDetailsModelArrayList.add(courseWeekSectionDetailsModel3);
        }

        CourseWeekSectionsModel courseWeekSectionsModel = new CourseWeekSectionsModel(sectionDetailsModelArrayList);


        for(int i = 0; i < 7; i++){

            sectionsModelArrayList.add(courseWeekSectionsModel);

        }

    }


}
