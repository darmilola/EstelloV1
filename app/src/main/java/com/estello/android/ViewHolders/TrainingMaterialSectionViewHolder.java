package com.estello.android.ViewHolders;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;
import com.estello.android.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;


public class TrainingMaterialSectionViewHolder extends GroupViewHolder {

    TextView divisionalType;

    public TrainingMaterialSectionViewHolder(View itemView) {
        super(itemView);

        divisionalType = itemView.findViewById(R.id.training_materials_section_divisional_type);

        Typeface customfont= Typeface.createFromAsset(itemView.getContext().getAssets(),"montsemi.ttf");

        divisionalType.setTypeface(customfont);
    }

    public void BindSection(ExpandableGroup group) {
        divisionalType.setText(group.getTitle());

    }
}
