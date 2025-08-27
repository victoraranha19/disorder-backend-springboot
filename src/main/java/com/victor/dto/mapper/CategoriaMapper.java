package com.victor.dto.mapper;

import com.victor.dto.CategoriaDTO;
import com.victor.model.Categoria;

public class CategoriaMapper {
  public CategoriaDTO toDTO(Categoria categoria) {
    if (categoria == null) {
      return null;
    }

    return new CategoriaDTO(categoria.getId(), categoria.getTitulo(), categoria.getValorPlanejado());
  }

  public Categoria toEntity(CategoriaDTO categoriaDTO) {
    if (categoriaDTO == null) {
      return null;
    }

    Categoria categoria = new Categoria();
    if (categoriaDTO.id() != null) {
      categoria.setId(categoriaDTO.id());
    }
    categoria.setTitulo(categoriaDTO.titulo());
    categoria.setValorPlanejado(categoriaDTO.valorPlanejado());
    categoria.setIdUsuario(0);
    return categoria;
  }
}
