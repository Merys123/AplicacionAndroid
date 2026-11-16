package com.example.adaptadores;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ListView lista = findViewById(R.id.listView);

        LayoutInflater inflater = LayoutInflater.from(this);
        View miCabecera =inflater.inflate(R.layout.cabecera, lista, false);
        lista.addHeaderView(miCabecera);

        Datos[] datos = new Datos[]{
                new Datos("España", "Madrid"),
                new Datos("Francia", "Paris"),
                new Datos("Italia", "Roma")
        };

        Adaptador miAdaptador = new Adaptador(this, datos);
        lista.setAdapter(miAdaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <? > parent, View view, int position, long id) {
                // Obtener el elemento seleccionado
                String selectedItem = ((Datos) parent.getItemAtPosition(position)).getTexto1();
                // Mostrar un mensaje con el elemento seleccionado
                Toast.makeText(MainActivity.this,"Elemento pulsado: " + selectedItem, Toast.LENGTH_SHORT).show();
                // Aqui puedes agregar cualquier otra acción para el elemento pulsado

            }
        });
    }
}