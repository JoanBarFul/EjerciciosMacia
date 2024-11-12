package com.example.ejercicios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class CommentFragment extends Fragment {

    private EditText editTextName;
    private EditText editTextComment;
    private Button buttonAccept;
    private Button buttonClose;

    public CommentFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.comment_fragment, container, false);

        editTextName = rootView.findViewById(R.id.editTextName);
        editTextComment = rootView.findViewById(R.id.editTextComment);
        buttonAccept = rootView.findViewById(R.id.buttonAccept);
        buttonClose = rootView.findViewById(R.id.buttonClose);

        buttonAccept.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String comment = editTextComment.getText().toString().trim();

            // Llama a `updateTextView6` en `TextViewActivity` para añadir el nombre en negrita y el comentario
            ((TextViewActivity) requireActivity()).updateTextView6(name, comment);

            // Cierra el fragmento y elimina el blur
            ((TextViewActivity) requireActivity()).hideCommentFragment();
        });

        buttonClose.setOnClickListener(v -> {
            ((TextViewActivity) requireActivity()).hideCommentFragment();
        });

        return rootView;
    }
}

