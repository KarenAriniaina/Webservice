package Tp.model;

import java.sql.Connection;
import java.sql.Date;

import Tp.dao.ObjetBDD;

public class Assurance extends ObjetBDD {
    private String idAssurance;
    private String idAvion;
    private Date DateDebut;
    private Date DateFin;
    private double MoisFin; 
    
    public Assurance() {
        this.setNomTable("Assurance");
        this.setPrimaryKey("idAssurance");
    }

    public String getIdAssurance() {
        return idAssurance;
    }

    public void setIdAssurance(String idAssurance) {
        this.idAssurance = idAssurance;
    }

    public String getidAvion() {
        return idAvion;
    }

    public void setidAvion(String idAvion) {
        this.idAvion = idAvion;
    }

    public Date getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        DateDebut = dateDebut;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date dateFin) {
        DateFin = dateFin;
    }

    public double getMoisFin() {
        return MoisFin;
    }

    public void setMoisFin(double moisFin) {
        MoisFin = moisFin;
    }

    @Override
    public ObjetBDD[] Find(Connection c) throws Exception {
        this.setNomTable("v_assuranceencours");
        ObjetBDD[] valiny= super.Find(c);
        this.setNomTable("Assurance");
        return valiny;
    }
    
}
