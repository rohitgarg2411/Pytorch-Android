package com.example.pytorch_android;
import java.io.*;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.io.File;
import static com.example.pytorch_android.Utils.assetFilePath;

public class MainActivity extends AppCompatActivity {

    int cameraRequestCode = 001;

    Classifier classifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        classifier = new Classifier(assetFilePath(this,"mobilenet-v2.pt"));

        Button capture = findViewById(R.id.capture);

        capture.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(cameraIntent,cameraRequestCode);

            }


        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        //System.out.println("a");

        if(requestCode == cameraRequestCode && resultCode == RESULT_OK){
            //System.out.println("b");

            Intent resultView = new Intent(this,Result.class);

            resultView.putExtra("imagedata",data.getExtras());

            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");

            String pred = classifier.predict(imageBitmap);
            System.out.println("pred");
            resultView.putExtra("pred",pred);

            startActivity(resultView);

        }

    }

}