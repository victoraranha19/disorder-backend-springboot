package com.victor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.victor.dto.CarteiraDTO;
import com.victor.dto.mapper.CarteiraMapper;
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
  private final CarteiraMapper carteiraMapper;

  public CarteiraService(CarteiraRepository carteiraRepository, CarteiraMapper carteiraMapper) {
    this.carteiraRepository = carteiraRepository;
    this.carteiraMapper = carteiraMapper;
  }

  public List<CarteiraDTO> listarCarteiras() {
    return carteiraRepository.findAll()
        .stream()
        .map(carteiraMapper::toDTO)
        .collect(Collectors.toList());
  }

  public CarteiraDTO carteiraPorId(@NotNull @Positive Long id) {
    return carteiraRepository.findById(id)
        .map(carteiraMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public CarteiraDTO criarCarteira(@Valid @NotNull CarteiraDTO carteiraDTO) {
    return carteiraMapper.toDTO(carteiraRepository.save(carteiraMapper.toEntity(carteiraDTO)));
  }

  public CarteiraDTO atualizarCarteira(@NotNull @Positive Long id, @Valid @NotNull CarteiraDTO carteiraDTO) {
    return carteiraRepository.findById(id)
        .map((recordFound) -> {
          Carteira carteira = carteiraMapper.toEntity(carteiraDTO);
          carteira.setId(recordFound.getId());
          return carteiraRepository.save(carteira);
        })
        .map(carteiraMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public void deletarCarteira(@NotNull @Positive Long id) {
    carteiraRepository.delete(carteiraRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
  }
}
