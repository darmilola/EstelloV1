package com.estello.android.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.ViewModel.explore_category_chip_model;
import com.robertlevonyan.views.chip.Chip;

import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class explore_chip_adapter extends RecyclerView.Adapter<explore_chip_adapter.itemViewHolder> {




    ArrayList<explore_category_chip_model> explore_category_chip_models;
    Context context;


    public explore_chip_adapter(ArrayList<explore_category_chip_model> explore_category_chip_models, Context context){
        this.explore_category_chip_models = explore_category_chip_models;
        this.context = context;
    }


    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_category_chip, parent, false);
        return new itemViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {

             holder.categoryTitle.setText(explore_category_chip_models.get(position).getCategoryText());

    }

    @Override
    public int getItemCount() {
        return explore_category_chip_models.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder {

        Chip categoryTitle;
        public itemViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTitle = itemView.findViewById(R.id.chipTitle);
            Typeface customfont = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/latoreg.ttf");
            categoryTitle.setTypeface(customfont);

        }
    }
}
