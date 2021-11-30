package com.example.proyectogrupo6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class JuegoX0 extends AppCompatActivity {

    TextView textoVictoria;
    Button Reiniciar;
    Integer[] botones;
    int[] tablero = new int[]{
            0, 0, 0,
            0, 0, 0,
            0, 0, 0
    };
    int estado = 0;
    int fichasPuestas = 0;
    int turno = 1;
    int[] posGanadora = new int[]{-1, -1,-1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_x0);

        textoVictoria = (TextView) findViewById(R.id.textoVictoria);
        textoVictoria.setVisibility(View.INVISIBLE);

        Reiniciar = (Button) findViewById(R.id.btnReiniciar);
        Reiniciar.setVisibility(View.INVISIBLE);

        botones = new Integer[]{
                R.id.b1, R.id.b2, R.id.b3,
                R.id.b4, R.id.b5, R.id.b6,
                R.id.b7, R.id.b8, R.id.b9,
        };
//
    }

    public void ponerFicha(View v) {
        if (estado == 0) {
            turno = 1;

            int numBoton = Arrays.asList(botones).indexOf(v.getId());
            if (tablero[numBoton] == 0) {
                v.setBackgroundResource(R.drawable.x);
                tablero[numBoton] = 1;
                fichasPuestas += 1;
                estado = comprobarEstado();
                terminarPartida();
                //
                if (estado == 0) {
                    turno = -1;
                    ia();

                    fichasPuestas += 1;
                    estado = comprobarEstado();
                    terminarPartida();
                }
            }
        }
    }
    //
    public void terminarPartida(){
        int fichavictoria;

        if(estado == 1 || estado == -1)
        {
            if(estado == 1) {
                textoVictoria.setVisibility(View.VISIBLE);
                textoVictoria.setText("Has Ganado");
                textoVictoria.setTextColor(Color.BLACK);
                fichavictoria = R.drawable.xvictoria;
                Reiniciar.setVisibility(View.VISIBLE);
            }
            else{
                textoVictoria. setVisibility(View.VISIBLE);
                textoVictoria.setText("Has perdido");
                textoVictoria. setTextColor (Color.BLACK);
                fichavictoria = R.drawable.novictoria;
                Reiniciar.setVisibility(View.VISIBLE);
            }
            //
                for (int i = 0; i<posGanadora.length; i++)
                {
                    Button b = findViewById (botones[posGanadora[i]]);
                    b.setBackgroundResource (fichavictoria);
                }
        }
//
        else if(estado == 2)
        {
            textoVictoria.setVisibility(View.VISIBLE);
            textoVictoria.setText("Has Empatado");
            textoVictoria. setTextColor (Color.BLACK);
            Reiniciar.setVisibility(View.VISIBLE);
        }

    }
    //
    public void ia()
    {
        Random ran = new Random();
        int pos = ran.nextInt(tablero.length);
        while(tablero[pos] != 0){
            pos = ran.nextInt (tablero.length);
        }
        Button b = (Button) findViewById(botones[pos]);
        b.setBackgroundResource(R.drawable.novictoria);
        tablero[pos] = -1;
    }
//
    public int comprobarEstado()
    {
        int nuevoEstado = 0;
        //Filas
        if(Math.abs(tablero[0]+tablero[1]+tablero[2]) == 3){
            posGanadora = new int[]{0,1,2};
            nuevoEstado = 1*turno;
        }
        else if(Math.abs(tablero[3]+tablero[4]+tablero[5]) ==3){
            posGanadora = new int[]{3,4,5};
            nuevoEstado = 1*turno;
        }
        else if(Math.abs(tablero[6]+tablero[7]+tablero[8]) ==3){
            posGanadora = new int[]{6,7,8};
            nuevoEstado = 1*turno;
        }
        //Columnas
        else if(Math.abs(tablero[0]+tablero[3]+tablero[6]) ==3){
            posGanadora = new int[]{0,3,6};
            nuevoEstado = 1*turno;
        }
        else if(Math.abs(tablero[1]+tablero[4]+tablero[7]) ==3){
            posGanadora = new int[]{1,4,7};
            nuevoEstado = 1*turno;
        }
        else if(Math.abs(tablero[2]+tablero[5]+tablero[8]) ==3){
            posGanadora = new int[]{2,5,8};
            nuevoEstado = 1*turno;
        }
        //Diagonales
        else if(Math.abs(tablero[0]+tablero[4]+tablero[8]) ==3){
            posGanadora = new int[]{0,4,8};
            nuevoEstado = 1*turno;
        }
        else if(Math.abs(tablero[2]+tablero[4]+tablero[6]) ==3){
            posGanadora = new int[]{2,4,6};
            nuevoEstado = 1*turno;
        }
        //En caso de empate
        else if(fichasPuestas == 9){
            nuevoEstado = 2;
        }

        return nuevoEstado;

    }
//
    public void reiniciar(View view)
    {
        finish();
        Intent boton_x_0 = new Intent(this,JuegoX0.class);
        startActivity(boton_x_0);

    }
}


