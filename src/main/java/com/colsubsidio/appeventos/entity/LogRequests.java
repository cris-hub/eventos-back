/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.appeventos.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.IndexColumn;

/**
 *
 * @author mac
 */
@Entity(name = "log_requests")
public class LogRequests implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dateExeption;
    private String httpStatus;
    @Column(columnDefinition = "TEXT")
    private String body;
    @Column(columnDefinition = "TEXT")
    private String queryParams;
    @Column(columnDefinition = "TEXT")
    private String request;
    @Column(columnDefinition = "TEXT")
    private String response;
    @Column(columnDefinition = "TEXT")
    private String headers;
    private String uri;
    private String method;

    @OneToMany(mappedBy = "LogRequestsPadre")
    private List<LogRequests> LogRequestshijas;
    @JoinColumn(name = "log_requests_padre", referencedColumnName = "id")
    @ManyToOne
    private LogRequests LogRequestsPadre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return dateExeption;
    }

    public void setDate(Date date) {
        this.dateExeption = date;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getBody() {
        return body;
    }

    public String getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(String queryParams) {
        this.queryParams = queryParams;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }



    public List<LogRequests> getLogRequestshijas() {
        return LogRequestshijas;
    }

    public void setLogRequestshijas(List<LogRequests> LogRequestshijas) {
        this.LogRequestshijas = LogRequestshijas;
    }

    public LogRequests getLogRequestsPadre() {
        return LogRequestsPadre;
    }

    public void setLogRequestsPadre(LogRequests LogRequestsPadre) {
        this.LogRequestsPadre = LogRequestsPadre;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
