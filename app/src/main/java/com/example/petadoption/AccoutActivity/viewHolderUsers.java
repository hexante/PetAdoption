package com.example.petadoption.AccoutActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petadoption.R;
import com.squareup.picasso.Picasso;

public class viewHolderUsers extends RecyclerView.ViewHolder {

    View mView;

    public viewHolderUsers(@NonNull View itemView) {
        super(itemView);

        mView = itemView;
    }

    public void SetDetail(Context ctm, String Nombre, String Telefono,String Correo,String imagen){

        TextView nombre = mView.findViewById(R.id.mNombre);
        TextView telefono = mView.findViewById(R.id.mTelefono);
        TextView email = mView.findViewById(R.id.mEmail);
        ImageView Fotofun = mView.findViewById(R.id.mfotofun);

        nombre.setText(Nombre);
        telefono.setText(Telefono);
        email.setText(Correo);
        Picasso.get().load(imagen).into(Fotofun);




    }
}
