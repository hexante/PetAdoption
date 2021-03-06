package com.example.petadoption.AccoutActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.petadoption.Firebase.UsuariosApp;
import com.example.petadoption.R;
import com.example.petadoption.TerminosCondiciones;
import com.example.petadoption.VistaFundacion.InterfazPrincipal;
import com.example.petadoption.VistaUsuario.InterfazPrincipalUsuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class InicioActivity extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private DatabaseReference ValidarUsuarios;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset,terminos;

    private CheckBox aceptarTerminos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        checkCameraPermission();
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
         final FirebaseUser userapp = FirebaseAuth.getInstance().getCurrentUser();



        if (auth.getCurrentUser() != null) {
            auth.signOut();
        }




        setContentView(R.layout.activity_inicio);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);

        terminos = (Button) findViewById(R.id.btnTerminos);
        aceptarTerminos = (CheckBox) findViewById(R.id.AceptarTerminos);





        terminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, TerminosCondiciones.class);
                startActivity(intent);
            }
        });

        ValidarUsuarios = FirebaseDatabase.getInstance().getReference();

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InicioActivity.this, RegitrarseActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InicioActivity.this, RecuperarContrasenaActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
            }
        });

        aceptarTerminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    btnLogin.setEnabled(true);

                }else{
                    btnLogin.setEnabled(false);
                }

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (aceptarTerminos.isChecked()) {
                    btnLogin.setEnabled(false);
                    String email = inputEmail.getText().toString();
                    final String password = inputPassword.getText().toString();

                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(getApplicationContext(), "Ingresa Correo!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(password)) {
                        Toast.makeText(getApplicationContext(), "Ingresa Contraseña!", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    //authenticate user
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(InicioActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    progressBar.setVisibility(View.VISIBLE);
                                    if (!task.isSuccessful()) {
                                        // there was an error
                                        if (password.length() < 6) {
                                            inputPassword.setError(getString(R.string.minimum_password));
                                        } else {
                                            Toast.makeText(InicioActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                        }
                                    } else {

                                        ValidarUsuarios.child("UsuariosApp").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                for ( DataSnapshot snapshot : dataSnapshot.getChildren()) {


                                                    UsuariosApp user = snapshot.getValue(UsuariosApp.class);
                                                    String Correo = user.getCorreo();
                                                    String TipoUsuario = user.getTipoUsuario();

                                                    if(inputEmail.getText().toString().equals(Correo)) {


                                                        if (TipoUsuario.equals("Usuario")) {
                                                            Intent intent = new Intent(InicioActivity.this, InterfazPrincipalUsuarios.class);
                                                            startActivity(intent);
                                                            finish();
                                                        } else if (TipoUsuario.equals("Fundacion")) {
                                                            Intent intent = new Intent(InicioActivity.this, InterfazPrincipal.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }else {

                                                        }
                                                    }


                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {


                                            }
                                        });

                                    }
                                }

                            });
                } else {
                    Toast.makeText(InicioActivity.this, "Por Favor Acepte Terminos Y Condiciones", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void checkCameraPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("mensaje", "No se tiene permiso para la camara !.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 225);
        } else {
            Log.i("mensaje", "Tienes permiso para usar la camara.");

        }

    }



}
