package com.estello.android.ViewModel;

import com.bignerdranch.expandablerecyclerview.model.Parent;


import java.util.List;

public class CourseWeekSectionsModel implements Parent<CourseWeekSectionDetailsModel> {



    List<CourseWeekSectionDetailsModel> courseWeekSectionDetailsModelList;
    public CourseWeekSectionsModel(List<CourseWeekSectionDetailsModel> courseWeekSectionDetailsModelList){
        this.courseWeekSectionDetailsModelList = courseWeekSectionDetailsModelList;

    }

    @Override
    public List<CourseWeekSectionDetailsModel> getChildList() {
        return courseWeekSectionDetailsModelList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return true;
    }



    public List<CourseWeekSectionDetailsModel> getCourseWeekSectionDetailsModelList() {
        return courseWeekSectionDetailsModelList;
    }

    public void setCourseWeekSectionDetailsModelList(List<CourseWeekSectionDetailsModel> courseWeekSectionDetailsModelList) {
        this.courseWeekSectionDetailsModelList = courseWeekSectionDetailsModelList;
    }

}
