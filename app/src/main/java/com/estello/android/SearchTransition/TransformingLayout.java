package com.estello.android.SearchTransition;

import android.content.Context;
import android.util.AttributeSet;

import com.google.android.material.card.MaterialCardView;

/**
 * A Toolbar that knows how to hide and show its children.
 * <p>Overprotecting parent much?</p>
 */
public class TransformingLayout extends MaterialCardView {

    public TransformingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Sets the Visibility of all children to GONE
     */
    public void hideContent() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(GONE);
        }
    }

    /**
     * Sets the Visibility of all children to VISIBLE
     */
    public void showContent() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(VISIBLE);
        }
    }

}
