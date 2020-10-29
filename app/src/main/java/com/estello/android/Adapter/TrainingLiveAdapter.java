package com.estello.android.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.estello.android.ViewModel.TrainingLiveModel;
import com.google.android.material.button.MaterialButton;

import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrainingLiveAdapter extends RecyclerView.Adapter<TrainingLiveAdapter.viewHolder> {

    Context context;
    ArrayList<TrainingLiveModel> trainingLiveModelArrayList;

    public TrainingLiveAdapter(Context context, ArrayList<TrainingLiveModel> trainingLiveModelArrayList){

       this.context = context;
       this.trainingLiveModelArrayList = trainingLiveModelArrayList;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_live_item, parent, false);
        return new viewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return trainingLiveModelArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        private TextView divisionalTitle,sessionTitle,classIdTitle,classIdValue,classDurationTitle,classDurationValue,classPasswordTitle,classPasswordValue,status;


        MaterialButton joinClass,openClass;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

           divisionalTitle = itemView.findViewById(R.id.training_live_divisional_type);
           sessionTitle = itemView.findViewById(R.id.training_live_session_title);
           classIdTitle = itemView.findViewById(R.id.training_live_class_id_title);
           classIdValue = itemView.findViewById(R.id.training_live_class_id_value);
           classDurationTitle = itemView.findViewById(R.id.training_live_duration_title);
           classDurationValue = itemView.findViewById(R.id.training_live_duration_value);
           classPasswordTitle = itemView.findViewById(R.id.training_live_password_title);
           classPasswordValue = itemView.findViewById(R.id.training_live_password_value);
           joinClass = itemView.findViewById(R.id.training_live_join_class);
           openClass = itemView.findViewById(R.id.training_live_open_class);
           status = itemView.findViewById(R.id.training_live_status);
            Typeface customfont= Typeface.createFromAsset(itemView.getContext().getAssets(), "font/latoreg.ttf");

            divisionalTitle.setTypeface(customfont);
            sessionTitle.setTypeface(customfont);
            classIdTitle.setTypeface(customfont);
            classIdValue.setTypeface(customfont);
            classDurationTitle.setTypeface(customfont);
            classDurationValue.setTypeface(customfont);
            classPasswordValue.setTypeface(customfont);
            classPasswordTitle.setTypeface(customfont);
            joinClass.setTypeface(customfont);
            status.setTypeface(customfont);
            openClass.setTypeface(customfont);


        }
    }
}
