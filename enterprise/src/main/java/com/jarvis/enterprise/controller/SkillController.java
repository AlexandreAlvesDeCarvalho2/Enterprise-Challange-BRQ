package com.jarvis.enterprise.controller;

import java.util.List;

import com.jarvis.enterprise.models.Skill;
import com.jarvis.enterprise.repository.SkillRepository;

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
@RequestMapping({ "/skill" })
public class SkillController {

  private SkillRepository repository;

  SkillController(SkillRepository skillRepository) {
    this.repository = skillRepository;
  }
  // ============================= CRUD ===================================
  @GetMapping
  public List<Skill> findAll() {
    return repository.findAll();
  }

  @PostMapping
  public Skill create(@RequestBody Skill skill) {
    return repository.save(skill);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Skill> update(@PathVariable("id") long id,
      @RequestBody Skill skill) {
    return repository.findById(id)
        .map(records -> {
          records.setDescricao(skill.getDescricao());
          Skill updated = repository.save(records);
          return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping(path ={"/{id}"})
  public ResponseEntity<?> delete(@PathVariable("id") long id) {
    return repository.findById(id)
        .map(records -> {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
  }
  //============================= Custom =========================
  
  @GetMapping(path = { "/candidato/{candidato}" })
  public List<Skill> findByCandidatoSkill(@PathVariable String candidato) {
    return repository.findByCandidatoSkill(candidato);
  }

  @GetMapping(path = { "/iDcandidato/{candidato}" })
  public List<Skill> findByCandidato(@PathVariable Long iDcandidato) {
    return repository.findbyIdCandidatos(iDcandidato);
  }

}