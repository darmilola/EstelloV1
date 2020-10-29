package com.estello.android.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.estello.android.ViewModel.CourseWeekSectionDetailsModel;
import com.estello.android.ViewModel.CourseWeekSectionsModel;
import com.estello.android.ViewHolders.CourseActivityWeekSectionTitleViewHolder;
import com.estello.android.ViewHolders.CourseActivityWeeksLiveViewHolder;
import com.estello.android.ViewHolders.CourseActivityWeeksReadingViewHolder;
import com.estello.android.ViewHolders.CourseActivityWeeksVideoViewHolder;
import com.estello.android.R;

import java.util.List;
import androidx.annotation.NonNull;
import static android.view.LayoutInflater.from;

public class CourseActivityWeeksAdapter extends ExpandableRecyclerAdapter<CourseWeekSectionsModel, CourseWeekSectionDetailsModel, CourseActivityWeekSectionTitleViewHolder, ChildViewHolder> {


    private static final int typeVideo = 1;
    private static final int typeReading = 2;
    private static final int typeLive = 3;
    List<CourseWeekSectionsModel> courseWeekSectionsModelList;
    Context context;

    public CourseActivityWeeksAdapter(@NonNull List<CourseWeekSectionsModel> parentList,Context context) {
        super(parentList);
        this.courseWeekSectionsModelList = parentList;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseActivityWeekSectionTitleViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View view = from(context).inflate(R.layout.course_activity_week_section_title_item,parentViewGroup,false);
        return new CourseActivityWeekSectionTitleViewHolder(view);
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        switch (viewType) {
            case typeVideo:
                View video = from(context).inflate(R.layout.course_activity_week_section_details_item_video, childViewGroup, false);
                return new CourseActivityWeeksVideoViewHolder(video);
            case typeReading:
                View reading = from(context).inflate(R.layout.course_activity_week_section_details_item_reading, childViewGroup, false);
                return new CourseActivityWeeksReadingViewHolder(reading);

            case typeLive:
                View live = from(context).inflate(R.layout.course_activity_week_section_details_item_live, childViewGroup, false);
                return new CourseActivityWeeksLiveViewHolder(live);

            default:
                throw new IllegalArgumentException("Invalid viewType");
        }
    }

    @Override
    public void onBindParentViewHolder(@NonNull CourseActivityWeekSectionTitleViewHolder parentViewHolder, int parentPosition, @NonNull CourseWeekSectionsModel parent) {

    }

    @Override
    public void onBindChildViewHolder(@NonNull ChildViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull CourseWeekSectionDetailsModel child) {

    }

    @Override
    public int getChildViewType(int position, int childIndex) {

        if(courseWeekSectionsModelList.get(position).getCourseWeekSectionDetailsModelList().get(childIndex).getViewType() == 1){

            return typeVideo;
        }
        else if(courseWeekSectionsModelList.get(position).getCourseWeekSectionDetailsModelList().get(childIndex).getViewType() == 2){

            return typeReading;
        }
        else if(courseWeekSectionsModelList.get(position).getCourseWeekSectionDetailsModelList().get(childIndex).getViewType() == 3){

            return  typeLive;
        }
        else{

            return  typeReading;
        }


    }

}
