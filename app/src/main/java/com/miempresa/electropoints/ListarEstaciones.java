package com.miempresa.electropoints;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ListarEstaciones extends AppCompatActivity {

    private FirebaseAuth miAutenticacion;
    Button btnIrMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_estaciones);
        miAutenticacion = FirebaseAuth.getInstance();
        btnIrMain = findViewById(R.id.button2);
        btnIrMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListarEstaciones.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioActual = miAutenticacion.getCurrentUser();
        if(usuarioActual == null){
            Intent i = new Intent(ListarEstaciones.this, ActividadLogin.class);
            startActivity(i);
        }

    }
}