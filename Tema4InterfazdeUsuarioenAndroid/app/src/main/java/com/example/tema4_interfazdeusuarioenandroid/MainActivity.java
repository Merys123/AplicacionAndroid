package com.example.tema4_interfazdeusuarioenandroid;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    private Animation miAnimacion;
    private Button boton1, boton2, boton3;
    private  TextView miTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.linear);

        boton1 = findViewById(R.id.boton1);
        boton2 = findViewById(R.id.boton2);
        boton3 = findViewById(R.id.boton3);
        miTexto = findViewById(R.id.textoej);

        EdgeToEdge.enable(this);
        setContentView(R.layout.linear);

        //Estos son los elementos que se sugerirán
        String[] opciones = {"opcion1", "opcion2", "opcion3", "opcion4", "opcion5"};

        //Aqui se asigna el elemento AutoCompleteTextView creado en el XML al Java
        AutoCompleteTextView textoLeido = findViewById(R.id.autoTexto);

        //Creamos un ArrayAdapter al que le pasamos el array de strings
        //La mision de este "ArrayAdapter" es adaptar los datos del array al AutoCompleteTextView
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, opciones);

        //Por ultimo se le pasa al adaptador la instancia del texto definida en el XML
        textoLeido.setAdapter(adaptador);

        miTexto.setText("Nuevo texto a mostrar");

    }


    protected void onStart(){
        super.onStart();

        miAnimacion = AnimationUtils.loadAnimation(this, R.anim.animacion);


        boton1.setOnClickListener(v -> {

            miAnimacion = AnimationUtils.loadAnimation(MainActivity.this, R.anim.animacion);
            //aqui configuramos la animacion
            miAnimacion.setRepeatMode(Animation.RESTART);
            miAnimacion.setRepeatCount(20);
            //Por ultimo se aplica la animacion al texto
            miTexto.startAnimation(miAnimacion);
        });

        boton2.setOnClickListener(v -> {

            miAnimacion = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotacion);
            miTexto.startAnimation(miAnimacion);
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(miAnimacion != null){

                    miTexto.clearAnimation();
                    miAnimacion.cancel();
                    miAnimacion = null;
                }


            }
        });


        //Opcion1 para cambiar el color
        miTexto.setTextColor(Color.RED);
        //
        miTexto.setTypeface(null, Typeface.BOLD);
        //cambio el tamaño del texto
        miTexto.setTextSize(24);
        //cambiar tipo de letra
        miTexto.setTypeface(Typeface.SANS_SERIF);


        Spinner miSpinner = (Spinner) findViewById(R.id.miSpinner);
        String[] valores = {"Star Wars", "Reservoir Dogs", "El Club de la Lucha", "Snatch: Cerdos y Diamantes"};
        miSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));

        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View View, int position, long l) {
                System.out.println("Has seleccionado el valor: " + adapterView.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("No has seleccionado nada");
            }
        });


        CheckBox checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
        // Listener para cambios en el CheckBox
        checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    System.out.println("0");
                } else {
                    System.out.println("1");
                }
            }
        });
    }
}