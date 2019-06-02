package com.example.petadoption.VistaUsuario;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petadoption.R;
import com.example.petadoption.Firebase.UsuariosApp;
import com.squareup.picasso.Picasso;

public class viewHolderUsers extends RecyclerView.ViewHolder {

    View mView;

    public viewHolderUsers(@NonNull View itemView) {
        super(itemView);

        mView = itemView;
    }

    public void SetDetail(UsuariosApp model){

        TextView nombre = mView.findViewById(R.id.mNombre);
        TextView telefono = mView.findViewById(R.id.mTelefono);
        TextView email = mView.findViewById(R.id.mEmail);
        ImageView Fotofun = mView.findViewById(R.id.mfotofun);

        nombre.setText(model.getNombres()+ model.getApellidos());
        telefono.setText(model.getNumeroTelefono());
        email.setText(model.getCorreo());
        Picasso.get().load(model.getUrimagen()).into(Fotofun);




    }
}
