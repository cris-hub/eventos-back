/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.appeventos.dao;

import com.google.gson.internal.LinkedHashTreeMap;
import com.google.gson.internal.LinkedTreeMap;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author cris
 */
@Service
public class MailContentBuilder {

    private TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(LinkedTreeMap reservation, String nameTemplate) {
        Context context = new Context();
        LinkedTreeMap<String, Object> lounge = (LinkedTreeMap) reservation.get("lounge");
        LinkedTreeMap<String, Object> headquarter = (LinkedTreeMap) reservation.get("headquarte");
        LinkedTreeMap<String, Object> company = (LinkedTreeMap) reservation.get("company");

        ArrayList<Object> loungeImages = (ArrayList<Object>) lounge.get("images");
        ArrayList<Object> headquarterImages = (ArrayList<Object>) headquarter.get("images");
        

        context.setVariable("nameEvent", reservation.get("nameEvent"));
        context.setVariable("eventType", reservation.get("eventType"));
        context.setVariable("amountAttending", reservation.get("capacity"));
        context.setVariable("dateStart", reservation.get("dateStart"));
        context.setVariable("dateFinish", reservation.get("dateFinish"));
        context.setVariable("headquarteName", headquarter.get("name"));
        context.setVariable("headquarterImages", headquarterImages);
        context.setVariable("loungeName", lounge.get("name"));
        context.setVariable("loungeImages", loungeImages);
        context.setVariable("nameCompany", company.get("nameCompany"));
        context.setVariable("NIT", company.get("NIT"));
        context.setVariable("responsable", company.get("responsable"));
        context.setVariable("mail", company.get("mail"));

        return templateEngine.process(nameTemplate, context);
    }

}
