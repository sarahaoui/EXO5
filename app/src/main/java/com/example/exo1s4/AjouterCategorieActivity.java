package com.example.exo1s4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class AjouterCategorieActivity extends AppCompatActivity {


      EditText libelle;
      EditText description;
      String lib,desc;
       int codeannuler=0;
       int codeajouter=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_categorie);


        libelle=(EditText)findViewById(R.id.libelle);
        description=(EditText)findViewById(R.id.description);




    }

    public void Annuler(View view) {
        Intent intent = new Intent();
        this.setResult(codeannuler,intent);
        this.finish();
    }

    public void Ajouter(View view) {
        lib= libelle.getText().toString();
        desc= description.getText().toString();
        Intent intent = new Intent();
       // Toast.makeText(this,lib , Toast.LENGTH_SHORT).show();
        intent.putExtra("libelle", lib);
        intent.putExtra("description",desc);
        this.setResult(codeajouter,intent);
        this.finish();
    }


}
