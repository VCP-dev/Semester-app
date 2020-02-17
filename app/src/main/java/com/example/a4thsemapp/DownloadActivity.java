package com.example.a4thsemapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DownloadActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    String folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        folder = getfoldername(getIntent().getExtras().getString("folder"));

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(folder);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                //  Called for individual items at database reference

                String filename = dataSnapshot.getKey();            ///    returns filename
                String url = dataSnapshot.getValue(String.class);   ///    returns url for filename

                ((RecyclerviewAdapter)recyclerView.getAdapter()).update(filename,url);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new RecyclerviewAdapter(recyclerView,getApplicationContext(),new ArrayList<String>(),new ArrayList<String>(),folder));
        TextView title = findViewById(R.id.title);
        title.setText(folder);


    }


    public String getfoldername(String key)
    {
        String name=null;
        switch(key)
        {
            case "math":
                name="Engineering maths IV";
                break;
            case "fin_auto":
                name="Finite Automata and Formal languages";
                break;
            case "design_anal":
                name="Design and analysis of algorithms";
                break;
            case "micro":
                name="Microprocessors and controllers";
                break;
            case "OS":
                name="Operating systems";
                break;
            case "soft":
                name="Software engineering";
                break;
            case "constitution":
                name="Constitution of India and professional ethics";
                break;
        }
        return name;
    }

}
