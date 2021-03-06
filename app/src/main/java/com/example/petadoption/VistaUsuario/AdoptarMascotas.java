package com.example.petadoption.VistaUsuario;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petadoption.Firebase.DatosDeAdopcionApp;
import com.example.petadoption.R;
import com.example.petadoption.Firebase.SolicitudMascotaApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class AdoptarMascotas extends AppCompatActivity {

    private  TextView Nombre,Tamaño,Fundacion,Raza,Edad,Descripcion;
    private String imagen,CorreoUsuario,idMascota,fundacion;
    private ImageView Fotomasc;
    private Boolean cancelar;
    private Button AdoptarMascota,Cancelar;

    private DatabaseReference DatosDeAdoptante,Solicitud,mascota;
    private FirebaseUser auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoptar_mascotas);

        Nombre = (TextView) findViewById(R.id.txtNombreMascota);
        Edad = (TextView) findViewById(R.id.txtEdadMascota);
        Raza = (TextView) findViewById(R.id.txtRazaMascota);
        Tamaño = (TextView) findViewById(R.id.txtTamañoMascota);
        Descripcion = (TextView) findViewById(R.id.txtDescripcionMascota);
        Fundacion = (TextView) findViewById(R.id.txtFundacion);
        Fotomasc = (ImageView) findViewById(R.id.FotoMascota);

        auth = FirebaseAuth.getInstance().getCurrentUser();


        AdoptarMascota = (Button) findViewById(R.id.btnAdoptarMascota);
        Cancelar = (Button) findViewById(R.id.btnCancelarEvent);

        final Intent intent = getIntent();

        Nombre.setText(intent.getStringExtra("NombreMascota"));
        Raza.setText( intent.getStringExtra("Raza"));
        Edad.setText( intent.getStringExtra("Edad"));
        Tamaño.setText( intent.getStringExtra("Tamaño"));
        Descripcion.setText( intent.getStringExtra("Descripcion"));
        Fundacion.setText( intent.getStringExtra("Fundacion"));
        imagen = intent.getStringExtra("Fotomasc");

        Picasso.get().load(imagen).into(Fotomasc);

        DatosDeAdoptante = FirebaseDatabase.getInstance().getReference("DatosDeAdopcionApp");

        Solicitud = FirebaseDatabase.getInstance().getReference("SolicitudMascotaApp");

        mascota = FirebaseDatabase.getInstance().getReference("MascotasApp");

        AdoptarMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatosDeAdoptante.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists() == false){
                            Intent vista = new Intent(AdoptarMascotas.this, DatosDeAdopcion.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            vista.putExtra("Fundacion",Fundacion.getText().toString());
                            vista.putExtra("IdMascota",intent.getStringExtra("IdMascota"));
                            startActivity(vista);}else{

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            DatosDeAdopcionApp nuevasolicitud = snapshot.getValue(DatosDeAdopcionApp.class);
                            String correo = nuevasolicitud.getUsuario();
                            CorreoUsuario = auth.getEmail();
                            fundacion = intent.getStringExtra("Fundacion");
                            idMascota = intent.getStringExtra("IdMascota");

                            Log.e("Correro",correo);
                            Log.e("usuario",nuevasolicitud.getUsuario());

                            if (nuevasolicitud.getUsuario().equals(CorreoUsuario)){

                                String IdsolicitudAdiopcion = Solicitud.push().getKey();

                                SolicitudMascotaApp SolicitudAdopcion = new SolicitudMascotaApp(IdsolicitudAdiopcion,CorreoUsuario,fundacion,idMascota,"Pendiente");
                                Solicitud.child(IdsolicitudAdiopcion).setValue(SolicitudAdopcion);
                                mascota.child(idMascota).child("estado").setValue("Pendiente");

                                Toast.makeText(AdoptarMascotas.this,"Solicitud Enviada",Toast.LENGTH_LONG).show();

                                Intent vista = new Intent(AdoptarMascotas.this, InterfazPrincipalUsuarios.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(vista);

                            }else {
                                Intent vista = new Intent(AdoptarMascotas.this, DatosDeAdopcion.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                vista.putExtra("Fundacion",Fundacion.getText().toString());
                                startActivity(vista);
                            }



                        }}

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                        Toast.makeText(AdoptarMascotas.this,"Fallo Algo",Toast.LENGTH_LONG).show();
                    }
                });




            }
        });

        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cancelar = true;

                Intent vista = new Intent(AdoptarMascotas.this, InterfazPrincipalUsuarios.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                vista.putExtra("cancelar",cancelar);
                startActivity(vista);

            }
        });





    }



}
