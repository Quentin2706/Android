package com.example.testapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testapi.Models.ModeDePaiement;
import com.example.testapi.Parsers.JsonMdpParser;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

public class detailModeDePaiement extends AppCompatActivity {

    private EditText libelle;
    private Button boutonSuppr;
    private Button boutonModif;

    private ModeDePaiement obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mode_de_paiement);

        libelle = findViewById(R.id.editTextLibelle);
        boutonSuppr = findViewById(R.id.send_suppr);
        boutonModif = findViewById(R.id.send_modif);

        Intent liste = getIntent();
        obj = (ModeDePaiement) liste.getSerializableExtra("mdpObj");

        libelle.setText(obj.getLibelle());
        Log.d("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE", "onCreate: "+obj.getId());

        Intent listeRefresh = new Intent(this, ListeModeDePaiement.class);

        boutonSuppr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest(v);
                startActivity(listeRefresh);
                finish();

            }
        });

        boutonModif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (libelle.getText().toString().trim().length() > 0)
                {
                    Log.d("CHANGEMENT", "onClick: CHANGEMENT");
                    obj.setLibelle(libelle.getText().toString().trim());
                    sendRequestModif(v);
                    startActivity(listeRefresh);
                    finish();
                }
            }
        });


    }


    //méthode qui vérifie si le réseau est disponible
    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    //méthode de vérification si internet est disponible
    public Boolean isOnline() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal == 0);
            return reachable;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Lancement de la recherche
     */
    public void sendRequest(View view) {
        Log.d("*************** Send **********", "sendRequest: ");        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isNetworkAvailable()) {

            AppelAPISuppr task = new AppelAPISuppr();
            task.execute();

            if (isOnline()) {
                // On a accès au réseau et à internet
                // On récupère l'id de la personne et on enlève les espaces
//                EtIdPersonne.setText(EtIdPersonne.getText().toString().trim());
//                Log.d("Test", "onSendRequest: idPersonne" + EtIdPersonne.getText());
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                //  on lance une tâche asynchrone en fonction de la saisie
//                AppelAPI task = new AppelAPI();
////                if (EtIdPersonne.getText().toString().trim()!="" )
//                    task.execute();
            } else {
                Toast.makeText(view.getContext(), getApplicationContext().getResources().getString(R.string.no_online), Toast.LENGTH_LONG);
                Log.d("DEBUG", "sendRequest: pas d'internet");
            }
        } else {
            Toast.makeText(view.getContext(), getApplicationContext().getResources().getString(R.string.no_network), Toast.LENGTH_LONG);
            Log.d("DEBUG", "sendRequest: pas de reseau");

        }
    }




    private class AppelAPISuppr extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            //la requête doit nous retourner un objet JSON, ici on va le gérer en string simple
            String retour = new String();
            String data = "";

            //exécution de la requête http avec en paramètres l'id saisi ou pas
            data = ((new ApiHttpClient()).deleteMDP(obj.getId()));
            if (data != null) {
                Log.d("test", " retour:" + data);
                //on log et on renvoi les données
                return data;
            } else {
                return null;
            }

        }


        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);

            Log.d("testssssss", "onPostExecute: "+data);
            List<ModeDePaiement> listeMdp = new ArrayList<ModeDePaiement>();

            //si l'objet retourné n'est pas null on affiche le résultat brut
            if (data != null) {
                try {
                    Log.d("***************************** On passe *****************", "onPostExecute: ");
                    listeMdp= JsonMdpParser.parseListeMdp(data);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Lancement de la recherche
     */
    public void sendRequestModif(View view) {
        Log.d("*************** Send **********", "sendRequestModif: ");        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isNetworkAvailable()) {

            AppelAPIModif task = new AppelAPIModif();
            task.execute();

            if (isOnline()) {
                // On a accès au réseau et à internet
                // On récupère l'id de la personne et on enlève les espaces
//                EtIdPersonne.setText(EtIdPersonne.getText().toString().trim());
//                Log.d("Test", "onSendRequest: idPersonne" + EtIdPersonne.getText());
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                //  on lance une tâche asynchrone en fonction de la saisie
//                AppelAPI task = new AppelAPI();
////                if (EtIdPersonne.getText().toString().trim()!="" )
//                    task.execute();
            } else {
                Toast.makeText(view.getContext(), getApplicationContext().getResources().getString(R.string.no_online), Toast.LENGTH_LONG);
                Log.d("DEBUG", "sendRequest: pas d'internet");
            }
        } else {
            Toast.makeText(view.getContext(), getApplicationContext().getResources().getString(R.string.no_network), Toast.LENGTH_LONG);
            Log.d("DEBUG", "sendRequest: pas de reseau");

        }
    }


    private class AppelAPIModif extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            //la requête doit nous retourner un objet JSON, ici on va le gérer en string simple
            String retour = new String();
            String data = "";

            //exécution de la requête http avec en paramètres l'id saisi ou pas
            data = ((new ApiHttpClient()).modifMDP(obj));
            if (data != null) {
                Log.d("test", " retourggggg:" + data);
                //on log et on renvoi les données
                return data;
            } else {
                return null;
            }

        }


        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);

            Log.d("testssssss", "onPostExecute: "+data);
            List<ModeDePaiement> listeMdp = new ArrayList<ModeDePaiement>();

            //si l'objet retourné n'est pas null on affiche le résultat brut
            if (data != null) {
                try {
                    Log.d("***************************** On passe *****************", "onPostExecute: ");
                    listeMdp= JsonMdpParser.parseListeMdp(data);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}