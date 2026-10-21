package com.example.textview_en_movimiento;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Animation miAnimacion;
    //private Button boton1, boton2, boton3;
    private TextView miTexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //miAnimacion = AnimationUtils.loadAnimation(this, R.anim.animacion);
        miAnimacion = AnimationUtils.loadAnimation(this, R.anim.rotacion);
        miTexto = findViewById(R.id.Text);
        miTexto.startAnimation(miAnimacion);

      /*  boton1.setOnClickListener(v -> {

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
        });*/
    }
}