package com.example.petadoption.VistaFundacion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petadoption.Firebase.EventosFundaciones;
import com.example.petadoption.Firebase.TipsFundaciones;
import com.example.petadoption.R;
import com.squareup.picasso.Picasso;

public class ViewHolderMisTip extends RecyclerView.ViewHolder {

    View mView;
    TextView NombreTip,Descripciontip,Autor;
    ImageView fototip;
    String Imagen;
   TipsFundaciones model;
    private Context context;

    public ViewHolderMisTip(@NonNull final View itemView) {
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

    public void SetDetail(TipsFundaciones modelo){

        model = modelo;

        NombreTip = mView.findViewById(R.id.txtTTip);
        Descripciontip = mView.findViewById(R.id.txtdesTip);
        Autor = mView.findViewById(R.id.Autip);
        fototip = mView.findViewById(R.id.imaTip);

        Imagen = model.getImagentip();
        NombreTip.setText(model.getNombretip());
        Descripciontip.setText(model.getDescripciontip());
        Autor.setText(model.getAutor());


        Picasso.get().load(Imagen).into(fototip);






    }

}
