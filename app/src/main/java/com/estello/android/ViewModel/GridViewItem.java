package com.estello.android.ViewModel;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;


public class GridViewItem extends AppCompatImageView {

    public GridViewItem(Context context) {
        super(context);
    }

    public GridViewItem(Context context, AttributeSet attributeSet){

        super(context,attributeSet);
    }

    public GridViewItem(Context context, AttributeSet attributeSet, int defStyle){

        super(context,attributeSet,defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){

        super.onMeasure(widthMeasureSpec,widthMeasureSpec);
    }
}
