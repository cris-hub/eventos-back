/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.appeventos.dao;

import com.google.gson.internal.LinkedTreeMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private final TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(LinkedTreeMap reservation, String nameTemplate) throws ParseException {
        Context context = new Context();
        try {
            LinkedTreeMap<String, Object> lounge = (LinkedTreeMap) reservation.get("lounge");
            LinkedTreeMap<String, Object> headquarter = (LinkedTreeMap) reservation.get("headquarte");
            LinkedTreeMap<String, Object> company = (LinkedTreeMap) reservation.get("company");
            LinkedTreeMap<String, Object> experience = (LinkedTreeMap) reservation.get("experience");

            ArrayList<Object> loungeImages = (ArrayList<Object>) lounge.get("images");
            ArrayList<Object> headquarterImages = (ArrayList<Object>) headquarter.get("images");

            Date dateStart = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(reservation.get("dateStart").toString());
            Date dateFinish = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(reservation.get("dateFinish").toString());

            context.setVariable("nameEvent", reservation.get("nameEvent"));
            context.setVariable("experience", reservation.get("experience"));
            context.setVariable("amountAttending", reservation.get("capacity"));
            context.setVariable("amountAttendingEventChildren", reservation.get("amountAttendingEventChildren"));
            context.setVariable("amountAttendingEventAdults", reservation.get("amountAttendingEventAdults"));
            context.setVariable("dateStart", dateStart);
            context.setVariable("dateFinish", dateFinish);
            context.setVariable("headquarteName", headquarter.get("name"));
            context.setVariable("headquarterImages", headquarterImages);
            context.setVariable("loungeName", lounge.get("name"));
            context.setVariable("loungeImages", loungeImages);
            context.setVariable("nameCompany", company.get("nameCompany"));
            context.setVariable("mobilePhone", company.get("mobilePhone"));
            context.setVariable("landline", company.get("landline"));
            context.setVariable("extLandline", company.get("extLandline"));
            context.setVariable("NIT", company.get("NIT"));
            context.setVariable("numberVerification", company.get("numberVerification"));
            context.setVariable("responsable", company.get("responsable"));
            context.setVariable("mail", company.get("mail"));

            context.setVariable("hasBreakfast", reservation.get("hasBreakfast"));
            context.setVariable("hasLunch", reservation.get("hasLunch"));
            context.setVariable("hasRefreshmentAM", reservation.get("hasRefreshmentAM"));
            context.setVariable("hasRefreshmentPM", reservation.get("hasRefreshmentPM"));
            context.setVariable("hasDinner", reservation.get("hasDinner"));
            context.setVariable("hasIllumination", reservation.get("hasIllumination"));
            context.setVariable("hasProfessionalSound", reservation.get("hasProfessionalSound"));
            context.setVariable("hasPallet", reservation.get("hasPallet"));
            context.setVariable("hasTent", reservation.get("hasTent"));
            context.setVariable("hasDedicatedChannel", reservation.get("hasDedicatedChannel"));
            context.setVariable("hasSpeaker", reservation.get("hasSpeaker"));
            context.setVariable("hasArtPresentations", reservation.get("hasArtPresentations"));
            context.setVariable("hasTransport", reservation.get("hasTransport"));
            context.setVariable("hasOthers", reservation.get("hasOthers"));
            context.setVariable("others", reservation.get("others"));

        } catch (ParseException parseException) {
            Logger.getLogger(MailContentBuilder.class.getName()).log(Level.SEVERE, null, parseException);
            throw parseException;
        }
        return templateEngine.process(nameTemplate, context);
    }

}
