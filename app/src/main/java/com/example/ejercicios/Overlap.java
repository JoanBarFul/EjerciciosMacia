package com.example.ejercicios;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Overlap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.overlap);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button bigButton = findViewById(R.id.button1);
        bigButton.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "BIG BUTTON Clicked", Toast.LENGTH_SHORT).show();

        });

        Button smallButton = findViewById(R.id.button2);
        smallButton.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "SMALL BUTTON Clicked", Toast.LENGTH_SHORT).show();
        });

    }




}