package com.example.a4thsemapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PDFsFragment extends Fragment {


    private Button uploadbut;
    private Button downloadbut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pdfs,container,false);


        uploadbut = v.findViewById(R.id.uploadactbut);
        downloadbut = v.findViewById(R.id.downloadactbut);


        uploadbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog();

              //  Intent uploadactivity = new Intent(getContext(),UploadActivity.class);
              //  startActivity(uploadactivity);

            }
        });

        downloadbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pdfsforsubjects = new Intent(getContext(),PDFsforsubjects.class);
                startActivity(pdfsforsubjects);

            }
        });


        return v;
    }

    public void openDialog()
    {
        PasswordDialog password = new PasswordDialog();
        password.show(getFragmentManager(),"Password dialog");
    }


}
