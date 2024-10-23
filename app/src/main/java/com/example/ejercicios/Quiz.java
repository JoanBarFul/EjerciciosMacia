package com.example.ejercicios;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Iterator;

public class Quiz extends AppCompatActivity {

    // Diccionario que almacena las preguntas y respuestas
    private HashMap<String, Boolean> quizData;
    private Iterator<String> questionIterator;
    private String currentQuestion;
    private TextView questionTextView, textQuestions, textCorrects, textIncorrects;
    private Button trueButton, falseButton, nextButton;
    private int corrects = 0;
    private int incorrects = 0;
    private int actualQuestion = 1;
    private boolean finished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        questionTextView = findViewById(R.id.textView);
        trueButton = findViewById(R.id.buttonTrue);
        falseButton = findViewById(R.id.buttonFalse);
        nextButton = findViewById(R.id.buttonNext);
        textQuestions = findViewById(R.id.textQuestions);
        textCorrects = findViewById(R.id.textCorrects);
        textIncorrects = findViewById(R.id.textIncorrects);

        // Inicializar el quiz (datos y preguntas)
        quizData = new HashMap<>();
        initializeQuiz();

        // Obtener el iterador de preguntas
        questionIterator = quizData.keySet().iterator();

        // Mostrar la primera pregunta
        if (questionIterator.hasNext()) {
            currentQuestion = questionIterator.next();
            questionTextView.setText(currentQuestion);
        }

        // Manejar el botón de Verdadero
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!finished) {
                    checkAnswer(true);
                }
            }
        });

        // Manejar el botón de Falso
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!finished) {
                    checkAnswer(false);
                }
            }
        });

        // Manejar el botón de Next
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!finished) {
                    nextAnswer();
                }
            }
        });

        // Inicializar el texto de la primera pregunta
        updateUI();
    }

    // Método para inicializar el quiz con preguntas y respuestas
    private void initializeQuiz() {
        quizData.put("Las avestruces pueden volar.", false);
        quizData.put("Los elefantes son los animales terrestres más grandes del mundo.", true);
        quizData.put("Los tiburones son mamíferos.", false);
        quizData.put("Los gatos pueden ver en completa oscuridad.", false);
        quizData.put("Las abejas son los únicos insectos que producen alimentos consumidos por los humanos.", true);
        quizData.put("Los pingüinos viven tanto en el Ártico como en la Antártida.", false);
    }

    // Método para verificar la respuesta y mostrar la siguiente pregunta
    private void checkAnswer(boolean userAnswer) {
        boolean correct = quizData.get(currentQuestion) == userAnswer;

        // Mostrar un Toast con el resultado y actualizar los contadores
        if (correct) {
            if (!finished) {
                Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                corrects++;
            }

        } else {
            if (!finished) {
                Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
                incorrects++;
            }
        }

        nextAnswer(); // Llamar a nextAnswer después de verificar la respuesta
    }

    // Método para mostrar la siguiente pregunta si la hay
    private void nextAnswer() {
        if (questionIterator.hasNext()) {
            currentQuestion = questionIterator.next();
            actualQuestion++;
        } else {
            finished = true;
            Toast.makeText(this, "¡Has terminado el quiz!", Toast.LENGTH_SHORT).show();
        }

        updateUI(); // Actualizar la interfaz
    }

    // Método para actualizar el texto en la interfaz
    private void updateUI() {
        questionTextView.setText(currentQuestion);
        textQuestions.setText(actualQuestion + " / 6");
        textCorrects.setText("Correctas: " + corrects);
        textIncorrects.setText("Incorrectas: " + incorrects);
    }
}
