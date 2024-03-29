package com.estello.android.ViewModel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;


public class UroraPostRecyclerImage extends AppCompatImageView {

    public UroraPostRecyclerImage(Context context) {
        super(context);
    }

    public UroraPostRecyclerImage(Context context, AttributeSet attrs) {

        super(context, attrs);



    }

    public UroraPostRecyclerImage(Context context, AttributeSet attributeSet, int defStyle){

        super(context,attributeSet,defStyle);

    }



    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){

        try {
            Drawable drawable = getDrawable();

            if (drawable == null) {
                setMeasuredDimension(0, 0);
            } else {

                float imageSideRatio = (float)drawable.getIntrinsicWidth() / (float)drawable.getIntrinsicHeight();
                float viewSideRatio = (float)MeasureSpec.getSize(widthMeasureSpec) / (float)MeasureSpec.getSize(heightMeasureSpec);
                if (imageSideRatio >= viewSideRatio) {
                    // Image is wider than the display (ratio)
                    int width = MeasureSpec.getSize(widthMeasureSpec);
                    int height = (int)(width / imageSideRatio);
                    setMeasuredDimension((int) (drawable.getIntrinsicWidth() * imageSideRatio),(int)(drawable.getIntrinsicHeight()*imageSideRatio));
                } else {
                    // Image is taller than the display (ratio)
                    int height = MeasureSpec.getSize(heightMeasureSpec);
                    int width = (int)(height * imageSideRatio);
                    setMeasuredDimension((int) (drawable.getIntrinsicWidth() * imageSideRatio),(int)(drawable.getIntrinsicHeight()*imageSideRatio));

                }
            }
        } catch (Exception e) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }


    }

}
