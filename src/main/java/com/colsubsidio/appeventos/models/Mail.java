/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.appeventos.models;

import org.springframework.stereotype.Component;

/**
 *
 * @author cris
 */
@Component
public class Mail {

    private String from = "asesvirl@colsubsidio.com";
    private String to;
    private String subject;
    private String content;
    private String[] copyyCC;

    public Mail() {
    }

    public Mail(String from, String to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public String[] getCopyyCC() {
        return copyyCC;
    }

    public void setCopyyCC(String[] copyyCC) {
        this.copyyCC = copyyCC;
    }

    
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Mail{"
                + "from='" + from + '\''
                + ", to='" + to + '\''
                + ", subject='" + subject + '\''
                + ", content='" + content + '\''
                + '}';
    }
}
