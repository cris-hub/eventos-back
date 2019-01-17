/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.appeventos.controller;

import com.colsubsidio.appeventos.dao.MailDAO;
import com.colsubsidio.appeventos.entity.LogRequests;
import com.colsubsidio.appeventos.model.Mail;
import com.colsubsidio.appeventos.service.LogRequestsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LinkedTreeMap;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author cris
 */
@RestController
@CrossOrigin(origins = "*")
public class MailController {

    @Autowired
    private MailDAO notificationService;

    @Autowired
    LogRequestsService logRequestsService;

    @PostMapping(path = "send-mail", consumes = "application/json", produces = "application/json")
    public ResponseEntity send(@RequestBody Mail mail) {
        Object Reservation = null;
        try {
            Reservation = new Gson().fromJson(mail.getContent(), Object.class);
        } catch (JsonSyntaxException e) {
            Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {

            notificationService.sendEmail(mail, (LinkedTreeMap) Reservation);
            return new ResponseEntity("{\"esRequestOK\" : true }",
                    HttpStatus.OK
            );
        } catch (MailException e) {
            LogRequests logRequest = new LogRequests();
            logRequest.setHttpStatus(String.valueOf(HttpStatus.BAD_REQUEST));
            logRequest.setBody(e.getCause().getMessage());
            logRequest.setDate(new Date());
            logRequest.setResponse(e.toString());
            logRequest.setRequest(e.toString());
            logRequest.setUri(MailController.class.getName());
            logRequestsService.guardarReserva(logRequest);

            Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity(e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
