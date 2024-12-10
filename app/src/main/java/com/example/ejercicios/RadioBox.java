package com.example.ejercicios;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class RadioBox extends AppCompatActivity {

    private RadioGroup rvh;
    private RadioGroup rrcl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio_box);

        rvh = findViewById(R.id.rGroupHV);
        rrcl = findViewById(R.id.rGroupRCL);

        RadioButton rH = findViewById(R.id.radioHorizontal);
        rH.setChecked(true);
        RadioButton rC = findViewById(R.id.radioLeft);
        rC.setChecked(true);

        rvh.setOnCheckedChangeListener((group, checkedId) -> cambiarOrientacion(checkedId));
        rrcl.setOnCheckedChangeListener((group, checkedId) -> cambiarCentrado(checkedId));
    }

    public void cambiarOrientacion(int checkedId) {
        if (checkedId == R.id.radioHorizontal) {
            rvh.setOrientation(RadioGroup.HORIZONTAL);
        } else if (checkedId == R.id.radioVertical) {
            rvh.setOrientation(RadioGroup.VERTICAL);
        }
    }

    public void cambiarCentrado(int checkedId) {
        if (checkedId == R.id.radioRight) {
            rrcl.setGravity(Gravity.RIGHT);
        } else if (checkedId == R.id.radioCenter) {
            rrcl.setGravity(Gravity.CENTER_HORIZONTAL);
        } else if (checkedId == R.id.radioLeft) {
            rrcl.setGravity(Gravity.LEFT);
        }
    }
}
