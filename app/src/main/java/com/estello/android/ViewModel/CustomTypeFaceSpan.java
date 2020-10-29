package com.estello.android.ViewModel;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

   public class CustomTypeFaceSpan extends TypefaceSpan {
     final Typeface typeface;

    public CustomTypeFaceSpan(String family, Typeface type) {
        super(family);
        typeface = type;
    }

    @Override
    public void updateDrawState(TextPaint textPaint){
        applyCustomTypeFace(textPaint, typeface);
    }

    @Override
    public void updateMeasureState(TextPaint textPaint){
        applyCustomTypeFace(textPaint, typeface);
    }

    private void applyCustomTypeFace(Paint paint, Typeface typeface){
        int oldStyle;
        Typeface old = paint.getTypeface();
        if(old == null){
            oldStyle = 0;
        }else{
            oldStyle = old.getStyle();
        }

        int fake = oldStyle & ~typeface.getStyle();
        if((fake & Typeface.BOLD) != 0){
            paint.setFakeBoldText(true);
        }

        if((fake & Typeface.ITALIC) != 0){
            paint.setTextSkewX(-0.25f);
        }
        paint.setTypeface(typeface);
    }
}