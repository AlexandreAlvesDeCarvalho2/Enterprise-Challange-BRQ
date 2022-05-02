package com.jarvis.enterprise.repository;

import com.jarvis.enterprise.models.Candidato;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    
}
