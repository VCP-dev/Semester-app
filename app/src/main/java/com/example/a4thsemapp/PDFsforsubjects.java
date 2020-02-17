package com.example.a4thsemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PDFsforsubjects extends AppCompatActivity {



    String foldername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfsforsubjects);


        final View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (v.getId()) {


                        case R.id.pdfengmath:
                            foldername="math";
                            //Toast.makeText(v.getContext(),"engmath",Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.pdffin_aut:
                            foldername="fin_auto";
                            // Toast.makeText(v.getContext(),"finaut",Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.pdfdesign_anal:
                            foldername="design_anal";
                            // Toast.makeText(v.getContext(),"desanal",Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.pdfmicro:
                            foldername="micro";
                            // Toast.makeText(v.getContext(),"micro",Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.pdfos:
                            foldername="OS";
                            //  Toast.makeText(v.getContext(),"OS",Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.pdfsoft_eng:
                            foldername="soft";
                            // Toast.makeText(v.getContext(),"soft_eng",Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.pdfconstitution:
                            foldername="constitution";
                            //  Toast.makeText(v.getContext(),"constitution",Toast.LENGTH_SHORT).show();
                            break;
                }


                Intent pdfrecycleview = new Intent(getApplicationContext(),DownloadActivity.class);
                pdfrecycleview.putExtra("folder",foldername);
                startActivity(pdfrecycleview);

            }


        };



        findViewById(R.id.pdfengmath).setOnClickListener(onClick);
        findViewById(R.id.pdffin_aut).setOnClickListener(onClick);
        findViewById(R.id.pdfdesign_anal).setOnClickListener(onClick);
        findViewById(R.id.pdfmicro).setOnClickListener(onClick);
        findViewById(R.id.pdfos).setOnClickListener(onClick);
        findViewById(R.id.pdfsoft_eng).setOnClickListener(onClick);
        findViewById(R.id.pdfconstitution).setOnClickListener(onClick);



    }





}
