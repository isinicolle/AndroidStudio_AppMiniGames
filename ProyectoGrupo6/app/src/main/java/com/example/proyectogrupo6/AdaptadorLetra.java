package com.example.proyectogrupo6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class AdaptadorLetra extends BaseAdapter {
   private String[]letters;
   private LayoutInflater letterinf;
   public AdaptadorLetra(Context context){
       letters=new String[27];
       for(int i=0;i<letters.length;i++){
           if(i<=13)
           letters[i]=""+(char)(i+'A');
           else if(i==14)
               letters[i]=""+'\u00f1';
           else
               letters[i]=""+(char)((i-1)+'A');
       }
       letterinf=LayoutInflater.from(context);
   }
    @Override
    public int getCount() {
        return letters.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Button btnLettr;
        if(view==null)
        {
            btnLettr=(Button) letterinf.inflate(R.layout.letras,viewGroup,false);
        }else{
            btnLettr=(Button)view;
        }
        btnLettr.setText(letters[i]);
       return btnLettr;
    }
}
