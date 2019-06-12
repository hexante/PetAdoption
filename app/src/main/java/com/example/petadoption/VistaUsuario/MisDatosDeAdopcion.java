package com.example.petadoption.VistaUsuario;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petadoption.Firebase.DatosDeAdopcionApp;
import com.example.petadoption.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class MisDatosDeAdopcion extends Fragment {


    public MisDatosDeAdopcion() {
        // Required empty public constructor
    }

    private TextView Nombre,Telefono,Direccion,Barrio;

    private Button Cancelar,Enviar;
    private Spinner ActividadEconomica;

    private CheckBox MascotaTubo;

    private String idSolicitud,nombres,
            telefono,direccion,barrio,
            actividadEconomica,tuboMascota,
            fundacion,usuario,Mascota,idMascota;

    private DatosDeAdopcionApp Usuario;

    private FirebaseAuth auth;
    private DatabaseReference Solicitud,DatosDeAdoptante;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_mis_datos_de_adopcion, container, false);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        auth = FirebaseAuth.getInstance();

        Nombre = (TextView) view.findViewById(R.id.txtNombreSolicitud2);
        Telefono = (TextView) view.findViewById(R.id.txtTelefonoSolicitud2);
        Direccion = (TextView) view.findViewById(R.id.txtDireccionSolicitud2);
        Barrio = (TextView) view.findViewById(R.id.txtBarrioSolicitud2);

        Enviar = (Button) view.findViewById(R.id.btnEnviarSolicitud2);
        Cancelar = (Button) view.findViewById(R.id.btnCancelarSolicitud2);

        ActividadEconomica = (Spinner) view.findViewById(R.id.SpinnerActivadadSolicitud2);

        MascotaTubo = (CheckBox) view.findViewById(R.id.checkMascotaSolicitud2);

        DatosDeAdoptante = FirebaseDatabase.getInstance().getReference();

        DatosDeAdoptante.child("DatosDeAdopcionApp").orderByChild("usuario")
                .equalTo(auth.getCurrentUser().getEmail())
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for ( DataSnapshot snapshot : dataSnapshot.getChildren()){

                     Usuario = snapshot.getValue(DatosDeAdopcionApp.class);
                    final String Correo = Usuario.getUsuario();

                    if(Correo.equals(auth.getCurrentUser().getEmail())) {
                        Nombre.setText(Usuario.getNombres());
                        Telefono.setText(Usuario.getTelefono());
                        Direccion.setText(Usuario.getDireccion());
                        Barrio.setText(Usuario.getBarrio());
                    }else {
                        startActivity(new Intent(getContext(), DatosDeAdopcion.class));
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        ArrayAdapter<CharSequence> adapteru = ArrayAdapter.createFromResource(getActivity(),
                R.array.ActividadEconomica, android.R.layout.simple_spinner_item);
        adapteru.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ActividadEconomica.setAdapter(adapteru);

        if (MascotaTubo.isChecked() == true){
            Mascota = "si";
        }else {
            Mascota ="no";
        }






        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver = new Intent(getActivity(), InterfazPrincipalUsuarios.class);
                startActivity(volver);
            }
        });



        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                idSolicitud = Usuario.getIdDatos();
                nombres = Nombre.getText().toString();
                telefono = Telefono.getText().toString();
                direccion= Direccion.getText().toString();
                barrio=Barrio.getText().toString();
                actividadEconomica = ActividadEconomica.getSelectedItem().toString();
                tuboMascota= Mascota;
                usuario= user.getEmail();

                if (telefono.isEmpty()){
                    Toast.makeText(getContext(),"Por favor escriba un numero de contacto",Toast.LENGTH_LONG).show();
                }else if (direccion.isEmpty()){
                    Toast.makeText(getContext(),"Por favor escriba una direccion",Toast.LENGTH_LONG).show();
                }else if (barrio.isEmpty()){
                    Toast.makeText(getContext(),"Por favor escriba un barrio",Toast.LENGTH_LONG).show();
                }else if (actividadEconomica.equals("Actividad economica")){
                    Toast.makeText(getContext(),"Por favor elija una actividad economica",Toast.LENGTH_LONG).show();
                }else {
                    DatosDeAdopcionApp datosadoptante = new DatosDeAdopcionApp(idSolicitud,nombres,telefono,direccion,barrio,actividadEconomica,tuboMascota,usuario);
                    DatosDeAdoptante.child("DatosDeAdopcionApp").child(idSolicitud).setValue(datosadoptante);
                    Toast.makeText(getContext(),"Datos actualizados correctamente",Toast.LENGTH_LONG).show();

                    Intent volver = new Intent(getContext(), InterfazPrincipalUsuarios.class);
                    startActivity(volver);
                }




            }
        });



       return view;
    }

}
