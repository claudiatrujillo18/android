package com.example.trabajo1;

import static java.lang.Double.parseDouble;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] arrdestino= {"Cartagena", "San Andres", "Santa Marta"};
    String selDestino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nombre= findViewById(R.id.nombre);
        EditText fecha= findViewById(R.id.fecha);
        Spinner destino = findViewById(R.id.destino);
        EditText cantidadPersonas = findViewById(R.id.cantidadPersonas);
        EditText cantidadDias = findViewById(R.id.cantidadDias);
        RadioButton tour = findViewById(R.id.tour);
        RadioButton discoteca = findViewById(R.id.discoteca);
        TextView totalPlan = findViewById(R.id.totalPlan);
        TextView descuento = findViewById(R.id.descuento);
        Button calculate = findViewById(R.id.calculate);
        Button clean = findViewById(R.id.clean);


        ArrayAdapter adpdestino = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, arrdestino);

        destino.setAdapter(adpdestino);
        destino.setOnItemSelectedListener(this);


        calculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!nombre.getText().toString().isEmpty() && fecha.getText().toString().isEmpty()) {

                    double xcantidadPersonas = parseDouble(cantidadPersonas.getText().toString());
                    double xcantidadDias = parseDouble(cantidadDias.getText().toString());

                    if ((xcantidadPersonas <= 10 && xcantidadPersonas >= 1) && (xcantidadDias <= 5 && xcantidadDias >= 2)) {

                        double vlrViaje = 0;

                        switch (selDestino) {

                            case "Cartagena":
                                vlrViaje = 250000;
                            case "San Andres":
                                vlrViaje = 200000;
                            case "Santa Marta":
                                vlrViaje = 230000;

                        }

                        int xtour;
                        if (tour.isChecked()) {
                            xtour = 100000;
                        }

                        int xdiscoteca;

                        if (discoteca.isChecked()) {
                            xdiscoteca = 80000;
                        }

                        double xdescuento = parseDouble(descuento.getText().toString());


                        if (xcantidadPersonas >= 8) {

                            xdescuento = vlrViaje * 100 / 10;

                        }

                        double xtotalPlan = (xcantidadDias * vlrViaje) * xcantidadPersonas - xdescuento;

                        DecimalFormat numberFormat = new DecimalFormat("###,###,###,###,###.#");

                        totalPlan.setText(numberFormat.format(xtotalPlan));
                        descuento.setText(numberFormat.format(xdescuento));


                    } else {

                        Toast.makeText(getApplicationContext(), "El número de días debe estar entre 2 y 5 máximo", Toast.LENGTH_LONG).show();

                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Debe llenar todos los datos", Toast.LENGTH_LONG).show();
                }


            }


        });

        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombre.setText("");
                fecha.setText("");
                cantidadDias.setText("2");
                cantidadPersonas.setText("1");
                tour.setChecked(true);
                totalPlan.setText("");
                descuento.setText("");


            }
        });



    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {


        selDestino = arrdestino[i];

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}