package com.example.grafico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import java.security.PrivilegedActionException;

public class MainActivity extends AppCompatActivity {
    //Geral:
    public static final String A = "com.example.grafico.A";
    public static final String B = "com.example.grafico.B";
    public static final String C = "com.example.grafico.C";
    //Reduzida:
    public static final String M = "com.example.grafico.M";
    public static final String N = "com.example.grafico.N";
    //Type:
    public static final String TYPE = "com.example.grafico.TYPE";
    public boolean isGeral;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void changeType(View v) {
        Switch switchType = findViewById(R.id.switchType);
        LinearLayout redu = findViewById(R.id.insertFieldRedu);
        LinearLayout geral = findViewById(R.id.insertFieldGeral);

        isGeral = switchType.isChecked();
        if(isGeral){
            switchType.setText("Geral");
            redu.setVisibility(View.INVISIBLE);
            geral.setVisibility(View.VISIBLE);
        }else{
            switchType.setText("Reduzida");
            redu.setVisibility(View.VISIBLE);
            geral.setVisibility(View.INVISIBLE);
        }
    }
    public void insertEquation(View v) throws Exception{
        try {
            Intent insertEqua = new Intent(MainActivity.this, GraphicActivity.class);
            insertEqua.putExtra(TYPE, isGeral);
            if (isGeral) {
                EditText a = findViewById(R.id.editxGeral);
                EditText b = findViewById(R.id.editYGeral);
                EditText c = findViewById(R.id.editCGeral);
                if (a.getText().toString().contains(",") || b.getText().toString().contains(",") || c.getText().toString().contains(",")) {
                    throw new IllegalArgumentException("Utilize numeros decimais com . ao invez de ,");
                }else if (a.getText().toString().trim().isEmpty() || b.getText().toString().trim().isEmpty() || c.getText().toString().trim().isEmpty()){
                    throw new IllegalArgumentException("Precisamos de todos os campos completos");
                }else {
                    float floatA;
                    if (a.getText().toString().contains("/")) {
                        float num1 = Float.valueOf(a.getText().toString().split("/")[0]);
                        float num2 = Float.valueOf(a.getText().toString().split("/")[1]);
                        floatA = num1 / num2;
                    } else {
                        floatA = Float.valueOf(a.getText().toString());
                    }
                    float floatB;
                    if (b.getText().toString().contains("/")) {
                        float num1 = Float.valueOf(b.getText().toString().split("/")[0]);
                        float num2 = Float.valueOf(b.getText().toString().split("/")[1]);
                        floatB = num1 / num2;
                    } else {
                        floatB = Float.valueOf(b.getText().toString());
                    }
                    float floatC;
                    if (c.getText().toString().contains("/")) {
                        float num1 = Float.valueOf(c.getText().toString().split("/")[0]);
                        float num2 = Float.valueOf(c.getText().toString().split("/")[1]);
                        floatC = num1 / num2;
                    } else {
                        floatC = Float.valueOf(c.getText().toString());
                    }
                    insertEqua.putExtra(A, floatA);
                    insertEqua.putExtra(B, floatB);
                    insertEqua.putExtra(C, floatC);
                }
            } else {
                EditText m = findViewById(R.id.editXRedu);
                EditText n = findViewById(R.id.editLinearRedu);
                if (m.getText().toString().contains(",") || n.getText().toString().contains(",")) {
                    throw new IllegalArgumentException("Utilize numeros decimais com . ao invez de ,");
                }else if (m.getText().toString().trim().isEmpty() || n.getText().toString().trim().isEmpty()){
                    throw new IllegalArgumentException("Precisamos de todos os campos completos");
                }else {
                    float floatM;
                    if (m.getText().toString().contains("/")) {
                        float num1 = Float.valueOf(m.getText().toString().split("/")[0]);
                        float num2 = Float.valueOf(m.getText().toString().split("/")[1]);
                        floatM = num1 / num2;
                    } else {
                        floatM = Float.valueOf(m.getText().toString());
                    }
                    float floatN;
                    if (n.getText().toString().contains("/")) {
                        float num1 = Float.valueOf(n.getText().toString().split("/")[0]);
                        float num2 = Float.valueOf(n.getText().toString().split("/")[1]);
                        floatN = num1 / num2;
                    } else {
                        floatN = Float.valueOf(n.getText().toString());
                    }
                    insertEqua.putExtra(M, floatM);
                    insertEqua.putExtra(N, floatN);
                }
            }
            startActivity(insertEqua);
        }catch(IllegalArgumentException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        catch(Exception ex){
            Toast.makeText(this, "Insere um bagulho direito, animal", Toast.LENGTH_LONG).show();
        }
    }
}