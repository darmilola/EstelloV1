package com.estello.android.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.allattentionhere.autoplayvideos.AAH_CustomViewHolder;
import com.estello.android.ViewModel.NetworkPostModel;
import com.hendraanggrian.appcompat.widget.SocialTextView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import com.estello.android.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonalProfilePostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    List<NetworkPostModel> postList;
    PopupWindow popupWindow;
    Context context;
    //private ListItemClickListener mOnClickListener;

    public PersonalProfilePostAdapter(ArrayList<NetworkPostModel> postList, Context context){
        this.postList = postList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case 1:

                break;
            case 2:

                VideosViewHolder videosViewHolder = (VideosViewHolder)holder;
                videosViewHolder.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSlux0mAL9A9f5bisDKWgJmLABEZhTETP5RXA&usqp=CAU");
                videosViewHolder.setVideoUrl("http://techslides.com/demos/sample-videos/small.mp4");
                Picasso.get().load(videosViewHolder.getImageUrl()).placeholder(R.drawable.codingpic).into(videosViewHolder.getAAH_ImageView());
                break;

            case 3:

                ImagesViewHolder imagesViewHolder = (ImagesViewHolder)holder;
                ImageSliderAdapter sliderAdapter = new ImageSliderAdapter(context,postList.get(position).getNetworkPostImageModelArrayList());
                imagesViewHolder.sliderView.setSliderAdapter(sliderAdapter);
                break;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.personal_profile_network_post_item_text, parent, false);
                return new itemViewHolder(view);
            case 2:
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.personal_profile_network_post_item_video, parent, false);
                return new VideosViewHolder(view2);
            case 3:
                View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.personal_profile_network_post_item_image,parent,false);
                return  new ImagesViewHolder(view3);
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
    @Override
    public int getItemViewType(int position){
        if(postList.get(position).getViewType() == 1){
            return 1;
        }
        else if(postList.get(position).getViewType() == 2){
            return 2;
        }
        else if(postList.get(position).getViewType() == 3){

            return 3;
        }



        return 0;
    }





    public class itemViewHolder extends RecyclerView.ViewHolder{

        private SocialTextView socialTextView;
        private TextView senderFullName;
        private TextView senderProfession;
        private TextView reaction_count;
        private TextView comment_count;
        private TextView comment_count_title;
        private ImageButton react_button;
        private TextView clap_only_count;
        private TextView comment_selection_title;
        private TextView share_title;

        LinearLayout reactLayout;
        public itemViewHolder(View itemView) {

            super(itemView);
            socialTextView = itemView.findViewById(R.id.network_post_text);
            senderFullName = itemView.findViewById(R.id.network_sender_fullname);
            senderProfession = itemView.findViewById(R.id.network_sender_profession);
            reaction_count = itemView.findViewById(R.id.reaction_count);
            comment_count = itemView.findViewById(R.id.comment_count);
            comment_count_title = itemView.findViewById(R.id.comments_title);
            react_button = itemView.findViewById(R.id.react_button);
            clap_only_count = itemView.findViewById(R.id.clap_only_count);
            comment_selection_title = itemView.findViewById(R.id.comment_select_title);
            share_title = itemView.findViewById(R.id.share_title);

            reactLayout = itemView.findViewById(R.id.react_layout);
            Typeface customfont= Typeface.createFromAsset(itemView.getContext().getAssets(),"montreg.ttf");
            Typeface customfont2= Typeface.createFromAsset(itemView.getContext().getAssets(),"montsemi.ttf");
            senderProfession.setTypeface(customfont);
            senderFullName.setTypeface(customfont2);
            socialTextView.setTypeface(customfont);
            reaction_count.setTypeface(customfont);
            comment_selection_title.setTypeface(customfont);
            comment_count.setTypeface(customfont);

            comment_selection_title.setTypeface(customfont);
            share_title.setTypeface(customfont);

            comment_count_title.setTypeface(customfont);
            clap_only_count.setTypeface(customfont);

            react_button.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    showPopup(view,getAdapterPosition());
                    return  true;
                }
            });



        }


    }



    public class  VideosViewHolder extends AAH_CustomViewHolder {


        private SocialTextView socialTextView;
        private TextView senderFullName;
        private TextView senderProfession;
        private TextView reaction_count;
        private TextView comment_count;
        private TextView comment_count_title;

        private TextView comment_selection_title;
        private TextView share_title;

        private ImageButton react_button;
        private TextView clap_only_count;

        LinearLayout reactLayout;
        public VideosViewHolder(View itemView) {
            super(itemView);
            socialTextView = itemView.findViewById(R.id.network_post_text);
            senderFullName = itemView.findViewById(R.id.network_sender_fullname);
            senderProfession = itemView.findViewById(R.id.network_sender_profession);
            reaction_count = itemView.findViewById(R.id.reaction_count);
            comment_count = itemView.findViewById(R.id.comment_count);
            comment_count_title = itemView.findViewById(R.id.comments_title);

            reactLayout = itemView.findViewById(R.id.react_layout);
            comment_selection_title = itemView.findViewById(R.id.comment_select_title);
            comment_count_title = itemView.findViewById(R.id.comments_title);
            share_title = itemView.findViewById(R.id.share_title);

            react_button = itemView.findViewById(R.id.react_button);
            clap_only_count = itemView.findViewById(R.id.clap_only_count);




            Typeface customfont= Typeface.createFromAsset(itemView.getContext().getAssets(),"montreg.ttf");
            Typeface customfont2= Typeface.createFromAsset(itemView.getContext().getAssets(),"montsemi.ttf");
            senderProfession.setTypeface(customfont);
            senderFullName.setTypeface(customfont2);
            socialTextView.setTypeface(customfont);
            reaction_count.setTypeface(customfont);
            comment_count_title.setTypeface(customfont);
            comment_selection_title.setTypeface(customfont);
            comment_count.setTypeface(customfont);

            comment_selection_title.setTypeface(customfont);
            share_title.setTypeface(customfont);

            clap_only_count.setTypeface(customfont);

            react_button.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    showPopup(view,getAdapterPosition());
                    return  true;
                }
            });
        }



    }




    public class  ImagesViewHolder extends AAH_CustomViewHolder {


        private SocialTextView socialTextView;
        private TextView senderFullName;
        private TextView senderProfession;
        private TextView reaction_count;
        private TextView comment_count;
        private TextView comment_count_title;

        private TextView comment_selection_title;
        private TextView share_title;

        private ImageButton react_button;
        private TextView clap_only_count;
        SliderView sliderView;

        LinearLayout reactLayout;
        public ImagesViewHolder(View itemView) {
            super(itemView);
            socialTextView = itemView.findViewById(R.id.network_post_text);
            senderFullName = itemView.findViewById(R.id.network_sender_fullname);
            senderProfession = itemView.findViewById(R.id.network_sender_profession);
            reaction_count = itemView.findViewById(R.id.reaction_count);
            comment_count = itemView.findViewById(R.id.comment_count);
            comment_count_title = itemView.findViewById(R.id.comments_title);
            sliderView = itemView.findViewById(R.id.SliderView);
            reactLayout = itemView.findViewById(R.id.react_layout);
            comment_selection_title = itemView.findViewById(R.id.comment_select_title);
            comment_count_title = itemView.findViewById(R.id.comments_title);
            share_title = itemView.findViewById(R.id.share_title);

            react_button = itemView.findViewById(R.id.react_button);
            clap_only_count = itemView.findViewById(R.id.clap_only_count);




            sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
            sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
            sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
            sliderView.setIndicatorSelectedColor(Color.parseColor("#fa2d65"));
            sliderView.setIndicatorUnselectedColor(Color.GRAY);
            sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
            sliderView.startAutoCycle();


            Typeface customfont= Typeface.createFromAsset(itemView.getContext().getAssets(),"montreg.ttf");
            Typeface customfont2= Typeface.createFromAsset(itemView.getContext().getAssets(),"montsemi.ttf");
            senderProfession.setTypeface(customfont);
            senderFullName.setTypeface(customfont2);
            socialTextView.setTypeface(customfont);
            reaction_count.setTypeface(customfont);
            comment_count_title.setTypeface(customfont);
            comment_selection_title.setTypeface(customfont);
            comment_count.setTypeface(customfont);

            comment_selection_title.setTypeface(customfont);
            share_title.setTypeface(customfont);

            clap_only_count.setTypeface(customfont);

            react_button.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    showPopup(view,getAdapterPosition());
                    return  true;
                }
            });
        }



    }


    public void showPopup(View v,int position) {


        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View popupView = layoutInflater.inflate(R.layout.reaction_popup, null);
        popupWindow = new PopupWindow(popupView,ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
        Rect location = locateView(v);
        popupWindow.showAtLocation(v, Gravity.TOP|Gravity.LEFT, location.left, location.top);
        //popupWindow.showAsDropDown(v);
    }

    public static Rect locateView(View v)
    {
        int[] loc_int = new int[2];
        if (v == null) return null;
        try
        {
            v.getLocationOnScreen(loc_int);
        } catch (NullPointerException npe)
        {
            //Happens when the view doesn't exist on screen anymore.
            return null;
        }
        Rect location = new Rect();
        location.left = loc_int[0];
        location.top = loc_int[1];
        location.right = location.left + v.getWidth();
        location.bottom = location.top + v.getHeight();
        return location;
    }





}
