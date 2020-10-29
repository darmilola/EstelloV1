package com.estello.android.ViewModel;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.deltastream.example.edittextcontroller.RTEditText;
import com.deltastream.example.edittextcontroller.api.format.RTFormat;





public class RtEdittextScrollView extends RTEditText implements OnTouchListener {


    public static int WITHOUT_MAX_HEIGHT_VALUE = -1;
    private int maxHeight = WITHOUT_MAX_HEIGHT_VALUE;
    public mOnselectionChangedListener mOnselectionChangedListener;
    private  boolean isTopMovement = false;
    private  boolean isBottomMovement = false;
    private boolean isTopReachedFromDragging = false;
    private mOnFocusClearedFromTouchListener onFocusClearedFromTouchListener;
    private boolean isFocusClearedFromTouch = false;





    public interface mOnselectionChangedListener {

         void mOnSelectionChanged(int selStart, int selEnd);
    }


    public interface mOnFocusClearedFromTouchListener {

        void mOnFocusCleared();
    }




    @Override
    public void onSelectionChanged(int selStart, int selEnd){

         super.onSelectionChanged(selStart,selEnd);

        if(mOnselectionChangedListener != null){
            setClickable(true);
            mOnselectionChangedListener.mOnSelectionChanged(selStart,selEnd);
        }

    }

    public void setOnFocusClearedFromTouchListener(mOnFocusClearedFromTouchListener onFocusClearedFromTouchListener) {
        this.onFocusClearedFromTouchListener = onFocusClearedFromTouchListener;
    }

    public void setmOnselectionChangedListener(mOnselectionChangedListener mOnselectionChangedListener) {

        this.mOnselectionChangedListener = mOnselectionChangedListener;
    }


    public void setFocusClearedFromTouch(boolean focusClearedFromTouch) {
        isFocusClearedFromTouch = focusClearedFromTouch;
    }

     public boolean getFocusClearedFromTouch(){

        return isFocusClearedFromTouch;
     }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {



            if(isClickable()) {

                view.getParent().requestDisallowInterceptTouchEvent(true);
                Log.e("allowed", "onTouch: ");
                //requestFocus();
                //view.getParent().requestLayout();

            }
            else {

                setFocusClearedFromTouch(true);
               // clearFocus();
                view.getParent().requestDisallowInterceptTouchEvent(false);
                //onSelectionChanged(getSelectionStart(),getSelectionEnd());
                Log.e("disallowed", "onTouch:");




            }


        //requestFocus();


        switch (motionEvent.getAction()) {

            case MotionEvent.ACTION_UP:



                Log.e("bottom move", "onTouch: ");
                if(isUserBottomReached()){


                    Log.e("bottom reached", "onTouch: ");
                    onBottomReachedListener.onBottomReached();

                }


            case MotionEvent.ACTION_DOWN:

                Log.e("top move", "onTouch: ");
                if(isUserTopReached()  && !isUserBottomReached() && !isTopReachedFromDragging){

                    Log.e("top reached", "onTouch:");

                     //setSelection(getText(RTFormat.PLAIN_TEXT).length()-1);
                     onTopReachedListener.onTopReached();
                     setIsTopReachedFromDragging(false);

                }
                else{
                      setClickable(true);
                      setIsTopReachedFromDragging(true);
                }


                //if bottom and top reached allow intercept

        }
                return false;
    }


  /*  @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){



            if (maxHeight > 1000) {

                int heightSize = maxHeight;
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.AT_MOST);
                getLayoutParams().height = heightSize;

            }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);



    }

    @Override
    public void setMaxHeight(int maxHeight){

        this.maxHeight = maxHeight;
    }*/


    public void removeOnTouchListener(){

        setOnTouchListener(null);
    }

    public void setOnTouchListener(){

        setOnTouchListener(this);
    }

    public interface OnBottomReachedListener {
        void onBottomReached();
    }

    public interface OnTopReachedListener{
        void onTopReached();
    }
    public boolean mIsFling;
    public OnBottomReachedListener onBottomReachedListener;
    public OnTopReachedListener onTopReachedListener;




    public RtEdittextScrollView(Context context, AttributeSet attrs) {

        super(context, attrs);
        setOnTouchListener(this);
        setClickable(true);



    }

    public RtEdittextScrollView(Context context, AttributeSet attributeSet, int defStyle){

        super(context,attributeSet,defStyle);
        setOnTouchListener(this);
        setClickable(true);




    }

    public RtEdittextScrollView(Context context){

        super(context);
        setOnTouchListener(this);
        setClickable(true);


    }

    @Override
    protected void onScrollChanged(int x, int y, int oldX, int oldY){



        setIsTopReachedFromDragging(false);
        //setClickable(true);
        super.onScrollChanged(x,y,oldX,oldY);

    }



    public OnBottomReachedListener getOnBottomReachedListener() {
        return onBottomReachedListener;
    }

    public OnTopReachedListener getOnTopReachedListener() {
        return onTopReachedListener;
    }

    public void setOnTopReachedListener(OnTopReachedListener onTopReachedListener) {
        this.onTopReachedListener = onTopReachedListener;
    }

    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener;
    }

    public void removeOnBottomReachedListener(){

        this.onBottomReachedListener = null;
    }

    public void removeOnTopReachedListener(){
        this.onTopReachedListener = null;
    }


    public boolean isUserBottomReached(){


        if(!getText(RTFormat.PLAIN_TEXT).trim().equalsIgnoreCase("") && !canScrollVertically(1)){

            return  true;

        }

        else{

            return  false;
        }
    }

    public boolean isUserTopReached(){

        if(!getText(RTFormat.PLAIN_TEXT).trim().equalsIgnoreCase("") && !canScrollVertically(-1)){

            return  true;

        }

        else{

            return  false;
        }
    }


    public void setIsTopReachedFromDragging(boolean isTopReachedFromDragging){

        this.isTopReachedFromDragging = isTopReachedFromDragging;
    }





}
