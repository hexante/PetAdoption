package com.example.petadoption.VistaFundacion;


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

import com.example.petadoption.Firebase.TipsFundaciones;
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
public class ListaTipsFundaciones extends Fragment {

    RecyclerView evrecyclerView;
    FirebaseDatabase evfirebaseDatabase;
    private FirebaseRecyclerAdapter<TipsFundaciones, ViewHolderMisTip> evPeopleRVAdapter;
    private GridLayoutManager glm;
    private View.OnClickListener listener;



    public ListaTipsFundaciones() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_tips_fundaciones, container, false);

        evrecyclerView = view.findViewById(R.id.recyclertips);
        glm = new GridLayoutManager(getActivity(), 1);
        evrecyclerView.setLayoutManager(glm);
        evrecyclerView.setItemAnimator(new DefaultItemAnimator());

        final FirebaseUser userapp = FirebaseAuth.getInstance().getCurrentUser();

        evfirebaseDatabase = FirebaseDatabase.getInstance();


        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("TipFundacion");
        Query personsQuery = mRef.orderByKey();
        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<TipsFundaciones>().setQuery(personsQuery, TipsFundaciones.class).build();


        evPeopleRVAdapter = new FirebaseRecyclerAdapter<TipsFundaciones, ViewHolderMisTip>(personsOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderMisTip holder, int position, @NonNull TipsFundaciones model) {

                holder.SetDetail(model);


            }

            @NonNull
            @Override
            public ViewHolderMisTip onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.nowmistipfund, viewGroup, false);


                return new ViewHolderMisTip(view);


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

