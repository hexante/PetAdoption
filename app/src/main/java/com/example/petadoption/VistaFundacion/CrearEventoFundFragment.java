package com.example.petadoption.VistaFundacion;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.petadoption.Firebase.EventosFundaciones;
import com.example.petadoption.Firebase.MascotasPerdidasApp;
import com.example.petadoption.R;
import com.example.petadoption.VistaUsuario.CrearMascotaPerdida;
import com.example.petadoption.VistaUsuario.InterfazPrincipalUsuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class CrearEventoFundFragment extends Fragment {

    private String IdEvento,IdFundacion,NombreEvent,FechaEvent,DescripcionEvent,LugarEvent;
    private Button Crear,Cancelar;
    private EditText NomEvento,FecEvento,DescEvento,LugEvento;
    private ImageButton FotoEvento;

    private FirebaseAuth auth;
    private DatabaseReference Eventosfundacion;

    private final int PHOTO_CODE = 100;
    private final int SELECT_PICTURE = 200;


    private StorageReference storageRef,FotoR;
    private Bitmap bitmap;
    private Uri Fotogaleria;
    private UploadTask SubirFoto;





    public CrearEventoFundFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crear_evento_fund, container, false);

        auth = FirebaseAuth.getInstance();
        Eventosfundacion = FirebaseDatabase.getInstance().getReference("EventoFundaciones");
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();


        NomEvento = (EditText) view.findViewById(R.id.txtNombreEvent);
        FecEvento = (EditText) view.findViewById(R.id.txtFechaEvent);
        DescEvento = (EditText) view.findViewById(R.id.txtDescripcionEvent);
        LugEvento = (EditText) view.findViewById(R.id.txtlugar);
        FotoEvento = (ImageButton) view.findViewById(R.id.FotoEvento);

        Cancelar = (Button) view.findViewById(R.id.btnCancelarEvent);
        Crear = (Button) view.findViewById(R.id.btnCrear);

        final String[] items = new String[] {"Camara", "Galeria", "Cancelar"};

        // Creamos el diálogo
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Seleccione imagen");
        builder.setAdapter(adapter3, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int seleccion) {
                if (items[seleccion] == "Camara"){
                    // Opción Cámara
                    callCameraApp();
                }else if (items[seleccion] == "Galeria"){
                    // Opción Galería
                    CargarImagen();
                }else if (items[seleccion] == "Cancelar"){
                    dialog.dismiss();
                }
            }
        });

        final AlertDialog dialog = builder.create();

        FotoEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().remove(CrearEventoFundFragment.this).commit();
                Intent intent = new Intent(getActivity(), InterfazPrincipal.class);
                getActivity().startActivity(intent);
            }
        });


        Crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IdEvento = Eventosfundacion.push().getKey();
                IdFundacion = auth.getCurrentUser().getEmail();
                NombreEvent =  NomEvento.getText().toString();
                FechaEvent = FecEvento.getText().toString();
                DescripcionEvent = DescEvento.getText().toString();
                LugarEvent = LugEvento.getText().toString();
                if (NombreEvent.isEmpty()){
                    Toast.makeText(getActivity(),"Por favor escribir nombre del evento ",Toast.LENGTH_LONG).show();
                }else if (FechaEvent.isEmpty()){
                    Toast.makeText(getActivity(),"Por favor escribir Fecha del evento ",Toast.LENGTH_LONG).show();
                }else if (DescripcionEvent.isEmpty()){
                    Toast.makeText(getActivity(),"Por favor escribir Descripcion del evento ",Toast.LENGTH_LONG).show();
                }else if (LugarEvent.isEmpty()){
                    Toast.makeText(getActivity(),"Por favor escribir lugar del evento",Toast.LENGTH_LONG).show();
                }else {

                    FotoR = storageRef.child(" FotosEventosFundaciones/"+IdEvento+".jpg");
                    //  uriFoto = FotoR.getDownloadUrl().toString();

                    if (bitmap != null){
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] datas = baos.toByteArray();
                        SubirFoto = FotoR.putBytes(datas);

                        SubirFoto.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {

                                Toast.makeText(getActivity(),"Hubo un error",Toast.LENGTH_LONG);
                            }
                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                Toast.makeText(getActivity(),"Subida con exito",Toast.LENGTH_LONG);
                                FotoR.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        String uriFoto = task.getResult().toString();

                                       EventosFundaciones EventoFundacion = new  EventosFundaciones(IdEvento,IdFundacion,NombreEvent,uriFoto,FechaEvent,DescripcionEvent,LugarEvent);
                                        Eventosfundacion.child(IdEvento).setValue( EventoFundacion);
                                    }
                                });

                            }
                        });
                    }else{
                        FotoR.putFile(Fotogaleria).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getActivity(),"Subida con exito",Toast.LENGTH_LONG);
                                FotoR.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        String uriFoto = task.getResult().toString();

                                        EventosFundaciones EventoFundacion = new  EventosFundaciones(IdEvento,IdFundacion,NombreEvent,uriFoto,FechaEvent,DescripcionEvent,LugarEvent);
                                        Eventosfundacion.child(IdEvento).setValue( EventoFundacion);
                                    }
                                });


                            }
                        });

                    }

                }

                Intent intent = new Intent(getActivity(), InterfazPrincipal.class);
                getActivity().startActivity(intent);


            }
        });





        return view;
    }

    private void CargarImagen() {

        checkCameraPermission();

        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicacion"),SELECT_PICTURE);

    }

    private void callCameraApp(){
        checkCameraPermission();

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,PHOTO_CODE);

    }

    private void checkCameraPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                getActivity(), Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("mensaje", "No se tiene permiso para la camara !.");
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 225);
        } else {
            Log.i("mensaje", "Tienes permiso para usar la camara.");

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case PHOTO_CODE:
                if (resultCode == RESULT_OK){
                    bitmap = (Bitmap)data.getExtras().get("data");
                    FotoEvento.setImageBitmap(bitmap);

                }
                break;

            case SELECT_PICTURE:
                if (resultCode == RESULT_OK){
                    Fotogaleria = data.getData();
                    FotoEvento.setImageURI(Fotogaleria);
                }
        }
    }


}
