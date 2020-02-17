package com.example.a4thsemapp;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import android.content.pm.ResolveInfo ;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.os.Environment.DIRECTORY_DOWNLOADS;


public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {


    RecyclerView recyclerView;
    Context context;
    ArrayList<String> items = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();
    String folder;



    public RecyclerviewAdapter(RecyclerView recyclerView,Context context,ArrayList<String> Items,ArrayList<String> urls,String folder){

        this.recyclerView = recyclerView;
        this.context = context;
        this.items = Items;
        this.urls = urls;
        this.folder = folder;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nameoffile.setText(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void update(String filename,String url)
    {
        items.add(filename);
        urls.add(url);
        notifyDataSetChanged();    ///  refreshes recylerview
    }




    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameoffile;

        public ViewHolder(View itemView)
        {
            super(itemView);
            nameoffile = itemView.findViewById(R.id.nameoffile);
            Button downloadbut = itemView.findViewById(R.id.downloadbutPDF);
            downloadbut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //int position = recyclerView.getChildLayoutPosition(v);

                    Toast.makeText(context,"Downloading "+nameoffile.getText().toString(),Toast.LENGTH_SHORT).show();

                    download(nameoffile.getText().toString());



                }
            });
        }

    }


    public void download(final String nameoffile)
    {
        StorageReference storagereference =  FirebaseStorage.getInstance().getReference();
        StorageReference ref = storagereference.child(folder).child(nameoffile+".pdf");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                String url = uri.toString();
                downloadfiles(context,nameoffile,".pdf",DIRECTORY_DOWNLOADS,url);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(context,"Could not download file",Toast.LENGTH_SHORT).show();

            }
        });

    }



    public void downloadfiles(Context context,String filename,String fileextension,String destinationdirectory,String url)
    {
        DownloadManager downloadManager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context,destinationdirectory,filename + fileextension);

        downloadManager.enqueue(request);
    }



}
