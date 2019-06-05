package com.example.petadoption;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static com.facebook.GraphRequest.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frag_acerca_nosotros.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class frag_acerca_nosotros extends Fragment {
    private Button Terminos_Condiciones, BotonFacebook,BotonWhatsapp,
            BotonFacebookRedes,BotonInstagramRedes;

    public frag_acerca_nosotros() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_acerca_de_nosotros, container, false);


        Terminos_Condiciones = (Button) view.findViewById(R.id.btn_terminos_condiciones);

        Terminos_Condiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargarFragmentos(new VerTerminosCondiciones());
            }
        });

        //Redes sociales

        BotonFacebookRedes=(Button) view.findViewById(R.id.btn_facebook_redes);
        BotonInstagramRedes=(Button) view.findViewById(R.id.btn_instagram_redes);
        BotonFacebookRedes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String facebookId = "fb://page/188657535383918"; //*Los signos < y > no van!
                String urlPage = "http://www.facebook.com/LoveAdoption";

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookId )));
                } catch (Exception e) {
                    Log.e(TAG, "Aplicación no instalada.");
                    //Abre url de pagina.
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)));
                }
            }
        });
        BotonInstagramRedes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        String InstagramID = "http://instagram.com/adopcionamorosa"; //*Los signos < y > no van!
        String urlPage = "http://instagram.com/adopcionamorosa";

        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(InstagramID )));
        } catch (Exception e) {
            Log.e(TAG, "Aplicación no instalada.");
            //Abre url de pagina.
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)));
        }
            }
        });


        //Botones compartir

        BotonFacebook=(Button) view.findViewById(R.id.btn_facebook_compartir);
        BotonWhatsapp=(Button) view.findViewById(R.id.btn_whatsapp_compartir);

        BotonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "La mejor forma de adoptar es la adopciòn responsable http://www.facebook.com/LoveAdoption-188657535383918/");
                startActivity(Intent.createChooser(intent, "Share with"));
                //Abrimos la Url que deseamos ver
                intent.setPackage("https://www.facebook.com");
                startActivity(intent);
            }
        });

        BotonWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "La mejor forma de adoptar es la adopciòn responsable http://www.facebook.com/LoveAdoption-188657535383918/");
                startActivity(Intent.createChooser(intent, "Share with"));
                //Abrimos la Url que deseamos ver
                intent.setPackage("https://www.facebook.com");
                startActivity(intent);
            }
        });
        return view;


    }



    // TODO: Rename method, update argument and hook method into UI event




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void CargarFragmentos(Fragment fragmento) {

        FragmentManager manager;
        manager = getFragmentManager();

        manager.beginTransaction().replace(R.id.contenedorFragmento, fragmento).commit();
    }
}
