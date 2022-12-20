package Tp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import Tp.JSonData.JsonData;
import Tp.dao.ObjetBDD;
import Tp.model.DateEntretien;

@RestController
public class DateEntretientController {
    @CrossOrigin
    @GetMapping("/DateEntretiens")
    public JsonData findAll() throws Exception {
        JsonData json = new JsonData();
        try {
            ObjetBDD[] liste = new DateEntretien().Find(null);
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
        return json;
    }
   

}
