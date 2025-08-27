package com.victor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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
import com.victor.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

  private final CategoriaService categoriaService;

  public CategoriaController(CategoriaService categoriaService) {
    this.categoriaService = categoriaService;
  }

  @GetMapping
  public List<Categoria> listarCategorias() {
    return categoriaService.listarCategorias();
  }

  @GetMapping("/{id}")
  public Categoria categoriaPorId(@PathVariable Long id) {
    return categoriaService.categoriaPorId(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Categoria criarCategoria(@RequestBody Categoria categoria) {
    return categoriaService.criarCategoria(categoria);
  }

  @PutMapping("/{id}")
  public Categoria atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
    return categoriaService.atualizarCategoria(id, categoria);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletarCategoria(@PathVariable Long id) {
    categoriaService.deletarCategoria(id);
  }
}
