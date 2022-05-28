package com.example.testapi;

import android.util.Log;

import com.example.testapi.Models.ModeDePaiement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;


public class ApiHttpClient {
    //on définit une propriété pour l'url de base
    private static String BASE_URL = "https://gnosys.quentinbalair.fr/api/";
//    private static String BASE_URL = "http://127.0.0.1:8000/api/";

    final GsonBuilder builder = new GsonBuilder();
    final Gson gson = builder.create();


    public String getData(String idPersonne) {
        HttpURLConnection con = null;
        InputStream is = null;
        String url = "";
        int responseCode;
        StringBuffer buffer = new StringBuffer();

        // On ajoute l'idPersonne à l'url, s'il y a pas d'id, on ramène la liste
        url = BASE_URL + idPersonne;
        Log.d("Test", "URL: " + url);
        try {
            // on établit la connection
            con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(false);
            con.connect();

            // en fonction du code réponse, on récupère les données ou l'erreur
            responseCode = con.getResponseCode();
            if (200 <= responseCode && responseCode <= 299) {
                is = con.getInputStream();
            } else {
                is = con.getErrorStream();
            }
            // Let's read the response
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null)
                buffer.append(line + "\r\n");

            // on ferme la connection
            is.close();
            con.disconnect();

            //on récupère le résultat en chaîne de caractères (ici json)
            return buffer.toString();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Throwable t) {
            }
            try {
                con.disconnect();
            } catch (Throwable t) {
            }
        }
        return null;
    }

    public String getListeMDP() {
        HttpURLConnection con = null;
        InputStream is = null;
        String url = "";
        int responseCode;
        StringBuffer buffer = new StringBuffer();

        // On ajoute l'idPersonne à l'url, s'il y a pas d'id, on ramène la liste
        url = BASE_URL + "mode_de_paiements/";
        Log.d("Test", "URL: " + url);
        try {
            // on établit la connection
            con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(false);
            con.connect();

            // en fonction du code réponse, on récupère les données ou l'erreur
            responseCode = con.getResponseCode();
            if (200 <= responseCode && responseCode <= 299) {
                is = con.getInputStream();
            } else {
                is = con.getErrorStream();
            }
            // Let's read the response
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null)
                buffer.append(line + "\r\n");

            // on ferme la connection
            is.close();
            con.disconnect();

            //on récupère le résultat en chaîne de caractères (ici json)
            return buffer.toString();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Throwable t) {
            }
            try {
                con.disconnect();
            } catch (Throwable t) {
            }
        }
        return null;
    }

    public String deleteMDP(Integer id) {
        HttpURLConnection con = null;
        InputStream is = null;
        String url = "";
        int responseCode;
        StringBuffer buffer = new StringBuffer();

        // On ajoute l'idPersonne à l'url, s'il y a pas d'id, on ramène la liste
        url = BASE_URL + "mode_de_paiements/" + id;
        Log.d("Test", "URL: " + url);
        try {
            // on établit la connection
            con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestMethod("DELETE");
            con.setDoInput(true);
            con.setDoOutput(false);
            con.connect();

            // en fonction du code réponse, on récupère les données ou l'erreur
            responseCode = con.getResponseCode();
            if (200 <= responseCode && responseCode <= 299) {
                is = con.getInputStream();
            } else {
                is = con.getErrorStream();
            }
            // Let's read the response
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null)
                buffer.append(line + "\r\n");

            // on ferme la connection
            is.close();
            con.disconnect();

            //on récupère le résultat en chaîne de caractères (ici json)
            return buffer.toString();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Throwable t) {
            }
            try {
                con.disconnect();
            } catch (Throwable t) {
            }
        }
        return null;
    }


    public String modifMDP(ModeDePaiement mdp) {

        String json = gson.toJson(mdp);

        Log.d("ssssss", "modifMDP: " + json);

                

        HttpURLConnection con = null;
        InputStream is = null;
        String url = "";
        int responseCode;
        StringBuffer buffer = new StringBuffer();

        // On ajoute l'idPersonne à l'url, s'il y a pas d'id, on ramène la liste
        url = BASE_URL + "mode_de_paiements/" + mdp.getId();
        Log.d("Test", "URL: " + url);
        try {
            // on établit la connection
            con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            con.setRequestProperty("Content-Length", String.valueOf(json.getBytes(StandardCharsets.UTF_8).length));

            con.setDoInput(true);
            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(json.getBytes(StandardCharsets.UTF_8));
            }

            con.connect();

            // en fonction du code réponse, on récupère les données ou l'erreur
            responseCode = con.getResponseCode();
            if (200 <= responseCode && responseCode <= 299) {
                is = con.getInputStream();
            } else {
                is = con.getErrorStream();
            }
            // Let's read the response
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null)
                buffer.append(line + "\r\n");

            // on ferme la connection
            is.close();
            con.disconnect();

            //on récupère le résultat en chaîne de caractères (ici json)
            return buffer.toString();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Throwable t) {
            }
            try {
                con.disconnect();
            } catch (Throwable t) {
            }
        }
        return null;
    }

    public String ajoutMDP(ModeDePaiement mdp) {
        Log.d("ssssss", "ajoutMDP: " + mdp);
        String json = gson.toJson(mdp);

        Log.d("ssssss", "ajoutMDP: " + json);

        HttpURLConnection http = null;
        InputStream is = null;
        String url = "";
        int responseCode;
        StringBuffer buffer = new StringBuffer();

        url = BASE_URL + "mode_de_paiements/";
        try {
            http = (HttpURLConnection) (new URL(url)).openConnection();
            http.setRequestMethod("GET"); // PUT is another valid option

            byte[] out = json .getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            try(OutputStream os = http.getOutputStream()) {
                os.write(out);
            }

            http.connect();



            // en fonction du code réponse, on récupère les données ou l'erreur
            responseCode = http.getResponseCode();
            if (200 <= responseCode && responseCode <= 299) {
                is = http.getInputStream();
            } else {
                is = http.getErrorStream();
            }
            // Let's read the response
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null)
                buffer.append(line + "\r\n");

            // on ferme la connection
            is.close();
            http.disconnect();

            //on récupère le résultat en chaîne de caractères (ici json)
            return buffer.toString();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Throwable t) {
            }
            try {
                http.disconnect();
            } catch (Throwable t) {
            }
        }
        return null;

    }


}
