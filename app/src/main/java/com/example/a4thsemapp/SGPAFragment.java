package com.example.a4thsemapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class SGPAFragment extends Fragment {


    private Spinner engmath;
    private Spinner finaut;
    private Spinner desanal;
    private Spinner micro;
    private Spinner OS;
    private Spinner soft;
    private Spinner constitution;
    private Spinner desanallab;
    private Spinner microlab;


    private Button sgpacalc;


    String[] grades = {"S","A","B","C","D","E","F"};


    List<Float> pointslist = new ArrayList<Float>();
    float result=0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sgpa,container,false);

        sgpacalc = v.findViewById(R.id.sgpacalc);
        sgpacalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pointslist.size()>0)
                {
                    pointslist.clear();
                }
                result=0;
                pointslist.add(points(engmath.getSelectedItem().toString()) * 3);
                pointslist.add(points(finaut.getSelectedItem().toString()) * 4);
                pointslist.add(points(desanal.getSelectedItem().toString()) * 3);
                pointslist.add(points(micro.getSelectedItem().toString()) * 3);
                pointslist.add(points(OS.getSelectedItem().toString()) * 3);
                pointslist.add(points(soft.getSelectedItem().toString()) * 4);
                pointslist.add(points(constitution.getSelectedItem().toString()) * 1);
                pointslist.add(points(desanallab.getSelectedItem().toString()) * 1.5f);
                pointslist.add(points(microlab.getSelectedItem().toString()) * 1.5f);

                for(float p : pointslist)
                {
                    result += p;
                }
                
                result/=24;

                float rounded = Math.round(result * 100f) / 100f;


                Toast.makeText(getContext(),Float.toString(rounded), Toast.LENGTH_SHORT).show();

            }
        });

        ArrayAdapter adapterspinner = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,grades);
        adapterspinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        engmath = v.findViewById(R.id.spinnermath);
        engmath.setAdapter(adapterspinner);


        finaut = v.findViewById(R.id.spinnerfin);
        finaut.setAdapter(adapterspinner);

        desanal = v.findViewById(R.id.spinnerdes);
        desanal.setAdapter(adapterspinner);

        micro = v.findViewById(R.id.spinnermicro);
        micro.setAdapter(adapterspinner);

        OS = v.findViewById(R.id.spinneros);
        OS.setAdapter(adapterspinner);

        soft = v.findViewById(R.id.spinnersoft);
        soft.setAdapter(adapterspinner);

        constitution = v.findViewById(R.id.spinnerconst);
        constitution.setAdapter(adapterspinner);

        desanallab = v.findViewById(R.id.spinnerdesanallab);
        desanallab.setAdapter(adapterspinner);

        microlab = v.findViewById(R.id.spinnermicrolab);
        microlab.setAdapter(adapterspinner);


        return v;
    }


    float points(String grade)
    {
        switch(grade)
        {
            case "S":
                return 10;
            case "A":
                return 9;
            case "B":
                return 8;
            case "C":
                return 7;
            case "D":
                return 5;
            case "E":
                return 4;
            case "F":
                return 0;
        }
        return -1;
    }
}
