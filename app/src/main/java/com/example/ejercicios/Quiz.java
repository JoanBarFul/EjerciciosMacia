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
    private Button trueButton, falseButton, nextButton, prevButton;
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
        prevButton = findViewById(R.id.buttonPrev);
        textQuestions = findViewById(R.id.textQuestions);
        textCorrects = findViewById(R.id.textCorrects);
        textIncorrects = findViewById(R.id.textIncorrects);

        // Inicializar el quiz (datos y preguntas)
        quizData = new HashMap<>();
        initializeQuiz();

        // Restaurar el estado si lo hay
        if (savedInstanceState != null) {
            // Restaura los valores previos
            corrects = savedInstanceState.getInt("corrects");
            incorrects = savedInstanceState.getInt("incorrects");
            actualQuestion = savedInstanceState.getInt("actualQuestion");
            finished = savedInstanceState.getBoolean("finished");

            // Restaura el iterador al estado correcto
            questionIterator = quizData.keySet().iterator();
            for (int i = 0; i < actualQuestion; i++) {
                currentQuestion = questionIterator.next();
            }
        } else {
            // Obtener el iterador de preguntas
            questionIterator = quizData.keySet().iterator();
            if (questionIterator.hasNext()) {
                currentQuestion = questionIterator.next();
            }
        }

        // Mostrar la primera pregunta o restaurar la actual
        questionTextView.setText(currentQuestion);
        updateUI();

        // Manejar el botón de Verdadero
        trueButton.setOnClickListener(v -> {
            if (!finished) {
                checkAnswer(true);
            }
        });

        // Manejar el botón de Falso
        falseButton.setOnClickListener(v -> {
            if (!finished) {
                checkAnswer(false);
            }
        });

        // Manejar el botón de Next
        nextButton.setOnClickListener(v -> {
            if (!finished) {
                nextAnswer();
            }
        });

        // Manejar el botón de Prev (Pregunta anterior)
        prevButton.setOnClickListener(v -> {
            if (actualQuestion > 1) {
                previousAnswer();
            }
        });
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

        // Bloquear los botones después de responder
        trueButton.setEnabled(false);
        falseButton.setEnabled(false);
        updateUI();
    }

    // Método para mostrar la siguiente pregunta si la hay
    private void nextAnswer() {
        if (questionIterator.hasNext()) {
            currentQuestion = questionIterator.next();
            actualQuestion++;
            trueButton.setEnabled(true);  // Habilitar los botones para la siguiente pregunta
            falseButton.setEnabled(true);
        } else {
            finished = true;
            Toast.makeText(this, "¡Has terminado el quiz!", Toast.LENGTH_SHORT).show();
        }
        updateUI();
    }

    // Método para ir a la pregunta anterior
    private void previousAnswer() {
        // Reiniciar el iterador y avanzar hasta la pregunta anterior
        questionIterator = quizData.keySet().iterator();
        for (int i = 0; i < actualQuestion - 1; i++) {
            currentQuestion = questionIterator.next();
        }
        actualQuestion--;
        trueButton.setEnabled(false);  // Deshabilitar los botones para que no se pueda responder
        falseButton.setEnabled(false);

        updateUI();
    }

    // Método para actualizar el texto en la interfaz
    private void updateUI() {
        questionTextView.setText(currentQuestion);
        textQuestions.setText(actualQuestion + " / 6");
        textCorrects.setText("Correctas: " + corrects);
        textIncorrects.setText("Incorrectas: " + incorrects);
    }

    // Guardar el estado actual de la actividad antes de que sea destruida por la rotación
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("corrects", corrects);
        outState.putInt("incorrects", incorrects);
        outState.putInt("actualQuestion", actualQuestion);
        outState.putBoolean("finished", finished);
    }
}
