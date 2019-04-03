package com.example.petadoption;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.petadoption.AccoutActivity.DatosMascotas;
import com.example.petadoption.AccoutActivity.InterfazPrincipal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatosUsuario extends AppCompatActivity {

    private EditText NombreU,ApellidoU,
            NumeroDocumentoU,DepartamentoU,
            CiudadU,TelefonoU,CorreoU;
    private  Spinner spinner,spinnerU;
    private Button TerminarR;

    private DatabaseReference USUARIOS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        NombreU = (EditText) findViewById(R.id.NombreUsuario);
        ApellidoU = (EditText) findViewById(R.id.ApellidoUsuario);
        NumeroDocumentoU = (EditText) findViewById(R.id.NumeroDocumento);
        DepartamentoU = (EditText) findViewById(R.id.DepartamentoUsuario);
        CiudadU = (EditText) findViewById(R.id.CiudadUsuario);
        TelefonoU = (EditText) findViewById(R.id.TelefonoUsuario);
        CorreoU = (EditText) findViewById(R.id.CorreoUsuario);
        TerminarR = (Button) findViewById(R.id.btnTerminar);

        USUARIOS = FirebaseDatabase.getInstance().getReference("UsuariosApp");

         spinner = (Spinner) findViewById(R.id.spinner);
         spinnerU = (Spinner) findViewById(R.id.spinTipoU);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.TipoDocumento, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapteru = ArrayAdapter.createFromResource(this,
                R.array.TipoUsuario, android.R.layout.simple_spinner_item);
        adapteru.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerU.setAdapter(adapteru);

        CorreoU.setText(user.getEmail());

        TerminarR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre=NombreU.getText().toString();
                String Apellido=ApellidoU.getText().toString();
                String numerod=NumeroDocumentoU.getText().toString();
                String tipod=spinner.getSelectedItem().toString();
                String telefono=TelefonoU.getText().toString();
                String correo=CorreoU.getText().toString();
                String departamento=DepartamentoU.getText().toString();
                String ciudad=CiudadU.getText().toString();
                String Tipou = spinnerU.getSelectedItem().toString();


                if (!TextUtils.isEmpty(numerod)){
                    String id=USUARIOS.push().getKey();
                    UsuariosApp usuario = new UsuariosApp(id,nombre,Apellido,departamento,ciudad,telefono,correo,tipod,numerod,Tipou);
                    USUARIOS.child(Tipou).child(id).setValue(usuario);
                    Toast.makeText(DatosUsuario.this,"usuario registrado con exito",Toast.LENGTH_LONG).show();

                       Intent intent = new Intent(DatosUsuario.this, InterfazPrincipal.class);
                        startActivity(intent);


                }else{
                    Toast.makeText(DatosUsuario.this,"Debe Ingresar Numero De identidad",Toast.LENGTH_LONG).show();
                }

            }
        });


    }





}
