package com.example.grafico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.CatmullRomInterpolator;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;


public class GraphicActivity extends AppCompatActivity {
    boolean isGeral;

    float a;
    float b;
    float c;

    float m;
    float n;

    float interseptY;
    float interseptX;

    private XYPlot plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        plot = (XYPlot) findViewById(R.id.plot);
        plot.setDomainBoundaries(null, null, BoundaryMode.AUTO);

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

        Number[] domainLabels = {interseptX*2, interseptX, 0, interseptX*-1};
        Number[] series1Numbers = {interseptY*-1, 0, interseptY, interseptY*2};

        XYSeries series1 = new SimpleXYSeries(
                Arrays.asList(series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Reta inserida");

        // create formatters to use for drawing a series using LineAndPointRenderer
        // and configure them from xml:
        LineAndPointFormatter series1Format =
                new LineAndPointFormatter(this, R.xml.line_point_formatter_with_labels);


        // just for fun, add some smoothing to the lines:
        // see: http://androidplot.com/smooth-curves-and-androidplot/
        series1Format.setInterpolationParams(
                new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format);

        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                int i = Math.round(((Number) obj).floatValue());
                return toAppendTo.append(domainLabels[i]);
            }
            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;
            }
        });

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