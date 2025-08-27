package com.victor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.victor.model.Categoria;
import com.victor.repository.CategoriaRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/categorias")
@AllArgsConstructor
public class CategoriaController {

  private final CategoriaRepository categoriaRepository;

  @GetMapping
  public List<Categoria> listarCategorias() {
    return categoriaRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Categoria> carteiraPorId(@PathVariable @NotNull @Positive Long id) {
    return categoriaRepository.findById(id).map(categoria -> ResponseEntity.ok().body(categoria))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Categoria criarCategoria(@RequestBody @Valid Categoria categoria) {
    return categoriaRepository.save(categoria);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Categoria> atualizarCategoria(@PathVariable @NotNull @Positive Long id,
      @RequestBody @Valid Categoria categoria) {
    if (!categoriaRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    categoria.setId(id);
    Categoria atualizada = categoriaRepository.save(categoria);
    return ResponseEntity.ok().body(atualizada);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarCategoria(@PathVariable @NotNull @Positive Long id) {
    if (!categoriaRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    categoriaRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
