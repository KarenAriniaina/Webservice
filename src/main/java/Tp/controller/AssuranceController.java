package Tp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import Tp.JSonData.JsonData;
import Tp.dao.ObjetBDD;
import Tp.model.Assurance;

@RestController
public class AssuranceController {
    @CrossOrigin
    @GetMapping("/Assurances")
    public JsonData findAll() throws Exception {
        JsonData json = new JsonData();
        try {
            ObjetBDD[] liste = new Assurance().Find(null);
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
            json.setErreur(e.getMessage());
        } 
        return json;
    }
}
