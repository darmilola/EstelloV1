package com.estello.android.AudioRecordView;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.estello.android.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Varun John on 4 Dec, 2018
 * Github : https://github.com/varunjohn
 */
public class AudioRecordViewBottomSheetType1 {

    public enum UserBehaviour {
        CANCELING,
        LOCKING,
        NONE
    }

    public enum RecordingBehaviour {
        CANCELED,
        LOCKED,
        LOCK_DONE,
        RELEASED
    }

    public interface RecordingListener {

        void onRecordingStarted();

        void onRecordingLocked();

        void onRecordingCompleted();

        void onRecordingCanceled();

        void onDeleteAnimationStart();

    }

    private String TAG = "AudioRecordViewBottomSheetType1";


    private LinearLayout recordIconImageLayout;
    private View imageViewMic, dustin, dustin_cover;
    private View layoutSlideCancel, layoutEffect1, layoutEffect2;
    private TextView timeText, textViewSlide;

    private ImageView stop, send;

    private Animation animBlink, animJump, animJumpFast;
    private LinearLayout layoutDustin;
    private boolean isDeleting;
    private boolean stopTrackingAction;
    private Handler handler,mHandler;

    private int audioTotalTime;
    private TimerTask timerTask;
    private Timer audioTimer;
    private SimpleDateFormat timeFormatter;

    private float lastX, lastY;
    private float firstX, firstY;

    private float directionOffset, cancelOffset, lockOffset;
    private float dp = 0;
    private boolean isLocked = false;
    private boolean isLayoutDirectionRightToLeft = false;

    private UserBehaviour userBehaviour = UserBehaviour.NONE;
    private RecordingListener recordingListener;

    int screenWidth, screenHeight;
    private List<LinearLayout> layoutAttachments;
    private Context context;
    private onRecordTouchReceivedListener touchReceivedListener;
    private onRecordClickReceivedListener recordClickReceivedListener;
    FrameLayout deleteAnimLayoutRoot;
    private short touchTimeThreshold = 200;
    private short touchMoveThreshold = 10;
    private PointF actionDownPoint = new PointF(0f,0f);
    private long touchDownTime = 0L;
    private boolean isClickedAction = false;

    public interface onRecordTouchReceivedListener{
        public void onTouchReceived();
    }

    public interface onRecordClickReceivedListener{
        public void onClickReceived();
    }


    public void setRecordClickReceivedListener(onRecordClickReceivedListener recordClickReceivedListener) {
        this.recordClickReceivedListener = recordClickReceivedListener;
    }

    public void setTouchReceivedListener(onRecordTouchReceivedListener touchReceivedListener) {
        this.touchReceivedListener = touchReceivedListener;
    }

    public void initDeleteAnimView(ViewGroup viewGroup){

        if(viewGroup == null){

            showErrorLog("initView ViewGroup can't be NULL");

        }
        else{

            deleteAnimLayoutRoot = (FrameLayout) viewGroup;
            dustin = viewGroup.findViewById(R.id.dustin);
            dustin_cover = viewGroup.findViewById(R.id.dustin_cover);
            layoutDustin = viewGroup.findViewById(R.id.layoutDustin);
            imageViewMic = viewGroup.findViewById(R.id.imageViewMic);

        }
    }
    public void initView(ViewGroup view) {

        if (view == null) {
            showErrorLog("initView ViewGroup can't be NULL");
            return;
        }

        context = view.getContext();

        view.removeAllViews();
        view.addView(LayoutInflater.from(view.getContext()).inflate(R.layout.record_view_bottomsheet_type1, null));
        DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
        timeFormatter = new SimpleDateFormat("m:ss", Locale.getDefault());

        recordIconImageLayout = view.findViewById(R.id.recordIconImageLayout);
        recordIconImageLayout.setClickable(false);
        textViewSlide = view.findViewById(R.id.textViewSlide);
        timeText = view.findViewById(R.id.textViewTime);
        layoutSlideCancel = view.findViewById(R.id.layoutSlideCancel);
        layoutEffect2 = view.findViewById(R.id.layoutEffect2);
        layoutEffect1 = view.findViewById(R.id.layoutEffect1);
        handler = new Handler(Looper.getMainLooper());

        dp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, view.getContext().getResources().getDisplayMetrics());

        animBlink = AnimationUtils.loadAnimation(view.getContext(),
                R.anim.blink);
        animJump = AnimationUtils.loadAnimation(view.getContext(),
                R.anim.jump);
        animJumpFast = AnimationUtils.loadAnimation(view.getContext(),
                R.anim.jump_fast);

        setupRecording();



    }

    public void changeSlideToCancelText(int textResourceId) {
        textViewSlide.setText(textResourceId);
    }

    public void setStopButtonImage(int imageResource) {
        stop.setImageResource(imageResource);
    }

    public void setSendButtonImage(int imageResource) {
        send.setImageResource(imageResource);
    }

    public RecordingListener getRecordingListener() {
        return recordingListener;
    }

    public void setRecordingListener(RecordingListener recordingListener) {

        this.recordingListener = recordingListener;
    }


    @SuppressLint("ClickableViewAccessibility")
    private void setupRecording() {


        //imageViewSend.animate().scaleX(0f).scaleY(0f).setDuration(100).setInterpolator(new LinearInterpolator()).start();


     /*   recordIconImageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recordClickReceivedListener.onClickReceived();


            }
        });*/

        recordIconImageLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {



                if (isDeleting) {
                    return true;
                }

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    initTouchDownValues(motionEvent);

                    cancelOffset = (float) (screenWidth / 2.8);
                    lockOffset = (float) (screenWidth / 2.5);

                    if (firstX == 0) {

                        firstX = motionEvent.getRawX();

                    }

                    if (firstY == 0) {

                        firstY = motionEvent.getRawY();
                    }

                    Log.e("down", "onTouch: ");

                    startRecord();

                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP
                        || motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {

                    Log.e("here", "action up ");
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                        if(isClickedIntent(view,motionEvent)){
                            isClickedAction = true;
                            Log.e("clicking arena", "onTouch: ");
                            recordClickReceivedListener.onClickReceived();


                        }
                        else {

                            isClickedAction = false;
                            stopRecording(RecordingBehaviour.RELEASED);
                        }
                    }

                } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {


                    if (stopTrackingAction) {
                        return true;
                    }

                    UserBehaviour direction = UserBehaviour.NONE;

                    float motionX = Math.abs(firstX - motionEvent.getRawX());
                    float motionY = Math.abs(firstY - motionEvent.getRawY());

                    if (isLayoutDirectionRightToLeft ? (motionX > directionOffset && lastX > firstX && lastY > firstY) : (motionX > directionOffset && lastX < firstX && lastY < firstY)) {

                        if (isLayoutDirectionRightToLeft ? (motionX > motionY && lastX > firstX) : (motionX > motionY && lastX < firstX)) {
                            direction = UserBehaviour.CANCELING;

                        } else if (motionY > motionX && lastY < firstY) {
                           // direction = UserBehaviour.LOCKING;
                        }

                    } else if (isLayoutDirectionRightToLeft ? (motionX > motionY && motionX > directionOffset && lastX > firstX) : (motionX > motionY && motionX > directionOffset && lastX < firstX)) {
                        direction = UserBehaviour.CANCELING;
                    } else if (motionY > motionX && motionY > directionOffset && lastY < firstY) {
                        //direction = UserBehaviour.LOCKING;
                    }

                    if (direction == UserBehaviour.CANCELING) {
                        if (userBehaviour == UserBehaviour.NONE || motionEvent.getRawY() + recordIconImageLayout.getWidth() / 2 > firstY) {
                            userBehaviour = UserBehaviour.CANCELING;
                        }

                        if (userBehaviour == UserBehaviour.CANCELING) {
                            translateX(-(firstX - motionEvent.getRawX()));
                        }
                    } else if (direction == UserBehaviour.LOCKING) {
                        if (userBehaviour == UserBehaviour.NONE || motionEvent.getRawX() + recordIconImageLayout.getWidth() / 2 > firstX) {
                            //userBehaviour = UserBehaviour.LOCKING;
                        }

                        if (userBehaviour == UserBehaviour.LOCKING) {
                            translateY(-(firstY - motionEvent.getRawY()));
                        }
                    }

                    lastX = motionEvent.getRawX();
                    lastY = motionEvent.getRawY();
                }
                view.onTouchEvent(motionEvent);
                return true;
            }
        });


    }

    private void translateY(float y) {
        if (y < -lockOffset) {
            locked();
            recordIconImageLayout.setTranslationY(0);
            return;
        }
        recordIconImageLayout.setTranslationY(y);
        recordIconImageLayout.setTranslationX(0);
    }

    private void translateX(float x) {

        if (isLayoutDirectionRightToLeft ? x > cancelOffset : x < -cancelOffset) {
            canceled();
            recordIconImageLayout.setTranslationX(0);
            layoutSlideCancel.setTranslationX(0);
            return;
        }

        recordIconImageLayout.setTranslationX(x);
        layoutSlideCancel.setTranslationX(x);
        recordIconImageLayout.setTranslationY(0);


    }

    private void locked() {
        stopTrackingAction = true;
        stopRecording(RecordingBehaviour.LOCKED);
        isLocked = true;
    }

    private void canceled() {
        stopTrackingAction = true;
        stopRecording(RecordingBehaviour.CANCELED);
    }

    private void stopRecording(RecordingBehaviour recordingBehaviour) {



            stopTrackingAction = true;
            firstX = 0;
            firstY = 0;
            lastX = 0;
            lastY = 0;

            userBehaviour = UserBehaviour.NONE;

            recordIconImageLayout.animate().scaleX(1f).scaleY(1f).translationX(0).translationY(0).setDuration(100).setInterpolator(new LinearInterpolator()).start();
            layoutSlideCancel.setTranslationX(0);
            layoutSlideCancel.setVisibility(View.GONE);


            if (isLocked) {
                return;
            }

            if (recordingBehaviour == RecordingBehaviour.LOCKED) {

                if (recordingListener != null)
                    recordingListener.onRecordingLocked();

            } else if (recordingBehaviour == RecordingBehaviour.CANCELED) {
                timeText.clearAnimation();
                timeText.setVisibility(View.INVISIBLE);
                imageViewMic.setVisibility(View.INVISIBLE);
                layoutEffect2.setVisibility(View.GONE);
                layoutEffect1.setVisibility(View.GONE);
                timerTask.cancel();
                delete();

                // if (recordingListener != null)
                //recordingListener.onRecordingCanceled();

            } else if (recordingBehaviour == RecordingBehaviour.RELEASED || recordingBehaviour == RecordingBehaviour.LOCK_DONE) {
                timeText.clearAnimation();
                timeText.setVisibility(View.INVISIBLE);
                imageViewMic.setVisibility(View.INVISIBLE);
                layoutEffect2.setVisibility(View.GONE);
                layoutEffect1.setVisibility(View.GONE);
                timerTask.cancel();

                if (recordingListener != null)
                    recordingListener.onRecordingCompleted();
            }

    }

    private void startRecord() {
        if (recordingListener != null)


            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (isClickedAction) {

                        isClickedAction = false;

                    } else {


                        touchReceivedListener.onTouchReceived();
                        recordingListener.onRecordingStarted();
                        stopTrackingAction = false;
                        recordIconImageLayout.animate().scaleXBy(0.3f).scaleYBy(0.3f).setDuration(200).setInterpolator(new OvershootInterpolator()).start();
                        timeText.setVisibility(View.VISIBLE);
                        layoutSlideCancel.setVisibility(View.VISIBLE);
                        imageViewMic.setVisibility(View.VISIBLE);
                        layoutEffect2.setVisibility(View.VISIBLE);
                        layoutEffect1.setVisibility(View.VISIBLE);
                        dustin.setVisibility(View.GONE);
                        dustin_cover.setVisibility(View.GONE);
                        timeText.startAnimation(animBlink);


                        if (audioTimer == null) {
                            audioTimer = new Timer();
                            timeFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                        }

                        timerTask = new TimerTask() {
                            @Override
                            public void run() {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        timeText.setText(timeFormatter.format(new Date(audioTotalTime * 1000)));
                                        audioTotalTime++;
                                    }
                                });
                            }
                        };


                        audioTotalTime = 0;
                        audioTimer.schedule(timerTask, 0, 1000);
                    }
                }

                },250);

}








    private void delete() {

        imageViewMic.setVisibility(View.VISIBLE);
        imageViewMic.setRotation(0);
        isDeleting = true;


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isDeleting = false;


            }
        }, 1250);

        imageViewMic.animate().translationY(-dp * 150).rotation(180).scaleXBy(0.6f).scaleYBy(0.6f).setDuration(500).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

                float displacement = 0;

                if (isLayoutDirectionRightToLeft) {
                    displacement = dp * 40;
                } else {
                    displacement = -dp * 40;
                }

                dustin.setTranslationX(displacement);
                dustin_cover.setTranslationX(displacement);

                dustin_cover.animate().translationX(0).rotation(-120).setDuration(350).setInterpolator(new DecelerateInterpolator()).start();

                dustin.animate().translationX(0).setDuration(350).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        deleteAnimLayoutRoot.setVisibility(View.VISIBLE);
                        dustin.setVisibility(View.VISIBLE);
                        dustin_cover.setVisibility(View.VISIBLE);
                        recordingListener.onDeleteAnimationStart();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {



                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                imageViewMic.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).setInterpolator(new LinearInterpolator()).setListener(
                        new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                imageViewMic.setVisibility(View.INVISIBLE);
                                imageViewMic.setRotation(0);

                                float displacement = 0;

                                if (isLayoutDirectionRightToLeft) {
                                    displacement = dp * 40;
                                } else {
                                    displacement = -dp * 40;
                                }

                                dustin_cover.animate().rotation(0).setDuration(150).setStartDelay(50).start();
                                dustin.animate().translationX(displacement).setDuration(200).setStartDelay(250).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animator) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animator) {

                                        recordingListener.onRecordingCanceled();
                                        deleteAnimLayoutRoot.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animator) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animator) {

                                    }
                                }).start();

                                dustin_cover.animate().translationX(displacement).setDuration(200).setStartDelay(250).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {



                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                }).start();
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        }
                ).start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();
    }

    private void showErrorLog(String s) {
        Log.e(TAG, s);
    }

    private void initTouchDownValues(MotionEvent event){

        actionDownPoint.x = event.getX();
        actionDownPoint.y = event.getY();
        Log.e("action x  ", String.valueOf(actionDownPoint.x));
        Log.e("action y  ", String.valueOf(actionDownPoint.y));
        touchDownTime = System.currentTimeMillis();
    }

    private boolean isClickedIntent(View view,MotionEvent event){

        boolean isTouchLenght = (Math.abs(event.getX() - actionDownPoint.x) + Math.abs(event.getY() - actionDownPoint.y)) < touchMoveThreshold;


       // Log.e("clicked x  ", String.valueOf(event.getX()));
        // Log.e("clicked y  ", String.valueOf(event.getY()));
        // Log.e("isTouchlenght   ", String.valueOf(isTouchLenght));
        boolean isClickTimeRange = System.currentTimeMillis() - touchDownTime < touchTimeThreshold;

        Log.e("isClickTimeRange   ", String.valueOf(isClickTimeRange));
        if(isClickTimeRange){

            Log.e("within", "isClickedIntent: ");
            isClickedAction = true;
            return  true;
        }
        else {

            isClickedAction = false;
            return false;
        }


    }

}

