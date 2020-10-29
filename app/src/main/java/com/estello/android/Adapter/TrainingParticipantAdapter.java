package com.estello.android.Adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.estello.android.ViewModel.TrainingParticipantsModel;
import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrainingParticipantAdapter extends RecyclerView.Adapter<TrainingParticipantAdapter.viewHolder> {

    ArrayList<TrainingParticipantsModel> trainingParticipantsModels;

    public TrainingParticipantAdapter(ArrayList<TrainingParticipantsModel> trainingParticipantsModels){

        this.trainingParticipantsModels = trainingParticipantsModels;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_page_participant_item, parent, false);
        return new viewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return trainingParticipantsModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        private TextView Name,about;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.training_participant_profile_name);
            about = itemView.findViewById(R.id.training_participant_about);
            Typeface customfont= Typeface.createFromAsset(itemView.getContext().getAssets(), "font/latoreg.ttf");

            Name.setTypeface(customfont);
            about.setTypeface(customfont);

        }
    }
}
