/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.appeventos.dao;

import com.colsubsidio.appeventos.models.Mail;
import com.google.gson.internal.LinkedTreeMap;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import java.util.ResourceBundle;

/**
 *
 * @author cris
 */
@Service
public class MailDAO {

    private JavaMailSender javaMailSender;
    private MailContentBuilder mailContentBuilder;

    @Autowired
    public MailDAO(JavaMailSender javaMailSender, MailContentBuilder mailContentBuilder) {
        this.javaMailSender = javaMailSender;
        this.mailContentBuilder = mailContentBuilder;
    }

    public void sendEmail(Mail mail, LinkedTreeMap reservation) throws MailException {

        ResourceBundle rb = ResourceBundle.getBundle("application");
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

            messageHelper.setFrom(rb.getString("spring.mail.username"));
            if (mail.getTo() != null) {
                messageHelper.setTo(mail.getTo());
            }
            if (mail.getCopyyCC()[0] != "") {
                messageHelper.setCc(mail.getCopyyCC());
            }
            messageHelper.setSubject(mail.getSubject() == null ? "Reserva" : mail.getSubject());
            String content = mailContentBuilder.build(reservation, "reserva-eventos");
            messageHelper.setText(content, true);
        };

        javaMailSender.send(messagePreparator);
    }

    public void sendEmailWithAttachment(Mail mail) throws MailException, MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(mail.getTo());
        helper.setSubject("Testing Mail API with Attachment");
        helper.setText("Please find the attached document below.");

        FileSystemResource file = new FileSystemResource("/home/rockhard/Desktop/Registration.pdf");
        helper.addAttachment(file.getFilename(), file);

        javaMailSender.send(mimeMessage);
    }

}
