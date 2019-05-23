package com.example.petadoption;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class AdoptarMascotas extends AppCompatActivity {

    private  TextView Raza,Edad,Descripcion;
    private String imagen;
    private ImageView Fotomasc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoptar_mascotas);

        Raza = (TextView) findViewById(R.id.txtRazaMascota);
        Edad = (TextView) findViewById(R.id.txtEdadMascota);
        Descripcion = (TextView) findViewById(R.id.txtDescripcionMascota);
        Fotomasc = (ImageView) findViewById(R.id.FotoMascota);

        Intent intent = getIntent();

        Raza.setText( intent.getStringExtra("Raza"));
        Edad.setText( intent.getStringExtra("Edad"));
        Descripcion.setText( intent.getStringExtra("Descripcion"));
        imagen = intent.getStringExtra("Fotomasc");

        Picasso.get().load(imagen).into(Fotomasc);




    }
}
