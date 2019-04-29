package com.example.petadoption.AccoutActivity.Fragmentos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petadoption.R;

import java.util.ArrayList;

public class AdatadorDeConsultaMascota extends RecyclerView.Adapter<AdatadorDeConsultaMascota.ViewHolderDatos> {

    ArrayList<MascotasApp> ListaDeDatos;

    public AdatadorDeConsultaMascota(ArrayList<MascotasApp> listaDeDatos) {
        ListaDeDatos = listaDeDatos;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_de_mascotas,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos viewHolderDatos, int i) {
         viewHolderDatos.Nombre.setText(ListaDeDatos.get(i).getRaza());
         viewHolderDatos.Raza.setText(ListaDeDatos.get(i).DescripLesion);

    }

    @Override
    public int getItemCount() {
        return ListaDeDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView Nombre,Raza;
        ImageView foto;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            Nombre = itemView.findViewById(R.id.idRaza);
            Raza = itemView.findViewById(R.id.infomascota);
            foto = itemView.findViewById(R.id.IdFoto);

        }

    }
}
