package com.estello.android.Adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.estello.android.ViewModel.downloads_model;
import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DownloadFragmentAdapter extends RecyclerView.Adapter<DownloadFragmentAdapter.itemViewHolder> {


    ArrayList<downloads_model> downloads_modelArrayList;

    public DownloadFragmentAdapter(ArrayList<downloads_model> downloads_models){
        this.downloads_modelArrayList = downloads_models;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.downloads_recycler_item, parent, false);
        return new itemViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return downloads_modelArrayList.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder{

        TextView course_title,instructor,filesize;
        public itemViewHolder(@NonNull View itemView) {
            super(itemView);
            course_title = itemView.findViewById(R.id.downloads_course_title);
            instructor = itemView.findViewById(R.id.download_course_instructor);
            filesize = itemView.findViewById(R.id.download_file_size);
            Typeface customfont= Typeface.createFromAsset(itemView.getContext().getAssets(),"montsemi.ttf");
            Typeface customfont2= Typeface.createFromAsset(itemView.getContext().getAssets(),"montreg.ttf");

            instructor.setTypeface(customfont);
            filesize.setTypeface(customfont2);
            course_title.setTypeface(customfont);

        }
    }
}
