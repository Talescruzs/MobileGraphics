package com.example.grafico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class GraphicActivity extends AppCompatActivity {
    boolean isGeral;

    float a;
    float b;
    float c;

    float m;
    float n;

    float interseptY;
    float interseptX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        Intent getData = getIntent();
        isGeral = getData.getBooleanExtra(MainActivity.TYPE, true);
        if(isGeral){
            a = getData.getFloatExtra(MainActivity.A, 1);
            b = getData.getFloatExtra(MainActivity.B, 1);
            c = getData.getFloatExtra(MainActivity.C, 1);
        }else{
            m = getData.getFloatExtra(MainActivity.M, 1);
            n = getData.getFloatExtra(MainActivity.N, 1);
        }
    }
    public void teste(View v) {
        if(isGeral){
            Toast.makeText(this, "Inserido:"+a+"X+"+b+"Y+"+c+"=0", Toast.LENGTH_LONG).show();
            m=(a*-1)/b;
            n=(c*-1)/b;
            Toast.makeText(this, "Reduzido:"+"Y="+m+"X+"+n, Toast.LENGTH_LONG).show();
            interseptY = (c*-1)/b;
            interseptX = (c*-1)/a;
            Toast.makeText(this, "Intersepto X:"+interseptX+"\n"+"Intersepto Y:"+interseptY+"\n"+"Coeficiente angular:"+m+"\n"+"Coeficiente linear:"+n, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Inserido:"+"Y="+m+"X+"+n, Toast.LENGTH_LONG).show();
            interseptX = (n*-1)/m;
            interseptY = n;
            Toast.makeText(this, "Intersepto X:"+interseptX+"\n"+"Intersepto Y:"+interseptY+"\n"+"Coeficiente angular:"+m+"\n"+"Coeficiente linear:"+n, Toast.LENGTH_LONG).show();
        }

        // O que vai manter:
        Intent insertEqua = new Intent(GraphicActivity.this, MainActivity.class);
        startActivity(insertEqua);
    }
}