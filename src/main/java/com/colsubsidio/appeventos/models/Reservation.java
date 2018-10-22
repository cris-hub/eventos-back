/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colsubsidio.appeventos.models;

/**
 *
 * @author mac
 */
public class Reservation {

    private Integer id;
    private String reserva;
    private String nit;

    public Reservation(String reserva, String nit) {
        this.reserva = reserva;
        this.nit = nit;
    }

    public Reservation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReserva() {
        return reserva;
    }

    public void setReserva(String reserva) {
        this.reserva = reserva;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    @Override
    public String toString() {
        return "Reservas{" + "id=" + id + ", w=" + reserva + ", nit=" + nit + '}';
    }

}
