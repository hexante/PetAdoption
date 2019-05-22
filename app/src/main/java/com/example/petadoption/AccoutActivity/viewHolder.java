package com.example.petadoption.AccoutActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.petadoption.AccoutActivity.Fragmentos.AdoptarMascota;
import com.example.petadoption.AdoptarMascotas;
import com.example.petadoption.R;
import com.squareup.picasso.Picasso;


import static android.support.v4.content.ContextCompat.startActivity;

public class viewHolder extends RecyclerView.ViewHolder {

    View mView;
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

               itemView.getContext().startActivity(new Intent(itemView.getContext(), AdoptarMascota.class));

            }
        });
    }

    public void SetDetail(Context ctm, String raza,String edad, String descripcion, String imagen){

        TextView Raza = mView.findViewById(R.id.mRazam);
        TextView Edad = mView.findViewById(R.id.medad);
        TextView Descripcion = mView.findViewById(R.id.mdescripcion);
        ImageView Fotomasc = mView.findViewById(R.id.mfotomas);

        Raza.setText(raza);
        Edad.setText(edad);
        Descripcion.setText(descripcion);
        Picasso.get().load(imagen).into(Fotomasc);






    }






}
