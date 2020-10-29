package com.estello.android.Adapter;

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

public class MyCoursesAdapter extends RecyclerView.Adapter<MyCoursesAdapter.viewHolder> {

    ArrayList<learningModel> learningModelArrayList;

    public MyCoursesAdapter(ArrayList<learningModel> learningModelArrayList){
        this.learningModelArrayList = learningModelArrayList;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_courses_recycler_item, parent, false);
        return new viewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return learningModelArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        private TextView courseTitle,courseStatus;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            courseTitle = itemView.findViewById(R.id.course_title);
            courseStatus = itemView.findViewById(R.id.status);
            Typeface customfont= Typeface.createFromAsset(itemView.getContext().getAssets(),"montsemi.ttf");
            Typeface customfont2= Typeface.createFromAsset(itemView.getContext().getAssets(),"montreg.ttf");


        }
    }
}
