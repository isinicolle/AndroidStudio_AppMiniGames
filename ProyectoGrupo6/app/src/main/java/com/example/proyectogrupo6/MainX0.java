package com.example.proyectogrupo6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainX0 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_x0);

    }
    //Metodo para iniciar el juego x0
    public void iniciarjuego(View view)
    {
        Intent iniciarjuego = new Intent(this,JuegoX0.class);
        startActivity(iniciarjuego);
    }

    //Metodo para salir
    public void regresar(View view)
    {
        finish();
        Intent regresar = new Intent(this,MainActivity.class);
        startActivity(regresar);
    }

}