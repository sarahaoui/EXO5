package com.example.exo1s4;

import android.graphics.Bitmap;




public class Produit  {
    public String nom;
    public String description;
    public Bitmap image;

    public Produit(String nom, String description, Bitmap image){
        this.nom=nom;
        this.description=description;
        this.image=image;

    }
    public Produit(){
    }
    public Produit(String nom,String description){
        this.nom=nom;
        this.description=description;

    }


}
