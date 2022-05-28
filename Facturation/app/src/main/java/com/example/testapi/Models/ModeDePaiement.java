package com.example.testapi.Models;

import java.io.Serializable;

public class ModeDePaiement implements Serializable {

    private Integer id;
    private String libelle;

    public ModeDePaiement(Integer id, String libelle)
    {
        this.id = id;
        this.libelle=libelle;
    }

    public ModeDePaiement()
    {

    }

    public String getLibelle()
    {
        return this.libelle;
    }

    public int getId()
    {
        return this.id;
    }

    public void setLibelle(String libelle)
    {
        this.libelle=libelle;
    }


    @Override
    public String toString()
    {
        return this.libelle;
    }
}
