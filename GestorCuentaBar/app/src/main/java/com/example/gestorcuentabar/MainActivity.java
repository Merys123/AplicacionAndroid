package com.example.gestorcuentabar;

import android.os.Bundle;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText etCuenta;
    AutoCompleteTextView etCamarero;
    CheckBox cbPropina;
    SeekBar sbPropina;
    TextView tvPorcentaje, tvResultado;
    RadioGroup rgPago;
    RatingBar rbValoracion;
    Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCuenta = findViewById(R.id.etCuenta);
        etCamarero = findViewById(R.id.etCamarero);
        cbPropina = findViewById(R.id.cbPropina);
        sbPropina = findViewById(R.id.sbPropina);
        tvPorcentaje = findViewById(R.id.tvPorcentaje);
        tvResultado = findViewById(R.id.tvResultado);
        rgPago = findViewById(R.id.rgPago);
        rbValoracion = findViewById(R.id.rbValoracion);
        btnCalcular = findViewById(R.id.btnCalcular);

        // Lista de camareros
        String[] camareros = {
                "Carlos", "María", "Laura",
                "Pedro", "Antonio", "Lucía"
        };

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                camareros
        );

        etCamarero.setAdapter(adaptador);

        // Mostrar el porcentaje del SeekBar
        sbPropina.setProgress(10);

        sbPropina.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        tvPorcentaje.setText(progress + "%");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });

        // Activar o desactivar el SeekBar
        cbPropina.setOnCheckedChangeListener((buttonView, checked) -> {
            sbPropina.setEnabled(checked);
        });

        // Botón para calcular
        btnCalcular.setOnClickListener(v -> calcularCuenta());
    }

    private void calcularCuenta() {

        String cuentaTexto = etCuenta.getText().toString().trim();

        // Comprobar que no esté vacío
        if (cuentaTexto.isEmpty()) {
            mostrarError(getString(R.string.error_vacio));
            return;
        }

        double cuenta;

        try {
            // Permitir tanto punto como coma decimal
            cuenta = Double.parseDouble(cuentaTexto.replace(",", "."));

        } catch (NumberFormatException e) {
            mostrarError(getString(R.string.error_formato));
            return;
        }

        // Comprobar que la cuenta sea mayor que cero
        if (cuenta <= 0) {
            mostrarError(getString(R.string.error_valor));
            return;
        }

        // Comprobar el método de pago
        int seleccionado = rgPago.getCheckedRadioButtonId();

        if (seleccionado == -1) {
            mostrarError(getString(R.string.error_pago));
            return;
        }

        // Calcular la propina
        double propina = 0;

        if (cbPropina.isChecked()) {
            propina = cuenta * sbPropina.getProgress() / 100;
        }

        double total = cuenta + propina;

        // Obtener el método de pago
        String pago;

        if (seleccionado == R.id.rbEfectivo) {
            pago = getString(R.string.efectivo);
        } else {
            pago = getString(R.string.tarjeta);
        }

        // Obtener la valoración
        float valoracion = rbValoracion.getRating();

        // Mostrar resultado
        String resultado = String.format(
                Locale.getDefault(),
                "%s %.2f €\n%s %.2f €\n%s %.2f €\n%s %s\n%s %.0f/5",
                getString(R.string.total_cuenta), cuenta,
                getString(R.string.total_propina), propina,
                getString(R.string.total_final), total,
                getString(R.string.resultado_pago), pago,
                getString(R.string.resultado_valoracion), valoracion
        );

        tvResultado.setTextColor(Color.BLACK);
        tvResultado.setText(resultado);
    }

    // Mostrar errores en rojo
    private void mostrarError(String mensaje) {
        tvResultado.setTextColor(Color.RED);
        tvResultado.setText(mensaje);
    }
}
