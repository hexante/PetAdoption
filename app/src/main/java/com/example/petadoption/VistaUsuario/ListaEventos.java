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

import com.example.petadoption.Firebase.EventosFundaciones;
import com.example.petadoption.R;
import com.example.petadoption.VistaFundacion.ViewHolderMisEventos;
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
public class ListaEventos extends Fragment {


    public ListaEventos() {
        // Required empty public constructor
    }

    RecyclerView evrecyclerView;
    FirebaseDatabase evfirebaseDatabase;
    private FirebaseRecyclerAdapter<EventosFundaciones, ViewEventos> evPeopleRVAdapter;
    private GridLayoutManager glm;
    private View.OnClickListener listener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_eventos, container, false);

        evrecyclerView = view.findViewById(R.id.RecyclerEventos);
        glm = new GridLayoutManager(getActivity(), 1);
        evrecyclerView.setLayoutManager(glm);
        evrecyclerView.setItemAnimator(new DefaultItemAnimator());

        final FirebaseUser userapp = FirebaseAuth.getInstance().getCurrentUser();

        evfirebaseDatabase = FirebaseDatabase.getInstance();


        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("EventoFundaciones");
        Query personsQuery = mRef.orderByKey();
        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<EventosFundaciones>().setQuery(personsQuery, EventosFundaciones.class).build();


        evPeopleRVAdapter = new FirebaseRecyclerAdapter<EventosFundaciones, ViewEventos>(personsOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ViewEventos holder, int position, @NonNull EventosFundaciones model) {

                holder.SetDetail(model);


            }

            @NonNull
            @Override
            public ViewEventos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.noweventos, viewGroup, false);


                return new ViewEventos(view);


            }
        };
        evrecyclerView.setAdapter(evPeopleRVAdapter);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        evPeopleRVAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        evPeopleRVAdapter.stopListening();
    }

    private void CargarFragmentos(Fragment fragmento) {

        FragmentManager manager = getFragmentManager();

        manager.beginTransaction().replace(R.id.contenedorFragmento, fragmento).commit();
    }

}


