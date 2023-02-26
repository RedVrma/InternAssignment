package com.puneetverma.internassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class ImageOpen extends AppCompatActivity {

    ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_open);

        iv_image=findViewById(R.id.iv_imageOpen);
        Objects.requireNonNull(getSupportActionBar()).hide();


        Intent i = getIntent();
        Glide.with(this)
                .load(i.getStringExtra("imageUri"))
                .placeholder(R.drawable.ic_launcher_background)
                .into(iv_image);
    }
}