package com.example.ejercicios;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Calculadora extends AppCompatActivity {

    public Button buttonMR, buttonMPlus, buttonMMinus, buttonDiv,
            button7, button8, button9, buttonMul,
            button4, button5, button6, buttonSum,
            button1, button2, button3, buttonSub,
            buttonDel, button0, buttonPoint, buttonEqual;

    private TextView display;
    private double num1 = 0, num2 = 0, memory = 0;
    private String operator = "";
    private ArrayList<String> numeros;
    private LinearLayout linearLayoutOperations;  // El layout donde se guardará la operación

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora);

        numeros = new ArrayList<>();
        linearLayoutOperations = findViewById(R.id.linearLayout); // Obtener el layout

        display = findViewById(R.id.textView8);
        buttonMR = findViewById(R.id.buttonMR);
        buttonMPlus = findViewById(R.id.buttonMPlus);
        buttonMMinus = findViewById(R.id.buttonMMinus);
        buttonDiv = findViewById(R.id.buttonDiv);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonMul = findViewById(R.id.buttonMul);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        buttonSum = findViewById(R.id.buttonSum);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        buttonSub = findViewById(R.id.buttonSub);
        buttonDel = findViewById(R.id.buttonDel);
        button0 = findViewById(R.id.button0);
        buttonPoint = findViewById(R.id.buttonPoint);
        buttonEqual = findViewById(R.id.buttonEqual);



        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String text = button.getText().toString();

                if (text.equals(".") && numeros.size() > 0 && numeros.get(numeros.size() - 1).contains(".")) {
                    return;
                }
                numeros.add(text);
                display.setText(TextUtils.join("", numeros));
            }
        };

        button1.setOnClickListener(numberListener);
        button2.setOnClickListener(numberListener);
        button3.setOnClickListener(numberListener);
        button4.setOnClickListener(numberListener);
        button5.setOnClickListener(numberListener);
        button6.setOnClickListener(numberListener);
        button7.setOnClickListener(numberListener);
        button8.setOnClickListener(numberListener);
        button9.setOnClickListener(numberListener);
        button0.setOnClickListener(numberListener);
        buttonPoint.setOnClickListener(numberListener);

        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                operator = button.getText().toString();
                numeros.add(operator);
                display.setText(TextUtils.join("", numeros));
            }
        };

        buttonSum.setOnClickListener(operatorListener);
        buttonSub.setOnClickListener(operatorListener);
        buttonMul.setOnClickListener(operatorListener);
        buttonDiv.setOnClickListener(operatorListener);

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    num1 = Double.parseDouble(numeros.get(0));
                    for (int i = 1; i < numeros.size(); i++) {
                        String current = numeros.get(i);

                        if (current.equals("+")) {
                            num2 = Double.parseDouble(numeros.get(i + 1));
                            num1 += num2;
                        } else if (current.equals("-")) {
                            num2 = Double.parseDouble(numeros.get(i + 1));
                            num1 -= num2;
                        } else if (current.equals("*")) {
                            num2 = Double.parseDouble(numeros.get(i + 1));
                            num1 *= num2;
                        } else if (current.equals("/")) {
                            num2 = Double.parseDouble(numeros.get(i + 1));
                            if (num2 != 0) {
                                num1 /= num2;
                            } else {
                                display.setText("Error");
                                return;
                            }
                        }
                        i++;

                    }

                    String result = formatNumber(num1);
                    display.setText(result);
                    addOperationToHistory(TextUtils.join("", numeros) + " = " + result);  // Guardar la operación
                    numeros.clear();  // Limpiar la lista de números después de la operación
                } catch (NumberFormatException e) {
                    display.setText("Syntax Error");
                }
                numeros.clear();
                display.setText("0");
                num1 = 0;
                num2 = 0;
                operator = "";
            }
        });

        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeros.clear();
                display.setText("");
                num1 = 0;
                num2 = 0;
                operator = "";
            }
        });

        buttonMPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(display.getText().toString())) {
                    memory = Double.parseDouble(display.getText().toString());
                }
            }
        });

        buttonMMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memory = 0;
            }
        });

        buttonMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeros.add(formatNumber(memory));
                display.setText(TextUtils.join("", numeros));
            }
        });
    }

    private String formatNumber(double number) {
        if (number == Math.floor(number)) {
            return String.format("%.0f", number);
        } else {
            return String.valueOf(number);
        }
    }

    private void addOperationToHistory(String operation) {
        TextView operationTextView = new TextView(this);
        operationTextView.setText(operation);
        linearLayoutOperations.addView(operationTextView);  // Añadir la operación al layout vertical
    }
}
