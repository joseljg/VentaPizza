package com.example.ventapizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_NOMBRE_PIZZA = "com.example.ventapizza.nombrep";
    public static final String EXTRA_CANTIDAD_PIZZA = "com.example.ventapizza.cantidadp";
    EditText edt_nombrep;
    EditText edt_cantidadp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_nombrep = (EditText) findViewById(R.id.edt_nombrep);
        edt_cantidadp = (EditText) findViewById(R.id.edt_cantidadp);
    }

    public boolean validar_cantidad(int cantidad)
    {
        if(cantidad <= 0 || cantidad >5) { return false; }
        else { return true; }
    }

    public boolean validar_pizza_elegida(String pizza)
    {
        pizza = pizza.toLowerCase();
        if(pizza.equalsIgnoreCase("barbacoa")
                ||pizza.equalsIgnoreCase("carbonara")
                ||pizza.equalsIgnoreCase("4 quesos")) { return true; }
        else{ return false;}
    }
    public boolean validar_bebida_elegida(String bebida)
    {
        bebida = bebida.toLowerCase();
        if(bebida.equalsIgnoreCase("coca cola")
                ||bebida.equalsIgnoreCase("fanta")
                ||bebida.equalsIgnoreCase("cerveza")) { return true; }
        else{ return false;}
    }

    public void siguiente(View view) {
        int cantidadp = 0;
        int cantidadb = 0;
        boolean error = false;
        try {
            cantidadp = Integer.valueOf(String.valueOf(edt_cantidadp.getText()));
        }
        catch(Exception e){
            edt_cantidadp.setError("debes introducir un numero");
            cantidadp = 0;
            error = true;
        }

        if(!validar_cantidad(cantidadp))
        {
            edt_cantidadp.setError("Elige otro numero de pizzas");
            error = true;
        }

        String nombrep = String.valueOf(edt_nombrep.getText());
        if(!validar_pizza_elegida(nombrep))
        {
            edt_nombrep.setError("pizza no disponible, elige otra pizza");
            error = true;
        }


        if(error == false) {
            Intent intent = new Intent(this, VentaPizza2Activity.class);
            intent.putExtra(EXTRA_NOMBRE_PIZZA, nombrep);
            intent.putExtra(EXTRA_CANTIDAD_PIZZA, cantidadp);
            startActivity(intent);
        }
    }
}