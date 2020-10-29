package com.estello.android.ViewModel;

public class SyllabusSectionDetails {

    private String description;
    private String materialCount;
    private String liveSessionCount;


    public SyllabusSectionDetails(String description,String materialCount, String liveSessionCount){
     this.description = description;
     this.materialCount = materialCount;
     this.liveSessionCount = liveSessionCount;
    }

    public String getDescription() {
        return description;
    }

    public String getLiveSessionCount() {
        return liveSessionCount;
    }

    public String getMaterialCount() {
        return materialCount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLiveSessionCount(String liveSessionCount) {
        this.liveSessionCount = liveSessionCount;
    }

    public void setMaterialCount(String materialCount) {
        this.materialCount = materialCount;
    }
}
