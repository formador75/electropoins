package com.miempresa.electropoints;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActividadLogin extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnIngresar, btnIrRegistro;
    private FirebaseAuth miAutenticacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_login);
        etEmail = findViewById(R.id.etemail);
        etPassword = findViewById(R.id.etpassword);
        btnIngresar = findViewById(R.id.btningresar);
        btnIrRegistro = findViewById(R.id.btnirregistro);
        miAutenticacion = FirebaseAuth.getInstance();

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //codigo para ingresar
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();


                ingresar(email, password);

            }
        });

        btnIrRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActividadLogin.this, ActividadRegistro.class);
                startActivity(i);
            }
        });
    }

    public void ingresar(String email, String password){

        miAutenticacion.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){



                        }else{


                        }



                    }
                });


    }

}