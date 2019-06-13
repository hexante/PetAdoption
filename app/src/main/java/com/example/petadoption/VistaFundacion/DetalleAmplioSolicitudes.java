package com.example.petadoption.VistaFundacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petadoption.Firebase.DatosDeAdopcionApp;
import com.example.petadoption.Firebase.MascotasApp;
import com.example.petadoption.Firebase.UsuariosApp;
import com.example.petadoption.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetalleAmplioSolicitudes extends AppCompatActivity {

    private String Idusuario,IdMascota,IdSolicitud,NombresSolicitante,
            TelefonoSolicitante,DireccionSolicitante,
            BarrioSolicitante,ActividadEconomicaSolicitante,
            TuboMascotaSolicitante,NombreMAscota,FotoMascota,FotoUsuario,CorreoUsuario;

    private TextView Nombres,Telefono,Direccion,Barrio,
            ActividadEconomica,TuboMascota,nombremascota;

    private ImageView fotousuario,fotomascota;

    private Button Aceptar, Denegar, Proceso, Atras;

    private MascotasApp mascota ;
    private UsuariosApp usuarios;
    private DatosDeAdopcionApp datos;
    DatabaseReference MascotaRef, UsuarioRef, DatosRef, Solicitud;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_amplio_solicitudes);

        intent = getIntent();

        MascotaRef = FirebaseDatabase.getInstance().getReference();
        UsuarioRef = FirebaseDatabase.getInstance().getReference();
        DatosRef = FirebaseDatabase.getInstance().getReference();
        Solicitud = FirebaseDatabase.getInstance().getReference("SolicitudMascotaApp");

        IdMascota = intent.getStringExtra("IdMascota");
        Idusuario = intent.getStringExtra("IdUsuario");
        IdSolicitud = intent.getStringExtra("IdSolicitud");

        Nombres = (TextView) findViewById(R.id.NombreSolicitante);
        Telefono = (TextView) findViewById(R.id.TelefonoSolicitanet);
        Direccion = (TextView) findViewById(R.id.DireccionSolicitante);
        Barrio = (TextView) findViewById(R.id.BarrioSolicitante);
        ActividadEconomica = (TextView) findViewById(R.id.ActividadSolicitante);
        TuboMascota = (TextView) findViewById(R.id.MascotaSolicitante);
        nombremascota = (TextView) findViewById(R.id.NombreMascotaSolicitada);

        fotomascota = (ImageView) findViewById(R.id.FotoMascotaSolicitada);
        fotousuario = (ImageView) findViewById(R.id.FotoSolicitante);

        Aceptar = (Button) findViewById(R.id.SolicitudAceptada);
        Denegar = (Button) findViewById(R.id.SolicitudDenegada);
        Proceso = (Button) findViewById(R.id.solicitudProceso);
        Atras = (Button) findViewById(R.id.Atras);






        MascotaRef.child("MascotasApp")
                .orderByChild("idMascota")
                .equalTo(IdMascota)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            mascota = snapshot.getValue(MascotasApp.class);


                            NombreMAscota = mascota.getNombreMascota();
                            FotoMascota = mascota.getImagen();


                            nombremascota.setText(NombreMAscota);
                            Picasso.get().load(FotoMascota).into(fotomascota);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        UsuarioRef.child("UsuariosApp")
                .orderByChild("correo")
                .equalTo(Idusuario)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for ( DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            usuarios = snapshot.getValue(UsuariosApp.class);


                            NombresSolicitante = usuarios.getNombres() + usuarios.getApellidos();
                            CorreoUsuario = usuarios.getCorreo();
                            FotoUsuario = usuarios.getUrimagen();

                            DatosRef.child("DatosDeAdopcionApp")
                                    .orderByChild("usuario")
                                    .equalTo(CorreoUsuario)
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            for ( DataSnapshot snapshot : dataSnapshot.getChildren()) {

                                                datos = snapshot.getValue(DatosDeAdopcionApp.class);


                                                TelefonoSolicitante = datos.getTelefono();
                                                DireccionSolicitante = datos.getDireccion();
                                                BarrioSolicitante = datos.getBarrio();
                                                TuboMascotaSolicitante = datos.getTuboMascota();
                                                ActividadEconomicaSolicitante = datos.getActividadEconomica();


                                                Telefono.setText(TelefonoSolicitante);
                                                Direccion.setText(DireccionSolicitante);
                                                Barrio.setText(BarrioSolicitante);
                                                ActividadEconomica.setText(ActividadEconomicaSolicitante);
                                                TuboMascota.setText(TuboMascotaSolicitante);
                                            }



                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                            Nombres.setText(NombresSolicitante);
                            Picasso.get().load(FotoUsuario).into(fotousuario);


                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Solicitud.child(IdSolicitud).child("estadoSolicitud").setValue("Aceptada");
                Toast.makeText(DetalleAmplioSolicitudes.this, "La Solicitud Fue Aceptada", Toast.LENGTH_LONG).show();
            }
        });

        Denegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Solicitud.child(IdSolicitud).child("estadoSolicitud").setValue("Denegada");
                Toast.makeText(DetalleAmplioSolicitudes.this, "La Solicitud Fue denegada", Toast.LENGTH_LONG).show();

            }
        });

        Proceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Solicitud.child(IdSolicitud).child("estadoSolicitud").setValue("En Proseso");
                Toast.makeText(DetalleAmplioSolicitudes.this, "La Solicitud Esta En Proceso", Toast.LENGTH_LONG).show();
            }
        });

        Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetalleAmplioSolicitudes.this, InterfazPrincipal.class);
                startActivity(intent);

            }
        });





    }
}
