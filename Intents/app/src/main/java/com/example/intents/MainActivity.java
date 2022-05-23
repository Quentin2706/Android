package com.example.intents;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String TOTO = "deuxActivite.extra.TOTO";
    private EditText mEditTextMessage;

    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;

    public static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditTextMessage = findViewById(R.id.edit_text_message);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);
    }


    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG,"clique sur bouton");
        String message = mEditTextMessage.getText().toString();
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(TOTO,message);
        startActivityForResult(intent,TEXT_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mEditTextMessage.setText("");

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.sendReply());

                if (reply.length() != 0) {
                    mReplyHeadTextView.setVisibility(View.VISIBLE);

                    mReplyTextView.setText(reply);
                    mReplyTextView.setVisibility(View.VISIBLE);
                } else {
                    mReplyHeadTextView.setVisibility(View.INVISIBLE);
                    mReplyTextView.setVisibility(View.INVISIBLE);
                }
            }
        }
    }
}