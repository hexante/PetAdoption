package com.example.petadoption.AccoutActivity.Fragmentos;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petadoption.AccoutActivity.viewHolder;
import com.example.petadoption.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragVistasMascotas extends Fragment {


    public FragVistasMascotas() {
        // Required empty public constructor
    }

    RecyclerView mrecyclerView;
    FirebaseDatabase mfirebaseDatabase;
    private FirebaseRecyclerAdapter<MascotasApp, viewHolder> mPeopleRVAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_vistas_mascotas, container, false);

        mrecyclerView = view.findViewById(R.id.Recycler1);
        mrecyclerView.setHasFixedSize(true);


        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mfirebaseDatabase = FirebaseDatabase.getInstance();
        //mRef = mfirebaseDatabase.getReference("MascotasApp").child("Perro");


        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("MascotasApp");
        Query personsQuery = mRef.orderByValue();
        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<MascotasApp>().setQuery(personsQuery, MascotasApp.class).build();



            mPeopleRVAdapter = new FirebaseRecyclerAdapter<MascotasApp, viewHolder>(personsOptions) {
                @Override
                protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull MascotasApp model) {

                    holder.SetDetail(getActivity(), model.getRaza(), model.getEdad(), model.getDescripLesion(), model.getImagen());

                }

                @NonNull
                @Override
                public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.now, viewGroup, false);
                    return new viewHolder(view);


                }
            };
            mrecyclerView.setAdapter(mPeopleRVAdapter);


        return view;
    }
    @Override
    public void onStart () {
        super.onStart();
        mPeopleRVAdapter.startListening();
    }

    @Override
    public void onStop () {
        super.onStop();
        mPeopleRVAdapter.stopListening();
    }

}