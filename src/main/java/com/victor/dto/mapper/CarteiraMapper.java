package com.victor.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.victor.dto.CarteiraDTO;
import com.victor.dto.TransacaoDTO;
import com.victor.model.Carteira;
import com.victor.model.Transacao;

@Component
public class CarteiraMapper {
  public static CarteiraDTO toDTO(Carteira carteira) {
    if (carteira == null) {
      return null;
    }

    List<TransacaoDTO> transacoesDTO = carteira.getTransacoes()
        .stream()
        .map(TransacaoMapper::toDTO)
        .collect(Collectors.toList());

    return new CarteiraDTO(carteira.getIdCarteira(), carteira.getTitulo(), carteira.getContaCorrente(),
        carteira.getContaPoupanca(), carteira.getContaInvestimento(), carteira.getLimiteCreditoTotal(),
        transacoesDTO);
  }

  public static Carteira toEntity(CarteiraDTO carteiraDTO) {
    if (carteiraDTO == null) {
      return null;
    }

    Carteira carteira = new Carteira();
    if (carteiraDTO.id() != null) {
      carteira.setIdCarteira(carteiraDTO.id());
    }

    carteira.setTitulo(carteiraDTO.titulo());
    carteira.setContaCorrente(carteiraDTO.contaCorrente());
    carteira.setContaPoupanca(carteiraDTO.contaPoupanca());
    carteira.setContaInvestimento(carteiraDTO.contaInvestimento());
    carteira.setLimiteCreditoTotal(carteiraDTO.limiteCreditoTotal());

    carteira.getTransacoes().clear();
    carteiraDTO.transacoes().forEach(transacaoDTO -> {
      Transacao transacao = TransacaoMapper.toEntity(transacaoDTO);
      transacao.setCarteira(carteira);
      carteira.getTransacoes().add(transacao);
    });

    return carteira;
  }
}
