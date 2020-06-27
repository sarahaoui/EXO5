package com.example.exo1s4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GrilleProduitsActivity extends AppCompatActivity {

   // ArrayList<Produit> listProduit;
    Categorie listCtegorie;
    MyAdapterG myadapter;
    TextView nom;
    GridView listC;
    ArrayList<Produit> produit;
    int codeajouter=1;
    int codeannuler=0;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grille_produits);
        listC=(GridView)findViewById(R.id.Gridview);
        nom=(TextView)findViewById(R.id.nomg);

        //Création d'une liste de  produits
         Intent intent = getIntent();
        produit= new ArrayList<Produit>();
        listCtegorie=(Categorie)intent.getSerializableExtra("ListeP");
        //produit=listCtegorie.getListeproduit();
        listCtegorie.setListeproduit(produit);
        myadapter= new MyAdapterG(this,produit);
        listC.setAdapter(myadapter);
    }
    @Override
    //menu_add produit
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainproduit, menu);

        return true;
    }
    //click add produit
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.addNewListG){
           Intent intent = new Intent(this,AjouterProduit.class) ;
            startActivityForResult(intent,1);
           // startActivity(intent);
        }
        return true;
    }

    @Override
    //back from next activity
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if( requestCode==1 && resultCode==codeajouter){
            // Toast.makeText(this,data.getExtras().getString("libelle") , Toast.LENGTH_SHORT).show();
           Bitmap picture=(Bitmap) data.getExtras().get("picture");
            produit.add(new Produit(data.getExtras().getString("libelleG"),data.getExtras().getString("descriptionG"),picture));
            myadapter.notifyDataSetChanged();
        }//modifier
        else if(requestCode==2 && resultCode==codeajouter){

            Bitmap picture=(Bitmap) data.getExtras().get("picture");
           produit.set(i,new Produit(data.getExtras().getString("libelleG"),data.getExtras().getString("descriptionG"),picture));
            myadapter.notifyDataSetChanged();
        }

    }



    private class MyAdapterG extends ArrayAdapter<Produit> {     // utilise adapter when we have list view
        private ArrayList<Produit>  listProduit ;
        private Activity context;
        // initialisation d'une list

        public MyAdapterG(Activity context,ArrayList<Produit> listProduit) {   //constrocteur
            super(context, R.layout.ma_cellule,listProduit);
            this.listProduit=listProduit;
            this.context=context;
        }

        @Override
        public int getCount() {
            return listProduit.size();
        }

        @Override
        public Produit getItem(int position) {   // getteur
            return listProduit.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                LayoutInflater inflater=context.getLayoutInflater() ;
                convertView=inflater.inflate(R.layout.ma_cellule,null);

            }
            final   Produit s = listProduit.get(position);
            final ImageView IDs=( ImageView)convertView.findViewById(R.id.imageView);

            IDs.setImageBitmap(s.image);
            final TextView itemName=(TextView)convertView.findViewById(R.id.nomg);
            itemName.setText(s.nom);



            Button sup = (Button)convertView.findViewById(R.id.supprimerG);
            sup.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){

                    // int position =(Integer) v.getTag();
                    listProduit.remove(position);
                    notifyDataSetChanged();

                }
            });
            Button modifier = (Button)convertView.findViewById(R.id.modifierG);
            modifier.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Intent intent = new Intent(GrilleProduitsActivity.this,AjouterProduit.class) ;
                  i=position;
                    startActivityForResult(intent,2);


                }
            });




            return convertView;
        }


        }
}
