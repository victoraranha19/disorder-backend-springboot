package com.victor.dto.mapper;

import org.springframework.stereotype.Component;

import com.victor.dto.CarteiraDTO;
import com.victor.model.Carteira;

@Component
public class CarteiraMapper {
  public CarteiraDTO toDTO(Carteira carteira) {
    if (carteira == null) {
      return null;
    }

    return new CarteiraDTO(carteira.getId(), carteira.getTitulo(), carteira.getContaCorrente(),
        carteira.getContaPoupanca(), carteira.getContaInvestimento(), carteira.getLimiteCreditoTotal());
  }

  public Carteira toEntity(CarteiraDTO carteiraDTO) {
    if (carteiraDTO == null) {
      return null;
    }

    Carteira carteira = new Carteira();
    if (carteiraDTO.id() != null) {
      carteira.setId(carteiraDTO.id());
    }
    carteira.setTitulo(carteiraDTO.titulo());
    carteira.setContaCorrente(carteiraDTO.contaCorrente());
    carteira.setContaPoupanca(carteiraDTO.contaPoupanca());
    carteira.setContaInvestimento(carteiraDTO.contaInvestimento());
    carteira.setLimiteCreditoTotal(carteiraDTO.limiteCreditoTotal());
    carteira.setIdUsuario(0);
    return carteira;
  }
}
