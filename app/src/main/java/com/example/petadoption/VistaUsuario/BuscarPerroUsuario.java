package com.example.petadoption.VistaUsuario;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.petadoption.Firebase.MascotasApp;
import com.example.petadoption.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuscarPerroUsuario extends Fragment {


    public BuscarPerroUsuario() {
        // Required empty public constructor
    }

    RecyclerView bmrecyclerView;
    FirebaseDatabase bmfirebaseDatabase;
    private FirebaseRecyclerAdapter<MascotasApp, viewHolder> bmPeopleRVAdapter;
    private GridLayoutManager glm;
    private View.OnClickListener listener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buscar_perro_usuario, container, false);
        bmrecyclerView = view.findViewById(R.id.Recycler3);
        glm = new GridLayoutManager(getActivity(), 2);
        bmrecyclerView.setLayoutManager(glm);
        bmrecyclerView.setItemAnimator(new DefaultItemAnimator());


        bmfirebaseDatabase = FirebaseDatabase.getInstance();


        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("MascotasApp");
        Query personsQuery = mRef.orderByChild("tipo").equalTo("Perro");


        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<MascotasApp>().setQuery(personsQuery, MascotasApp.class).build();


        bmPeopleRVAdapter = new FirebaseRecyclerAdapter<MascotasApp, viewHolder>(personsOptions) {
            @Override
            protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull MascotasApp model) {

                holder.SetDetail(model);

                if (position % 3 != 0) {
                    holder.itemView.setBackgroundColor(Color.rgb(165, 214, 167));
                } else {
                    holder.itemView.setBackgroundColor(Color.rgb(129, 199, 132));
                }

            }

            @NonNull
            @Override
            public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.now, viewGroup, false);


                return new viewHolder(view);


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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                Log.i("ActionBar", "Atrás!");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
