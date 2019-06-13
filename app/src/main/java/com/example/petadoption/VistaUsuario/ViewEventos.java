package com.example.petadoption.VistaUsuario;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petadoption.Firebase.EventosFundaciones;
import com.example.petadoption.R;
import com.squareup.picasso.Picasso;

public class ViewEventos extends RecyclerView.ViewHolder{

    View mView;
    TextView NombreEventu,FechaEventu,DescripcionEventu,LugarEventu;
    ImageView FotoEventu;
    String Imagen;
    EventosFundaciones model;
    private Context context;

    public ViewEventos(@NonNull final View itemView) {
        super(itemView);

        mView = itemView;



        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = getAdapterPosition();

                // Snackbar.make(v, "Click detected on item " + position,
                //         Snackbar.LENGTH_LONG)
                //         .setAction("Action", null).show();
                //


            }
        });
    }

    public void SetDetail(EventosFundaciones modelo){

        model = modelo;

        NombreEventu = mView.findViewById(R.id.NombreEvento);
        FechaEventu = mView.findViewById(R.id.FechaEventoU);
        DescripcionEventu = mView.findViewById(R.id.DescripEventoU);
        LugarEventu = mView.findViewById(R.id.LugarEventoU);
        FotoEventu = mView.findViewById(R.id.FotoEventoU);

        Imagen = model.getFotoEvento();
        NombreEventu.setText(model.getNombreEvent());
        FechaEventu.setText(model.getFecha());
        DescripcionEventu.setText(model.getDescripcion());
        LugarEventu.setText(model.getLugar());

        Picasso.get().load(Imagen).into(FotoEventu);






    }
}
