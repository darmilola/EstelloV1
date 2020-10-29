package com.estello.android.ViewHolders;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;


import com.estello.android.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import androidx.annotation.NonNull;

public class TrainingMaterialSectionDetailsViewholderVideo extends ChildViewHolder {

    TextView filename,video,timeToComplete,downloading;

    public TrainingMaterialSectionDetailsViewholderVideo(@NonNull View itemView) {
        super(itemView);

        filename = itemView.findViewById(R.id.training_materials_section_details_video_name);
        video = itemView.findViewById(R.id.training_materials_section_details_video);
        timeToComplete = itemView.findViewById(R.id.training_materials_section_details_video_time_to_complete);
        downloading = itemView.findViewById(R.id.training_materials_section_details_video_downloading);


        Typeface customfont= Typeface.createFromAsset(itemView.getContext().getAssets(), "font/latoreg.ttf");

        filename.setTypeface(customfont);
        video.setTypeface(customfont);
        timeToComplete.setTypeface(customfont);
        downloading.setTypeface(customfont);
    }
}
