package com.example.petadoption.VistaUsuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.petadoption.Firebase.DatosDeAdopcionApp;
import com.example.petadoption.R;
import com.example.petadoption.Firebase.SolicitudMascotaApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatosDeAdopcion extends AppCompatActivity {

    private TextView Nombre,Telefono,Direccion,Barrio;
    private String idSolicitud,nombres,
            telefono,direccion,barrio,
            actividadEconomica,tuboMascota,
            fundacion,usuario,Mascota,idMascota;

    private Button Cancelar,Enviar;

    private Spinner ActividadEconomica;

    private CheckBox MascotaTubo;

    private FirebaseAuth auth;
    private DatabaseReference Solicitud,DatosDeAdoptante,mascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_de_adopcion);


        final Intent intent = getIntent();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        auth = FirebaseAuth.getInstance();

        Nombre = (TextView) findViewById(R.id.txtNombreSolicitud);
        Telefono = (TextView) findViewById(R.id.txtTelefonoSolicitud);
        Direccion = (TextView) findViewById(R.id.txtDireccionSolicitud);
        Barrio = (TextView) findViewById(R.id.txtBarrioSolicitud);

        Enviar = (Button) findViewById(R.id.btnEnviarSolicitud);
        Cancelar = (Button) findViewById(R.id.btnCancelarSolicitud);

        ActividadEconomica = (Spinner) findViewById(R.id.SpinnerActivadadSolicitud);

        MascotaTubo = (CheckBox) findViewById(R.id.checkMascotaSolicitud);


        DatosDeAdoptante = FirebaseDatabase.getInstance().getReference("DatosDeAdopcionApp");

        Solicitud = FirebaseDatabase.getInstance().getReference("SolicitudMascotaApp");

        mascota = FirebaseDatabase.getInstance().getReference("MascotasApp");


        ArrayAdapter<CharSequence> adapteru = ArrayAdapter.createFromResource(this,
                R.array.ActividadEconomica, android.R.layout.simple_spinner_item);
        adapteru.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ActividadEconomica.setAdapter(adapteru);


        if (MascotaTubo.isChecked() == true){
                    Mascota = "si";
        }else {
            Mascota ="no";
        }


        fundacion = intent.getStringExtra("Fundacion");
        idMascota = intent.getStringExtra("IdMascota");


        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                idSolicitud = DatosDeAdoptante.push().getKey();
                nombres = Nombre.getText().toString();
                telefono = Telefono.getText().toString();
                direccion= Direccion.getText().toString();
                barrio=Barrio.getText().toString();
                actividadEconomica = ActividadEconomica.getSelectedItem().toString();
                tuboMascota= Mascota;

                usuario= user.getEmail();

                DatosDeAdopcionApp datosadoptante = new DatosDeAdopcionApp(idSolicitud,nombres,telefono,direccion,barrio,actividadEconomica,tuboMascota,usuario);

                DatosDeAdoptante.child(idSolicitud).setValue(datosadoptante);

                String IdsolicitudAdiopcion = Solicitud.push().getKey();

                SolicitudMascotaApp SolicitudAdopcion = new SolicitudMascotaApp(IdsolicitudAdiopcion,usuario,fundacion,idMascota,"Pendiente");
                Solicitud.child(IdsolicitudAdiopcion).setValue(SolicitudAdopcion);
                mascota.child(idMascota).child("estado").setValue("Pendiente");


                Intent volver = new Intent(DatosDeAdopcion.this, InterfazPrincipalUsuarios.class);
                startActivity(volver);

            }
        });

        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver = new Intent(DatosDeAdopcion.this, InterfazPrincipalUsuarios.class);
                startActivity(volver);
            }
        });




    }
}
