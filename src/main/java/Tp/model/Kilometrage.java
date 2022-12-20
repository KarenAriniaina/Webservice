package Tp.model;

import java.sql.Connection;
import java.sql.Date;
import Tp.dao.ObjetBDD;

public class Kilometrage extends ObjetBDD{
	private String idKilometrage;
	private String idAvion;
	private Date date;
	private double debutKm;
	private double finKm;

    public Kilometrage() {
        this.setNomTable("Kilometrage");
        this.setPrimaryKey("idKilometrage");
    }

    public String getIdKilometrage() {
        return idKilometrage;
    }

    public void setIdKilometrage(String idKilometrage) {
        this.idKilometrage = idKilometrage;
    }

    public String getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(String idAvion) {
        this.idAvion = idAvion;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getDebutKm() {
        return debutKm;
    }

    public void setDebutKm(double debutKm) {
        this.debutKm = debutKm;
    }

    public double getFinKm() {
        return finKm;
    }

    public void setFinKm(double finKm) {
        this.finKm = finKm;
    }
    
    @Override
    public ObjetBDD[] Find(Connection c) throws Exception {
        this.setNomTable("v_Kilometrage");
        ObjetBDD[] valiny=super.Find(c);
        this.setNomTable("Kilometrage");
        return valiny;
    }  
    
	
}