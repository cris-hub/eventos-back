package com.colsubsidio.appeventos.service;

import org.springframework.stereotype.Service;

import com.colsubsidio.appeventos.entity.LogRequests;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import com.colsubsidio.appeventos.repository.LogRequestsRepository;

@Service("logRequestsService")
public class LogRequestsService {

    @Resource
    private LogRequestsRepository repositorioExeption;

    public LogRequests guardarReserva(LogRequests logRequests) {
        try {
            repositorioExeption.save(logRequests);
            return logRequests;
        } catch (Exception e) {
            throw e;
        }
    }

    public LogRequests actualizarReserva(LogRequests logRequests) {
        try {
            repositorioExeption.save(logRequests);
            return logRequests;
        } catch (Exception e) {
            throw e;
        }
    }

    public LogRequests findById(int id) {
        try {
            Optional<LogRequests> logRequests = repositorioExeption.findById(id);
            return logRequests.get();
        } catch (Exception ex) {
            Logger.getLogger(LogRequestsService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public Collection<LogRequests> findAll() {
        try {
            Collection<LogRequests> logRequests = repositorioExeption.findAll();
            return logRequests;
        } catch (Exception ex) {
            Logger.getLogger(LogRequestsService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

    }

}
