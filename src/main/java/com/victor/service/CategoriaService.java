package com.victor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.victor.dto.CategoriaDTO;
import com.victor.dto.mapper.CategoriaMapper;
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
  private final CategoriaMapper categoriaMapper;

  public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
    this.categoriaRepository = categoriaRepository;
    this.categoriaMapper = categoriaMapper;
  }

  public List<CategoriaDTO> listarCategorias() {
    return categoriaRepository.findAll()
        .stream()
        .map(categoriaMapper::toDTO)
        .collect(Collectors.toList());
  }

  public CategoriaDTO categoriaPorId(@NotNull @Positive Long id) {
    return categoriaRepository.findById(id)
        .map(categoriaMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public CategoriaDTO criarCategoria(@Valid @NotNull CategoriaDTO categoriaDTO) {
    return categoriaMapper.toDTO(categoriaRepository.save(categoriaMapper.toEntity(categoriaDTO)));
  }

  public CategoriaDTO atualizarCategoria(@NotNull @Positive Long id, @Valid @NotNull CategoriaDTO categoriaDTO) {
    return categoriaRepository.findById(id)
        .map((recordFound) -> {
          Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
          categoria.setId(recordFound.getId());
          return categoriaRepository.save(categoria);
        })
        .map(categoriaMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public void deletarCategoria(@NotNull @Positive Long id) {
    categoriaRepository.delete(categoriaRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
  }
}
