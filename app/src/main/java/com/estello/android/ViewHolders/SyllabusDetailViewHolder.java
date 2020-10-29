package com.estello.android.ViewHolders;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import com.estello.android.ViewModel.SyllabusSectionDetails;
import com.estello.android.R;

import androidx.annotation.NonNull;

public class SyllabusDetailViewHolder extends ChildViewHolder {
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */

    TextView description,material_count,live_session_count;
    public SyllabusDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        description = itemView.findViewById(R.id.syllabus_detail_section_description);
        material_count = itemView.findViewById(R.id.syllabus_detail_section_material_count);
        live_session_count = itemView.findViewById(R.id.syllabus_detail_live_sessions_count);

        Typeface customfont= Typeface.createFromAsset(itemView.getContext().getAssets(), "font/latoreg.ttf");


    }


    public void bind(SyllabusSectionDetails syllabusSectionDetails) {
        //description.setText(syllabusSectionDetails.getDescription());
        //material_count.setText(syllabusSectionDetails.getMaterialCount());
        //live_session_count.setText(syllabusSectionDetails.getLiveSessionCount());
    }
}
