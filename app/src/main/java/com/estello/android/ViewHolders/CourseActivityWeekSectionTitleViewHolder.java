package com.estello.android.ViewHolders;

import android.view.View;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import androidx.annotation.NonNull;

public class CourseActivityWeekSectionTitleViewHolder extends ParentViewHolder {
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public CourseActivityWeekSectionTitleViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public boolean shouldItemViewClickToggleExpansion() {
        return false;
    }

}
