/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.appeventos.dao;

import com.colsubsidio.appeventos.model.Mail;
import com.google.gson.internal.LinkedTreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cris
 */
@Service
public class MailDAO {

    private final JavaMailSender javaMailSender;
    private final MailContentBuilder mailContentBuilder;

    @Autowired
    public MailDAO(JavaMailSender javaMailSender, MailContentBuilder mailContentBuilder) {
        this.javaMailSender = javaMailSender;
        this.mailContentBuilder = mailContentBuilder;
    }

    public void sendEmail(Mail mail, LinkedTreeMap reservation) throws MailException {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("application");
            MimeMessagePreparator messagePreparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

                messageHelper.setFrom(rb.getString("spring.mail.username"));
                if (mail.getTo() != null) {
                    messageHelper.setTo(mail.getTo());
                }
                if (!"".equals(mail.getCopyyCC()[0])) {
                    messageHelper.setCc(mail.getCopyyCC());
                }
                messageHelper.setSubject(mail.getSubject() == null ? "Reserva" : mail.getSubject());
                String content = mailContentBuilder.build(reservation, "reserva-eventos");
                messageHelper.setText(content, true);
            };

            javaMailSender.send(messagePreparator);
        } catch (MailException mailException) {
            Logger.getLogger(MailDAO.class.getName()).log(Level.SEVERE, null, mailException);
            throw mailException;
        }
    }
}
