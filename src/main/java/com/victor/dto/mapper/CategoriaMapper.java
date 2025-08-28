package com.victor.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.victor.dto.CategoriaDTO;
import com.victor.dto.TransacaoDTO;
import com.victor.model.Categoria;
import com.victor.model.Transacao;

@Component
public class CategoriaMapper {
  public static CategoriaDTO toDTO(Categoria categoria) {
    if (categoria == null) {
      return null;
    }

    List<TransacaoDTO> transacoesDTO = categoria.getTransacoes()
        .stream()
        .map(TransacaoMapper::toDTO)
        .collect(Collectors.toList());

    return new CategoriaDTO(categoria.getIdCategoria(), categoria.getTitulo(), categoria.getValorPlanejado(),
        transacoesDTO);
  }

  public static Categoria toEntity(CategoriaDTO categoriaDTO) {
    if (categoriaDTO == null) {
      return null;
    }

    Categoria categoria = new Categoria();
    if (categoriaDTO.id() != null) {
      categoria.setIdCategoria(categoriaDTO.id());
    }

    categoria.setTitulo(categoriaDTO.titulo());
    categoria.setValorPlanejado(categoriaDTO.valorPlanejado());

    List<Transacao> transacoes = categoriaDTO.transacoes()
        .stream()
        .map(TransacaoMapper::toEntity)
        .collect(Collectors.toList());
    categoria.setTransacoes(transacoes);

    return categoria;
  }
}
