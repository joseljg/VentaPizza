package com.example.ventapizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class VentaPizza2Activity extends AppCompatActivity {

    double saldo;
    double total;
    TextView txt_cantidads;
    TextView txt_cantidadi;
    TextView txt_cantidad2x1;
    TextView txt_cantidadt;
    TextView txt_cantidadb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta_pizza2);
        //---------------------------------------------------------
        saldo = 15;
        txt_cantidads = (TextView) findViewById(R.id.txt_cantidads);
        txt_cantidads.setText(String.valueOf(saldo));
        //-----------------------------------------------------------
        Intent intent = getIntent();
        String nombrep ="";
        nombrep = intent.getStringExtra(MainActivity.EXTRA_NOMBRE_PIZZA);
        int cantidadp = 0;
        cantidadp = intent.getIntExtra(MainActivity.EXTRA_CANTIDAD_PIZZA, 0);
        //-----------------------------------------------------------
        double preciop = coger_precio_pizza(nombrep);
        double preciobase = (preciop * cantidadp);
        txt_cantidadb = (TextView) findViewById(R.id.txt_cantidadb);
        txt_cantidadb.setText(String.valueOf(preciobase));

        //-----------------------------------------------------------
        double iva = preciobase * 0.21;
        txt_cantidadi = (TextView) findViewById(R.id.txt_cantidadi);
        txt_cantidadi.setText(String.valueOf(iva));
        //-----------------------------------------------------------
        double descuento2x1 = calcular_descuento2x1(preciop, cantidadp);
        txt_cantidad2x1 = (TextView) findViewById(R.id.txt_cantidad2x1);
        txt_cantidad2x1.setText(String.valueOf(String.valueOf(descuento2x1)));
        //-----------------------------------------------------------
        total = preciobase + iva - descuento2x1;
        txt_cantidadt = (TextView) findViewById(R.id.txt_cantidadt);
        txt_cantidadt.setText(String.valueOf(total));
    }

    private double calcular_descuento2x1(double preciop, int cantidadp) {
        double descuento2x1 = 0.0;
        if(cantidadp >= 2 )
        {
            descuento2x1 = (preciop * (cantidadp/2));
        }
            return descuento2x1;
    }

    private double coger_precio_pizza(String nombrep) {
        double precio = 0.0;
        if(nombrep.equalsIgnoreCase("barbacoa"))
        {
            precio = 5.0;
        }
        if(nombrep.equalsIgnoreCase("carbonara"))
        {
            precio = 4.5;
        }
        if(nombrep.equalsIgnoreCase("4 quesos"))
        {
            precio = 4.0;
        }
        return precio;
    }



    public void usar_saldo(View view) {
        if (saldo <= total) {
            total = total - saldo;
            saldo = 0;
        } else {
            saldo = saldo - total;
            total = 0;
        }
        txt_cantidadt.setText(String.valueOf(total));
        txt_cantidads.setText(String.valueOf(saldo));
    }
}