package Tp.controller;

import Tp.JSonData.JsonData;
import java.sql.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Tp.dao.ObjetBDD;
import Tp.model.Kilometrage;
import Tp.model.Personne;

@RestController
public class KilometrageController {

    @CrossOrigin
    @GetMapping("/Kilometrages")
    public JsonData findAll() throws Exception {
        JsonData json = new JsonData();
        try {
            ObjetBDD[] liste = new Kilometrage().Find(null);
            Kilometrage[] lv = new Kilometrage[liste.length];
            System.arraycopy(liste, 0, lv, 0, lv.length);
            json.setData(lv);
            json.setMessage("Operation reussit");
            json.setStatus(true);
        } catch (Exception e) {
            json.setData(null);
            json.setMessage("Operation echoue");
            json.setStatus(false);
            json.setErreur(e.getCause().getMessage());
        }
        return json;
    }

    @GetMapping("/Kilometrages/{id}")
    public JsonData findById(@RequestParam String id) throws Exception {
        JsonData json = new JsonData();
        try {
            Kilometrage km = new Kilometrage();
            km.setIdKilometrage(id);
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
            json.setErreur(e.getCause().getMessage());
        }
        return json;
    }

    @PostMapping("/Kilometrage")
    public JsonData save(@RequestParam(value = "idAvion") String idAvion, @RequestParam(value = "date") Date date,
            @RequestParam(value = "debutKm") double debutKm, @RequestParam(value = "finKm") double finKm)
            throws Exception {
        JsonData json = new JsonData();
        try {
            Kilometrage km = new Kilometrage();
            km.setDate(date);
            km.setDebutKm(debutKm);
            km.setFinKm(finKm);
            km.setIdAvion(idAvion);
            km.Create(null);
            json.setData(null);
            json.setMessage("Operation reussit");
            json.setStatus(true);
        } catch (Exception e) {
            json.setData(null);
            json.setMessage("Operation echoue");
            json.setStatus(false);
            json.setErreur(e.getCause().getMessage());
        }
        return json;
    }

    @PutMapping("/Kilometrage/{id}")
    public JsonData Update(@RequestParam(value = "id") String id, @RequestParam(value = "idAvion") String idAvion,
            @RequestParam(value = "date") Date date, @RequestParam(value = "debutKm") double debutKm,
            @RequestParam(value = "finKm") double finKm) throws Exception {
        JsonData json = new JsonData();
        try {
            Kilometrage km = new Kilometrage();
            km.setIdKilometrage(id);
            km.setDate(date);
            km.setDebutKm(debutKm);
            km.setFinKm(finKm);
            km.setIdAvion(idAvion);
            km.Update(null);

        } catch (Exception e) {
            json.setData(null);
            json.setMessage("Operation echoue");
            json.setStatus(false);
            json.setErreur(e.getCause().getMessage());
        }
        return json;
    }
   
    // @DeleteMapping("/Kilometrages/{id}")
    // public JsonData delete(@RequestParam String id) throws Exception {
    // JsonData json = new JsonData();
    // try {
    // KilometrageDelete km = new KilometrageDelete();
    // km.setIdKilometrage(id);
    // km.Create(null);
    // json.setData(null);
    // json.setMessage("Operation reussit");
    // json.setStatus(true);

    // } catch (Exception e) {
    // json.setData(null);
    // json.setMessage("Operation echoue");
    // json.setStatus(false);
    // json.setErreur(e.getCause().getMessage());
    // }
    // return json;
    // }
}
