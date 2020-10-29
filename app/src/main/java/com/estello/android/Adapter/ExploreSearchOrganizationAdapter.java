package com.estello.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.estello.android.ViewModel.OrganizationModel;
import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExploreSearchOrganizationAdapter extends RecyclerView.Adapter<ExploreSearchOrganizationAdapter.itemViewHolder>  {




    ArrayList<OrganizationModel> organizationModelArrayList;
    Context context;


    public ExploreSearchOrganizationAdapter(ArrayList<OrganizationModel> organizationModels, Context context){
        this.organizationModelArrayList = organizationModels;
        this.context = context;
    }


    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_organization_recycler_item, parent, false);
        return new itemViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return organizationModelArrayList.size();
    }



    public class itemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public itemViewHolder(View ItemView){
            super(ItemView);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {

        }
    }
}
