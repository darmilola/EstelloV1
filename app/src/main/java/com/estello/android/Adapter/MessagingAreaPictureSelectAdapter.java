package com.estello.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.ViewModel.MessagingAreaPictureSelectModel;
import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessagingAreaPictureSelectAdapter extends RecyclerView.Adapter<MessagingAreaPictureSelectAdapter.itemViewHolder> {

        ArrayList<MessagingAreaPictureSelectModel> messagingAreaPictureSelectModelArrayList;
        Context context;


        public MessagingAreaPictureSelectAdapter(ArrayList<MessagingAreaPictureSelectModel> messagingAreaPictureSelectModels, Context context){

            this.messagingAreaPictureSelectModelArrayList = messagingAreaPictureSelectModels;

            this.context = context;

        }


     @NonNull
     @Override
     public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.messaging_area_picture_select_item, parent, false);
        return new itemViewHolder(view2);
        }

     @Override
     public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {


        }

     @Override
     public int getItemCount() {

            return messagingAreaPictureSelectModelArrayList.size();
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
