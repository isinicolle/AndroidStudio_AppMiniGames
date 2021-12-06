package com.example.proyectogrupo6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainAhorcado extends AppCompatActivity {
    private Random random;
    private TextView txtPistaPalabra;
    private String palAct;
    private TextView[] charView;
    ArrayList<String> mis_palabras;
    private LinearLayout layoutPal;
    private AdaptadorLetra adaptador;
    private GridView gridView;
    private int numChar;
    private int numCorr;
    private ImageView[] partes;
    private int partAct;
    private int numPist;
    private String  pista;
    private static final  int intentos =6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ahorcado);
        mis_palabras = new ArrayList<String>();
        layoutPal=findViewById(R.id.palabras);
        gridView=findViewById(R.id.letras);
        random=new Random();
        txtPistaPalabra=findViewById(R.id.txtpistaPalabra);
        partes=new ImageView[intentos];
        partes[0]=findViewById(R.id.head);
        partes[1]=findViewById(R.id.body);
        partes[2]=findViewById(R.id.brazoDer);
        partes[3]=findViewById(R.id.brazoIzq);
        partes[4]=findViewById(R.id.piernDer);
        partes[5]=findViewById(R.id.piernIzq);

        basededatos();

        jugarJuego();

    }
    private void jugarJuego(){
        //Reinicio de variables
        numPist = 0;
        numCorr=0;
        partAct=0;
        layoutPal.removeAllViews();
        //Reasignacion de variables
        String nuevaPal=mis_palabras.get(random.nextInt(mis_palabras.size()));
        while(nuevaPal.split("\\|")[0].equals(palAct))
            nuevaPal=mis_palabras.get(random.nextInt(mis_palabras.size()));
        String[] separador=nuevaPal.split("\\|");
        palAct=separador[0];
        pista=separador[1];
        txtPistaPalabra.setText(pista); //Asignar la pista
        charView= new TextView[palAct.length()];
        numChar=palAct.length();
        //Asignacion de caracteres
        for(int i=0;i<palAct.length();i++)
        {
            charView[i]= new TextView(this  );
            charView[i].setText(palAct.charAt(i)+"");
            charView[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            charView[i].setGravity(Gravity.CENTER);
            charView[i].setTextColor(Color.WHITE);
            charView[i].setBackgroundResource(R.drawable.letter_bg);
            layoutPal.addView(charView[i]);
        }
        //Reestablecer las letras
        adaptador=new AdaptadorLetra(this);
        gridView.setAdapter(adaptador);
        //Reestablecer el ahorcado
        for (int i=0;i<intentos;i++)
        {
            partes[i].setVisibility(View.INVISIBLE);
        }
    }
    public void btnLetra_onClick(View view) {
        char letra=((TextView)view).getText().toString().charAt(0);
        view.setEnabled(false);
        boolean correct=false;

        for(int i=0;i<palAct.length();i++) {
            if(palAct.charAt(i)==letra) {
                correct = true;
                numCorr++;
                charView[i].setTextColor(Color.BLACK);
            }
        }
        if (correct){
            if(numCorr==numChar){ //Ganar
                AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder.setTitle("Ganaste");
                builder.setMessage("Felicidades acertaste");
                builder.setNegativeButton("Jugar de nuevo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainAhorcado.this.jugarJuego();
                    }
                });
                builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainAhorcado.this.finish();
                    }
                });
                builder.setCancelable(false);
                builder.show();
            }}
        else if (partAct<intentos){
            partes[partAct].setVisibility(View.VISIBLE);
            partAct++;
        }
        if (partAct==6){ //Perdeer
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setTitle("Perdiste");
            builder.setMessage("Que mal! perdiste");
            builder.setNegativeButton("Jugar de nuevo", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainAhorcado.this.jugarJuego();
                }
            });
            builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainAhorcado.this.finish();
                }
            });
            builder.setCancelable(false);
            builder.show();
        }
    }
    public void mostrar(){
        int a = random.nextInt(palAct.length());
        char letra = palAct.charAt(a);
        while(charView[a].getCurrentTextColor() == Color.BLACK){
            a = random.nextInt(palAct.length());
            letra = palAct.charAt(a);
        }
        for(int i=0;i<palAct.length();i++) {
            if(palAct.charAt(i)==letra) {
                numCorr++;
                charView[i].setTextColor(Color.BLACK);
            }
        }
        for (int i=0;i<adaptador.getCount();i++)
        {
            if (((Button)gridView.getChildAt(i)).getText().charAt(0)==letra) {
                gridView.getChildAt(i).setEnabled(false);
            }
        }

    }

    public void btnPista_onClick(View view){
        int guardar = 0;
        for(int i = 0; i<palAct.length();i++){
            if(charView[i].getCurrentTextColor() == Color.BLACK){
                guardar++;
            }
        }
        if(numPist < 3 && guardar < palAct.length()/2){
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setTitle("Alerta!");
            builder.setMessage("Seguro que quieres obtener una pista?");
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mostrar();
                    numPist ++;
                }
            });
            builder.show();
        }
        else{
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setTitle("Alerta!");
            builder.setMessage("Alcazaste el limite de pistas!");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
        }
    }

    public void apagarBtn()
    {
        for (int i=0;i<gridView.getChildCount();i++){
            gridView.getChildAt(i).setEnabled(false);
        }
    }

    public void basededatos(){
        InputStream myInputStream = null;
        Scanner in = null;
        String una_palabra = "";
        try {
            myInputStream = getAssets().open("basededatos_archivo.txt");
            in = new Scanner(myInputStream);
            while(in.hasNext()){
                una_palabra = in.nextLine();
                mis_palabras.add(una_palabra);
            }
        } catch (IOException e) {
            Toast.makeText(this,
                    e.getClass().getSimpleName() +": " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
        finally {
            //cerrar Scanner
            if(in != null){
                in.close();
            }
            //cerrar InputStream
            try {
                if(myInputStream != null){
                    myInputStream.close();
                }
            } catch (IOException e) {
                Toast.makeText(this,
                        e.getClass().getSimpleName() +": " + e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}