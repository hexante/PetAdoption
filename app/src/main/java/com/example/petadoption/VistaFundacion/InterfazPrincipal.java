package com.example.petadoption.VistaFundacion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.petadoption.AccoutActivity.InicioActivity;
import com.example.petadoption.AcercaDeNosotros;
import com.example.petadoption.Firebase.UsuariosApp;
import com.example.petadoption.MiCuenta;
import com.example.petadoption.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class InterfazPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private StorageReference storageRef;
    private DatabaseReference ValidarUsuarios;
    private FirebaseAuth auth;

    private String CorreoUsuario,IDusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        auth = FirebaseAuth.getInstance();
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        ValidarUsuarios = FirebaseDatabase.getInstance().getReference();

        CorreoUsuario = auth.getCurrentUser().getEmail();

        Log.e("Correo","" + CorreoUsuario);



        final NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view_fundacion);
        final View headerLayout = mNavigationView.getHeaderView(0);

        final ImageView fotoPerfil = headerLayout.findViewById(R.id.FotoPerfilPrincipalFundacion);
        final TextView nombre = headerLayout.findViewById(R.id.NombrePrincipalFundacion);
        final TextView correo = headerLayout.findViewById(R.id.CorreoPrincipalFundacion);


        ValidarUsuarios.child("UsuariosApp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for ( DataSnapshot snapshot : dataSnapshot.getChildren()) {


                    UsuariosApp user = snapshot.getValue(UsuariosApp.class);
                    final String Correo = user.getCorreo();
                    IDusuario = user.getIdUsuario();
                    final String NombreUsuario = user.getNombres();
                    final String ApelldoUsuario= user.getApellidos();


                    if(CorreoUsuario.equals(Correo)) {

                        Log.e("IdUsuario: ", "" + IDusuario);
//                        Log.e("Datos: ", "" + snapshot.getValue());

                        storageRef.child(" FotosUsuarios/"+IDusuario+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.e("uri",""+uri);

                                Glide.with(getApplicationContext())
                                        .load(uri)
                                        .into(fotoPerfil);
                                nombre.setText(NombreUsuario+" "+ApelldoUsuario);
                                correo.setText(Correo);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getBaseContext(),"Hubo un error",Toast.LENGTH_LONG);
                            }
                        });



                    }



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {



            }
        });








        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedorFragmento,new FragMenuFundacion()).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_fundacion);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.interfaz_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Desconectar) {

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(InterfazPrincipal.this, InicioActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            CargarFragmentos(new FragMenuFundacion());
        } else if (id == R.id.nav_gallery) {
            CargarFragmentos(new MiCuenta());
        }
        else if (id == R.id.nav_slideshow)  {
            CargarFragmentos(new AcercaDeNosotros());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void CargarFragmentos(Fragment fragmento) {

        FragmentManager manager = getSupportFragmentManager();

        manager.beginTransaction().replace(R.id.contenedorFragmento, fragmento).commit();
    }

    //***Traer Correo Y nombre de la base de datos****



}
