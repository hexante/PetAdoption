package com.example.petadoption.AccoutActivity.Fragmentos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        viewHolderDatos.asignarDatos(ListaDeDatos.get(i));

    }

    @Override
    public int getItemCount() {
        return ListaDeDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView dato;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            dato = itemView.findViewById(R.id.IdDato);

        }

        public void asignarDatos(MascotasApp Datos) {
            dato.setText((CharSequence) Datos);
        }
    }
}
