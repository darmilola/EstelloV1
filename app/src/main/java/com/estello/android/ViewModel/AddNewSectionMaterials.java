package com.estello.android.ViewModel;

public class AddNewSectionMaterials {

    private int materialImage;
    private String materialUri;
    private String materialName;
    private String materialType;
    private int materialCloseImage;

    public AddNewSectionMaterials(int materialImage, String materialName){

        this.materialName = materialName;
        this.materialImage = materialImage;
    }

    public int getMaterialCloseImage() {
        return materialCloseImage;
    }

    public int getMaterialImage() {
        return materialImage;
    }

    public String getMaterialName() {
        return materialName;
    }

    public String getMaterialType() {
        return materialType;
    }

    public String getMaterialUri() {
        return materialUri;
    }

    public void setMaterialCloseImage(int materialCloseImage) {
        this.materialCloseImage = materialCloseImage;
    }

    public void setMaterialImage(int materialImage) {
        this.materialImage = materialImage;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public void setMaterialUri(String materialUri) {
        this.materialUri = materialUri;
    }

}
