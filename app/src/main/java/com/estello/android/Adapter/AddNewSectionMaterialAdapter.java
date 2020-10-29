package com.estello.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.estello.android.ViewModel.AddNewSectionMaterials;

import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class AddNewSectionMaterialAdapter extends RecyclerView.Adapter<AddNewSectionMaterialAdapter.itemViewHolder> {


    ArrayList<AddNewSectionMaterials> addNewSectionMaterials = new ArrayList<>();
    Context context;

    public AddNewSectionMaterialAdapter(Context context, ArrayList<AddNewSectionMaterials> addNewSectionMaterialsArrayList){

        this.addNewSectionMaterials = addNewSectionMaterialsArrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_new_section_material_item, parent, false);
        return new itemViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {

        holder.materialName.setText(addNewSectionMaterials.get(position).getMaterialName());
        holder.materialImage.setImageResource(addNewSectionMaterials.get(position).getMaterialImage());


    }

    @Override
    public int getItemCount() {
        return addNewSectionMaterials.size();
    }


    public class itemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView materialName;
        private ImageView materialImage;
        private ImageView materialCloseImage;

        public itemViewHolder(View ItemView){
            super(ItemView);
            materialName = ItemView.findViewById(R.id.create_section_add_or_edit_material_item_title);
            materialImage = ItemView.findViewById(R.id.create_section_add_or_edit_material_item_image);
            materialCloseImage = ItemView.findViewById(R.id.create_section_add_or_edit_material_item_delete);
        }
        @Override
        public void onClick(View view) {

        }
    }

}
