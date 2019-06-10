package com.example.petadoption.VistaFundacion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petadoption.Firebase.EventosFundaciones;
import com.example.petadoption.R;
import com.squareup.picasso.Picasso;

public class ViewHolderMisEventos extends RecyclerView.ViewHolder {

    View mView;
    TextView NombreEvent,FechaEvent,DescripcionEvent,LugarEvent,FotoEvento;
    ImageView FotoEvent;
    String Imagen;
    EventosFundaciones model;
    private Context context;

    public ViewHolderMisEventos(@NonNull final View itemView) {
        super(itemView);

        mView = itemView;



        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = getAdapterPosition();

                Snackbar.make(v, "Click detected on item " + position,
                        Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();



            }
        });
    }

    public void SetDetail(EventosFundaciones modelo){

        model = modelo;

        NombreEvent = mView.findViewById(R.id.NombreEvento);
        FechaEvent = mView.findViewById(R.id.FechaEvento);
        DescripcionEvent = mView.findViewById(R.id.DescripEvento);
        LugarEvent = mView.findViewById(R.id.LugarEvento);
        FotoEvent = mView.findViewById(R.id.FotoEvento);

        Imagen = model.getFotoEvento();
        NombreEvent.setText(model.getNombreEvent());
        FechaEvent.setText(model.getFecha());
        DescripcionEvent.setText(model.getDescripcion());
        LugarEvent.setText(model.getLugar());

        Picasso.get().load(Imagen).into(FotoEvent);






    }



}
