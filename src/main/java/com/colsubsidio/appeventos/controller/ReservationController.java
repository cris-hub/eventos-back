/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.appeventos.controller;

import com.colsubsidio.appeventos.Filter.Filter;
import com.colsubsidio.appeventos.dao.interfaces.IReservationDAO;
import com.colsubsidio.appeventos.entity.LogRequests;
import com.colsubsidio.appeventos.entity.Reservation;
import com.colsubsidio.appeventos.service.LogRequestsService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mac
 */
@RestController
@RequestMapping("/reserva")
@CrossOrigin(origins = "*")
public class ReservationController {

    @Autowired
    public IReservationDAO reservaDAO;

    @Autowired
    LogRequestsService logRequestsService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        try {
            reservaDAO.createReserva(reservation);

        } catch (Exception e) {
            LogRequests logRequest = new LogRequests();
            logRequest.setHttpStatus(String.valueOf(HttpStatus.BAD_REQUEST));
            logRequest.setBody(e.getCause().getMessage());
            logRequest.setDate(new Date());
            logRequest.setResponse(e.toString());
            logRequest.setRequest(e.toString());
            logRequest.setUri(ReservationController.class.getName());
            logRequestsService.guardarReserva(logRequest);
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(reservation, HttpStatus.CREATED);
    }

}
