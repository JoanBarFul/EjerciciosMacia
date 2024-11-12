package com.example.ejercicios;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class TextViewActivity extends AppCompatActivity {

    private Button buttonComment;
    private View blurBackground;
    private TextView mainTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_view_activity);

        buttonComment = findViewById(R.id.buttonComment);
        blurBackground = findViewById(R.id.blurBackground);
        mainTextView = findViewById(R.id.textView6);

        buttonComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentFragment();
            }
        });
    }

    private void showCommentFragment() {
        blurBackground.setVisibility(View.VISIBLE);
        findViewById(R.id.container).setVisibility(View.VISIBLE);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new CommentFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void hideCommentFragment() {
        // Oculta el fondo borroso y el fragmento
        blurBackground.setVisibility(View.GONE);
        findViewById(R.id.container).setVisibility(View.GONE);
        getSupportFragmentManager().popBackStack();
    }

    public void updateTextView6(String name, String comment) {
        // Formateamos el nombre en negrita, agregamos un salto de línea y luego el comentario
        String formattedText = "<br><b>" + name + ":</b><br>" + comment + "<br><br>";

        // Agrega el nuevo texto al final del texto actual en textView6 interpretándolo como HTML
        mainTextView.append(Html.fromHtml(formattedText, Html.FROM_HTML_MODE_LEGACY));
    }


}
