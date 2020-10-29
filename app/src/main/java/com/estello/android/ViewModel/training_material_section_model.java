package com.estello.android.ViewModel;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;


import java.util.List;

public class training_material_section_model extends ExpandableGroup<training_materials_section_details_model> {

    String divisionalType;
    String fileSize;
    String fileCount;
    String sectionId;
    List<training_materials_section_details_model> training_section_details_modelList;

    public training_material_section_model(String divisionalType,List<training_materials_section_details_model> items) {
        super(divisionalType, items);
    }


    public List<training_materials_section_details_model> getTraining_section_details_modelList() {
        return training_section_details_modelList;
    }

    public void setTraining_section_details_modelList(List<training_materials_section_details_model> training_section_details_modelList) {
        this.training_section_details_modelList = training_section_details_modelList;
    }

    public training_materials_section_details_model getTrainingMaterialSectionDetailsModel(int position){
        return training_section_details_modelList.get(position);
    }

    public String getDivisionalType() {
        return divisionalType;
    }

    public String getFileCount() {
        return fileCount;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setDivisionalType(String divisionalType) {
        this.divisionalType = divisionalType;
    }

    public void setFileCount(String fileCount) {
        this.fileCount = fileCount;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }
}
