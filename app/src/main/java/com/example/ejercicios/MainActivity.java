package com.example.ejercicios;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {

    private TextView swipeDirectionText;
    private GestureDetector gestureDetector;
    private Handler handler;
    private Runnable askSwipeRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeDirectionText = findViewById(R.id.swipeDirectionText);
        handler = new Handler();

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float diffX = e2.getX() - e1.getX();
                float diffY = e2.getY() - e1.getY();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        return true;
                    }
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeDown();
                    } else {
                        onSwipeUp();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private void onSwipeRight() {
        swipeDirectionText.setText("Swipe a la derecha");
        // Toast.makeText(this, "Swipe a la derecha", Toast.LENGTH_SHORT).show();
        askSwipe();
    }

    private void onSwipeLeft() {
        swipeDirectionText.setText("Swipe a la izquierda");
        // Toast.makeText(this, "Swipe a la izquierda", Toast.LENGTH_SHORT).show();
        askSwipe();
    }

    private void onSwipeUp() {
        swipeDirectionText.setText("Swipe hacia arriba");
        // Toast.makeText(this, "Swipe hacia arriba", Toast.LENGTH_SHORT).show();
        askSwipe();
    }

    private void onSwipeDown() {
        swipeDirectionText.setText("Swipe hacia abajo");
        // gToast.makeText(this, "Swipe hacia abajo", Toast.LENGTH_SHORT).show();
        askSwipe();
    }

    private void askSwipe(){
        if (askSwipeRunnable != null) {
            handler.removeCallbacks(askSwipeRunnable);
        }
        askSwipeRunnable = new Runnable() {
            @Override
            public void run() {
                swipeDirectionText.setText("Swipe en cualquier dirección");
            }
        };
        handler.postDelayed(askSwipeRunnable, 3000);
    }
}

