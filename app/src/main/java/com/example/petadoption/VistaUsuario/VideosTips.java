package com.example.petadoption.VistaUsuario;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.petadoption.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideosTips extends Fragment {

    private TextView TituloTip, DescripcionTip;
    private Spinner Spin;
    private WebView browser;
    private ProgressBar progressBar;

    public VideosTips() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_videos_tips, container, false);

        Spin = (Spinner) view.findViewById(R.id.spinnertips);
        TituloTip = (TextView) view.findViewById(R.id.titulovideo);
        DescripcionTip = (TextView) view.findViewById(R.id.Descrivideo);
        // Definimos el webView
        browser = (WebView) view.findViewById(R.id.WVtip);

        //Habilitamos JavaScript
        browser.getSettings().setJavaScriptEnabled(true);

        //Habilitamos los botones de Zoom
        browser.getSettings().setBuiltInZoomControls(true);

        browser.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        // Cargamos la web
        browser.loadUrl("https://youtu.be/g31Dn3fbBuI");

        //Sincronizamos la barra de progreso de la web
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        browser.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setProgress(0);
                progressBar.setVisibility(View.VISIBLE);
                //VideosTips.this.set(progress * 1000);

                progressBar.incrementProgressBy(progress);

                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        return view;

    }

}





