package com.jarvis.enterprise.repository;

import java.util.List;

import com.jarvis.enterprise.models.Candidato;
import com.jarvis.enterprise.models.Skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    @Query(value= "SELECT * FROM TB_CANDIDATO WHERE NOME LIKE :nome", nativeQuery = true)
    List<Candidato> findbyName(@Param("nome") String nome);

    @Query(value= "SELECT * FROM TB_CANDIDATO WHERE CPF LIKE :cpf", nativeQuery = true)
    List<Candidato> findbyCpf(@Param("cpf") String cpf);

    @Query(value= "SELECT * FROM TB_CANDIDATO WHERE EMAIL LIKE :email", nativeQuery = true)
    List<Candidato> findbyEmail(@Param("email") String email);


    @Query(value= "SELECT * FROM TB_SKILL WHERE DESCRICAO LIKE descricao INNER JOIN TB_CANDIDATO", nativeQuery = true)
    List<Skill> findbyDescricao1(@Param("descricao") String descricao);

  
}


// 
// 