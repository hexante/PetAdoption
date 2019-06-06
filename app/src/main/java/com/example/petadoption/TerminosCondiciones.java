package com.example.petadoption;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TerminosCondiciones extends AppCompatActivity {

    WebView terminosycondiciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminos_condiciones);

        terminosycondiciones = (WebView) findViewById(R.id.TerminosCondiciones);

        terminosycondiciones.loadUrl("file:///android_asset/index.html");

        terminosycondiciones.setWebViewClient( new WebViewClient());



    }
}
