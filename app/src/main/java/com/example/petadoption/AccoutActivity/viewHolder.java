package com.example.petadoption.AccoutActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petadoption.R;
import com.squareup.picasso.Picasso;

public class viewHolder extends RecyclerView.ViewHolder {

    View mView;

    public viewHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;
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
