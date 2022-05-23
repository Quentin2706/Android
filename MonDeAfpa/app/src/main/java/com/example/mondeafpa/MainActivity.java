package com.example.mondeafpa;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MAX = "com.example.mondeafpa.MAX";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendDe(View view) {
        TextView textView = findViewById(R.id.nbDe);

        Intent intent =  new Intent(this, LanceLeDe.class);
        intent.putExtra(EXTRA_MAX, textView.getText().toString());
        startActivity(intent);
    }
}