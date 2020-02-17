package com.example.a4thsemapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadActivity extends AppCompatActivity {

    private Button selectfile,uploadfile;
    private EditText notification;
    private Spinner subject;
    String[] subjects = {"Engineering maths IV","Finite Automata and Formal languages","Design and analysis of algorithms","Microprocessors and controllers","Operating systems","Software engineering","Constitution of India and professional ethics"};


    private FirebaseStorage storage;            //  used to upload files
    private FirebaseDatabase database;          //  used to store urls of stored files
    private Uri pdfUri;                               /// path for file in local storage


    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        selectfile = findViewById(R.id.selectfile);
        uploadfile = findViewById(R.id.uploadfile);
        notification = findViewById(R.id.notification);
        subject = findViewById(R.id.subject);


        storage = FirebaseStorage.getInstance();   // returns object of firebase storage
        database = FirebaseDatabase.getInstance();    // returns object of firebase database


        selectfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(UploadActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    selectPdf();
                }
                else
                {
                    ActivityCompat.requestPermissions(UploadActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
                }      ////     above line invokes onRequestPermissionResult

            }
        });



        uploadfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pdfUri != null)
                {
                    uploadFile(pdfUri);
                }
                else
                {
                    Toast.makeText(UploadActivity.this, "Please select a file", Toast.LENGTH_SHORT).show();
                }

            }
        });


        ArrayAdapter adapterspinner = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,subjects);
        adapterspinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subject = findViewById(R.id.subject);
        subject.setAdapter(adapterspinner);


    }


    private void uploadFile(Uri pdfUri)
    {

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading file");
        progressDialog.setProgress(0);
        progressDialog.show();



        final String fileName = notification.getText().toString();
        StorageReference storageReference = storage.getReference();    ///    returns root path
        storageReference.child(subject.getSelectedItem().toString()).child(fileName+".pdf").putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();   /// return url of uploaded file
                        //  Store url in realtime database

                        DatabaseReference reference = database.getReference();  ///   return path to root
                        reference.child(subject.getSelectedItem().toString()).child(fileName).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful())
                                {
                                    Toast.makeText(UploadActivity.this,"File successfully uploaded",Toast.LENGTH_SHORT);
                                }
                                else
                                {
                                    Toast.makeText(UploadActivity.this,"File was not uploaded :(",Toast.LENGTH_SHORT);
                                }

                            }
                        });



                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(UploadActivity.this,"File was not uploaded :(",Toast.LENGTH_SHORT);

                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                        int currentProgress = (int)(100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        progressDialog.setProgress(currentProgress);

                    }
                });
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(requestCode == 9 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            selectPdf();
        }
        else
        {
            Toast.makeText(UploadActivity.this,"Please provide permission....",Toast.LENGTH_SHORT).show();
        }
    }


    private void selectPdf()    ///  offer user to select file using filemanager
    {
        Intent intent = new Intent();
        intent.setType("application/pdf");      //// shows that intent is targetted for pdf files
        intent.setAction(Intent.ACTION_GET_CONTENT);    /// denotes that intent is used to fetch files
        startActivityForResult(intent,86);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)    /// check whether user has selected a file or not
    {
        if(requestCode == 86 && resultCode == RESULT_OK && data != null)
        {
            pdfUri = data.getData();    ///  returns uri of selected file
            notification.setText(data.getData().getLastPathSegment());
        }
        else
        {
            Toast.makeText(UploadActivity.this,"Please select a file",Toast.LENGTH_SHORT).show();
        }
    }
}
