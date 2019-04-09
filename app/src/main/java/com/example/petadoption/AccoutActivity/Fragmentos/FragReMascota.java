package com.example.petadoption.AccoutActivity.Fragmentos;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.petadoption.AccoutActivity.DatosMascotas;
import com.example.petadoption.AccoutActivity.InicioActivity;
import com.example.petadoption.AccoutActivity.InterfazPrincipal;
import com.example.petadoption.BuildConfig;
import com.example.petadoption.R;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.Registrar;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.FileNotFoundException;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragReMascota extends Fragment {

    private Spinner Spitamaño,SpiTipo;
    private EditText RazaM,EdadM,ColorM,descripcionLeM;
    private RadioGroup sexoM,lesionM;
    private Button Registrar,Cancelar,Foto;
    private FirebaseAuth auth;
    private ImageView picImageView;
    String Sexo,lesiones;

    CameraPhoto cameraPhoto;
    GalleryPhoto galleryPhoto;
    private static final int CAMERA_REQUEST = 1;
    private static final int GALLERY_REQUEST = 2;

    private DatabaseReference Mascotas;

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


        RazaM = (EditText) view.findViewById(R.id.autocomplete_raza);
        EdadM = (EditText) view.findViewById(R.id.EdadMascota);
        ColorM = (EditText) view.findViewById(R.id.Color);
        descripcionLeM = (EditText) view.findViewById(R.id.Text_descrip);

        sexoM = (RadioGroup) view.findViewById(R.id.RadioG1);
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


        if (sexoM.getCheckedRadioButtonId()==R.id.RbHembra){
            Sexo="Hembra";
        }if(sexoM.getCheckedRadioButtonId()==R.id.RbMacho){
            Sexo="Macho";
        }

        if (lesionM.getCheckedRadioButtonId()==R.id.RbSi){
            lesiones="Si";
        }if(lesionM.getCheckedRadioButtonId()==R.id.RbNo){
            lesiones="No";
        }


        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Raza= RazaM.getText().toString();
                String Tamaño= Spitamaño.getSelectedItem().toString();
                String Edad= EdadM.getText().toString();
                String Lesion= lesiones;
                String Color= ColorM.getText().toString() ;
                String Descripcio_les= descripcionLeM.getText().toString();
                String Id_Fundacion= user.getUid();
                String Genero= Sexo;
                String TipoM=  SpiTipo.getSelectedItem().toString();
                String Estado= "en adopcion";

                String idM=Mascotas.push().getKey();
                MascotasApp Mascota = new MascotasApp(idM,Raza,Color,Edad,Genero,Lesion,Descripcio_les,Tamaño,Id_Fundacion,Estado);
                Mascotas.child(TipoM).child(idM).setValue(Mascota);
                if(TipoM.equals("Perro")) {
                    Toast.makeText(getActivity(), "Perro registrado con exito", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(), "Gato registrado con exito", Toast.LENGTH_LONG).show();
                }


            }
        });




        //**+++++++ inicio codigo para tomar las fotos

    // Los objetos de la librería que nos ayudarán en la tarea
    cameraPhoto = new CameraPhoto(getActivity());
    galleryPhoto = new GalleryPhoto(getActivity());

    // Array con las opciones para el diálogo que se abrirá al pulsar el botón "PIC"
    final String[] items = new String[] {"Camera", "Gallery"};

    // Creamos el diálogo
    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, items);
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select image");
        builder.setAdapter(adapter3, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == 0){
                // Opción Cámara
                callCameraApp();
                dialog.cancel();
            }else{
                // Opción Galería
                startActivityForResult(galleryPhoto.openGalleryIntent(), GALLERY_REQUEST);
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
    picImageView = (ImageView) view.findViewById(R.id.picImageView);

        return view;
}

    private void callCameraApp(){
        try {
            // Lanzamos la actividad correspondiente a la Cámara, gracias a la librería.
            startActivityForResult(cameraPhoto.takePhotoIntent(), CAMERA_REQUEST);

            // Indicamos que queremos guardar la imagen tomada en la galería
            cameraPhoto.addToGallery();
        } catch (IOException e) {
            Toast.makeText(getActivity(), "Something goes wrong while taking photos", Toast.LENGTH_SHORT).show();
        }
    }

    private void createImage(Bitmap bitmap){
        picImageView.setImageBitmap(bitmap);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                // Obtenemos el path de la imagen que hemos tomado
                String photoPath = cameraPhoto.getPhotoPath();
                try {
                    // Creamos un bitmap a partir de la imagen y lo redimensionamos
                    Bitmap imageBitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();

                    // Aplicamos el Bitmap al ImageView
                    createImage(imageBitmap);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getActivity(), "Something goes wrong while loading photos", Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == GALLERY_REQUEST) {
                // Creamos una ruta en formato Uri para los datos correspondientes a la imagen
                Uri uri = data.getData();

                // Asignamos esa ruta al objeto de la librería
                galleryPhoto.setPhotoUri(uri);

                // Obtenemos la ruta completa para acceder a la imagen
                String photoPath = galleryPhoto.getPath();
                try {
                    // Seguimos el mismo proceso que para la cámara
                    Bitmap imageBitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();
                    createImage(imageBitmap);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getActivity(), "Something goes wrong while choosing photos", Toast.LENGTH_SHORT).show();
                }


            }


        }
    }

}
