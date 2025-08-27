package com.victor.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.victor.exception.RecordNotFoundException;
import com.victor.model.Carteira;
import com.victor.repository.CarteiraRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CarteiraService {

  private final CarteiraRepository carteiraRepository;

  public CarteiraService(CarteiraRepository carteiraRepository) {
    this.carteiraRepository = carteiraRepository;
  }

  public List<Carteira> listarCarteiras() {
    return carteiraRepository.findAll();
  }

  public Carteira carteiraPorId(@NotNull @Positive Long id) {
    return carteiraRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
  }

  public Carteira criarCarteira(@Valid Carteira carteira) {
    return carteiraRepository.save(carteira);
  }

  public Carteira atualizarCarteira(@NotNull @Positive Long id, @Valid Carteira carteira) {
    return carteiraRepository.findById(id).map((recordFound) -> {
      carteira.setId(recordFound.getId());
      return carteiraRepository.save(carteira);
    }).orElseThrow(() -> new RecordNotFoundException(id));
  }

  public void deletarCarteira(@NotNull @Positive Long id) {
    carteiraRepository.delete(carteiraRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
  }
}
