package com.example.petadoption.AccoutActivity.Fragmentos;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petadoption.R;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragConsultaMascotaUsuario extends Fragment {

    private DatabaseReference ValidarMascotas;

    private ArrayList<MascotasApp> ListaDeMascotas;
    RecyclerView recyclerView;


    public FragConsultaMascotaUsuario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_frag_consulta_mascota_usuario, container, false);

        ValidarMascotas = FirebaseDatabase.getInstance().getReference();
        recyclerView = (RecyclerView) view.findViewById(R.id.ConsultarMascotasUsuario);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        ListaDeMascotas = new ArrayList<MascotasApp>();

        ValidarMascotas.child("MascotasApp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for ( DataSnapshot snapshot : dataSnapshot.getChildren()) {


                    MascotasApp user = snapshot.getValue(MascotasApp.class);
                    ListaDeMascotas.add(user);

                }

                AdatadorDeConsultaMascota adaptador = new AdatadorDeConsultaMascota(ListaDeMascotas);
                recyclerView.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {



            }
        });



        return view;
    }

}
