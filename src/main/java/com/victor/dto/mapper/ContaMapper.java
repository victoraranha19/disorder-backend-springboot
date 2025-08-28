package com.victor.dto.mapper;

import org.springframework.stereotype.Component;

import com.victor.dto.ContaDTO;
import com.victor.enums.TipoTransacao;
import com.victor.model.Conta;

@Component
public class ContaMapper {
  public static ContaDTO toDTO(Conta conta) {
    if (conta == null) {
      return null;
    }

    return new ContaDTO(conta.getIdConta(), conta.getNome(), conta.getValorConta(),
        conta.getTipoTransacao().getValue());
  }

  public static Conta toEntity(ContaDTO contaDTO) {
    if (contaDTO == null) {
      return null;
    }

    Conta conta = new Conta();
    if (contaDTO.id() != null) {
      conta.setIdConta(contaDTO.id());
    }

    conta.setNome(contaDTO.nome());
    conta.setValorConta(contaDTO.valorConta());
    conta.setTipoTransacao(TipoTransacao.convertTipoTransacaoValue(contaDTO.tipo()));

    return conta;
  }
}
