package com.victor.dto.mapper;

import org.springframework.stereotype.Component;

import com.victor.dto.TransacaoDTO;
import com.victor.enums.TipoTransacao;
import com.victor.model.Transacao;

@Component
public class TransacaoMapper {
  public static TransacaoDTO toDTO(Transacao transacao) {
    if (transacao == null) {
      return null;
    }

    return new TransacaoDTO(transacao.getIdTransacao(), transacao.getDescricao(), transacao.getValor(),
        transacao.getDataTransacao(), transacao.getTipo().getValue());
  }

  public static Transacao toEntity(TransacaoDTO transacaoDTO) {
    if (transacaoDTO == null) {
      return null;
    }

    Transacao transacao = new Transacao();
    if (transacaoDTO.id() != null) {
      transacao.setIdTransacao(transacaoDTO.id());
    }

    transacao.setDescricao(transacaoDTO.descricao());
    transacao.setValor(transacaoDTO.valor());
    transacao.setDataTransacao(transacaoDTO.dataTransacao());
    transacao.setTipo(convertTipoTransacaoValue(transacaoDTO.tipo()));

    return transacao;
  }

  public static TipoTransacao convertTipoTransacaoValue(String value) {
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
