package com.ldm.practica1_quiz;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nombreUsuario;
    Button botonComenzar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        nombreUsuario = findViewById(R.id.editTextTextPersonName);
        botonComenzar = findViewById(R.id.comienzo);
        botonComenzar.setOnClickListener(this);

    }


    public void onClick(View view) {
        if (view.getId() == R.id.comienzo && !nombreUsuario.getText().toString().isEmpty()) {
            Intent nombreUser = new Intent(this, GameActivity.class);
            nombreUser.putExtra("nombre", nombreUsuario.getText().toString());
            startActivity(nombreUser);
        }else{
            Toast.makeText(this,"Introduzca un nombre", Toast.LENGTH_SHORT).show();


        }
    }
}