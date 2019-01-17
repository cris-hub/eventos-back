/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.appeventos.dao;

import com.colsubsidio.appeventos.dao.interfaces.IReservationDAO;
import com.colsubsidio.appeventos.entity.Reservation;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            KeyHolder holder = new GeneratedKeyHolder();
            params.addValue("id", 0,Statement.RETURN_GENERATED_KEYS);
            params.addValue("nit", reserva.getNit());
            params.addValue("reserva", reserva.getReserva());
            

            int update = jdbcTemplate.update("CALL PR_COLSEVENTOS_I_RESERVA(:id,:nit,:reserva)", params,holder);
            System.out.println(holder.getKey());
        } catch (DataAccessException dataAccessException) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, dataAccessException);
            throw dataAccessException;
        }
        return reserva;
    }

}
