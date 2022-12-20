package Tp.model;

import java.sql.Connection;

import Tp.dao.ObjetBDD;

public class Avion extends ObjetBDD {
    private String idAvion;
    private String Nom;
    private int NbrPlace;
    private String idModele;
    private String Modele;

    public Avion() {
        this.setNomTable("Avion");
        this.setPrimaryKey("idAvion");
    }

    public String getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(String idAvion) {
        this.idAvion = idAvion;
    }

    @Override
    public ObjetBDD[] Find(Connection c) throws Exception {
        this.setNomTable("v_Avion");
        ObjetBDD[] valiny = super.Find(c);
        this.setNomTable("Avion");
        return valiny;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public int getNbrPlace() {
        return NbrPlace;
    }

    public void setNbrPlace(int nbrPlace) {
        NbrPlace = nbrPlace;
    }

    public String getIdModele() {
        return idModele;
    }

    public void setIdModele(String idModele) {
        this.idModele = idModele;
    }

    public String getModele() {
        return Modele;
    }

    public void setModele(String modele) {
        Modele = modele;
    }
}