package com.ldm.practica1_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class PuntuacionActivity extends AppCompatActivity implements View.OnClickListener {
    Button confirmar, primerActivity,anteriorActivity;
    TextView nombreVista;
    RadioButton verResultado;
    RadioButton reiniciar;
    int puntuacionResultado;
    int cantidadPreguntas;
    String nombre;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion);
        confirmar = findViewById(R.id.confirmacion);
        nombreVista = findViewById(R.id.nombreVista);
        verResultado = findViewById(R.id.opcion1);
        reiniciar = findViewById(R.id.opcion2);
        puntuacionResultado = getIntent().getIntExtra("puntuacion", 0);
        cantidadPreguntas = getIntent().getIntExtra("cantidadPreguntas", 0);
        nombre = getIntent().getStringExtra("nombreJugador");
        nombreVista.setText("Hola: " + nombre);
        confirmar.setOnClickListener(this);

    }

    public void onClick(View view) {
        Button botonPulsado = (Button) view;
        if (verResultado.isChecked() == true && botonPulsado != null) {
            String nota;
            if (puntuacionResultado > (cantidadPreguntas * 3 / 2)) {
                nota = "Aprobado";
            } else {
                nota = "Suspendido";
            }
            new AlertDialog.Builder(this)
                    .setTitle(nota)
                    .setMessage("Hola " + nombre + " tu puntuación es de " + puntuacionResultado + " puntos de " + cantidadPreguntas * 3 + " posibles")
                    .setPositiveButton("Cerrar", (dialogInterface, i) -> dialogInterface.dismiss())
                    .setCancelable(false)
                    .setNegativeButton("Nuevo Intento", ((dialogInterface, i) -> Anterior(anteriorActivity)))
                    .show();
        } else if (reiniciar.isChecked() == true && botonPulsado != null) {

            Primera(primerActivity);
        }

    }


    //Método para volver a la anterior activity
    public void Anterior(View view) {
        Intent anterior = new Intent(this, GameActivity.class);
        anterior.putExtra("nombre", nombre);
        startActivity(anterior);
    }

    public void Primera(View view) {
        Intent anterior = new Intent(this, LogInActivity.class);
        startActivity(anterior);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}