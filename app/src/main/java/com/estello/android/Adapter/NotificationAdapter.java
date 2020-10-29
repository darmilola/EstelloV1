package com.estello.android.Adapter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.estello.android.ViewModel.notification_model;
import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    ArrayList<notification_model> notificationModelArrayList;


    public NotificationAdapter(ArrayList<notification_model> notification_models){
        this.notificationModelArrayList = notification_models;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item_type_like_share_mention_post, parent, false);
        return new NotificationViewHolder(view2);




    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return notificationModelArrayList.size();
    }




  /*  public class createCourseViewHolder extends RecyclerView.ViewHolder{


        private TextView notificationText;
        private TextView timestamp;

        public createCourseViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationText = itemView.findViewById(R.id.notification_text);
            timestamp = itemView.findViewById(R.id.notfication_timestamp);
            Typeface customfont2= Typeface.createFromAsset(itemView.getContext().getAssets(),"montreg.ttf");
            notificationText.setTypeface(customfont2);
            timestamp.setTypeface(customfont2);
            String source = "<b>Damilola Akinterinwa,Emmanuel and james Bond</b>"+" Followed you";
            notificationText.setText(Html.fromHtml(source));
        }
    }*/

    public class NotificationViewHolder extends RecyclerView.ViewHolder{


         private TextView notificationText;
         private TextView timestamp;
         public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationText = itemView.findViewById(R.id.notification_text);
            timestamp = itemView.findViewById(R.id.notfication_timestamp);
            Typeface customfont2= Typeface.createFromAsset(itemView.getContext().getAssets(),"montreg.ttf");
            notificationText.setTypeface(customfont2);
            timestamp.setTypeface(customfont2);
            String notifGen = "Damilola Akinterinwa,Emmanuel and james Bond,Mark and Cloudinary ";
            String notifCause = "followed";
            String lastPart = " You";

            String myNotif = notifGen+notifCause+lastPart;

            Spannable spannable = new SpannableString(myNotif);

            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#fa2d65")),notifGen.length(), (notifGen+notifCause).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            spannable.setSpan(new StyleSpan(Typeface.BOLD),0,notifGen.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            notificationText.setText(spannable, TextView.BufferType.SPANNABLE);
            //notificationText.setText(Html.fromHtml(source));
        }
    }
   /* public class followingViewHolder extends RecyclerView.ViewHolder{



        private TextView notificationText;
        private TextView timestamp;
        public followingViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationText = itemView.findViewById(R.id.notification_text);
            timestamp = itemView.findViewById(R.id.notfication_timestamp);
            Typeface customfont2= Typeface.createFromAsset(itemView.getContext().getAssets(),"montreg.ttf");
            notificationText.setTypeface(customfont2);
            timestamp.setTypeface(customfont2);
            String source = "<b>Damilola Akinterinwa,Emmanuel and james Bond</b>"+" Followed you";
            notificationText.setText(Html.fromHtml(source));
        }
    }*/
}
