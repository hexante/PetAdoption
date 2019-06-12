package com.example.petadoption.VistaFundacion;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petadoption.Firebase.TipsFundaciones;
import com.example.petadoption.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

/**
 * A simple {@link Fragment} subclass.
 */
public class CrearTipFundacion extends Fragment {

    private String Idtip, IdFundacion, Nombretip, Descricionptip, Autor, link;
    private Button Creartip, Cancelartip;
    private EditText NomTip, Descriptip, linktip;


    private FirebaseAuth auth;
    private DatabaseReference TipFundaciones;


    public CrearTipFundacion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crear_tip_fundacion, container, false);

        auth = FirebaseAuth.getInstance();
        TipFundaciones = FirebaseDatabase.getInstance().getReference("TipFundacion");
        FirebaseStorage storage = FirebaseStorage.getInstance();


        NomTip = (EditText) view.findViewById(R.id.txtNombretip);
        Descriptip = (EditText) view.findViewById(R.id.Descripciontip);
        linktip = (EditText) view.findViewById(R.id.linktips);

        Cancelartip = (Button) view.findViewById(R.id.Cancelartip);
        Creartip = (Button) view.findViewById(R.id.Creartip);


        Cancelartip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().remove(CrearTipFundacion.this).commit();
                Intent intent = new Intent(getActivity(), InterfazPrincipal.class);
                getActivity().startActivity(intent);
            }
        });


        Creartip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Idtip = TipFundaciones.push().getKey();
                IdFundacion = auth.getCurrentUser().getEmail();
                Autor = auth.getCurrentUser().getDisplayName();
                Nombretip = NomTip.getText().toString();
                Descricionptip = Descriptip.getText().toString();
                link = linktip.getText().toString();

                if (Nombretip.isEmpty()) {
                    Toast.makeText(getActivity(), "Por favor escribir nombre del tip ", Toast.LENGTH_LONG).show();
                } else if (Descricionptip.isEmpty()) {
                    Toast.makeText(getActivity(), "Por favor escribir Descripcion del tip ", Toast.LENGTH_LONG).show();

                } else {
                    TipsFundaciones tipFundaciones = new TipsFundaciones(Idtip, IdFundacion, Nombretip, Descricionptip, Autor, link);
                    TipFundaciones.child(Idtip).setValue(tipFundaciones);
                }
                Intent intent = new Intent(getActivity(), InterfazPrincipal.class);
                getActivity().startActivity(intent);


            }
        });

        return view;
    }


}


