package com.example.petadoption.VistaFundacion;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.petadoption.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragMenuFundacion extends Fragment {

    private Button Consultar,Registrar,Eventos;



    public FragMenuFundacion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_menu_fundacion, container, false);

        Consultar = (Button) view.findViewById(R.id.BtnConsultar);
        Registrar = (Button) view.findViewById(R.id.btnRegistrarMascotas);
        Eventos = (Button) view.findViewById(R.id.btnEventos);

        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               CargarFragmentos(new FragReMascota());
            }
        });


        Consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = { "Buscar Mascota", "Solicitudes Adopcion"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Que deseas consultar");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        // will toast your selection
                       //showToast("Name: " + items[item]);
                        if(items[item].equals("Buscar Mascota")){
                            CargarFragmentos(new FragVistasMascotas());
                        }
                        if(items[item].equals("Solicitudes Adopcion")){
                            CargarFragmentos(new FregSolicitudesAdoptantes());
                        }
                        dialog.dismiss();

                    }
                }).show();
            }
        });

        Eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] items = { "Crear Evento", "Mis Eventos"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Que Hacer");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        // will toast your selection
                        //showToast("Name: " + items[item]);
                        if(items[item].equals("Crear Evento")){
                            CargarFragmentos(new CrearEventoFundFragment());
                        }
                        if(items[item].equals("Mis Eventos")){
                            CargarFragmentos(new ListaDeMisEventosFund());
                        }
                        dialog.dismiss();

                    }
                }).show();

            }
        });

        return view;
    }

    private void CargarFragmentos(Fragment fragmento) {

        FragmentManager manager;
        manager = getFragmentManager();

        manager.beginTransaction().replace(R.id.contenedorFragmento, fragmento).commit();
    }


}
