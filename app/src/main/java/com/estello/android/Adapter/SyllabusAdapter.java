package com.estello.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.estello.android.ViewModel.SyllabusSection;
import com.estello.android.ViewModel.SyllabusSectionDetails;
import com.estello.android.ViewHolders.SyllabusDetailViewHolder;
import com.estello.android.ViewHolders.SyllabusSectionViewHolder;

import com.estello.android.R;

import java.util.List;

import androidx.annotation.NonNull;

public class SyllabusAdapter extends ExpandableRecyclerAdapter<SyllabusSection, SyllabusSectionDetails, SyllabusSectionViewHolder, SyllabusDetailViewHolder> {


    private LayoutInflater mInflater;

    public SyllabusAdapter(Context context, @NonNull List<SyllabusSection> syllabusSectionList) {
        super(syllabusSectionList);
        mInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public SyllabusSectionViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {

        View syllabusSectionView = mInflater.inflate(R.layout.syllabus_section, parentViewGroup, false);
        return new SyllabusSectionViewHolder(syllabusSectionView);

    }

    @NonNull
    @Override
    public SyllabusDetailViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View syllabusSectionDetailView = mInflater.inflate(R.layout.syllabus_section_details, childViewGroup, false);
        return new SyllabusDetailViewHolder(syllabusSectionDetailView);
    }

    @Override
    public void onBindParentViewHolder(@NonNull SyllabusSectionViewHolder syllabusSectionViewHolder, int parentPosition, @NonNull SyllabusSection syllabusSection) {
        syllabusSectionViewHolder.bind(syllabusSection);
    }

    @Override
    public void onBindChildViewHolder(@NonNull SyllabusDetailViewHolder syllabusDetailViewHolder, int parentPosition, int childPosition, @NonNull SyllabusSectionDetails syllabusSectionDetails) {
      syllabusDetailViewHolder.bind(syllabusSectionDetails);
    }
}
