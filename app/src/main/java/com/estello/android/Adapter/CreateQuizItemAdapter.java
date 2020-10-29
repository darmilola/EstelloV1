package com.estello.android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.estello.android.CreateOrEditQuiz;
import com.estello.android.ViewModel.QuizModel;
import com.google.android.material.button.MaterialButton;


import com.estello.android.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CreateQuizItemAdapter extends RecyclerView.Adapter<CreateQuizItemAdapter.itemViewHolder> {


    ArrayList<QuizModel> quizModelArrayList = new ArrayList<>();
    Context context;

    public CreateQuizItemAdapter(ArrayList<QuizModel> quizModels, Context context){

        this.context = context;
        this.quizModelArrayList = quizModels;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.create_course_quiz_item, parent, false);
        return new itemViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return quizModelArrayList.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder{

        private TextView quizTitle;
        private TextView description;
        private TextView quizNumber;
        private MaterialButton edit;
        public itemViewHolder(View ItemView){

            super(ItemView);
            quizTitle = ItemView.findViewById(R.id.create_quiz_item_title);
            description = ItemView.findViewById(R.id.create_quiz_item_description);
            quizNumber = ItemView.findViewById(R.id.create_quiz_item_number);
            edit = ItemView.findViewById(R.id.create_quiz_item_edit);


            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, CreateOrEditQuiz.class));


                }
            });

        }



    }
}
