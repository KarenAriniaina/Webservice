package Tp.model;



import java.sql.Connection;
import Tp.dao.ObjetBDD;

public class DateEntretien extends ObjetBDD{
    private String idDateEntretien;
    private String idAvion;
    private String idEntretien;

    public DateEntretien() {
    }

    public String getIdDateEntretien() {
        return idDateEntretien;
    }

    public void setIdDateEntretien(String idDateEntretien) {
        this.idDateEntretien = idDateEntretien;
    }

    public String getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(String idAvion) {
        this.idAvion = idAvion;
    }

    public String getIdEntretien() {
        return idEntretien;
    }

    public void setIdEntretien(String idEntretien) {
        this.idEntretien = idEntretien;
    }
    
    @Override
    public ObjetBDD[] Find(Connection c) throws Exception {
        this.setNomTable("v_Entretient");
        ObjetBDD[] valiny = super.Find(c);
        this.setNomTable("Avion");
        return valiny;
    }

}
