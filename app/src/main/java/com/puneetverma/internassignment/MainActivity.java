package com.puneetverma.internassignment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 0;
    private static final String TAG = "MainActivity";

    FloatingActionButton btn_float_add;
    ArrayList<model> modelList;
    RecyclerView rv_titles;
    RvTitlesAdaptor titleAdaptor;
    int position=0;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {


            ArrayList<String> list= new ArrayList<>();
            list.addAll(modelList.get(position).getListImageUri());

            Log.d(TAG, "listBefore:" + list);

            if(data.getClipData() !=null)
            {
                int count = data.getClipData().getItemCount();
                for(int i=0;i<count;i++)
                {
                    Uri imageUri= data.getClipData().getItemAt(i).getUri();
                    list.add(imageUri.toString());
                }
                modelList.get(position).setListImageUri(list);
                titleAdaptor.notifyDataSetChanged();
            }
            else
            {
                Uri imageUri = data.getData();
                list.add(imageUri.toString());
                modelList.get(position).setListImageUri(list);
                titleAdaptor.notifyDataSetChanged();
            }

            Log.d(TAG, "listAfter:" + list);

            titleAdaptor.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //id's
        btn_float_add=findViewById(R.id.btn_float_add);
        rv_titles=findViewById(R.id.rv_titles);
        rv_titles.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



        //data
        modelList= new ArrayList<>();




        //data ends


        titleAdaptor =new RvTitlesAdaptor(this,modelList);
        rv_titles.setAdapter(titleAdaptor);


        titleAdaptor.imagesCallback = (holder, position) ->{

            holder.btn_gallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.this.position =position;
                    Intent i = new Intent();
                    i.setType("image/*");
                    i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                    i.setAction(Intent.ACTION_GET_CONTENT);
                    MainActivity.this.startActivityForResult(i,RESULT_LOAD_IMAGE);

                }
            });

        };




        btn_float_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.activity_add_title_layout,null);

                EditText et_title = view.findViewById(R.id.et_title);

                new MaterialAlertDialogBuilder(MainActivity.this)
                        .setTitle("Add a Title")
                        .setView(view)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                model m = new model();
                                m.setTitle(et_title.getText().toString());

                                ArrayList<String> uri =new ArrayList<>();
                                m.setListImageUri(uri);
                                modelList.add(m);
                                titleAdaptor.notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).show();
            }
        });


    }
}