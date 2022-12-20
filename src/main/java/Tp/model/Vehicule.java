package Tp.model;

import java.sql.Connection;

import Tp.dao.ObjetBDD;

public class Vehicule extends ObjetBDD
{
    private String idVehicule;
    private String Plaque;
    private String idMarque;
    private String NomMarque;

    public Vehicule() {
        this.setNomTable("Vehicule");
        this.setPrimaryKey("idVehicule");
    }
    public String getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(String idVehicule) {
        this.idVehicule = idVehicule;
    }

    public String getPlaque() {
        return Plaque;
    }

    public void setPlaque(String Plaque) {
        this.Plaque = Plaque;
    }

    public String getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(String idMarque) {
        this.idMarque = idMarque;
    }

    public String getNomMarque() {
        return NomMarque;
    }
    
    public void setNomMarque(String nomMarque) {
        NomMarque = nomMarque;
    }

    @Override
    public ObjetBDD[] Find(Connection c) throws Exception {
        this.setNomTable("v_vehicule");
        ObjetBDD[] valiny=super.Find(c);
        this.setNomTable("Vehicule");
        return valiny;
    }
}