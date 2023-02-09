package com.ldm.practica1_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    int indicePreguntaActual = 0, puntuacionResultado = 0, preguntasRestantes = PreguntasRespuestas.preguntas.length, cantidadPreguntas = PreguntasRespuestas.preguntas.length;
    String respuestaSeleccionada = "", nombre ;
    TextView preguntasTotales, pregunta, puntaje, nombreJugador;
    Button res1, res2, res3, res4, confirmarBtn, verImagen, cerrarImagen, ultimoBotonPulsado;
    boolean visible = false;
    ImageView imagenNadal, imagenFederer, imagenMurray, imagenDjockovic, imagenMckenrow, imagenMoya, toni, borj;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        nombre = getIntent().getStringExtra("nombre");

        imagenNadal = findViewById(R.id.nadal);
        imagenFederer = findViewById(R.id.federer);
        imagenMurray = findViewById(R.id.murray);
        imagenDjockovic = findViewById(R.id.djockovic);
        imagenMckenrow = findViewById(R.id.Mckerrow);
        imagenMoya = findViewById(R.id.Moya);
        toni = findViewById(R.id.Toni);
        borj = findViewById(R.id.borj);

        // Con esto tenemos acceso a todos los elementos creados en el  layout
        preguntasTotales = findViewById(R.id.preguntas_totales);
        pregunta = findViewById(R.id.Pregunta);
        nombreJugador = findViewById(R.id.jugador_actual);
        res1 = findViewById(R.id.Res1);
        res2 = findViewById(R.id.Res2);
        res3 = findViewById(R.id.Res3);
        res4 = findViewById(R.id.Res4);
        confirmarBtn = findViewById(R.id.Confirmar);
        verImagen = findViewById(R.id.VerImagen);
        cerrarImagen = findViewById(R.id.CerrarImagen);
        puntaje = findViewById(R.id.puntuacion);

        res1.setOnClickListener(this);
        res2.setOnClickListener(this);
        res3.setOnClickListener(this);
        res4.setOnClickListener(this);
        confirmarBtn.setOnClickListener(this);
        verImagen.setOnClickListener(this);
        cerrarImagen.setOnClickListener(this);


    }

    public void onStart() {
        super.onStart();
        //Actualizamos cantidad de preguntas totales
        preguntasTotales.setText("Preguntas Restantes: " + cantidadPreguntas);
        //Actualización de la puntuación
        puntaje.setText("Puntuación: " + puntuacionResultado);
        nombreJugador.setText("Nombre: " + nombre);
        nuevaPregunta();
    }


    @Override
    public void onClick(View view) {
        Button botonPulsado = (Button) view;

        if (botonPulsado.getId() == R.id.Confirmar && visible == true) {
            Toast.makeText(this, "Cierre primero la imagen", Toast.LENGTH_SHORT).show();

        } else if (botonPulsado.getId() == R.id.Confirmar && ultimoBotonPulsado == null) {
            Toast.makeText(this, "Seleccione una opción", Toast.LENGTH_SHORT).show();
        }

        //Si entramos aquí significa que el usuario ha pulsado en confirmar
        else if (botonPulsado.getId() == R.id.Confirmar && ultimoBotonPulsado != null) {

            if (respuestaSeleccionada.equals(PreguntasRespuestas.respuestas[indicePreguntaActual])) {
                layoutCorrect(ultimoBotonPulsado);

            } else {

                if (puntuacionResultado != 0 && puntuacionResultado > 1) {
                    puntuacionResultado -= 2;

                } else if (puntuacionResultado == 1) {
                    puntuacionResultado -= 1;
                }

                layoutWrong(ultimoBotonPulsado);
            }

        } else if (botonPulsado.getId() == R.id.VerImagen && ultimoBotonPulsado != null) {

            if (indicePreguntaActual == 0) {
                controlImagenes1(botonPulsado, ultimoBotonPulsado);
            }
            if (indicePreguntaActual > 0 && indicePreguntaActual < 4) {
                Toast.makeText(this, "No hay imágenes disponibles en esta pregunta", Toast.LENGTH_SHORT).show();
            }
            if (indicePreguntaActual == 4) {
                controlImagenes1(botonPulsado, ultimoBotonPulsado);
            }


        } else if (botonPulsado.getId() == R.id.VerImagen && visible == true) {
            Toast.makeText(this, "La imagen ya está abierta", Toast.LENGTH_SHORT).show();


        } else if (botonPulsado.getId() == R.id.CerrarImagen && visible == false) {
            Toast.makeText(this, "Ninguna Imagen Abierta", Toast.LENGTH_SHORT).show();


        } else if (botonPulsado.getId() == R.id.VerImagen && ultimoBotonPulsado == null) {
            Toast.makeText(this, "Seleccione una opción", Toast.LENGTH_SHORT).show();

        } else if (botonPulsado.getId() == R.id.CerrarImagen && visible == true) {

            controlImagenes1(botonPulsado, ultimoBotonPulsado);


        } else if (botonPulsado.getId() != R.id.Confirmar && botonPulsado.getId() != R.id.VerImagen && botonPulsado.getId() != R.id.CerrarImagen) {
            //Si entramos aquí significa que el usuario ha pulsado alguna opción de respuesta
            ultimoBotonPulsado = botonPulsado;
            respuestaSeleccionada = botonPulsado.getText().toString();
            layoutEleccion(botonPulsado);

        }

    }

    void controlImagenes1(Button botonPulsado, Button ultimoBotonPulsado) {

        if (visible && botonPulsado.getId() == R.id.CerrarImagen) {
            switch (ultimoBotonPulsado.getText().toString()) {
                case "Rafael Nadal":
                    imagenNadal.setVisibility(View.INVISIBLE);
                    visible = false;
                    break;
                case "Roger Federer":
                    imagenFederer.setVisibility(View.INVISIBLE);
                    visible = false;
                    break;
                case "Andy Murray":
                    imagenMurray.setVisibility(View.INVISIBLE);
                    visible = false;
                    break;
                case "Novak Djockovic":
                    imagenDjockovic.setVisibility(View.INVISIBLE);
                    visible = false;
                    break;
                case "John mckerrow":
                    imagenMckenrow.setVisibility(View.INVISIBLE);
                    visible = false;
                    break;
                case "Carlos Moyá":
                    imagenMoya.setVisibility(View.INVISIBLE);
                    visible = false;
                    break;
                case "Toni Nadal":
                    toni.setVisibility(View.INVISIBLE);
                    visible = false;
                    break;
                case "John borg":
                    borj.setVisibility(View.INVISIBLE);
                    visible = false;
                    break;

            }

        } else if (!visible && botonPulsado.getId() == R.id.VerImagen) {
            switch (ultimoBotonPulsado.getText().toString()) {
                case "Rafael Nadal":
                    imagenNadal.setVisibility(View.VISIBLE);
                    visible = true;
                    break;
                case "Roger Federer":
                    imagenFederer.setVisibility(View.VISIBLE);
                    visible = true;
                    break;
                case "Andy Murray":
                    imagenMurray.setVisibility(View.VISIBLE);
                    visible = true;
                    break;
                case "Novak Djockovic":
                    imagenDjockovic.setVisibility(View.VISIBLE);
                    visible = true;
                    break;
                case "John mckerrow":
                    imagenMckenrow.setVisibility(View.VISIBLE);
                    visible = true;
                    break;
                case "Carlos Moyá":
                    imagenMoya.setVisibility(View.VISIBLE);
                    visible = true;
                    break;
                case "Toni Nadal":
                    toni.setVisibility(View.VISIBLE);
                    visible = true;
                    break;
                case "John borg":
                    borj.setVisibility(View.VISIBLE);
                    visible = true;
                    break;
            }
        }
    }

    void layoutEleccion(Button boton) {
        reiniciarColores();
        boton.setBackgroundColor(Color.MAGENTA);
    }

    void layoutWrong(Button ultimoOpcionPulsada) {
        indicePreguntaActual++;
        ultimoOpcionPulsada.setBackgroundColor(Color.RED);
        ultimoBotonPulsado = null;
        if (indicePreguntaActual == cantidadPreguntas) {
            String nota = "Incorrecto";
            new AlertDialog.Builder(this)
                    .setTitle(nota)
                    .setMessage("¿Desea Continuar?")
                    .setPositiveButton("Reiniciar", (dialogInterface, i) -> restablecerQuiz())
                    .setCancelable(false)
                    .setNegativeButton("Continuar", ((dialogInterface, i) -> SiguienteActivity()))
                    .show();
        } else {
            AlertDialogIncorrect();
        }
    }

    void SiguienteActivity() {
        Intent puntuacionMostrar = new Intent(this, PuntuacionActivity.class);
        puntuacionMostrar.putExtra("puntuacion", puntuacionResultado); //Pasamos el parámetro de la puntuación al segundo activity
        puntuacionMostrar.putExtra("cantidadPreguntas", cantidadPreguntas);
        puntuacionMostrar.putExtra("nombreJugador", nombre);
        startActivity(puntuacionMostrar);
        restablecerQuiz();
    }

    void layoutCorrect(Button ultimaOpcionPulsada) {
        ultimaOpcionPulsada.setBackgroundColor(Color.GREEN);
        ultimoBotonPulsado = null; //Para que no se quede guardado el último marcado
        puntuacionResultado += 3;
        indicePreguntaActual++; //Aumentamos el indice para mostrar posteriormente la siguiente pregunta
        if (indicePreguntaActual == cantidadPreguntas) {
            String nota = "Respuesta Correcta";
            new AlertDialog.Builder(this)
                    .setTitle(nota)
                    .setMessage("Pulse para continuar")
                    .setPositiveButton("Continuar", (dialogInterface, i) -> SiguienteActivity())
                    .setCancelable(false)
                    .show();

        } else {
            AlertDialogCorrect();
        }
    }


    void AlertDialogIncorrect() {
        String nota = "Incorrecto";
        new AlertDialog.Builder(this)
                .setTitle(nota)
                .setMessage("¿Desea Continuar?")
                .setPositiveButton("Reiniciar", (dialogInterface, i) -> restablecerQuiz())
                .setCancelable(false)
                .setNegativeButton("Continuar", ((dialogInterface, i) -> nuevaPregunta()))
                .show();
    }

    void AlertDialogCorrect() {
        String nota = "Respuesta Correcta";
        new AlertDialog.Builder(this)
                .setTitle(nota)
                .setMessage("Pulse para continuar")
                .setPositiveButton("Continuar", (dialogInterface, i) -> nuevaPregunta())
                .setCancelable(false)
                .show();
    }

    void nuevaPregunta() {

        reiniciarColores();
        //Actualizamos dinámicamente la pregunta junto con las respuestas
        pregunta.setText(PreguntasRespuestas.preguntas[indicePreguntaActual]);
        res1.setText(PreguntasRespuestas.opciones[indicePreguntaActual][0]);
        res2.setText(PreguntasRespuestas.opciones[indicePreguntaActual][1]);
        res3.setText(PreguntasRespuestas.opciones[indicePreguntaActual][2]);
        res4.setText(PreguntasRespuestas.opciones[indicePreguntaActual][3]);

        puntaje.setText("Puntuación: " + puntuacionResultado);

        if (indicePreguntaActual != 0) {
            preguntasRestantes -= 1;
            //Actualizamos cantidad de preguntas totales
            preguntasTotales.setText("Preguntas Restantes: " + preguntasRestantes);
        }else{
            preguntasTotales.setText("Preguntas Restantes: " + cantidadPreguntas);
        }
    }

    void reiniciarColores() {
        res1.setBackgroundColor(Color.WHITE);
        res2.setBackgroundColor(Color.WHITE);
        res3.setBackgroundColor(Color.WHITE);
        res4.setBackgroundColor(Color.WHITE);

    }


    void restablecerQuiz() {
        cantidadPreguntas = PreguntasRespuestas.preguntas.length;
        preguntasRestantes = PreguntasRespuestas.preguntas.length;
        puntuacionResultado = 0;
        indicePreguntaActual = 0;
        reiniciarColores();
        nuevaPregunta();
    }


}