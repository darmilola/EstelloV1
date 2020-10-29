package com.estello.android.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.ViewModel.training_material_section_model;
import com.estello.android.ViewHolders.TrainingMaterialSectionDetailsViewholderReading;
import com.estello.android.ViewHolders.TrainingMaterialSectionDetailsViewholderVideo;
import com.estello.android.ViewHolders.TrainingMaterialSectionViewHolder;

import com.estello.android.R;

import com.thoughtbot.expandablerecyclerview.MultiTypeExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import java.util.List;

import androidx.annotation.NonNull;

import static android.view.LayoutInflater.from;

public class TrainingMaterialAdapter extends MultiTypeExpandableRecyclerViewAdapter<TrainingMaterialSectionViewHolder, ChildViewHolder> {

    public static final int type1 = 1;
    Context context;
    LayoutInflater inflater;
    List<training_material_section_model> training_material_section_modelArrayList;

    public TrainingMaterialAdapter(Context context, @NonNull List<training_material_section_model> parentList) {
        super(parentList);
        this.training_material_section_modelArrayList = parentList;
        this.context = context;
    }


    @Override
    public TrainingMaterialSectionViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = from(context).inflate(R.layout.training_material_section_item, parent, false);
        return new TrainingMaterialSectionViewHolder(view);
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View video = from(parent.getContext()).inflate(R.layout.training_material_section_detail_item_video, parent, false);
                return new TrainingMaterialSectionDetailsViewholderVideo(video);
            case 1:
                View ebook = from(parent.getContext()).inflate(R.layout.training_material_item_ebook, parent, false);
                return new TrainingMaterialSectionDetailsViewholderReading(ebook);
            default:
                throw new IllegalArgumentException("Invalid viewType");
        }
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {


    }

    @Override
    public void onBindGroupViewHolder(TrainingMaterialSectionViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.BindSection(group);


    }


    @Override
    public int getChildViewType(int position, ExpandableGroup group, int childIndex) {
        if (((training_material_section_model) group).getItems().get(childIndex).getFileType() == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public boolean isChild(int viewType) {
        return viewType == 0 || viewType == 1;
    }
}
