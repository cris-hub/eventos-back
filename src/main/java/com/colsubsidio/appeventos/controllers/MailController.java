/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.appeventos.controllers;

import com.colsubsidio.appeventos.dao.MailDAO;
import com.colsubsidio.appeventos.models.Mail;
import com.colsubsidio.appeventos.models.Reservation;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
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
    private Mail mail;

    @PostMapping(path = "send-mail",consumes = "application/json", produces = "application/json")
    public String send(@RequestBody Mail mail) {

        // Now do the magic.
        Object Reservation = new Gson().fromJson(mail.getContent(), Object.class);

        /*
		 * Creating a User with the help of User class that we have declared and setting
		 * Email address of the sender.
         */
        
        /*
		 * Here we will call sendEmail() for Sending mail to the sender.
         */
        try {
            notificationService.sendEmail(mail,(LinkedTreeMap)Reservation);
            return "Congratulations! Your mail has been send to the user.";

        } catch (MailException mailException) {
            System.out.println(mailException);
            return mailException.toString();
        }


    }

    /**
     *
     * @return @throws MessagingException
     */
//    @RequestMapping("send-mail-attachment")
//    public String sendWithAttachment() throws MessagingException {
//
//        /*
//		 * Creating a User with the help of User class that we have declared and setting
//		 * Email address of the sender.
//         */
//        mail.setTo("mukul.jaiswal786@gmail.com");
//
//        /*
//		 * Here we will call sendEmailWithAttachment() for Sending mail to the sender
//		 * that contains a attachment.
//         */
//        try {
//            notificationService.sendEmailWithAttachment(mail);
//        } catch (MailException mailException) {
//            System.out.println(mailException);
//        }
//        return "Congratulations! Your mail has been send to the user.";
//    }

}
