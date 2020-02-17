package com.example.a4thsemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class PDF_viewer extends AppCompatActivity {


    PDFView pdfviewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);


        pdfviewer = findViewById(R.id.pdfviewer);

        switch(getIntent().getExtras().getString("pdfkey"))
        {
            case "math":
                pdfviewer.fromAsset("Engineering_math_3.pdf").load();
                break;
            case "fin_auto":
                pdfviewer.fromAsset("Finite_Auto.pdf").load();
                break;
            case "design_anal":
                pdfviewer.fromAsset("Design_anal.pdf").load();
                break;
            case "micro":
                pdfviewer.fromAsset("micro.pdf").load();
                break;
            case "OS":
                pdfviewer.fromAsset("OS.pdf").load();
                break;
            case "soft":
                pdfviewer.fromAsset("Software_eng.pdf").load();
                break;
            case "constitution":
                pdfviewer.fromAsset("Constitution.pdf").load();
                break;
        }

    }



}
