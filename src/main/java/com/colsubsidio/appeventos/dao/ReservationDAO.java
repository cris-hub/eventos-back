/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.appeventos.dao;

import com.colsubsidio.appeventos.dao.interfaces.IReservationDAO;
import com.colsubsidio.appeventos.models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author mac
 */
@Component
public class ReservationDAO implements IReservationDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Reservation createReserva(Reservation reserva) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        KeyHolder holder = new GeneratedKeyHolder();
        params.addValue("id", null);
        params.addValue("nit", reserva.getNit());
        params.addValue("reserva", reserva.getReserva());
        
        int update = jdbcTemplate.update("CALL PR_COLSEVENTOS_I_RESERVA(:id,:nit,:reserva)", params);
        return reserva;
    }

}
