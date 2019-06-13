package com.example.petadoption.AccoutActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.petadoption.R;
import com.example.petadoption.Firebase.UsuariosApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class DatosUsuario extends AppCompatActivity {

    private EditText NombreU,ApellidoU,
            NumeroDocumentoU,DepartamentoU,
            CiudadU,TelefonoU,CorreoU;
    private  Spinner TipoDocumento,TipoUsuario;
    private Button TerminarR;

    private ImageView FotoPerfil;
    private final int PHOTO_CODE = 100;

    private String id,nombre,
            apellido,numerod,
            departamento,ciudad,
            tipod,telefono,tipou,correo;


    private FirebaseAuth auth;
    private DatabaseReference USUARIOS;
    private StorageReference storageRef;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        auth = FirebaseAuth.getInstance();


        NombreU = (EditText) findViewById(R.id.NombreUsuario);
        ApellidoU = (EditText) findViewById(R.id.ApellidoUsuario);
        NumeroDocumentoU = (EditText) findViewById(R.id.NumeroDocumento);
        DepartamentoU = (EditText) findViewById(R.id.DepartamentoUsuario);
        CiudadU = (EditText) findViewById(R.id.CiudadUsuario);
        TelefonoU = (EditText) findViewById(R.id.TelefonoUsuario);
        CorreoU = (EditText) findViewById(R.id.CorreoUsuario);
        TerminarR = (Button) findViewById(R.id.btnTerminar);

        final FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        FotoPerfil = (ImageView) findViewById(R.id.FotoPerfilUsuario);

        USUARIOS = FirebaseDatabase.getInstance().getReference("UsuariosApp");

        TipoDocumento = (Spinner) findViewById(R.id.spinner);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.TipoDocumento, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        TipoDocumento.setAdapter(adapter);



        CorreoU.setText(user.getEmail());



        //Cargar foto a Storage en firebase






        TerminarR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 tipod=TipoDocumento.getSelectedItem().toString();
                 numerod=NumeroDocumentoU.getText().toString();
                 nombre=NombreU.getText().toString();
                 apellido=ApellidoU.getText().toString();
                 departamento=DepartamentoU.getText().toString();
                 ciudad=CiudadU.getText().toString();
                 telefono=TelefonoU.getText().toString();
                 correo=CorreoU.getText().toString();
                tipou = "Usuario";


                if ("Tipo Documento".equals(tipod)){
                    Toast.makeText(DatosUsuario.this,"Elija tipo de documento" , Toast.LENGTH_LONG).show();
                }else{
                    if (numerod.isEmpty()){
                        Toast.makeText(DatosUsuario.this,"Campo Numero Documento vacio" , Toast.LENGTH_LONG).show();
                    } else {
                        if (nombre.isEmpty()){
                            Toast.makeText(DatosUsuario.this,"Campo nombre vacio" , Toast.LENGTH_LONG).show();
                        }else {
                            if (apellido.isEmpty()){
                                Toast.makeText(DatosUsuario.this,"Campo apellidos vacio" , Toast.LENGTH_LONG).show();
                            }else {
                                if (departamento.isEmpty()){
                                    Toast.makeText(DatosUsuario.this,"Campo departamento vacio" , Toast.LENGTH_LONG).show();
                                }else {
                                    if (ciudad.isEmpty()){
                                        Toast.makeText(DatosUsuario.this,"Campo ciudad vacio" , Toast.LENGTH_LONG).show();
                                    }else {
                                        if (telefono.isEmpty()){
                                            Toast.makeText(DatosUsuario.this,"Campo telefono vacio" , Toast.LENGTH_LONG).show();
                                        }else {
                                            if (correo.isEmpty()){
                                                Toast.makeText(DatosUsuario.this,"Campo correo vacio" , Toast.LENGTH_LONG).show();
                                            }else {
                                                if (tipou.isEmpty()) {
                                                    Toast.makeText(DatosUsuario.this, " ", Toast.LENGTH_LONG).show();
                                                }else {
                                                    if (bitmap != null){
                                                     id=USUARIOS.push().getKey();


                                                    final StorageReference FotoR = storageRef.child(" FotosUsuarios/"+id+".jpg");
                                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                                                    byte[] datas = baos.toByteArray();

                                                    final UploadTask SubirFoto = FotoR.putBytes(datas);
                                                    SubirFoto.addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception exception) {
                                                            Toast.makeText(getBaseContext(),"Hubo un error",Toast.LENGTH_LONG);
                                                        }
                                                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                                        @Override
                                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                                            Toast.makeText(getBaseContext(),"Subida con exito",Toast.LENGTH_LONG);
                                                            FotoR.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Uri> task) {
                                                                    String uriFoto = task.getResult().toString();
                                                                    UsuariosApp usuario = new UsuariosApp(id,nombre,apellido,departamento,ciudad,telefono,correo,tipod,numerod,tipou,uriFoto);
                                                                    USUARIOS.child(id).setValue(usuario);

                                                                }
                                                            });

                                                        }
                                                    });

                                                    Toast.makeText(DatosUsuario.this,"usuario registrado con exito",Toast.LENGTH_LONG).show();
                                                    Intent intent = new Intent(DatosUsuario.this, InicioActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                                    startActivity(intent);
                                                    auth.signOut();
                                                }else {
                                                        Toast.makeText(DatosUsuario.this,"Por favor Tomar foto de perfil",Toast.LENGTH_LONG).show();}
                                                }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }




        });

        FotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkCameraPermission();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, PHOTO_CODE);
                }
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case PHOTO_CODE:
                if (requestCode == PHOTO_CODE && resultCode == RESULT_OK){
                    bitmap = (Bitmap)data.getExtras().get("data");
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





