package com.example.petadoption;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.petadoption.Firebase.UsuariosApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class MiCuenta extends Fragment {


    private  String Nombre,Correo,CorreoUsuario;
    private TextView NombreCuent,CorreoCuenta;

    private DatabaseReference ValidarUsuarios;
    private FirebaseAuth auth;
    FirebaseDatabase bmfirebaseDatabase;

    public MiCuenta() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mi_cuenta, container, false);

        auth = FirebaseAuth.getInstance();

        NombreCuent = (TextView) view.findViewById(R.id.NombreUsuario);
        CorreoCuenta = (TextView) view.findViewById(R.id.CorreoMiCuenta);




        CorreoUsuario = auth.getCurrentUser().getEmail();
        bmfirebaseDatabase = FirebaseDatabase.getInstance();


        ValidarUsuarios = FirebaseDatabase.getInstance().getReference();

        ValidarUsuarios.child("UsuariosApp")
                .orderByChild("correo")
                .equalTo(CorreoUsuario)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for ( DataSnapshot snapshot : dataSnapshot.getChildren()){

                    UsuariosApp user = snapshot.getValue(UsuariosApp.class);
                    final String Correo = user.getCorreo();
                    final String NombreUsuario = user.getNombres();
                    final String ApelldoUsuario= user.getApellidos();

                    Log.e("IdUsuario: ", "" + NombreUsuario);
                    Log.e("IdUsuario: ", "" + Correo);


                    if(CorreoUsuario.equals(Correo)) {
                        NombreCuent.setText(NombreUsuario + ApelldoUsuario);
                        CorreoCuenta.setText(Correo);

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }

}
