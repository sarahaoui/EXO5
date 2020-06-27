package com.example.exo1s4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AjouterProduit extends AppCompatActivity {
    EditText libelleG;
    EditText descriptionG;
    int codeajouter=1;
    int codeannuler=0;
    String libG,descG;
    ImageView image;
    Bitmap img2;
    Bitmap picture;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_produit);
        libelleG=(EditText)findViewById(R.id.libelleG);
        descriptionG=(EditText)findViewById(R.id.descriptionG);
        image=(ImageView)findViewById(R.id.picture) ;

        if(ContextCompat.checkSelfPermission(AjouterProduit.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AjouterProduit.this,new String[]{Manifest.permission.CAMERA},100);
        }




    }
    public void takepicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if( requestCode==100 ){
            picture=(Bitmap) data.getExtras().get("data");
            image.setImageBitmap(picture);
        }

    }/*
    public void takepicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager())!= null){
            startActivityForResult(intent,1888);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if( requestCode==1888 && resultCode== Activity.RESULT_OK){
            Bitmap picture=(Bitmap) data.getExtras().get("data");
            ImageView image=(ImageView)findViewById(R.id.imageView2) ;
            image.setImageBitmap(picture);
        }

    }*/


    public void ajouterg(View view) {
        libG= libelleG.getText().toString();
        descG= descriptionG.getText().toString();


        Intent intent = new Intent();
        // Toast.makeText(this,lib , Toast.LENGTH_SHORT).show();
        intent.putExtra("libelleG", libG);
        intent.putExtra("picture", picture);
        intent.putExtra("descriptionG",descG);
        this.setResult(codeajouter,intent);
        this.finish();
    }

    public void annulerg(View view) {
        Intent intent = new Intent();
        this.setResult(codeannuler,intent);
        this.finish();
    }
}


