package com.jarvis.enterprise.repository;

import java.util.List;

import com.jarvis.enterprise.models.Skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    
    @Query(value= "SELECT * FROM TB_SKILL WHERE ID_CANDIDADO = :iDcandidato", nativeQuery = true)
    List<Skill> findbyIdCandidatos(@Param("iDcandidato") Long iDcandidato);

    @Query(value= "SELECT C.* FROM TB_SKILL S INNER JOIN TB_CANDIDATO C ON C.ID_CANDIDATO = S.ID_CANDIDATO WHERE S.DESCRICAO LIKE :candidato", nativeQuery = true)
    List<Skill> findByCandidatoSkill(@Param("candidato") String candidato);

}
