package com.jarvis.enterprise.controller;

import java.util.List;
import java.util.Optional;

import com.jarvis.enterprise.models.Certificacao;
import com.jarvis.enterprise.repository.CertificacaoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/certificacao" })
public class CertificacaoController {

    private CertificacaoRepository repository;

    CertificacaoController(CertificacaoRepository certificacaoRepository) {
        this.repository = certificacaoRepository;
    }
    // ======================= CRUD ============================================


    @GetMapping
    public List<Certificacao> findAll() {
        return repository.findAll();
    }

    @GetMapping(path = { "/{id}" })
    public Optional<Certificacao> findById(@PathVariable long id) {
        return repository.findById(id);
    }

    @PostMapping
    public Certificacao create(@RequestBody Certificacao certificacao) {
        return repository.save(certificacao);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Certificacao> update(@PathVariable("id") long id,
            @RequestBody Certificacao certificacao) {
        return repository.findById(id)
                .map(records -> {
                    records.setDescricao(certificacao.getDescricao());
                    Certificacao updated = repository.save(records);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = { "/{id}" })
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        return repository.findById(id)
                .map(records -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    //============================== Custom ==================================== 

    @GetMapping(path = {"/idCandidato/{idCandidato}"})
    public List<Certificacao> findByIdCandidato(@PathVariable Long idCandidato){
      return repository.findbyIdCandidato(idCandidato);
    }

    @GetMapping(path = {"/descricao/{descricao}"})
    public List<Certificacao> findbyDescricao(@PathVariable String descricao){
      return repository.findbyDescricao(descricao);
    }

}