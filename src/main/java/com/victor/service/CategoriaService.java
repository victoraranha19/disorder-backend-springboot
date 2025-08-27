package com.victor.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.victor.exception.RecordNotFoundException;
import com.victor.model.Categoria;
import com.victor.repository.CategoriaRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CategoriaService {

  private final CategoriaRepository categoriaRepository;

  public CategoriaService(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  public List<Categoria> listarCategorias() {
    return categoriaRepository.findAll();
  }

  public Categoria categoriaPorId(@NotNull @Positive Long id) {
    return categoriaRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
  }

  public Categoria criarCategoria(@Valid Categoria categoria) {
    return categoriaRepository.save(categoria);
  }

  public Categoria atualizarCategoria(@NotNull @Positive Long id, @Valid Categoria categoria) {
    return categoriaRepository.findById(id).map((recordFound) -> {
      categoria.setId(recordFound.getId());
      return categoriaRepository.save(categoria);
    }).orElseThrow(() -> new RecordNotFoundException(id));
  }

  public void deletarCategoria(@NotNull @Positive Long id) {
    categoriaRepository.delete(categoriaRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
  }
}
