package com.estello.android.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.estello.android.ViewModel.CoursesModel;
import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class more_adapter extends RecyclerView.Adapter<more_adapter.itemViewHolder> {

        ArrayList<CoursesModel> courses_modelArrayList;
        Context context;


public more_adapter(ArrayList<CoursesModel> courses_models, Context context){
        this.courses_modelArrayList = courses_models;
        this.context = context;
        }


@NonNull
@Override
public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.starting_soon_more_recycler_item, parent, false);
        return new itemViewHolder(view2);
        }

@Override
public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {


        }

@Override
public int getItemCount() {
        return courses_modelArrayList.size();
        }

public class itemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView Title;
    private TextView instructor_details;
    public itemViewHolder(View ItemView){
        super(ItemView);
        Title = ItemView.findViewById(R.id.more_courses_title);
        instructor_details = ItemView.findViewById(R.id.more_courses_instructor_details);
        Typeface customfont= Typeface.createFromAsset(itemView.getContext().getAssets(),"clanbold.ttf");
        Typeface customfont2= Typeface.createFromAsset(itemView.getContext().getAssets(), "font/latoreg.ttf");

        Title.setTypeface(customfont);
        instructor_details.setTypeface(customfont2);
    }
    @Override
    public void onClick(View view) {

    }
}
}
