/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.appeventos.controllers;

import com.colsubsidio.appeventos.dao.interfaces.IReservationDAO;
import com.colsubsidio.appeventos.models.Reservation;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping( consumes = "application/json", produces = "application/json")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        reservaDAO.createReserva(reservation);
        return new ResponseEntity(reservation, HttpStatus.CREATED);
    }

}
