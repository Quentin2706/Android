package com.example.mondeafpa;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import java.util.Random;

public class LanceLeDe extends AppCompatActivity {

    private static final String LOG_TAG = LanceLeDe.class.getSimpleName();

    private static TextView textView;
    private static Random rand = new Random();
    private  static int max;
    private  static int min = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lance_le_de);
        Intent intent = getIntent();
        textView = findViewById(R.id.resultat);
        max = Integer.parseInt(intent.getStringExtra(MainActivity.EXTRA_MAX));
        int nb = rand.nextInt(max-min+1)+1;
        textView.setText(Integer.toString(nb));
    }

    public void randomNumber(View view)
    {
        int nb = rand.nextInt(max-min+1)+1;
        textView.setText(Integer.toString(nb));
        Log.d(LOG_TAG,"test : "+nb);
    }

}