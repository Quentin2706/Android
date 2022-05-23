package com.example.tuto1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView resultat;
    private EditText entre;
    private Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultat = findViewById(R.id.resultat);
        entre = findViewById(R.id.entre);
        go = findViewById(R.id.go);

        go.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == go) {
            resultat.setText(entre.getText());
        }

    }
}