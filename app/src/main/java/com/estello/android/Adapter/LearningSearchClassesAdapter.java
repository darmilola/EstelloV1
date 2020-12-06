package com.estello.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.R;
import com.estello.android.ViewModel.LearningSearchModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LearningSearchClassesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<LearningSearchModel> learningSearchModelArrayList;
    Context context;
    private static int typeWatch = 0;
    private static int typeLive = 1;

    public LearningSearchClassesAdapter(ArrayList<LearningSearchModel> learningSearchModelArrayList, Context context){

        this.context = context;
        this.learningSearchModelArrayList = learningSearchModelArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == typeWatch) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.learning_search_classes_item, parent, false);
            return new WatchItemViewHolder(view);
        }
        else if(viewType == typeLive){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.learning_search_classes_item_type_live, parent, false);
            return new LiveItemViewHolder(view);
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

        if(learningSearchModelArrayList.get(position).getType() == typeWatch){
            return  typeWatch;
        }
        else if(learningSearchModelArrayList.get(position).getType() == typeLive){
            return  typeLive;
        }
        return  typeLive;
    }

    public class LiveItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public LiveItemViewHolder(View ItemView){
            super(ItemView);

        }
        @Override
        public void onClick(View view) {

        }
    }
    public class WatchItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public WatchItemViewHolder(View ItemView){
            super(ItemView);

        }
        @Override
        public void onClick(View view) {

        }
    }
}
