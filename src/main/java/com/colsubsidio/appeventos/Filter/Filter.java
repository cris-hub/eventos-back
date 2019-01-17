/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.appeventos.Filter;

import com.colsubsidio.appeventos.entity.LogRequests;
import com.colsubsidio.appeventos.service.LogRequestsService;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

/**
 *
 * @author mac
 */
@Component
public class Filter extends GenericFilterBean {

    @Autowired
    LogRequestsService logRequestsService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            if (response.getCharacterEncoding() == null) {
                response.setCharacterEncoding("UTF-8"); // Or whatever default. UTF-8 is good for World Domination.
            }

            LogRequests logRequest = new LogRequests();

            ResponseWrapper responseCopier = new ResponseWrapper((HttpServletResponse) response);
            HttpServletRequest currentRequest = (HttpServletRequest) request;

            RequestWrapper wrappedRequest = new RequestWrapper(currentRequest);
            request = wrappedRequest;

            logRequest.setRequest(wrappedRequest.getBody());
            logRequest.setMethod(currentRequest.getMethod());
            logRequest.setUri(currentRequest.getRequestURI());
            logRequest.setQueryParams(currentRequest.getQueryString());
            logRequest.setDate(new Date());

            Enumeration<String> headerNames = currentRequest.getHeaderNames();

            if (headerNames != null) {
                LinkedHashMap<String, String> headerMap = new LinkedHashMap<>();

                while (headerNames.hasMoreElements()) {

                    String key = headerNames.nextElement();
                    if (key != null) {
                        String value = String.valueOf(currentRequest.getHeader(key));
                        if (value != null) {
                            headerMap.put(key, value);
                        }
                    }

                }

                logRequest.setHeaders(new com.google.gson.Gson().toJson(headerMap));
            }

            chain.doFilter(request, responseCopier);
            responseCopier.flushBuffer();
            byte[] copy = responseCopier.getCopy();
            String content = new String(copy, response.getCharacterEncoding());
            System.out.println(content);

            logRequest.setResponse(content);
            logRequest.setHttpStatus(String.valueOf(responseCopier.getStatus()));

            logRequestsService.guardarReserva(logRequest);

        } catch (Exception e) {
            LogRequests logRequest = new LogRequests();
            logRequest.setHttpStatus(String.valueOf(HttpStatus.BAD_REQUEST));
            logRequest.setBody(e.getCause().getMessage());
            logRequest.setDate(new Date());
            logRequest.setResponse(e.toString());
            logRequest.setRequest(e.toString());
            logRequest.setUri(Filter.class.getName());
            logRequestsService.guardarReserva(logRequest);
            Logger.getLogger(Filter.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
    }

}
