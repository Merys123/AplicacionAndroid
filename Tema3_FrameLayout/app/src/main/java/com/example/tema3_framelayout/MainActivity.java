package com.example.tema3_framelayout;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge. enable (this);
        setContentView(R.layout.frame_layout);
        setContentView(R.layout.grind_layout);
        setContentView(R.layout.linear_layout);
        setContentView(R.layout.relative_layout);
        setContentView(R.layout.table_layout);
        });
    }