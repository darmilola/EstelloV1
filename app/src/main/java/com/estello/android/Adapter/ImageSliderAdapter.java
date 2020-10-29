package com.estello.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.estello.android.ViewModel.NetworkPostImageModel;
import com.smarteist.autoimageslider.SliderViewAdapter;

import com.estello.android.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageSliderAdapter extends SliderViewAdapter<ImageSliderAdapter.SliderAdapterVH> {

    private Context context;
    ArrayList<NetworkPostImageModel> networkPostImageModelArrayList;

    public ImageSliderAdapter(Context context, ArrayList<NetworkPostImageModel> networkPostImageModelArrayList) {
        this.context = context;
        this.networkPostImageModelArrayList = networkPostImageModelArrayList;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_image_layout, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {

        Picasso.get().load(networkPostImageModelArrayList.get(position).getImageUrl()).placeholder(R.drawable.codingpic).into(viewHolder.postimage);


    }



    @Override
    public int getCount() {

        return networkPostImageModelArrayList.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView postimage;


        public SliderAdapterVH(View itemView) {
            super(itemView);
            postimage = itemView.findViewById(R.id.slider_image);
            this.itemView = itemView;
        }
    }
}