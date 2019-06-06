package com.example.petadoption.VistaUsuario;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.petadoption.Firebase.MascotasApp;
import com.example.petadoption.R;
import com.squareup.picasso.Picasso;

public class viewHolder extends RecyclerView.ViewHolder {

    View mView;
    TextView Raza,Edad,Descripcion;
    ImageView Fotomasc;
    String Imagen;
    MascotasApp model;
    private Context context;




    public viewHolder(@NonNull final View itemView) {
        super(itemView);

        mView = itemView;



        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = getAdapterPosition();

                Snackbar.make(v, "Click detected on item " + position,
                        Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

              Intent intent = new Intent(itemView.getContext(), AdoptarMascotas.class);

              intent.putExtra("NombreMascota", model.getNombreMascota());
              intent.putExtra("Raza",  model.getRaza());
              intent.putExtra("Edad",  model.getEdad());
              intent.putExtra("Tamaño", model.getTamaño());
              intent.putExtra("Descripcion",  model.getDescripLesion());
              intent.putExtra("Fotomasc", model.getImagen());
              intent.putExtra("Fundacion", model.getIdFundacion());
              intent.putExtra("IdMascota",model.getIdMascota());


              itemView.getContext().startActivity(intent);

            }
        });
    }

    public void SetDetail(MascotasApp modelo){

        model = modelo;

        Raza = mView.findViewById(R.id.mRazam);
        Edad = mView.findViewById(R.id.medad);
        Descripcion = mView.findViewById(R.id.mdescripcion);
        Fotomasc = mView.findViewById(R.id.mfotomas);

        Imagen = model.getImagen();
        Raza.setText(model.getRaza());
        Edad.setText(model.getEdad());
        Descripcion.setText(model.getDescripLesion());
        Picasso.get().load(Imagen).into(Fotomasc);






    }






}
