package com.estello.android.ViewModel;

public class OrganisationProfileInstructorsModel {

    private String instructorsId;
    private String instructorsName;
    private int instructorsImage;

    public OrganisationProfileInstructorsModel(String instructorsName, int instructorsImage){

        this.instructorsName = instructorsName;
        this.instructorsImage = instructorsImage;
    }

    public int getInstructorsImage() {
        return instructorsImage;
    }

    public String getInstructorsId() {
        return instructorsId;
    }

    public void setInstructorsId(String instructorsId) {
        this.instructorsId = instructorsId;
    }

    public void setInstructorsImage(int instructorsImage) {
        this.instructorsImage = instructorsImage;
    }

    public void setInstructorsName(String instructorsName) {
        this.instructorsName = instructorsName;
    }

    public String getInstructorsName() {
        return instructorsName;
    }
}
