package com.example.petadoption.VistaUsuario;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petadoption.Firebase.MascotasPerdidasApp;
import com.example.petadoption.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaDeMisAnuncios extends Fragment {


    public ListaDeMisAnuncios() {
        // Required empty public constructor
    }

    RecyclerView bmrecyclerView;
    FirebaseDatabase bmfirebaseDatabase;
    private FirebaseRecyclerAdapter<MascotasPerdidasApp, MiAnuncioPerdida> bmPeopleRVAdapter;
    private GridLayoutManager glm;
    private View.OnClickListener listener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_de_mis_anuncios, container, false);
        bmrecyclerView = view.findViewById(R.id.MisAnunciosRecicler);
        glm = new GridLayoutManager(getActivity(), 1);
        bmrecyclerView.setLayoutManager(glm);
        bmrecyclerView.setItemAnimator(new DefaultItemAnimator());

        final FirebaseUser userapp = FirebaseAuth.getInstance().getCurrentUser();

        bmfirebaseDatabase = FirebaseDatabase.getInstance();


        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("MascotaPerdidaApp");
        Query personsQuery = mRef.orderByChild("usuario").equalTo(userapp.getEmail());
        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<MascotasPerdidasApp>().setQuery(personsQuery, MascotasPerdidasApp.class).build();


        bmPeopleRVAdapter = new FirebaseRecyclerAdapter<MascotasPerdidasApp, MiAnuncioPerdida>(personsOptions) {
            @Override
            protected void onBindViewHolder(@NonNull MiAnuncioPerdida holder, int position, @NonNull MascotasPerdidasApp model) {

                holder.SetDetail(model);


            }

            @NonNull
            @Override
            public MiAnuncioPerdida onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.nowmascotaperdida, viewGroup, false);


                return new MiAnuncioPerdida(view);


            }
        };
        bmrecyclerView.setAdapter(bmPeopleRVAdapter);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        bmPeopleRVAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        bmPeopleRVAdapter.stopListening();
    }

    private void CargarFragmentos(Fragment fragmento) {

        FragmentManager manager = getFragmentManager();

        manager.beginTransaction().replace(R.id.contenedorFragmento, fragmento).commit();
    }

}
