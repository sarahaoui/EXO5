package com.example.exo1s4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   public ArrayList<Produit> listProduit,listeFrommage,listHuile;
   public ArrayList<Categorie> listCtegorie;
    MyAdapter myadapter;
    TextView item;
    ListView listC;
    int codeannuler=0;
    int codeajouter=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        item=(TextView)findViewById(R.id.item);
        listC=(ListView)findViewById(R.id.listview);
        //Création d'une liste de catégories et produits
        listProduit= new ArrayList<Produit>();
        listeFrommage= new ArrayList<Produit>();
        listHuile= new ArrayList<Produit>();
        listCtegorie= new ArrayList<Categorie>();

        listCtegorie.add(new Categorie("Farine","",listProduit));

        listCtegorie.add(new Categorie("Frommage","",listeFrommage));
        listCtegorie.add(new Categorie("Huile",""));
        myadapter= new MyAdapter(this,listCtegorie);
        listC.setAdapter(myadapter);
    }
    @Override
    //menu_add
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        return true;
    }
    //click menu_add
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.addNewList){
           Intent intent = new Intent(this,AjouterCategorieActivity.class) ;
           startActivityForResult(intent,1);
        }
        return true;
    }

    @Override
    //back from next activity
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       if( requestCode==1 && resultCode==codeajouter){
          // Toast.makeText(this,data.getExtras().getString("libelle") , Toast.LENGTH_SHORT).show();
           listCtegorie.add(new Categorie(data.getExtras().getString("libelle"),data.getExtras().getString("description")));
           myadapter.notifyDataSetChanged();
       }

    }

    private class MyAdapter extends ArrayAdapter<Categorie> {     // utilise adapter when we have list view
            private ArrayList<Categorie>  listCategorie ;
            private Activity context;
            // initialisation d'une list

            public MyAdapter(Activity context,ArrayList<Categorie> listCategorie) {   //constrocteur
                super(context, R.layout.liste_view,listCategorie);
                this.listCategorie=listCategorie;
                this.context=context;
            }

            @Override
            public int getCount() {
                return listCategorie.size();
            }

            @Override
            public Categorie getItem(int position) {   // getteur
                return listCategorie.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                if(convertView==null){
                    LayoutInflater inflater=context.getLayoutInflater() ;
                    convertView=inflater.inflate(R.layout.liste_view,null);

                }
                final   Categorie s = listCategorie.get(position);
                final TextView itemName=(TextView)convertView.findViewById(R.id.item);
                //pass data to the next Activity
                itemName.setText(s.nom);
                itemName.setOnClickListener(new View.OnClickListener() {  // if we click
                    @Override
                    public void onClick(View v) {
                       Intent intent = new Intent(MainActivity.this,GrilleProduitsActivity.class);
                       Categorie categorie = new Categorie();
                        categorie= listCategorie.get(position);
                        //Bundle args= new Bundle();
                        //args.putSerializable("list",(Serializable)categorie);
                       // intent.putExtra("ListeP",args);
                        intent.putExtra("ListeP",categorie);
                        startActivity(intent);




                    }
                });



                Button sup = (Button)convertView.findViewById(R.id.supprimer);
                sup.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){

                        // int position =(Integer) v.getTag();
                       listCategorie.remove(position);
                        notifyDataSetChanged();

                    }
                });



                return convertView;
            }


        }}
