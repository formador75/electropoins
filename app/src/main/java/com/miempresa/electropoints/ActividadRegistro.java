package com.miempresa.electropoints;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActividadRegistro extends AppCompatActivity {

    EditText etEmail, etPassword, etPassword2;
    Button btnRegistrarse, btnIrLogin;
    private FirebaseAuth miAutenticacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_registro);
        etEmail = findViewById(R.id.etemailreg);
        etPassword = findViewById(R.id.etpasswordreg);
        etPassword2 = findViewById(R.id.etpasswordreg2);

        btnRegistrarse = findViewById(R.id.btnregistrarse);
        btnIrLogin = findViewById(R.id.btnirlogin);
        miAutenticacion = FirebaseAuth.getInstance();

        btnIrLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActividadRegistro.this, ActividadLogin.class);
                startActivity(i);
            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String password2 = etPassword2.getText().toString();

                if (password.equals(password2)){
                    registrar(email, password);
                }else{

                    Toast.makeText(ActividadRegistro.this, "los passwords no coinciden", Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioActual = miAutenticacion.getCurrentUser();
        actualizarUI(usuarioActual);
    }

    public void actualizarUI(FirebaseUser usuario){

        if(usuario != null){
            Intent i = new Intent(ActividadRegistro.this, MainActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(ActividadRegistro.this, "Ingrese sud datos de registro", Toast.LENGTH_LONG).show();
        }

    }


    public void registrar(String email,String password){

        miAutenticacion.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = miAutenticacion.getCurrentUser();
                            actualizarUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(ActividadRegistro.this, "Fallo el registro.",
                                    Toast.LENGTH_SHORT).show();
                            actualizarUI(null);
                        }
                    }
                });



    }
}