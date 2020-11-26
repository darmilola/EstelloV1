package com.estello.android.ViewModel;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class ExploreCommunityGridViewItem extends LinearLayout {
    public ExploreCommunityGridViewItem(Context context) {
        super(context);
    }

    public ExploreCommunityGridViewItem(Context context, AttributeSet attributeSet){

        super(context,attributeSet);
    }

    public ExploreCommunityGridViewItem(Context context, AttributeSet attributeSet, int defStyle){

        super(context,attributeSet,defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){

        super.onMeasure(widthMeasureSpec,widthMeasureSpec);
    }
}
