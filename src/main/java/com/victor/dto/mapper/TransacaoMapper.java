package com.victor.dto.mapper;

import com.victor.dto.TransacaoDTO;
import com.victor.enums.TipoTransacao;
import com.victor.model.Transacao;

public class TransacaoMapper {
  public TransacaoDTO toDTO(Transacao transacao) {
    if (transacao == null) {
      return null;
    }

    return new TransacaoDTO(transacao.getId(), transacao.getDescricao(), transacao.getValor(),
        transacao.getDataTransacao(), transacao.getTipo().getValue(), transacao.getIdCategoria(),
        transacao.getIdCarteira());
  }

  public Transacao toEntity(TransacaoDTO transacaoDTO) {
    if (transacaoDTO == null) {
      return null;
    }

    Transacao transacao = new Transacao();
    if (transacaoDTO.id() != null) {
      transacao.setId(transacaoDTO.id());
    }
    transacao.setDescricao(transacaoDTO.descricao());
    transacao.setValor(transacaoDTO.valor());
    transacao.setDataTransacao(transacaoDTO.dataTransacao());
    transacao.setTipo(convertTipoTransacaoValue(transacaoDTO.tipo()));
    transacao.setIdCategoria(transacaoDTO.idCategoria());
    transacao.setIdCarteira(transacaoDTO.idCarteira());
    transacao.setIdUsuario(0);
    return transacao;
  }

  public TipoTransacao convertTipoTransacaoValue(String value) {
    if (value == null) {
      return null;
    }
    return switch (value) {
      case "D" -> TipoTransacao.DEBITO;
      case "C" -> TipoTransacao.CREDITO;
      default -> throw new IllegalArgumentException("Tipo de Transação inválido: " + value);
    };
  }
}
