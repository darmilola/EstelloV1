package com.estello.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.estello.android.ViewModel.MessagingAreaAttachmentModel;

import com.estello.android.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessagingAreaAttachmentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    List<MessagingAreaAttachmentModel> messagingAreaAttachmentModelList;
    private static final int TYPE_FILES = 0;
    private static final int TYPE_IMAGES = 1;
    PopupWindow popupWindow;
    Context context;


    public MessagingAreaAttachmentsAdapter(ArrayList<MessagingAreaAttachmentModel> messagingAreaAttachmentModelList, Context context){

        this.messagingAreaAttachmentModelList = messagingAreaAttachmentModelList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case TYPE_FILES:



                break;

            case TYPE_IMAGES:

                break;

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {

            case TYPE_FILES:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messaging_area_attachment_type_file, parent, false);
                return new FilesItemViewHolder(view);

            case TYPE_IMAGES:
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_creation_area_attachment_item_type_image, parent, false);
                return new ImagesItemViewHolder(view2);
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return messagingAreaAttachmentModelList.size();
    }
    @Override
    public int getItemViewType(int position){
        if(messagingAreaAttachmentModelList.get(position).getAttachmentType() == 0){
            return TYPE_FILES;
        }
        else if(messagingAreaAttachmentModelList.get(position).getAttachmentType() == 1){
            return TYPE_IMAGES;
        }

        return TYPE_FILES;
    }



    public class ImagesItemViewHolder extends RecyclerView.ViewHolder {


        public ImagesItemViewHolder(View itemView) {

            super(itemView);

        }

    }

    public class FilesItemViewHolder extends RecyclerView.ViewHolder{


        public FilesItemViewHolder(View itemView) {

            super(itemView);

        }


    }

}
