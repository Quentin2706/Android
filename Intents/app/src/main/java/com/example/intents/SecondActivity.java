package com.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String LOG_TAG = SecondActivity.class.getSimpleName();
    private static final String EXTRA_REPLY = "deuxActivite.extra.REPLY";
    private EditText mReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.TOTO);
        TextView textView = findViewById(R.id.text_message);
        textView.setText(message);
        mReply = findViewById(R.id.edit_text_reponse);
    }

    public void returnReply(View view) {
        // récupération de la réponse de l'utilisateur
        String reply = mReply.getText().toString();
        // Instanciation d'un nouvel intent
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY,reply);
        // mise à jour de l'état de la réponse
        setResult(RESULT_OK, replyIntent);

        // on termine l'activité 2
        finish();
    }

    public static String sendReply()
    {
        return SecondActivity.EXTRA_REPLY;
    }
}