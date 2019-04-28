package com.example.petadoption;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.petadoption.AccoutActivity.InicioActivity;
import com.example.petadoption.AccoutActivity.InterfazPrincipal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class DatosUsuario extends AppCompatActivity {

    private EditText NombreU,ApellidoU,
            NumeroDocumentoU,DepartamentoU,
            CiudadU,TelefonoU,CorreoU;
    private  Spinner spinner,spinnerU;
    private Button TerminarR;

    private ImageView FotoPerfil;
    private final int PHOTO_CODE = 100;

    private DatabaseReference USUARIOS;
    private StorageReference mStorege;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        checkCameraPermission();

        NombreU = (EditText) findViewById(R.id.NombreUsuario);
        ApellidoU = (EditText) findViewById(R.id.ApellidoUsuario);
        NumeroDocumentoU = (EditText) findViewById(R.id.NumeroDocumento);
        DepartamentoU = (EditText) findViewById(R.id.DepartamentoUsuario);
        CiudadU = (EditText) findViewById(R.id.CiudadUsuario);
        TelefonoU = (EditText) findViewById(R.id.TelefonoUsuario);
        CorreoU = (EditText) findViewById(R.id.CorreoUsuario);
        TerminarR = (Button) findViewById(R.id.btnTerminar);

        FotoPerfil = (ImageView) findViewById(R.id.FotoPerfilUsuario);

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

        FotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {

                checkCameraPermission();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,PHOTO_CODE);
            }
        });

        //Cargar foto a Storage en firebase






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
                    USUARIOS.child(id).setValue(usuario);

                    Toast.makeText(DatosUsuario.this,"usuario registrado con exito",Toast.LENGTH_LONG).show();
                       Intent intent = new Intent(DatosUsuario.this, InterfazPrincipal.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                }else{
                    Toast.makeText(DatosUsuario.this,"Debe Ingresar Numero De identidad",Toast.LENGTH_LONG).show();

                }

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case PHOTO_CODE:
                if (resultCode == RESULT_OK){
                    Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                    FotoPerfil.setImageBitmap(bitmap);

                }
                break;
        }
    }

    private void checkCameraPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("mensaje", "No se tiene permiso para la camara !.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 225);
        } else {
            Log.i("mensaje", "Tienes permiso para usar la camara.");

        }

    }

}





