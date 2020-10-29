package com.estello.android.Adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.estello.android.ViewModel.SearchHistoryModel;
import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.itemViewHolder> {

    ArrayList<SearchHistoryModel> searchHistoryModelArrayList;
    private ListItemClickListener mOnClickListener;


    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public SearchHistoryAdapter(ArrayList<SearchHistoryModel> searchHistoryModelArrayList, ListItemClickListener mOnClickListener){
        this.searchHistoryModelArrayList = searchHistoryModelArrayList;
        this.mOnClickListener = mOnClickListener;

    }


    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_course_history_recycler_item, parent, false);
        return new itemViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {

        holder.searchQuery.setText(searchHistoryModelArrayList.get(position).getSearchQuery());

    }

    @Override
    public int getItemCount() {
        return searchHistoryModelArrayList.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView searchQuery;
        public itemViewHolder(View ItemView){
            super(ItemView);
            ItemView.setOnClickListener(this);
            searchQuery = ItemView.findViewById(R.id.search_query);
            Typeface customfont= Typeface.createFromAsset(itemView.getContext().getAssets(),"opensansreg.ttf");
             searchQuery.setTypeface(customfont);
        }
        @Override
        public void onClick(View view) {

            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
