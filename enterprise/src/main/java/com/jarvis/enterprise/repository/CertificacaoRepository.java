package com.jarvis.enterprise.repository;

import java.util.List;

import com.jarvis.enterprise.models.Certificacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificacaoRepository extends JpaRepository<Certificacao, Long> {

    @Query(value= "SELECT * FROM TB_CERTIFICACAO WHERE DESCRICAO LIKE :idCandidato INNER JOIN TB_CANDIDATO ON TB_CANDIDATO.ID_CANDIDATO = TB_CERTIFICACAO.ID_CANDIDATO", nativeQuery = true)
    List<Certificacao> findbyIdCandidato(@Param("idCandidato") Long idCandidato);

    @Query(value= "SELECT * FROM TB_CERTIFICACAO WHERE DESCRICAO LIKE %VALOR% INNER JOIN TB_CANDIDATO", nativeQuery = true)
    List<Certificacao> findbyDescricao(@Param("descricao") String descricao);

}