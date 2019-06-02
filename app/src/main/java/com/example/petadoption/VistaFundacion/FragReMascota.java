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
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.petadoption.Firebase.MascotasApp;
import com.example.petadoption.R;

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

import static android.app.Activity.RESULT_OK;





/**
 * A simple {@link Fragment} subclass.
 */
public class FragReMascota extends Fragment {

    private Spinner Spitamaño,SpiTipo;
    private EditText RazaM,EdadM,ColorM,descripcionLeM,NombreMascota;
    private RadioGroup sexoM,lesionM;
    private RadioButton Macho,Hembra,Lsi,Lno;
    private Button Registrar,Cancelar,Foto,Reg;
    private FirebaseAuth auth;
    private ImageView picImageView;

    String NombreM,Sexo,lesiones,Raza,Tamaño,
            Edad,Lesion,Color,Descripcio_les,
            Id_Fundacion,Genero,TipoM,Estado,idM;

    private final int PHOTO_CODE = 100;
    private final int SELECT_PICTURE = 200;



    private DatabaseReference Mascotas;
    private StorageReference storageRef,FotoR;
    private Bitmap bitmap;
    private Uri Fotogaleria;
    private UploadTask SubirFoto;

    public FragReMascota() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       //return inflater.inflate(R.layout.fragment_frag_re_mascota, container, false);



       View view = inflater.inflate(R.layout.fragment_frag_re_mascota, container, false);

        auth = FirebaseAuth.getInstance();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();



        picImageView = (ImageView)  view.findViewById(R.id.picImageView);

        RazaM = (EditText) view.findViewById(R.id.autocomplete_raza);
        EdadM = (EditText) view.findViewById(R.id.EdadMascota);
        ColorM = (EditText) view.findViewById(R.id.Color);
        descripcionLeM = (EditText) view.findViewById(R.id.Text_descrip);
        NombreMascota = (EditText) view.findViewById(R.id.txtNombreMascota);

        sexoM = (RadioGroup) view.findViewById(R.id.RadioG1);
        Macho = (RadioButton) view.findViewById(R.id.RbMacho) ;
        Hembra = (RadioButton) view.findViewById(R.id.RbHembra) ;
        lesionM = (RadioGroup) view.findViewById(R.id.RadioG2);

        Foto = (Button) view.findViewById(R.id.btnFoto);
        Registrar = (Button) view.findViewById(R.id.Btn_Registrar);
        Cancelar = (Button) view.findViewById(R.id.Btn_Cancelar);

        Spitamaño = (Spinner) view.findViewById(R.id.spinTamaño);
        SpiTipo = (Spinner) view.findViewById(R.id.spinTipoMascota);

        Sexo = "N/A";
        lesiones = "N/A";

        Mascotas = FirebaseDatabase.getInstance().getReference("MascotasApp");


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.TipoMascota, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpiTipo.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.TamañoMascota, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spitamaño.setAdapter(adapter2);


        sexoM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId==R.id.RbHembra){
                    Sexo="Hembra";
                }else if(checkedId==R.id.RbMacho){
                    Sexo="Macho";

                }}
        });


        lesionM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId==R.id.RbSi){
                    lesiones="Si";
                }else if(checkedId==R.id.RbNo){
                    lesiones="No";

                }}
        });


        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NombreM = NombreMascota.getText().toString();
                Raza = RazaM.getText().toString();
                Tamaño = Spitamaño.getSelectedItem().toString();
                Edad = EdadM.getText().toString();
                Lesion = lesiones;
                Color = ColorM.getText().toString() ;
                Descripcio_les = descripcionLeM.getText().toString();
                Id_Fundacion = user.getEmail();
                Genero = Sexo;
                TipoM =  SpiTipo.getSelectedItem().toString();
                Estado = "en adopcion";

                idM=Mascotas.push().getKey();

                if(idM.isEmpty()){
                    Toast.makeText(getActivity(),"Nada",Toast.LENGTH_LONG).show();
                }else {

                   FotoR = storageRef.child(" FotosMascotas/"+idM+".jpg");

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
                                        Log.e("LinkDeDescarga","" + uriFoto);
                                        MascotasApp Mascota = new MascotasApp(idM,NombreM, Raza, Color, Edad, Genero, Lesion, Descripcio_les, Tamaño, Id_Fundacion, Estado, uriFoto,TipoM);
                                        Mascotas.child(idM).setValue(Mascota);
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
                                        Log.e("LinkDeDescarga","" + uriFoto);
                                        MascotasApp Mascota = new MascotasApp(idM,NombreM, Raza, Color, Edad, Genero, Lesion, Descripcio_les, Tamaño, Id_Fundacion, Estado, uriFoto,TipoM);
                                        Mascotas.child(idM).setValue(Mascota);
                                    }
                                });


                            }
                        });

                    }



                    //  MascotasApp Mascota = new MascotasApp(idM, Raza, Color, Edad, Genero, Lesion, Descripcio_les, Tamaño, Id_Fundacion, Estado);
                    //  Mascotas.child(TipoM).child(idM).setValue(Mascota);


                    if (TipoM.equals("Perro")) {
                        Toast.makeText(getActivity(), "Perro registrado con exito", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Gato registrado con exito", Toast.LENGTH_LONG).show();
                    }

                    Intent intent = new Intent(getActivity(), InterfazPrincipal.class);
                    getActivity().startActivity(intent);
                }


            }


        });

        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InterfazPrincipal.class);
                getActivity().startActivity(intent);

            }
        });







        //**+++++++ inicio codigo para tomar las fotos

    // Los objetos de la librería que nos ayudarán en la tarea
   // cameraPhoto = new CameraPhoto(getActivity());
    //    galleryPhoto = new GalleryPhoto(getActivity());

    // Array con las opciones para el diálogo que se abrirá al pulsar el botón "PIC"
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

    // Buscamos el botón por su ID y creamos una referencia al mismo

        Foto.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.show();
        }
    });

    // Buscamos el ImageView por su ID y creamos una referencia al mismo


        return view;
}

    private void CargarImagen() {

            Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicacion"),SELECT_PICTURE);

    }

    private void callCameraApp(){
        checkCameraPermission();

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,PHOTO_CODE);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case PHOTO_CODE:
                if (resultCode == RESULT_OK){
                    bitmap = (Bitmap)data.getExtras().get("data");
                    picImageView.setImageBitmap(bitmap);

                }
            break;

            case SELECT_PICTURE:
                if (resultCode == RESULT_OK){
                    Fotogaleria = data.getData();
                    picImageView.setImageURI(Fotogaleria);
                }
        }
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
}