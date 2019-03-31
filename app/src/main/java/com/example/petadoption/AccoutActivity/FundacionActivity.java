package com.example.petadoption.AccoutActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.petadoption.R;
import com.google.firebase.auth.FirebaseAuth;

public class FundacionActivity extends AppCompatActivity {

    private Button btnRegistar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundacion);

        auth = FirebaseAuth.getInstance();

        btnRegistar = (Button) findViewById(R.id.btnRegistrar);

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FundacionActivity.this, DatosMascotas.class));

            }
        });



    }
}
