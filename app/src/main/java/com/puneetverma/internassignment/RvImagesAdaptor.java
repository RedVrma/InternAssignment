package com.puneetverma.internassignment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RvImagesAdaptor extends RecyclerView.Adapter<RvImagesAdaptor.myImageHolder>{

    private static final String TAG = "RvImagesAdaptor";
    Context context;
    ArrayList<String> listLocationImages;

    public RvImagesAdaptor(Context context, ArrayList<String> listLocationImages) {
        this.context = context;
        this.listLocationImages = listLocationImages;
    }

    @NonNull
    @Override
    public myImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_images_layout,parent,false);


        return new myImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myImageHolder holder, int position) {

        Glide.with(context)
                .load(listLocationImages.get(position))
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.iv_image);

        Log.d(TAG, "onBindViewHolder: " + listLocationImages.get(position));

        holder.iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i  = new Intent(context, ImageOpen.class);
                i.putExtra("imageUri",listLocationImages.get(position));
                context.startActivity(i);

            }
        });

    }


    @Override
    public int getItemCount() {
        return listLocationImages.size();
    }


    class myImageHolder extends RecyclerView.ViewHolder
    {
        ImageView iv_image;

        public myImageHolder(@NonNull View itemView) {
            super(itemView);

            iv_image=itemView.findViewById(R.id.iv_image);

        }
    }
}
