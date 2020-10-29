package com.estello.android.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.estello.android.Adapter.TrainingMaterialAdapter;

import com.estello.android.ViewModel.training_material_section_model;
import com.estello.android.ViewModel.training_materials_section_details_model;
import com.estello.android.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class training_material_fragment extends Fragment {


    View view;
    RecyclerView recyclerView;
    TrainingMaterialAdapter adapter;
    ArrayList<training_material_section_model> trainingMaterialSectionModelArrayList = new ArrayList<>();
    public training_material_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

              view =   inflater.inflate(R.layout.fragment_training_material_fragment, container, false);

              initRecyclerView();

              return view;
    }


    private void initRecyclerView(){
        recyclerView = view.findViewById(R.id.training_materials_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        training_materials_section_details_model model = new training_materials_section_details_model();
        training_materials_section_details_model model2 = new training_materials_section_details_model();
        ArrayList<training_materials_section_details_model> detailsModelArrayList = new ArrayList<>();

        model2.setFileType(1);
        model.setFileType(0);

        detailsModelArrayList.add(model);
        detailsModelArrayList.add(model2);

        training_material_section_model material_section_model = new training_material_section_model("First lecture",detailsModelArrayList);
        training_material_section_model material_section_model2 = new training_material_section_model("Second lecture",detailsModelArrayList);


        for(int i = 0; i < 10; i++){

            trainingMaterialSectionModelArrayList.add(material_section_model);
            trainingMaterialSectionModelArrayList.add(material_section_model2);
        }
        adapter = new TrainingMaterialAdapter(getContext(),trainingMaterialSectionModelArrayList);
        recyclerView.setAdapter(adapter);

    }

}
