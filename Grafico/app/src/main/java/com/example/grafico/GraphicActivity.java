package com.example.grafico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.lang.Math;

import androidx.appcompat.app.AppCompatActivity;


public class GraphicActivity extends AppCompatActivity {
    boolean isGeral;

    float a;
    float b;
    float c;

    float m;
    float n;

    float angulo;

    float interseptY;
    float interseptX;

    ImageView reta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        reta = findViewById(R.id.reta);

        Intent getData = getIntent();
        isGeral = getData.getBooleanExtra(MainActivity.TYPE, true);
        if(isGeral){
            a = getData.getFloatExtra(MainActivity.A, 1);
            b = getData.getFloatExtra(MainActivity.B, 1);
            c = getData.getFloatExtra(MainActivity.C, 1);
            m=(a*-1)/b;
            n=(c*-1)/b;
        }else{
            m = getData.getFloatExtra(MainActivity.M, 1);
            n = getData.getFloatExtra(MainActivity.N, 1);
        }
        interseptX = (n*-1)/m;
        interseptY = n;

        angulo = (float) Math.atan(m);
        angulo = (float) Math.toDegrees(angulo);

        reta.setRotation(-angulo);


    }
    public void teste(View v) {
        if(isGeral){
            Toast.makeText(this, "Inserido:"+a+"X+"+b+"Y+"+c+"=0", Toast.LENGTH_LONG).show();

            Toast.makeText(this, "Intersepto X:"+interseptX+"\n"+"Intersepto Y:"+interseptY+"\n"+"Coeficiente angular:"+m+"\n"+"Coeficiente linear:"+n, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Inserido:"+"Y="+m+"X+"+n, Toast.LENGTH_LONG).show();

            Toast.makeText(this, "Intersepto X:"+interseptX+"\n"+"Intersepto Y:"+interseptY+"\n"+"Coeficiente angular:"+m+"\n"+"Coeficiente linear:"+n, Toast.LENGTH_LONG).show();
        }
    }
}