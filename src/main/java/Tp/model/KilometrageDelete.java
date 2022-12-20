package Tp.model;

import Tp.dao.ObjetBDD;

public class KilometrageDelete extends ObjetBDD{
	private String idKilometrage;
	private String idKilometrageDelete;

    public KilometrageDelete() {
        this.setNomTable("KilometrageDelete");
        this.setPrimaryKey("idKilometrageDelete");
    }

    public String getIdKilometrage() {
        return idKilometrage;
    }

    public void setIdKilometrage(String idKilometrage) {
        this.idKilometrage = idKilometrage;
    }

    public String getIdKilometrageDelete() {
        return idKilometrageDelete;
    }

    public void setIdKilometrageDelete(String idKilometrageDelete) {
        this.idKilometrageDelete = idKilometrageDelete;
    }
        
}