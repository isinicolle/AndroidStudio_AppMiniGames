package com.example.proyectogrupo6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Metodo para el boton de flappy bird
    public void boton_flapp(View view)
    {
        Intent boton_flapp = new Intent(this,MainFlappy.class);
        startActivity(boton_flapp);
    }

    //Metodo para el boton de X0
    public void boton_x_0(View view)
    {
        Intent boton_x_0 = new Intent(this,MainX0.class);
        startActivity(boton_x_0);
    }

    //Metodo para el boton de ahorcado
    public void boton_aho(View view)
    {
        Intent boton_aho = new Intent(this,MainAhorcado.class);
        startActivity(boton_aho);
    }
}