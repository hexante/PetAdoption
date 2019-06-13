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




        final String[] BusquedaMascota = new String[] {"Perros","Gatos", "Cancelar"};

        // Creamos el diálogo
        ArrayAdapter<String> TipoMascota = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, BusquedaMascota);
        AlertDialog.Builder builderTipo = new AlertDialog.Builder(getActivity());
        builderTipo.setTitle("Que Tipo?");
        builderTipo.setAdapter(TipoMascota, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface MenuTipoMascota, int seleccion) {
                if (BusquedaMascota[seleccion] == "Perros"){
                    CargarFragmentos(new BuscarPerroUsuario());
                }else if (BusquedaMascota[seleccion] == "Gatos"){
                    CargarFragmentos(new BusquedaMascotasU());
                }else if (BusquedaMascota[seleccion] == "Cancelar"){
                    MenuTipoMascota.dismiss();
                }
            }
        });
        final AlertDialog MenuTipoMascota = builderTipo.create();
        Consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MenuTipoMascota.show();

            }
        });

        fundaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] items = { "Ver fundaciones", "Eventos fundaciones", "ver tips fundaciones"};

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                builder.setTitle("Que deseas consultar");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        // will toast your selection
                        //showToast("Name: " + items[item]);
                        if(items[item].equals("Ver fundaciones")){
                            CargarFragmentos(new FragVistaUsuario());
                        }
                        if(items[item].equals("Eventos fundaciones")){
                            CargarFragmentos(new ListaEventos());

                        }
                        if (items[item].equals("ver tips fundaciones")) {
                            CargarFragmentos(new VideosTips());

                        }
                        dialog.dismiss();

                    }
                }).show();



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
                    CargarFragmentos(new ListaDeAnunciosPerdida());
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
        manager.beginTransaction().replace(R.id.contenedorFragmento, fragmento).commit();
    }

}
