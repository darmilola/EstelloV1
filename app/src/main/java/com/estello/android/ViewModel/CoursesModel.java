package com.estello.android.ViewModel;

public class CoursesModel {

    private int ViewType;
    private String course_title;
    private String instructor;
    private String course_banner;
    private String course_id;
    private int Title_Background;


    public CoursesModel(){

    }
    public CoursesModel(int mViewType, String mCourse_title, String mCourse_banner, String mInstructor, String mCourse_id){

        this.ViewType = mViewType;
        this.course_title = mCourse_title;
        this.course_banner = mCourse_banner;
        this.instructor = mInstructor;
        this.course_id = mCourse_id;
    }

    public CoursesModel(int mViewType, String Title, int mTitle_Background){
        this.Title_Background = mTitle_Background;
        this.course_title = Title;
        this.ViewType = mViewType;
    }


    public int getTitle_Background() {
        return Title_Background;
    }

    public int getViewType() {
        return ViewType;
    }

    public String getCourse_banner() {
        return course_banner;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getCourse_title() {
        return course_title;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setViewType(int viewType) {
        ViewType = viewType;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public void setCourse_banner(String course_banner) {
        this.course_banner = course_banner;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setTitle_Background(int title_Background) {
        Title_Background = title_Background;
    }

}
