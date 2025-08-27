package com.victor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.victor.model.Carteira;
import com.victor.repository.CarteiraRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@Validated
@RestController
@RequestMapping("/api/carteiras")
@AllArgsConstructor
public class CarteiraController {

  private final CarteiraRepository carteiraRepository;

  @GetMapping
  public @ResponseBody List<Carteira> listarCarteiras() {
    return carteiraRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Carteira> carteiraPorId(@PathVariable @NotNull @Positive Long id) {
    return carteiraRepository.findById(id).map(carteira -> ResponseEntity.ok().body(carteira))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Carteira criarCarteira(@RequestBody @Valid Carteira carteira) {
    return carteiraRepository.save(carteira);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Carteira> atualizarCarteira(@PathVariable @NotNull @Positive Long id,
      @RequestBody @Valid Carteira carteira) {
    if (!carteiraRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    carteira.setId(id);
    Carteira atualizada = carteiraRepository.save(carteira);
    return ResponseEntity.ok().body(atualizada);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarCarteira(@PathVariable @NotNull @Positive Long id) {
    if (!carteiraRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    carteiraRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
