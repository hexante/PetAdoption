package com.example.petadoption.VistaFundacion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petadoption.Firebase.MascotasApp;
import com.example.petadoption.Firebase.SolicitudMascotaApp;
import com.example.petadoption.Firebase.UsuariosApp;
import com.example.petadoption.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ViewHolderSolicitudes extends RecyclerView.ViewHolder {

    View mView;
    TextView NombreSolicitante,Nombremascota;
    ImageView FotoSolicitante;
    String Imagen,NombreMascota,NombreUsuarios;
    SolicitudMascotaApp model;
    DatabaseReference MascotaRef,UsuarioRef;
    private Context context;

    public ViewHolderSolicitudes(@NonNull final View itemView) {
        super(itemView);

        mView = itemView;

        NombreSolicitante = mView.findViewById(R.id.NombreSolicitante);
        Nombremascota = mView.findViewById(R.id.Mascota);
        FotoSolicitante = mView.findViewById(R.id.fotoSolicitd);


        MascotaRef = FirebaseDatabase.getInstance().getReference();
        UsuarioRef = FirebaseDatabase.getInstance().getReference();




        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = getAdapterPosition();

                Snackbar.make(v, "Click detected on item " + position,
                        Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();



            }
        });
    }

    public void SetDetail(SolicitudMascotaApp modelo){

        model = modelo;


        MascotaRef.child("MascotasApp")
                .orderByChild("idMascota")
                .equalTo(model.getIdMascota())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for ( DataSnapshot snapshot : dataSnapshot.getChildren()){

                            MascotasApp mascota = snapshot.getValue(MascotasApp.class);
                            if (mascota.getIdMascota().equals(model.getIdMascota())){
                            NombreMascota = mascota.getNombreMascota();
                            Nombremascota.setText(NombreMascota);}

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        UsuarioRef.child("UsuariosApp")
                .orderByChild("correo")
                .equalTo(model.getIdUsuario())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for ( DataSnapshot snapshot : dataSnapshot.getChildren()){
                            UsuariosApp usuarios = snapshot.getValue(UsuariosApp.class);

                            NombreUsuarios = usuarios.getNombres()+usuarios.getApellidos();
                            Imagen = usuarios.getUrimagen();
                            NombreSolicitante.setText(NombreUsuarios);
                            Picasso.get().load(Imagen).into(FotoSolicitante);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


    }



}
