package Tp.controller;

import Tp.JSonData.JsonData;
import Tp.model.Personne;
import Tp.dao.ObjetBDD;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonneController {

    @CrossOrigin
    @PostMapping("/Personne")
    public JsonData login(@RequestParam String email, @RequestParam String passwords) throws Exception {
        JsonData json = new JsonData();
        try {
            Personne pers = new Personne();
            pers.setMail(email);
            pers.setPasswords(passwords);
            ObjetBDD[] list = pers.Find(null);
            Personne[] lv = new Personne[list.length];
            System.arraycopy(list, 0, lv, 0, lv.length);
            if (lv.length != 0) {
                if (lv[0] != null) {
                    //--- tsy maintsy atsoina satria tsy auto setter ao amn re ObjetBDD re token---//
                    Personne p=lv[0];
                    System.out.println(p.getMail());
                    p.getToken();
                    lv[0]=p;
                    json.setData(lv);
                    json.setMessage("Operation reussie");
                    json.setStatus(true);

                } else {
                    json.setData(null);
                    json.setMessage("failed to log");
                    json.setStatus(false);
                }
            }
        } catch (Exception e) {
            json.setData(null);
            json.setMessage("Operation echoue");
            json.setStatus(false);
            json.setErreur(e.getMessage());
        }
        return json;

    }

    @CrossOrigin
    @DeleteMapping("/Personne/{id}")
    public JsonData Deconnexion(@PathVariable String id) throws Exception {
        JsonData json = new JsonData();
        try {
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
}
