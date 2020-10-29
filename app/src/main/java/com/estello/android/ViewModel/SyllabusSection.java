package com.estello.android.ViewModel;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

public class SyllabusSection implements Parent<SyllabusSectionDetails> {

    String divisional;
    String sectionTitle;
    List<SyllabusSectionDetails> syllabusSectionDetailsList;


    public SyllabusSection(String mdivisional, String mSectionTitle, List<SyllabusSectionDetails> msyllabusSectionDetails){

        this.divisional = mdivisional;
        this.sectionTitle = mSectionTitle;
        this.syllabusSectionDetailsList = msyllabusSectionDetails;

    }
    @Override
    public List<SyllabusSectionDetails> getChildList() {
        return syllabusSectionDetailsList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public List<SyllabusSectionDetails> getSyllabusSectionDetailsList() {
        return syllabusSectionDetailsList;
    }

    public String getDivisional() {
        return divisional;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public void setDivisional(String divisional) {
        this.divisional = divisional;
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    public void setSyllabusSectionDetailsList(List<SyllabusSectionDetails> syllabusSectionDetailsList) {
        this.syllabusSectionDetailsList = syllabusSectionDetailsList;
    }
}
