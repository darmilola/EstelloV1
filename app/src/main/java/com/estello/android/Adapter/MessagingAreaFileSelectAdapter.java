package com.estello.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.ViewModel.MessagingAreaFileSelectModel;

import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessagingAreaFileSelectAdapter extends RecyclerView.Adapter<MessagingAreaFileSelectAdapter.itemViewHolder> {

    private ArrayList<MessagingAreaFileSelectModel> messagingAreaFileSelectModelArrayList;
    Context context;


    public MessagingAreaFileSelectAdapter(ArrayList<MessagingAreaFileSelectModel> messagingAreaPictureDisplayModelArrayList, Context context){

        this.messagingAreaFileSelectModelArrayList = messagingAreaPictureDisplayModelArrayList;

        this.context = context;

    }


    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.messaging_area_file_display_item, parent, false);
        return new itemViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {

        return messagingAreaFileSelectModelArrayList.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public itemViewHolder(View ItemView){
            super(ItemView);

        }
        @Override
        public void onClick(View view) {

        }
    }
}


