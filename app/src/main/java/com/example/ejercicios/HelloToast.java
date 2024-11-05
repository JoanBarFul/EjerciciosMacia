package com.example.ejercicios;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HelloToast  extends AppCompatActivity {

    private TextView showCount;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_toast);

        // Inicializar el TextView y los botones
        showCount = findViewById(R.id.show_count);
        Button buttonToast = findViewById(R.id.Button1);
        Button buttonCount = findViewById(R.id.Button2);

        // Configurar el botón "Toast" para mostrar un mensaje
        buttonToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HelloToast.this, "Hello Toast!", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar el botón "Count" para incrementar el contador
        buttonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                showCount.setText(String.valueOf(count));
            }
        });
    }
}
