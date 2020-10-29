package com.estello.android.ViewHolders;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import com.estello.android.ViewModel.SyllabusSection;
import com.estello.android.R;

import androidx.annotation.NonNull;

public class FaqQuestionViewHolder extends ParentViewHolder {
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */

    TextView sectionDivisional;
    TextView sectionTitle;
    ImageView dropdown;
    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;


    public FaqQuestionViewHolder(@NonNull final View itemView) {
        super(itemView);
            dropdown = itemView.findViewById(R.id.faq_drop_down_icon);



        dropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isExpanded()){
                    collapseView();
                }
                else{
                    expandView();

                }
            }
        });


    }

    public void bind(SyllabusSection syllabusSection){
        //sectionDivisional.setText(syllabusSection.getDivisional());
        //sectionTitle.setText(syllabusSection.getSectionTitle());
    }

    @Override
    public boolean shouldItemViewClickToggleExpansion() {
        return false;
    }



    @SuppressLint("NewApi")
    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (expanded) {
                dropdown.setRotation(ROTATED_POSITION);
            } else {
                dropdown.setRotation(INITIAL_POSITION);
            }
        }
    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        super.onExpansionToggled(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            RotateAnimation rotateAnimation;
            if (expanded) { // rotate clockwise
                rotateAnimation = new RotateAnimation(ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            } else { // rotate counterclockwise
                rotateAnimation = new RotateAnimation(-1 * ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            }

            rotateAnimation.setDuration(200);
            rotateAnimation.setFillAfter(true);
            dropdown.startAnimation(rotateAnimation);
        }
    }
}
