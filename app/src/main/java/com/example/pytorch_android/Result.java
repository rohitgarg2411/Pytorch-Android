package com.example.pytorch_android;
import java.io.*;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bitmap imageBitmap = (Bitmap) getIntent().getBundleExtra("imagedata").get("data");

        String pred = getIntent().getStringExtra("pred");
        System.out.println("a");
        System.out.println(pred);
        //System.out.println(pred);
        ImageView imageView = findViewById(R.id.image);
        imageView.setImageBitmap(imageBitmap);
        //System.out.println("hello");
        TextView textView = findViewById(R.id.label);
        textView.setText(pred);
        //System.out.println("hi");

    }

}