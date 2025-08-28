package com.victor.dto.mapper;

import org.springframework.stereotype.Component;

import com.victor.dto.CategoriaDTO;
import com.victor.model.Categoria;

@Component
public class CategoriaMapper {
  public static CategoriaDTO toDTO(Categoria categoria) {
    if (categoria == null) {
      return null;
    }

    return new CategoriaDTO(categoria.getIdCategoria(), categoria.getNome(), categoria.getValorPlanejado());
  }

  public static Categoria toEntity(CategoriaDTO categoriaDTO) {
    if (categoriaDTO == null) {
      return null;
    }

    Categoria categoria = new Categoria();
    if (categoriaDTO.id() != null) {
      categoria.setIdCategoria(categoriaDTO.id());
    }

    categoria.setNome(categoriaDTO.nome());
    categoria.setValorPlanejado(categoriaDTO.valorPlanejado());

    return categoria;
  }
}
