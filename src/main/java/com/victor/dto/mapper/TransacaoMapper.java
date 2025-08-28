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
        transacao.getDataTransacao(), transacao.getTipo().getValue(), transacao.getParcelas());
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
    transacao.setTipo(TipoTransacao.convertTipoTransacaoValue(transacaoDTO.tipo()));
    transacao.setParcelas(transacaoDTO.parcelas());

    return transacao;
  }
}
