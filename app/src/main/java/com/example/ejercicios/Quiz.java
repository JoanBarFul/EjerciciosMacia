package com.example.ejercicios;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class Quiz extends AppCompatActivity {

    private HashMap<String, Boolean> quizData;
    private HashMap<String, Boolean> answeredQuestions;  // HashMap para almacenar si una pregunta ha sido respondida
    private ArrayList<String> questionList;  // Lista de preguntas para poder acceder a ellas por índice
    private String currentQuestion;
    private TextView questionTextView, textQuestions, textCorrects, textIncorrects;
    private Button trueButton, falseButton, nextButton, prevButton;
    private int corrects = 0;
    private int incorrects = 0;
    private int actualQuestionIndex = 0;  // Índice actual de la pregunta
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
        answeredQuestions = new HashMap<>();  // HashMap para almacenar las preguntas respondidas
        questionList = new ArrayList<>();  // Lista de preguntas
        initializeQuiz();

        // Restaurar el estado si lo hay
        if (savedInstanceState != null) {
            corrects = savedInstanceState.getInt("corrects");
            incorrects = savedInstanceState.getInt("incorrects");
            actualQuestionIndex = savedInstanceState.getInt("actualQuestionIndex");
            finished = savedInstanceState.getBoolean("finished");

            answeredQuestions = (HashMap<String, Boolean>) savedInstanceState.getSerializable("answeredQuestions");
        }

        // Establecer la pregunta actual y actualizar la UI
        currentQuestion = questionList.get(actualQuestionIndex);
        updateUI();

        // Manejar el botón de Verdadero
        trueButton.setOnClickListener(v -> {
            if (!answeredQuestions.getOrDefault(currentQuestion, false)) {  // Verifica si la pregunta ya fue respondida
                checkAnswer(true);
            }
        });

        // Manejar el botón de Falso
        falseButton.setOnClickListener(v -> {
            if (!answeredQuestions.getOrDefault(currentQuestion, false)) {  // Verifica si la pregunta ya fue respondida
                checkAnswer(false);
            }
        });

        // Manejar el botón de Next
        nextButton.setOnClickListener(v -> {
            if (actualQuestionIndex < questionList.size() - 1) {  // Evita ir más allá de la última pregunta
                actualQuestionIndex++;
                currentQuestion = questionList.get(actualQuestionIndex);
                updateUI();
            } else {
                Toast.makeText(this, "Has llegado al final del quiz", Toast.LENGTH_SHORT).show();
            }
        });

        // Manejar el botón de Prev (Pregunta anterior)
        prevButton.setOnClickListener(v -> {
            if (actualQuestionIndex > 0) {  // Evita ir más allá de la primera pregunta
                actualQuestionIndex--;
                currentQuestion = questionList.get(actualQuestionIndex);
                updateUI();
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

        // Agregar todas las preguntas a una lista para fácil acceso por índice
        questionList.addAll(quizData.keySet());

        // Inicializar las preguntas como no respondidas
        for (String question : quizData.keySet()) {
            answeredQuestions.put(question, false);
        }
    }

    // Método para verificar la respuesta y bloquear los botones después de responder
    private void checkAnswer(boolean userAnswer) {
        boolean correct = quizData.get(currentQuestion) == userAnswer;

        if (correct) {
            Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
            corrects++;
        } else {
            Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
            incorrects++;
        }

        // Marcar la pregunta como respondida
        answeredQuestions.put(currentQuestion, true);

        // Bloquear los botones para que no se pueda volver a responder esta pregunta
        trueButton.setEnabled(false);
        falseButton.setEnabled(false);

        updateUI();
    }

    // Método para actualizar la interfaz
    private void updateUI() {
        questionTextView.setText(currentQuestion);
        textQuestions.setText((actualQuestionIndex + 1) + " / " + quizData.size());
        textCorrects.setText("Correctas: " + corrects);
        textIncorrects.setText("Incorrectas: " + incorrects);

        // Habilitar o deshabilitar los botones dependiendo de si la pregunta ya fue respondida
        if (answeredQuestions.get(currentQuestion)) {
            trueButton.setEnabled(false);
            falseButton.setEnabled(false);
        } else {
            trueButton.setEnabled(true);
            falseButton.setEnabled(true);
        }
    }

    // Guardar el estado actual de la actividad antes de que sea destruida por la rotación
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("corrects", corrects);
        outState.putInt("incorrects", incorrects);
        outState.putInt("actualQuestionIndex", actualQuestionIndex);
        outState.putBoolean("finished", finished);
        outState.putSerializable("answeredQuestions", answeredQuestions);  // Guardar el estado de las respuestas
    }
}
