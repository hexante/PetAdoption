package com.example.petadoption.VistaUsuario;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.petadoption.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragMenuUsuario extends Fragment {

    private Button Consultar,fundaciones,MascotasPerdidas;


    public FragMenuUsuario() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_menu_usuario, container, false);

        Consultar = (Button) view.findViewById(R.id.btnConsultarMascotas);
        fundaciones = (Button) view.findViewById(R.id.btnBfundaciones);
        MascotasPerdidas = (Button) view.findViewById(R.id.btnMascotasPerdidasUsuario);

        Consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CargarFragmentos(new BusquedaMascotasU());

            }
        });

        fundaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              CargarFragmentos(new FragVistaUsuario());

            }
        });





        final String[] items = new String[] {"Crear Anuncio","Anuncios","Mis Anuncios", "Cancelar"};

        // Creamos el diálogo
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Que Deseas?");
        builder.setAdapter(adapter3, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int seleccion) {
                if (items[seleccion] == "Crear Anuncio"){
                    CargarFragmentos(new CrearMascotaPerdida());
                }else if (items[seleccion] == "Anuncios"){

                }else if (items[seleccion] == "Mis Anuncios") {
                    CargarFragmentos(new ListaDeMisAnuncios());
                }else if (items[seleccion] == "Cancelar"){
                    dialog.dismiss();
                }
            }
        });
        final AlertDialog dialog = builder.create();

        MascotasPerdidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        return view;
    }

    private void CargarFragmentos(Fragment fragmento) {

        FragmentManager manager = getFragmentManager();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().remove(this).commit();

        manager.beginTransaction().replace(R.id.contenedorFragmento, fragmento).commit();
    }

}
