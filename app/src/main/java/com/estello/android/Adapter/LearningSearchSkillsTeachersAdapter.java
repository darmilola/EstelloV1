package com.estello.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.R;
import com.estello.android.ViewModel.CoursesModel;
import com.estello.android.ViewModel.LearningSearchModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LearningSearchSkillsTeachersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<LearningSearchModel> learningSearchModelArrayList;
    Context context;
    private static int typeSkills = 0;
    private static int typeTeacher = 1;

    public LearningSearchSkillsTeachersAdapter(ArrayList<LearningSearchModel> learningSearchModelArrayList, Context context){

        this.context = context;
        this.learningSearchModelArrayList = learningSearchModelArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == typeSkills) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.learning_search_skill_item, parent, false);
            return new SkillsItemViewHolder(view);
        }
        else if(viewType == typeTeacher){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.learning_search_teachers_item, parent, false);
            return new TeachersItemViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return learningSearchModelArrayList.size();
    }

    @Override
    public int getItemViewType(int position){

        if(learningSearchModelArrayList.get(position).getType() == typeSkills){
            return  typeSkills;
        }
        else if(learningSearchModelArrayList.get(position).getType() == typeTeacher){
            return  typeTeacher;
        }
        return  typeTeacher;
    }

    public class SkillsItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public SkillsItemViewHolder(View ItemView){
            super(ItemView);

        }
        @Override
        public void onClick(View view) {

        }
    }
    public class TeachersItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public TeachersItemViewHolder(View ItemView){
            super(ItemView);

        }
        @Override
        public void onClick(View view) {

        }
    }
}
