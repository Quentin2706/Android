package com.example.eval_mobile_quentin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Resultat extends AppCompatActivity {
    // On récupère les deux controles pour afficher les scores
    private TextView nbView;
    private TextView nbCoupsView;
    private Button relancer;
    private TextView resultat;

    // On récupère les deux images afin de les afficher ou non;
    private ImageView imgPerdu;
    private ImageView imgGagne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        // on récupère les éléments dans la fenêtre
        nbView = (TextView) findViewById(R.id.nbchoisi);
        nbCoupsView = (TextView) findViewById(R.id.nbcoups);
        imgGagne = (ImageView) findViewById(R.id.imgGagne);
        imgPerdu = (ImageView) findViewById(R.id.imgPerdu);
        relancer = (Button) findViewById(R.id.relancer);
        resultat = (TextView) findViewById(R.id.resultatTW);

        // On récupère les informations envoyées.
        Intent resultats = getIntent();
        Integer etat = resultats.getIntExtra("etat",0);
        Integer nbCoups = resultats.getIntExtra("coupsJoues",0);
        Integer nbAleatoire = resultats.getIntExtra("nombreAleatoire",0);


        // On met les valeurs dans les contrôles
        nbView.setText(nbAleatoire.toString());
        nbCoupsView.setText(nbCoups.toString());

        // Si etat = 0 c'est perdu !
        if (etat == 0)
        {
            resultat.setText("Dommage !");
            imgPerdu.setVisibility(View.VISIBLE);
        } else {
            resultat.setText("Bravo !");
            imgGagne.setVisibility(View.VISIBLE);
        }

        // On renvoi l'utilisateur à la page précédente.
        Intent retour = new Intent(this,MainActivity.class);
        relancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(retour);
                finish();
            }
        });


    }
}