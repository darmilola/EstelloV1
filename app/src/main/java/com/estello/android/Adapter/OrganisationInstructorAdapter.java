package com.estello.android.Adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.estello.android.ViewModel.OrganisationProfileInstructorsModel;
import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrganisationInstructorAdapter extends RecyclerView.Adapter<OrganisationInstructorAdapter.itemViewHolder>  {

    ArrayList<OrganisationProfileInstructorsModel> instructorsModelArrayList;

    public OrganisationInstructorAdapter(ArrayList<OrganisationProfileInstructorsModel> instructorsModelArrayList){

        this.instructorsModelArrayList = instructorsModelArrayList;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.organisation_profile_instructor_item, parent, false);
         return new itemViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {

        holder.instructorsName.setText(instructorsModelArrayList.get(position).getInstructorsName());
        holder.instructorsImage.setImageResource(instructorsModelArrayList.get(position).getInstructorsImage());
    }

    @Override
    public int getItemCount() {
        return instructorsModelArrayList.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder{

        private ImageView instructorsImage;
        private TextView  instructorsName;
        public itemViewHolder(View ItemView){
            super(ItemView);

            instructorsImage = ItemView.findViewById(R.id.organisation_profile_instructors_item_image);
            instructorsName = ItemView.findViewById(R.id.organisation_profile_instructors_item_name);
            Typeface customfont= Typeface.createFromAsset(itemView.getContext().getAssets(),"larsseit.ttf");
            instructorsName.setTypeface(customfont);

        }

    }
}
