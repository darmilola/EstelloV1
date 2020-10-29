package com.estello.android.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estello.android.Adapter.CreateQuizItemAdapter;
import com.estello.android.ViewModel.QuizModel;
import com.google.android.material.button.MaterialButton;


import com.estello.android.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateCourseQuizFragment extends Fragment implements Step {


    ArrayList<QuizModel> quizModelArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    CreateQuizItemAdapter quizItemAdapter;
    View view;
    MaterialButton addNewQuiz;


    public CreateCourseQuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view =  inflater.inflate(R.layout.fragment_create_course_quiz, container, false);

       InitView();
       return  view;

    }

    private void InitView(){

        addNewQuiz = view.findViewById(R.id.add_new_quiz);
        recyclerView = view.findViewById(R.id.create_quiz_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        QuizModel quizModel = new QuizModel();

        for(int i = 0; i < 5; i++){
            quizModelArrayList.add(quizModel);
        }

        quizItemAdapter = new CreateQuizItemAdapter(quizModelArrayList,getContext());

        recyclerView.setAdapter(quizItemAdapter);



    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}
