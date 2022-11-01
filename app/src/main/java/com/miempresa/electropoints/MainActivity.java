package com.miempresa.electropoints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btnLogin, btnListar;
    private FirebaseAuth miAutenticacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.button);
        btnListar = findViewById(R.id.button3);

        miAutenticacion = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miAutenticacion.signOut();
                Intent i = new Intent(MainActivity.this, ActividadLogin.class);
                startActivity(i);
            }
        });



    }


    public void irListar(View view){
        Intent i = new Intent(MainActivity.this, ListarEstaciones.class);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioActual = miAutenticacion.getCurrentUser();
        actualizarUI(usuarioActual);
    }





    public void actualizarUI(FirebaseUser usuario){

        if(usuario != null){
            Toast.makeText(MainActivity.this, "Usted esta logeado", Toast.LENGTH_LONG).show();
        }else{
            Intent i = new Intent(MainActivity.this, ActividadLogin.class);
            startActivity(i);
        }

    }
}