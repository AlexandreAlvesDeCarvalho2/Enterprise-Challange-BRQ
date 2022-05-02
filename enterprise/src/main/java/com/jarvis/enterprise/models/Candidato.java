package com.jarvis.enterprise.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "TB_CANDIDATO")
public class Candidato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Candidato")
    private Long idCandidato;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "cpf", length = 50, nullable = false)
    private String cpf;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "telefone", length = 50, nullable = false)
    private String telefone;

    @Column(name = "genero", length = 50, nullable = false)
    private String genero;

    @Column(name = "dt_nascimento")
    @Temporal(TemporalType.DATE)
    private Calendar dtNascimento;

    // ==================== relações ======================

    @OneToMany(mappedBy = "skills", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Skill> skill = new ArrayList<>();

    @OneToMany(mappedBy = "certificacoes", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Certificacao> certificacao = new ArrayList<>();

}
