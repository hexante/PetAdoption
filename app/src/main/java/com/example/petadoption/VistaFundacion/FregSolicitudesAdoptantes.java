package com.example.petadoption.VistaFundacion;


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
import android.widget.RatingBar;

import com.example.petadoption.Firebase.SolicitudMascotaApp;
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
public class FregSolicitudesAdoptantes extends Fragment {


    public FregSolicitudesAdoptantes() {
        // Required empty public constructor
    }

    RecyclerView mrecyclerView;
    FirebaseDatabase mfirebaseDatabase;
    private FirebaseRecyclerAdapter<SolicitudMascotaApp, ViewHolderSolicitudes> mPeopleRVAdapter;
    private GridLayoutManager glm;
    private RatingBar rt;

    private FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =inflater.inflate(R.layout.fragment_freg_solicitudes_adoptantes, container, false);


        auth = FirebaseAuth.getInstance();
        final FirebaseUser userapp = FirebaseAuth.getInstance().getCurrentUser();

        /*rt = (RatingBar) view.findViewById(R.id.ratingBar2);
        LayerDrawable stars = ( LayerDrawable) rt.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);*/

        mrecyclerView = view.findViewById(R.id.SolicitudesAdoptantes);
        glm = new GridLayoutManager(getActivity(), 2);
        mrecyclerView.setLayoutManager(glm);
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());


        // mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        mrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {

                if(newState==RecyclerView.SCROLL_STATE_IDLE){

                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                if(dy>0 || dy<0 ){
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });


        mfirebaseDatabase = FirebaseDatabase.getInstance();


        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("SolicitudMascotaApp");
        Query personsQuery = mRef.orderByChild("idFundacion").equalTo(userapp.getEmail());
        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<SolicitudMascotaApp>().setQuery(personsQuery, SolicitudMascotaApp.class).build();



        mPeopleRVAdapter = new FirebaseRecyclerAdapter<SolicitudMascotaApp, ViewHolderSolicitudes>(personsOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderSolicitudes holder, int position, @NonNull SolicitudMascotaApp model) {

                holder.SetDetail(model);

                if(position%3!=0){
                    holder.itemView.setBackgroundColor(Color.rgb(165,214,167));
                } else {
                    holder.itemView.setBackgroundColor(Color.rgb(129,199,132));
                }

            }

            @NonNull
            @Override
            public ViewHolderSolicitudes onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.now_solicitudes, viewGroup, false);
                return new ViewHolderSolicitudes(view);


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

    private void CargarFragmentos(Fragment fragmento) {

        FragmentManager manager = getFragmentManager();

        manager.beginTransaction().replace(R.id.contenedorFragmento, fragmento).commit();
    }


}


