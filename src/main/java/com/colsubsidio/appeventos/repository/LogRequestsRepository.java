package com.colsubsidio.appeventos.repository;

import com.colsubsidio.appeventos.entity.LogRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRequestsRepository extends JpaRepository<LogRequests, Integer> {

}
