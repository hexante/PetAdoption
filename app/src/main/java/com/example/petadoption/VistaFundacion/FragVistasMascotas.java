package com.example.petadoption.VistaFundacion;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.example.petadoption.Firebase.MascotasApp;
import com.example.petadoption.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    FloatingActionButton BTF;
    RecyclerView mrecyclerView;
    FirebaseDatabase mfirebaseDatabase;
    private FirebaseRecyclerAdapter<MascotasApp, viewHolderMascFund> mPeopleRVAdapter;
    private GridLayoutManager glm;
    private RatingBar rt;

    private FirebaseAuth auth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_vistas_mascotas, container, false);

        auth = FirebaseAuth.getInstance();
        final FirebaseUser userapp = FirebaseAuth.getInstance().getCurrentUser();

        BTF = view.findViewById(R.id.botonflotante);
        /*rt = (RatingBar) view.findViewById(R.id.ratingBar2);
        LayerDrawable stars = ( LayerDrawable) rt.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);*/

        mrecyclerView = view.findViewById(R.id.Recycler1);
        glm = new GridLayoutManager(getActivity(), 2);
        mrecyclerView.setLayoutManager(glm);
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());


       // mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        BTF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CargarFragmentos(new FragReMascota());

            }
        });


        mrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {

                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    BTF.show();

                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                if(dy>0 || dy<0 && BTF.isShown()){
                    BTF.hide();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });


        mfirebaseDatabase = FirebaseDatabase.getInstance();
        //mRef = mfirebaseDatabase.getReference("MascotasApp").child("Perro");


        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("MascotasApp");
        Query personsQuery = mRef.orderByChild("idFundacion").equalTo(userapp.getEmail());
        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<MascotasApp>().setQuery(personsQuery, MascotasApp.class).build();



            mPeopleRVAdapter = new FirebaseRecyclerAdapter<MascotasApp, viewHolderMascFund>(personsOptions) {
                @Override
                protected void onBindViewHolder(@NonNull viewHolderMascFund holder, int position, @NonNull MascotasApp model) {

                    holder.SetDetail(model);

                    if(position%3!=0){
                        holder.itemView.setBackgroundColor(Color.rgb(165,214,167));
                    } else {
                        holder.itemView.setBackgroundColor(Color.rgb(129,199,132));
                    }

                }

                @NonNull
                @Override
                public viewHolderMascFund onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.now, viewGroup, false);
                    return new viewHolderMascFund(view);


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