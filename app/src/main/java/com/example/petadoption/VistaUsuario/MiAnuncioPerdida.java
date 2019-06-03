package com.example.petadoption.VistaUsuario;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



import com.example.petadoption.Firebase.MascotasPerdidasApp;
import com.example.petadoption.R;
import com.squareup.picasso.Picasso;

public class MiAnuncioPerdida extends RecyclerView.ViewHolder {

    View mView;
    TextView Raza,Edad,Descripcion;
    ImageView Fotomasc;
    String Imagen;
    MascotasPerdidasApp model;
    private Context context;




    public MiAnuncioPerdida(@NonNull final View itemView) {
        super(itemView);

        mView = itemView;



        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = getAdapterPosition();

                Snackbar.make(v, "Click detected on item " + position,
                        Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

             //  Intent intent = new Intent(itemView.getContext(), AdoptarMascotas.class);
             //
             //  intent.putExtra("NombreMascota", model.getNombreMascota());
             //  intent.putExtra("Raza",  model.getRaza());
             //  intent.putExtra("Edad",  model.getEdad());
             //  intent.putExtra("Tamaño", model.getTamaño());
             //  intent.putExtra("Descripcion",  model.getDescripLesion());
             //  intent.putExtra("Fotomasc", model.getImagen());
             //  intent.putExtra("Fundacion", model.getIdFundacion());
             //  intent.putExtra("IdMascota",model.getIdMascota());
             //
             //
             //  itemView.getContext().startActivity(intent);

            }
        });
    }

    public void SetDetail(MascotasPerdidasApp modelo){

        model = modelo;

        Raza = mView.findViewById(R.id.NombrePerdida);
        Edad = mView.findViewById(R.id.TelefonoContacto);
        Descripcion = mView.findViewById(R.id.DescripcionPerdida);
        Fotomasc = mView.findViewById(R.id.FotoPerdida);

       Imagen = model.getFotoMacota();
       Raza.setText(model.getNombreMascota());
       Edad.setText(model.getTelefonoUsuario());
       Descripcion.setText(model.getDescripcionPerdida());
       Picasso.get().load(Imagen).into(Fotomasc);






    }




}
