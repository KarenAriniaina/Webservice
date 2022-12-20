package Tp.model;

import Tp.dao.ObjetBDD;

public class VehiculeDelete extends ObjetBDD 
{      
    private String idVehicule;
    private String idVehiculeDelete;

    public VehiculeDelete() {
        this.setNomTable("VehiculeDelete");
        this.setPrimaryKey("idVehiculeDelete");
    }

    public String getIdVehicule() {
        return idVehicule;
    }

    public String getIdVehiculeDelete() {
        return idVehiculeDelete;
    }

    public void setIdVehicule(String idVehicule) {
        this.idVehicule = idVehicule;
    }

    public void setIdVehiculeDelete(String idVehiculeDelete) {
        this.idVehiculeDelete = idVehiculeDelete;
    }


}