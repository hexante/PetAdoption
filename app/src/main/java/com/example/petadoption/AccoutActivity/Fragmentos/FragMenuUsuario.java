package com.example.petadoption.AccoutActivity.Fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.petadoption.AccoutActivity.viewHolder;
import com.example.petadoption.AccoutActivity.viewHolderUsers;
import com.example.petadoption.R;
import com.example.petadoption.UsuariosApp;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragMenuUsuario extends Fragment {

    private Button Consultar,fundaciones;


    public FragMenuUsuario() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_menu_usuario, container, false);

        Consultar = (Button) view.findViewById(R.id.btnConsultarMascotas);
       // fundaciones = (Button) view.findViewById(R.id.btnBfundaciones);

        Consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CargarFragmentos(new BusquedaMascotasU());

            }
        });

 //    fundaciones.setOnClickListener(new View.OnClickListener() {
 //        @Override
 //        public void onClick(View v) {
 //
 //            CargarFragmentos(new FragVistaUsuario());
 //
 //        }
 //    });



        return view;
    }

    private void CargarFragmentos(Fragment fragmento) {

        FragmentManager manager = getFragmentManager();

        manager.beginTransaction().replace(R.id.contenedorFragmento, fragmento).commit();
    }

}
