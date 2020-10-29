package com.estello.android.ViewHolders;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;



import com.estello.android.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import androidx.annotation.NonNull;

public class TrainingMaterialSectionDetailsViewholderReading extends ChildViewHolder {

    TextView reading,filename,timeToComplete,downloading;

    public TrainingMaterialSectionDetailsViewholderReading(@NonNull View itemView) {
        super(itemView);

        reading = itemView.findViewById(R.id.training_materials_details_reading);
        filename = itemView.findViewById(R.id.training_material_details_ebook_name);
        timeToComplete = itemView.findViewById(R.id.training_materials_details_ebook_time_to_complete);
        downloading = itemView.findViewById(R.id.training_materials_details_ebook_downloading);

        Typeface customfont= Typeface.createFromAsset(itemView.getContext().getAssets(), "font/latoreg.ttf");

        reading.setTypeface(customfont);
        filename.setTypeface(customfont);
        timeToComplete.setTypeface(customfont);
        downloading.setTypeface(customfont);
    }
}
