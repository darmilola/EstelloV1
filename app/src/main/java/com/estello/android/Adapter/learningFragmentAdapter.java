package com.estello.android.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.estello.android.ViewModel.learningModel;
import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class learningFragmentAdapter extends RecyclerView.Adapter<learningFragmentAdapter.viewHolder> {


     ArrayList<learningModel> modelArrayList;
     Context context;

     public learningFragmentAdapter(ArrayList<learningModel> learningModelArrayList, Context context){
         this.modelArrayList = learningModelArrayList;
         this.context = context;

     }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.learn_recycler_item, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        private TextView instructorName,courseTitle,courseStatus;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            instructorName = itemView.findViewById(R.id.instructors_name);
            courseTitle = itemView.findViewById(R.id.course_title);
            courseStatus = itemView.findViewById(R.id.status);
            Typeface customfont= Typeface.createFromAsset(itemView.getContext().getAssets(),"clanbold.ttf");
            Typeface customfont2= Typeface.createFromAsset(itemView.getContext().getAssets(), "font/latoreg.ttf");
            courseTitle.setTypeface(customfont);
            courseStatus.setTypeface(customfont2);
            instructorName.setTypeface(customfont2);

        }
    }
}
