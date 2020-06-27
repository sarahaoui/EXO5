package com.example.exo1s4;


import android.os.Parcelable;


import java.io.Serializable;
import java.util.ArrayList;


public class Categorie implements Serializable {
    public String nom;
    public String description;
    public ArrayList<Produit> listeproduit;

    //for news details
    public Categorie(String nom,String description,ArrayList<Produit> listeproduit){  //pass data to layout list view
        this.nom=nom;
        this.description=description;
        this.listeproduit= listeproduit;
    }
    public Categorie(String nom,String description){  //pass data to layout list view
        this.nom=nom;
        this.description=description;

    }
    public Categorie(ArrayList<Produit> listeproduit){  //pass data to layout list view
        this.listeproduit= listeproduit;

    }
    public Categorie(){  //pass data to layout list view


    }
    public ArrayList<Produit> getListeproduit(){

            return listeproduit;


    }
    public void setListeproduit(ArrayList<Produit>listeproduit){

        this.listeproduit=listeproduit;


    }



}


