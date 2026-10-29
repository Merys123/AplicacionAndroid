

package com.example.calculadoraprincipal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView pantalla;
    double numero1;
    double numero2;
    String operador = "";
    String operacion = "";
    String entrada = "";
    boolean nuevoNumero = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pantalla = findViewById(R.id.pantalla);

        int[] botones = {
                R.id.boton0, R.id.boton1, R.id.boton2,
                R.id.boton3, R.id.boton4, R.id.boton5,
                R.id.boton6, R.id.boton7, R.id.boton8,
                R.id.boton9, R.id.botonPunto,
                R.id.botonSuma, R.id.botonResta,
                R.id.botonMultiplicar, R.id.botonDividir,
                R.id.botonIgual, R.id.botonAC, R.id.botonC
        };

        for (int id : botones) {
            Button boton = findViewById(id);

            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String texto = boton.getText().toString();

                    if (texto.matches("[0-9]")) {

                        if (nuevoNumero) {
                            entrada = texto;
                        } else if (entrada.equals("0")) {
                            entrada = texto.equals("0") ? "0" : texto;
                        } else {
                            entrada += texto;
                        }

                        nuevoNumero = false;

                        if (operador.equals("")) {
                            pantalla.setText(entrada);
                        } else {
                            pantalla.setText(operacion + " " + entrada);
                        }

                    } else if (texto.equals(".")) {

                        if (nuevoNumero) {
                            entrada = "0.";
                            nuevoNumero = false;
                        } else if (!entrada.contains(".")) {
                            entrada += ".";
                        }

                        if (operador.equals("")) {
                            pantalla.setText(entrada);
                        } else {
                            pantalla.setText(operacion + " " + entrada);
                        }

                    } else if (texto.equals("AC")) {

                        pantalla.setText("0");
                        numero1 = 0;
                        numero2 = 0;
                        operador = "";
                        operacion = "";
                        entrada = "";
                        nuevoNumero = true;

                    } else if (texto.equals("C")) {

                        if (!entrada.isEmpty()) {
                            entrada = entrada.substring(0, entrada.length() - 1);
                        } else if (!operador.equals("")) {
                            operador = "";
                            operacion = "";
                        }

                        if (entrada.isEmpty()) {
                            entrada = "0";
                        }

                        nuevoNumero = false;

                        if (operador.equals("")) {
                            pantalla.setText(entrada);
                        } else {
                            pantalla.setText(operacion + " " + entrada);
                        }

                    } else if (texto.equals("=")) {

                        if (operador.equals("") || entrada.isEmpty()
                                || entrada.equals(".")) {
                            return;
                        }

                        numero2 = Double.parseDouble(entrada);
                        double resultado = 0;

                        switch (operador) {
                            case "+":
                                resultado = numero1 + numero2;
                                break;

                            case "-":
                                resultado = numero1 - numero2;
                                break;

                            case "*":
                                resultado = numero1 * numero2;
                                break;

                            case "/":
                                if (numero2 == 0) {
                                    pantalla.setText("Error");
                                    operador = "";
                                    operacion = "";
                                    entrada = "";
                                    nuevoNumero = true;
                                    return;
                                }

                                resultado = numero1 / numero2;
                                break;
                        }

                        entrada = formatear(resultado);
                        pantalla.setText(entrada);

                        operador = "";
                        operacion = "";
                        nuevoNumero = true;

                    } else {

                        if (entrada.isEmpty() || entrada.equals(".")
                                || !operador.equals("")) {
                            return;
                        }

                        numero1 = Double.parseDouble(entrada);
                        operador = texto;
                        operacion = formatear(numero1) + " " + operador;
                        entrada = "";
                        nuevoNumero = true;

                        pantalla.setText(operacion);
                    }
                }
            });
        }
    }

    private String formatear(double numero) {
        if (numero == (long) numero) {
            return String.valueOf((long) numero);
        } else {
            return String.valueOf(numero);
        }
    }
}