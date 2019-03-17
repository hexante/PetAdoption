package com.example.petadoption.AccoutActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.petadoption.R;

public class RegistroDatosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_datos);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] TipoDocumento = {"Nit","CC","TI"};
        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.activity_registro_datos, TipoDocumento));




    }
}
