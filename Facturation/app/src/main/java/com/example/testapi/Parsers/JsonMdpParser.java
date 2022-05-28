package com.example.testapi.Parsers;

import android.util.Log;

import com.example.testapi.Models.ModeDePaiement;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonMdpParser {
    public static  List<ModeDePaiement> parseListeMdp(String json) throws JSONException {
        List<ModeDePaiement> listeMdp= new ArrayList<ModeDePaiement>();
        try {
            JSONObject jObj = new JSONObject(json);
            Log.d("*********************** JSON ******************************", "parseListeMdp: "+jObj.getJSONArray("hydra:member").length());
            for (int i=0;i<jObj.getJSONArray("hydra:member").length();i++)
            {
                JSONObject objet = ((JSONObject)jObj.getJSONArray("hydra:member").get(i));
                Integer id = Integer.parseInt(objet.getString("@id").substring(objet.getString("@id").lastIndexOf("/")+1));
                ModeDePaiement mdp = new ModeDePaiement(id, objet.getString("libelle"));
                listeMdp.add(mdp);
                Log.d("*********************** JSON ******************************", "parseListeMdp: "+mdp);

            }
        }
        catch (Exception e){}

//            JSONObject jObj = new JSONObject(json);
//            mdp.SetLibelle(jObj.getString("libelle"));

        return  listeMdp;
    }
}
