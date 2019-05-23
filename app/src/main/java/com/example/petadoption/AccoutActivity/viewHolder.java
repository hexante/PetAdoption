package com.example.petadoption.AccoutActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.petadoption.AdoptarMascotas;
import com.example.petadoption.R;
import com.squareup.picasso.Picasso;

public class viewHolder extends RecyclerView.ViewHolder {

    View mView;
    TextView Raza,Edad,Descripcion;
    ImageView Fotomasc;
    String Imagen;
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
                BitmapDrawable bitmap = ((BitmapDrawable)Fotomasc.getDrawable());
                intent.putExtra("Raza",  Raza.getText());
                intent.putExtra("Edad",  Edad.getText());
                intent.putExtra("Descripcion",  Descripcion.getText());
                intent.putExtra("Fotomasc", Imagen);

                itemView.getContext().startActivity(intent);

            }
        });
    }

    public void SetDetail(Context ctm, String raza,String edad, String descripcion, String imagen){

         Raza = mView.findViewById(R.id.mRazam);
         Edad = mView.findViewById(R.id.medad);
         Descripcion = mView.findViewById(R.id.mdescripcion);
         Fotomasc = mView.findViewById(R.id.mfotomas);

        Imagen = imagen;
        Raza.setText(raza);
        Edad.setText(edad);
        Descripcion.setText(descripcion);
        Picasso.get().load(Imagen).into(Fotomasc);






    }






}
