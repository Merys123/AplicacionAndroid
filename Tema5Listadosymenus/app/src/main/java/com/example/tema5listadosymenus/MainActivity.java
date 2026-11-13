package com.example.tema5listadosymenus;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Datos de los países
        List<String> data = Arrays.asList(
                "España", "Portugal", "Africa", "China",
                "Austria", "Francia", "Bélgica", "Japón",
                "Grecia", "Italia"
        );

        // Configurar el ListView y asignarle el adaptador
        GridView listado = findViewById(R.id.gridView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listado.setAdapter(adapter);

        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicion, long l) {
                //Obtener el texto del elemento pulsado
                String elemento = (String) parent.getItemAtPosition(posicion);
            }
        });
    }
}