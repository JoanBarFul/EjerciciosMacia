package com.example.ejercicios;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DragAndDrop extends AppCompatActivity {
    private ImageView ballView;
    private FrameLayout ballFrame;
    private TextView textView;
    private float dX, dY;
    private float velocityY = 0; // Velocidad en Y
    private float gravity = 1.0f; // Gravedad
    private Handler handler = new Handler();
    private int frameHeight, ballHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drag_and_drop);

        ballView = findViewById(R.id.ballView);
        ballFrame = findViewById(R.id.ballFrame);
        textView = findViewById(R.id.text);

        ballFrame.post(() -> {
            frameHeight = ballFrame.getHeight();
            ballHeight = ballView.getHeight();

            // Pos inicial pelota
            float centerX = (((ballFrame.getWidth() - ballView.getWidth()) / 2) - textView.getWidth()/2) - ballView.getWidth()/3;
            float centerY = (frameHeight - ballHeight) / 2;
            ballView.setX(centerX);
            ballView.setY(centerY);
        });

        ballView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        dX = view.getX() - event.getRawX();
                        dY = view.getY() - event.getRawY();
                        // Parar Simulacion
                        handler.removeCallbacksAndMessages(null);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        // Actualizar la posición con el arrastre
                        float newX = event.getRawX() + dX;
                        float newY = event.getRawY() + dY;

                        // Restringir el movimiento dentro del frame
                        if (newX < 0) newX = 0;
                        else if (newX + ballView.getWidth() > ballFrame.getWidth())
                            newX = ballFrame.getWidth() - ballView.getWidth();

                        if (newY < 0) newY = 0;
                        else if (newY + ballHeight > frameHeight)
                            newY = frameHeight - ballHeight;

                        view.setX(newX);
                        view.setY(newY);
                        return true;

                    case MotionEvent.ACTION_UP:
                        // Iniciar la simulación
                        velocityY = gravity; // Establecer velocidad Y
                        startFallSimulation();
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private void startFallSimulation() {
        handler.post(new Runnable() {
            @Override
            public void run() {

                float newY = ballView.getY() + velocityY;

                // Aplicar gravedad
                velocityY += gravity;

                // Limites
                if (newY + ballHeight >= frameHeight) {
                    newY = frameHeight - ballHeight;
                    velocityY = 0; // Detener la bola al llegar  abajo
                }

                // Actualizar la posición Y
                ballView.setY(newY);

                if (velocityY > 0) {
                    handler.postDelayed(this, 16);
                }
            }
        });
    }
}
