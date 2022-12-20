package Tp.controller;

import Tp.JSonData.JsonData;

import Tp.dao.ObjetBDD;
import Tp.model.Assurance;
import Tp.model.Kilometrage;
import Tp.model.Personne;
import Tp.model.Vehicule;
import Tp.model.VehiculeDelete;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehiculeController {

    @CrossOrigin
    @GetMapping("/Vehicules")
    public JsonData findAll() throws Exception {
        JsonData json = new JsonData();
        try {
            ObjetBDD[] liste = new Vehicule().Find(null);
            Vehicule[] lv = new Vehicule[liste.length];

            System.arraycopy(liste, 0, lv, 0, lv.length);

            json.setData(lv);
            json.setMessage("Operation reussit");
            json.setStatus(true);

        } catch (Exception e) {
            json.setData(null);
            json.setMessage("Operation echoue");
            json.setStatus(false);
            json.setErreur(e.getMessage());
        }
        return json;
    }

    @CrossOrigin
    @GetMapping("/Vehicule/{id}")
    public JsonData findById(@PathVariable String id) throws Exception {
        JsonData json = new JsonData();
        try {
            Vehicule km = new Vehicule();
            km.setIdVehicule(id);
            ObjetBDD[] liste = km.Find(null);
            Vehicule[] lv = new Vehicule[liste.length];
            System.arraycopy(liste, 0, lv, 0, lv.length);
            json.setData(lv);
            json.setMessage("Operation reussit");
            json.setStatus(true);
        } catch (Exception e) {
            json.setData(null);
            json.setMessage("Operation echoue");
            json.setStatus(false);
            json.setErreur(e.getMessage());
        }
        return json;
    }

    @CrossOrigin
    @PostMapping("/Vehicule")
    public JsonData save(@RequestParam(value = "plaque") String numero, @RequestParam(value = "idmarque") String marque) throws Exception {
        JsonData json = new JsonData();
        try {
            Vehicule v = new Vehicule();
            v.setPlaque(numero);
            v.setIdMarque(marque);
            v.Create(null);
            json.setData(null);
            json.setMessage("Operation reussit");
            json.setStatus(true);
        } catch (Exception e) {
            json.setData(null);
            json.setMessage("Operation echoue");
            json.setStatus(false);
            json.setErreur(e.getMessage());
        }
        return json;
    }

    @CrossOrigin
    @DeleteMapping("/Vehicules/{id}")
    public JsonData delete(@PathVariable(value = "id") String id) throws Exception {
        JsonData json = new JsonData();
        try {
            VehiculeDelete km = new VehiculeDelete();
            km.setIdVehicule(id);
            km.Create(null);
            json.setData(null);
            json.setMessage("Operation reussit");
            json.setStatus(true);
        } catch (Exception e) {
            json.setData(null);
            json.setMessage("Operation echoue ");
            json.setStatus(false);
            json.setErreur(e.getMessage());
        }
        return json;
    }

    @CrossOrigin 
    @GetMapping("/Vehicule/{id}/Kilometrages/")
    public JsonData findKilometrage(@RequestHeader(name = "token") String token,@PathVariable(name = "id") String id,@RequestHeader(name = "idPersonne") String idPersonne) throws Exception {
        Personne p=new Personne();
        p.setIdPersonne(idPersonne);
        p.setToken(token);
        JsonData json = new JsonData();
        if(!p.VerifToken()){
            json.setData(null);
            json.setMessage("Impossible de se logger");
            json.setStatus(false);
        }
        else{
             try {
                Kilometrage km = new Kilometrage();
                km.setIdVehicule(id);
                ObjetBDD[] liste = km.Find(null);
                Kilometrage[] lv = new Kilometrage[liste.length];
                System.arraycopy(liste, 0, lv, 0, lv.length);
    
                json.setData(lv);
                json.setMessage("Operation reussit");
                json.setStatus(true);
            } catch (Exception e) {
                json.setData(null);
                json.setMessage("Operation echoue");
                json.setStatus(false);
                json.setErreur(e.getMessage());
            } 
        }
        return json;
    }
    @GetMapping("/Vehicule/{id}/Assurances/")
    public JsonData findAssurance(@PathVariable(name = "id") String id) throws Exception{
        JsonData json = new JsonData();
        try {
            Assurance a=new Assurance();
            a.setIdVehicule(id);
            ObjetBDD[] liste = a.Find(null);
            Assurance[] lv = new Assurance[liste.length];
            System.arraycopy(liste, 0, lv, 0, lv.length);
            json.setData(lv);
            json.setMessage("Operation reussit");
            json.setStatus(true);
        } catch (Exception e) {
            json.setData(null);
            json.setMessage("Operation echoue");
            json.setStatus(false);
            json.setErreur(e.getMessage());
        } 
        return json;
    }
}
