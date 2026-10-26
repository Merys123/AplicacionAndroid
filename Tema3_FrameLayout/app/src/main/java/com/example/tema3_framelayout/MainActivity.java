package com.example.tema3_framelayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView pantalla;
    double numero1;
    String operador = "";
    boolean nuevoNumero = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.calculadora_grind);

        pantalla = findViewById(R.id.pantalla);

        int[] botones = {
                R.id.boton0, R.id.boton1, R.id.boton2,
                R.id.boton3, R.id.boton4, R.id.boton5,
                R.id.boton6, R.id.boton7, R.id.boton8,
                R.id.boton9, R.id.botonSuma, R.id.botonResta,
                R.id.botonMultiplicar, R.id.botonDividir,
                R.id.botonIgual, R.id.botonBorrar
        };

        for (int id : botones) {
            Button boton = findViewById(id);

            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String texto = boton.getText().toString();

                    if (texto.matches("[0-9]")) {
                        if (nuevoNumero || pantalla.getText().toString().equals("0")) {
                            pantalla.setText(texto);
                        } else {
                            pantalla.append(texto);
                        }
                        nuevoNumero = false;

                    } else if (texto.equals(".")) {
                        if (nuevoNumero) {
                            pantalla.setText("0.");
                            nuevoNumero = false;
                        } else if (!pantalla.getText().toString().contains(".")) {
                            pantalla.append(".");
                        }

                    } else if (texto.equals("C")) {
                        pantalla.setText("0");
                        numero1 = 0;
                        operador = "";
                        nuevoNumero = true;

                    } else if (texto.equals("=")) {
                        double numero2 = Double.parseDouble(
                                pantalla.getText().toString());

                        switch (operador) {
                            case "+":
                                numero1 = numero1 + numero2;
                                break;
                            case "-":
                                numero1 = numero1 - numero2;
                                break;
                            case "*":
                                numero1 = numero1 * numero2;
                                break;
                            case "/":
                                if (numero2 == 0) {
                                    pantalla.setText("Error");
                                    operador = "";
                                    nuevoNumero = true;
                                    return;
                                }
                                numero1 = numero1 / numero2;
                                break;
                        }

                        pantalla.setText(String.valueOf(numero1));
                        operador = "";
                        nuevoNumero = true;

                    } else {
                        numero1 = Double.parseDouble(
                                pantalla.getText().toString());
                        operador = texto;
                        nuevoNumero = true;
                    }
                }
            });
        }
    }
    }