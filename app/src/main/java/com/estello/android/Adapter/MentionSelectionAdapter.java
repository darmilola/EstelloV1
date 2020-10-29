package com.estello.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.ViewModel.MentionSelectionModel;

import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MentionSelectionAdapter extends RecyclerView.Adapter<MentionSelectionAdapter.itemViewHolder> {


    ArrayList<MentionSelectionModel> mentionSelectionModelArrayList;
    MentionItemClickListener mentionItemClickListener;
    Context context;

    public interface MentionItemClickListener{
        public void onMentionItemClick();
    }

    public MentionSelectionAdapter(ArrayList<MentionSelectionModel> mentionSelectionModelArrayList, MentionItemClickListener mentionItemClickListener, Context context){
        this.mentionSelectionModelArrayList = mentionSelectionModelArrayList;
        this.context = context;
        this.mentionItemClickListener = mentionItemClickListener;
    }


    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.mentioning_select_recycler_item, parent, false);
        return new itemViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return mentionSelectionModelArrayList.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public itemViewHolder(View ItemView){
            super(ItemView);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {

            mentionItemClickListener.onMentionItemClick();
        }
    }
}