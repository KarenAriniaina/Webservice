package Tp.model;

import java.sql.Connection;
import java.sql.Date;
import Tp.dao.ObjetBDD;

public class Kilometrage extends ObjetBDD{
	private String idKilometrage;
	private String idVehicule;
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

    

    public String getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(String idVehicule) {
        this.idVehicule = idVehicule;
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