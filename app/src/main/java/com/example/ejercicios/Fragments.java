package com.example.ejercicios;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Fragments extends AppCompatActivity {

    private Button fragmentButton1, fragmentButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // En landscape carga ambos fragmentos automáticamente
            loadFragment(new FirstFragment(), R.id.fragmentContainerView1);
            loadFragment(new SecondFragment(), R.id.fragmentContainerView2);
        } else {
            // Configuración normal para portrait (con botones)
            fragmentButton1 = findViewById(R.id.fragmentButton1);
            fragmentButton2 = findViewById(R.id.fragmentButton2);

            fragmentButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadFragment(new FirstFragment(), R.id.fragmentContainerView);
                }
            });

            fragmentButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadFragment(new SecondFragment(), R.id.fragmentContainerView);
                }
            });
        }
    }

    // Método sobrecargado para cargar fragmentos en contenedores específicos
    private void loadFragment(Fragment fragment, int containerId) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(containerId, fragment);
        fragmentTransaction.commit();
    }
}
