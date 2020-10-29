package com.estello.android.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.ViewModel.GradesAssessmentModel;
import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GradesAssessmentAdapter extends RecyclerView.Adapter<GradesAssessmentAdapter.itemViewHolder>  {


    ArrayList<GradesAssessmentModel> gradesAssessmentModelArrayList;

    public GradesAssessmentAdapter(ArrayList<GradesAssessmentModel> gradesAssessmentModelArrayList){

        this.gradesAssessmentModelArrayList = gradesAssessmentModelArrayList;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_activity_grades_recycler_item, parent, false);
        return new itemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return gradesAssessmentModelArrayList.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        public itemViewHolder(View ItemView) {
            super(ItemView);

        }

        @Override
        public void onClick(View view) {

        }

    }

    }
