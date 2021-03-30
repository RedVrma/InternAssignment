package com.puneetverma.internassignment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RvTitlesAdaptor extends RecyclerView.Adapter<RvTitlesAdaptor.myHolder>{


    private static final String TAG = "RvTitlesAdaptor" ;
    Context context;
    ArrayList<model> modelList;


    RvImagesAdaptor imageAdaptor;
    public  addImagesCallback imagesCallback;


    public RvTitlesAdaptor(Context context, ArrayList<model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_titles_layout,parent,false);

        return new myHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {

        holder.tv_title.setText(modelList.get(position).getTitle());


        imagesCallback.imageSelector(holder,position);

        imageAdaptor = new RvImagesAdaptor(context,modelList.get(position).getListImageUri());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        holder.rv_images.setLayoutManager(gridLayoutManager);
        holder.rv_images.setAdapter(imageAdaptor);
        imageAdaptor.notifyDataSetChanged();

        Log.d(TAG, "list in title: " +modelList.get(position).getListImageUri());
        Log.d(TAG, "list in title: " +modelList.get(position).getTitle());



    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }



    class myHolder extends RecyclerView.ViewHolder
    {
        TextView tv_title;
        Button btn_gallery;

        RecyclerView rv_images;


        public myHolder(@NonNull View itemView) {
            super(itemView);

            tv_title= itemView.findViewById(R.id.tv_title);
            btn_gallery=itemView.findViewById(R.id.btn_gallery);

            rv_images=itemView.findViewById(R.id.rv_images);

        }
    }

}
