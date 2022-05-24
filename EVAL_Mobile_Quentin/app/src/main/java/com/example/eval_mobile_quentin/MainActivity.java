package com.example.eval_mobile_quentin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random rand = new Random();
    private Integer nombre;
    private Integer compteur = 0;

    // Les controles de l'activité principale
    private Button sendBouton;
    private TextView historique;
    private EditText inputProposition;
    private Button cheat;
    private TextView cheatTW;
    private ProgressBar progressBar;

    private Intent resultatIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // enregistrement d'un nombre aléatoire.
        Integer max = 1000;
        Integer min = 0;
        nombre = rand.nextInt(max - min + 1) + 1;
        nombre = 122;
        // récupération de tous les controles
        sendBouton = (Button) findViewById(R.id.send_button);
        historique = (TextView) findViewById(R.id.historique);
        inputProposition = (EditText) findViewById(R.id.proposition);
        inputProposition.requestFocus();

        // bouton de triche et textview de triche
        cheat = (Button) findViewById(R.id.cheatmode);
        cheatTW = (TextView) findViewById(R.id.cheatTW);

        //progressbar
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        sendBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputProposition.clearFocus();
                // Cette méthode sert a fermer le clavier virtuel
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                imm.showSoftInput(v.getWindowToken(),1);

                // on regarde si ce n'est pas une chaine vide sinon on fait rien
                if (inputProposition.getText().toString().length() > 0) {
                    compteur++;
                    progressBar.setProgress(compteur);
                    Integer nbPropose = Integer.parseInt(inputProposition.getText().toString());
                    // on vérifie que nous sommes toujours en dessous de 8 propositions
                    checkNb(nbPropose);
                    if (compteur >= 8)
                        finDuJeu(0);
                }
            }
        });

        cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cheatTW.setText(cheatTW.getText() + " " + nombre);
                cheatTW.setVisibility(View.VISIBLE);
            }
        });

    }

    /**
     * @param nb =>  nombre proposé par l'utilisateur qu'on doit check
     */
    private void checkNb(Integer nb) {
        inputProposition.setText("");
        Double marge = 0.0;

        if (nb > nombre) {
            marge = (Double.valueOf(nombre) / Double.valueOf(nb)) * 100;
        } else {
            marge = (Double.valueOf(nb) / Double.valueOf(nombre)) * 100;
        }

        Log.d("**********************************************", "checkNb: "+marge);

        if (marge == 100) {
            finDuJeu(1);
        } else if (marge < 10) {
            historique.setText(historique.getText() + "\n" + "Tu es glacé ");
        } else if (marge < 25) {
            historique.setText(historique.getText() + "\n" + "Tu es froid");
        } else if (marge < 50) {
            historique.setText(historique.getText() + "\n" + "Tu es tiède");
        } else if (marge < 75) {
            historique.setText(historique.getText() + "\n" + "Tu es assez chaud");
        } else {
            historique.setText(historique.getText() + "\n" + "Tu es bouillant");
        }

    }

    /**
     * @param etat => contient un int entre 0 et 1 pour dire si c'est  1 (gagné) ou 0 (perdu)
     */
    private void finDuJeu(Integer etat) {
        resultatIntent = new Intent(this, Resultat.class);

        resultatIntent.putExtra("nombreAleatoire", nombre);
        resultatIntent.putExtra("coupsJoues", compteur);
        resultatIntent.putExtra("etat", etat);

        startActivity(resultatIntent);
        finish();
    }


}