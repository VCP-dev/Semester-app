package com.example.a4thsemapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SyllabusFragment extends Fragment {


    String pdfvalue;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_syllabus,container,false);

        v.findViewById(R.id.engmath).setOnClickListener(onClick);
        v.findViewById(R.id.fin_aut).setOnClickListener(onClick);
        v.findViewById(R.id.design_anal).setOnClickListener(onClick);
        v.findViewById(R.id.micro).setOnClickListener(onClick);
        v.findViewById(R.id.os).setOnClickListener(onClick);
        v.findViewById(R.id.soft_eng).setOnClickListener(onClick);
        v.findViewById(R.id.constitution).setOnClickListener(onClick);

        return v;

    }



    private final View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId())
            {
                case R.id.engmath:
                    pdfvalue="math";
                    //Toast.makeText(v.getContext(),"engmath",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.fin_aut:
                    pdfvalue="fin_auto";
                   // Toast.makeText(v.getContext(),"finaut",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.design_anal:
                    pdfvalue="design_anal";
                   // Toast.makeText(v.getContext(),"desanal",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.micro:
                    pdfvalue="micro";
                   // Toast.makeText(v.getContext(),"micro",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.os:
                    pdfvalue="OS";
                  //  Toast.makeText(v.getContext(),"OS",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.soft_eng:
                    pdfvalue="soft";
                   // Toast.makeText(v.getContext(),"soft_eng",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.constitution:
                    pdfvalue="constitution";
                  //  Toast.makeText(v.getContext(),"constitution",Toast.LENGTH_SHORT).show();
                    break;

            }

            Intent intentpdfviewer = new Intent(getContext(),PDF_viewer.class);
            intentpdfviewer.putExtra("pdfkey",pdfvalue);
            startActivity(intentpdfviewer);

        }
    };





}
