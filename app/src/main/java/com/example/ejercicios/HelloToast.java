package com.example.ejercicios;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HelloToast extends AppCompatActivity {

    private TextView showCount;
    private int count = 0;
    private static final String KEY_COUNT = "count_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_toast);

        showCount = findViewById(R.id.show_count);
        Button buttonToast = findViewById(R.id.Button1);
        Button buttonCount = findViewById(R.id.Button2);

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(KEY_COUNT, 0);
            showCount.setText(String.valueOf(count));
        }

        buttonToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HelloToast.this, "Hello Toast!", Toast.LENGTH_SHORT).show();
            }
        });

        buttonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                showCount.setText(String.valueOf(count));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, count);
    }
}
