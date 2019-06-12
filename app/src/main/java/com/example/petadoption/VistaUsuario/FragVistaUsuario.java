package com.example.petadoption.VistaUsuario;


import android.graphics.Color;
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

import com.example.petadoption.R;
import com.example.petadoption.Firebase.UsuariosApp;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragVistaUsuario extends Fragment {


    public FragVistaUsuario() {
        // Required empty public constructor
    }

    RecyclerView urecyclerView;
    FirebaseDatabase mfirebaseDatabase;
    private FirebaseRecyclerAdapter<UsuariosApp, viewHolderUsers> uPeopleRVAdapter;
    private GridLayoutManager glm;
    private UsuariosApp model2;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_vista_usuario, container, false);


        urecyclerView = view.findViewById(R.id.Recycler2);
        glm = new GridLayoutManager(getActivity(), 2);
        urecyclerView.setLayoutManager(glm);
        urecyclerView.setItemAnimator(new DefaultItemAnimator());

        mfirebaseDatabase = FirebaseDatabase.getInstance();
        //mRef = mfirebaseDatabase.getReference("MascotasApp").child("Perro");


        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("UsuariosApp");
        Query personsQuery = mRef.orderByChild("tipoUsuario").equalTo("Fundacion");
        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<UsuariosApp>().setQuery(personsQuery,UsuariosApp.class).build();


        uPeopleRVAdapter = new FirebaseRecyclerAdapter<UsuariosApp, viewHolderUsers>(personsOptions) {
            @NonNull
            @Override
            public  viewHolderUsers onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.nowusers, viewGroup, false);
                return new viewHolderUsers(view);


            }

            @Override
            protected void onBindViewHolder(@NonNull viewHolderUsers holder, int position, @NonNull UsuariosApp model) {

                model2 = model;

                    holder.SetDetail(model);

                if(position%3!=0){
                    holder.itemView.setBackgroundColor(Color.rgb(165,214,167));
                } else {
                    holder.itemView.setBackgroundColor(Color.rgb(129,199,132));
                }



            }

        };
        urecyclerView.setAdapter(uPeopleRVAdapter);
        return view;
    }

    @Override
    public void onStart () {
        super.onStart();
        uPeopleRVAdapter.startListening();
    }

    @Override
    public void onStop () {
        super.onStop();
        uPeopleRVAdapter.stopListening();
    }

    private void CargarFragmentos(Fragment fragmento) {

        FragmentManager manager = getFragmentManager();

        manager.beginTransaction().replace(R.id.contenedorFragmento, fragmento).commit();
    }

}