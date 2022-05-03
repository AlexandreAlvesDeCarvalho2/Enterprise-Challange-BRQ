package com.jarvis.enterprise.controller;

import java.util.List;
import java.util.Optional;

import com.jarvis.enterprise.models.Candidato;
import com.jarvis.enterprise.repository.CandidatoRepository;

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
@RequestMapping({"/candidato"})
public class CandidatoController {

    private CandidatoRepository repository;

    CandidatoController(CandidatoRepository candidatoRepository) {
    this.repository = candidatoRepository;
    }
    
    //=============================== CRUD =======================================

    @GetMapping
    public List<Candidato> findAll(){
      return repository.findAll();
    }

    @PostMapping
    public Candidato create(@RequestBody Candidato candidato){
    return repository.save(candidato);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Candidato> update(@PathVariable("id") long id,
                                        @RequestBody Candidato candidato){
    return repository.findById(id)
        .map(records -> {
            records.setCpf(candidato.getCpf());
            records.setDtNascimento(candidato.getDtNascimento());
            records.setEmail(candidato.getEmail());
            records.setGenero(candidato.getGenero());
            records.setNome(candidato.getNome());
            
            Candidato updated = repository.save(records);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());

      }

        @DeleteMapping(path ={"/{id}"})
        public ResponseEntity<Object> delete(@PathVariable("id") long id) {
          return repository.findById(id)
              .map(records -> {
                  repository.deleteById(id);
                  return ResponseEntity.ok().build();
              }).orElse(ResponseEntity.notFound().build());
        }

    //=============================== Custom  =======================================

    @GetMapping(path = {"/{id}"})
    public Optional<Candidato> findById(@PathVariable Long id){
      return repository.findById(id);
    }

    @GetMapping(path = {"/nome/{nome}"})
    public List<Candidato> findByName(@PathVariable String nome){
      return repository.findbyName(nome);
    }

    @GetMapping(path = {"/cpf/{cpf}"})
    public List<Candidato> findByCpf(@PathVariable String cpf){
      return repository.findbyCpf(cpf);
    }

    @GetMapping(path = {"/email/{email}"})
    public List<Candidato> findByEmail(@PathVariable String email){
      return repository.findbyEmail(email);
    }
}