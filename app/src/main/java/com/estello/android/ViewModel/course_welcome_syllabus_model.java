package com.estello.android.ViewModel;

public class course_welcome_syllabus_model {

    private boolean isActive;
    private String division;
    private String title;
    private String description;

    public course_welcome_syllabus_model(boolean isActive, String division, String title, String description){

        this.isActive = isActive;
        this.division = division;
        this.title = title;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getDivision() {
        return division;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    public boolean getIsActive() {
        return isActive;
    }

    public void setDivision(String division) {
        this.division = division;
    }


}
