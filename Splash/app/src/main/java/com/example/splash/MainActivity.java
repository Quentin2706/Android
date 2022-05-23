package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Animation bottomAnim;
    private Animation topAnim;
    private ImageView logo;
    private TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // enlevons la bare en haut

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager
                .LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation_layout);
        bottomAnim =
                AnimationUtils.loadAnimation(this,R.anim.bottom_animation_layout);

        logo = findViewById(R.id.imageView);
        desc = findViewById(R.id.textView);

        logo.setAnimation(topAnim);
        desc.setAnimation(bottomAnim);
    }
}