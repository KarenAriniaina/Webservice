package Tp.controller;

import Tp.JSonData.JsonData;

import Tp.dao.ObjetBDD;
import Tp.model.Assurance;
import Tp.model.Avion;
import Tp.model.DateEntretien;
import Tp.model.Kilometrage;
import Tp.model.Personne;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvionController {

    @CrossOrigin
    @GetMapping("/Avions")
    public JsonData findAll() throws Exception {
        JsonData json = new JsonData();
        try {
            ObjetBDD[] liste = new Avion().Find(null);
            Avion[] lv = new Avion[liste.length];

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
    @GetMapping("/Avion/{id}")
    public JsonData findById(@PathVariable String id) throws Exception {
        JsonData json = new JsonData();
        try {
            Avion km = new Avion();
            km.setIdAvion(id);
            ObjetBDD[] liste = km.Find(null);
            Avion[] lv = new Avion[liste.length];
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
    @PostMapping("/Avion")
    public JsonData save(@RequestParam(value = "nom") String nom, @RequestParam(value = "NbrPlace") int NbrPlace,
            @RequestParam(value = "Modele") String model,
            @RequestParam(value = "photo") String photo) throws Exception {
        JsonData json = new JsonData();
        try {
            Avion v = new Avion();
            v.setIdModele(model);
            v.setNbrPlace(NbrPlace);
            v.setNom(nom);
            v.setPhoto(photo);

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

    // @CrossOrigin
    // @DeleteMapping("/Avions/{id}")
    // public JsonData delete(@PathVariable(value = "id") String id) throws
    // Exception {
    // JsonData json = new JsonData();
    // try {
    // AvionDelete km = new AvionDelete();
    // km.setIdAvion(id);
    // km.Create(null);
    // json.setData(null);
    // json.setMessage("Operation reussit");
    // json.setStatus(true);
    // } catch (Exception e) {
    // json.setData(null);
    // json.setMessage("Operation echoue ");
    // json.setStatus(false);
    // json.setErreur(e.getMessage());
    // }
    // return json;
    // }

    @CrossOrigin
    @GetMapping("/Avion/{id}/Kilometrages/")
    public JsonData findKilometrage(@RequestHeader(name = "token") String token, @PathVariable(name = "id") String id,
            @RequestHeader(name = "idPersonne") String idPersonne) throws Exception {
        Personne p = new Personne();
        p.setIdPersonne(idPersonne);
        p.setToken(token);
        JsonData json = new JsonData();
        if (!p.VerifToken()) {
            json.setData(null);
            json.setMessage("Impossible de se logger");
            json.setStatus(false);
        } else {
            try {
                Kilometrage km = new Kilometrage();
                km.setIdAvion(id);
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

    @CrossOrigin
    @GetMapping("/Avion/{id}/Assurances/")
    public JsonData findAssurance(@PathVariable(name = "id") String id) throws Exception {
        JsonData json = new JsonData();
        try {
            Assurance a = new Assurance();
            a.setIdAvion(id);
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
            e.printStackTrace();
            json.setErreur(e.toString());
        }
        return json;
    }

    @CrossOrigin
    @GetMapping("/Avion/{id}/DateEntretiens/")
    public JsonData findDateEntretien(@RequestHeader(name = "token") String token, @PathVariable(name = "id") String id,
            @RequestHeader(name = "idPersonne") String idPersonne) throws Exception {
        Personne p = new Personne();
        p.setIdPersonne(idPersonne);
        p.setToken(token);
        JsonData json = new JsonData();
        if (!p.VerifToken()) {
            json.setData(null);
            json.setMessage("Impossible de se logger");
            json.setStatus(false);
        } else {
            try {
                DateEntretien km = new DateEntretien();
                km.setIdAvion(id);
                ObjetBDD[] liste = km.Find(null);
                DateEntretien[] lv = new DateEntretien[liste.length];
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
}
