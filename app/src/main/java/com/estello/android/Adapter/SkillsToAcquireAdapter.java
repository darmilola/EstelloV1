package com.estello.android.Adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.ViewModel.SkillsAndTagsModel;
import com.robertlevonyan.views.chip.Chip;

import com.estello.android.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SkillsToAcquireAdapter extends RecyclerView.Adapter<SkillsToAcquireAdapter.itemViewHolder> {

    List<SkillsAndTagsModel> chipList;

    public SkillsToAcquireAdapter(ArrayList<SkillsAndTagsModel> chipList) {
        this.chipList = chipList;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_skill_to_acquire, parent, false);
        return new itemViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {


        holder.chip.setText(chipList.get(position).getLabel());

    }

    @Override
    public int getItemCount() {
        return chipList.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder{


        private Chip chip;

        public itemViewHolder(View ItemView){
            super(ItemView);
            chip = ItemView.findViewById(R.id.selected_skill_chip);
            Typeface customfont = Typeface.createFromAsset(ItemView.getContext().getAssets(), "font/latoreg.ttf");
            chip.setTypeface(customfont);

        }

    }
}
