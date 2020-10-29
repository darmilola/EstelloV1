package com.estello.android.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.estello.android.ViewModel.TrainingPostModel;
import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrainingQandAAdapter extends RecyclerView.Adapter<TrainingQandAAdapter.viewHolder> {

    Context context;
    ArrayList<TrainingPostModel> trainingPostModelArrayList;


    public TrainingQandAAdapter(Context context, ArrayList<TrainingPostModel> trainingPostModelArrayList){

        this.context = context;
        this.trainingPostModelArrayList = trainingPostModelArrayList;

    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_post_item, parent, false);
        return new viewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return trainingPostModelArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        private TextView profileName,timeStamp,postText,comments;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profileName = itemView.findViewById(R.id.training_post_profile_name);
            timeStamp = itemView.findViewById(R.id.training_post_timestamp);
            postText = itemView.findViewById(R.id.training_post_post_text);
            comments = itemView.findViewById(R.id.training_post_add_comment);
            Typeface customfont= Typeface.createFromAsset(itemView.getContext().getAssets(), "font/latoreg.ttf");

            profileName.setTypeface(customfont);
            timeStamp.setTypeface(customfont);
            postText.setTypeface(customfont);
            comments.setTypeface(customfont);


        }
    }
}
