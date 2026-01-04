package com.victor.service;

import com.victor.dto.CarteiraDTO;
import com.victor.dto.mapper.CarteiraMapper;
import com.victor.exception.RecordNotFoundException;
import com.victor.model.Carteira;
import com.victor.repository.CarteiraRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CarteiraService {

    private final CarteiraRepository carteiraRepository;

    public CarteiraService(CarteiraRepository carteiraRepository) {
        this.carteiraRepository = carteiraRepository;
    }

    public List<CarteiraDTO> listarCarteiras() {
        return carteiraRepository.findAll()
                .stream()
                .map(CarteiraMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CarteiraDTO carteiraPorId(@NotNull @Positive Integer id) {
        return carteiraRepository.findById(id)
                .map(CarteiraMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CarteiraDTO criarCarteira(@Valid @NotNull CarteiraDTO carteiraDTO) {
        return CarteiraMapper.toDTO(carteiraRepository.save(CarteiraMapper.toEntity(carteiraDTO)));
    }

    public CarteiraDTO atualizarCarteira(@NotNull @Positive Integer id, @Valid @NotNull CarteiraDTO carteiraDTO) {
        return carteiraRepository.findById(id)
                .map((recordFound) -> {
                    Carteira carteira = CarteiraMapper.toEntity(carteiraDTO);
                    recordFound.setTitulo(carteiraDTO.titulo());
                    recordFound.setContaCorrente(carteiraDTO.contaCorrente());
                    recordFound.setContaPoupanca(carteiraDTO.contaPoupanca());
                    recordFound.setContaInvestimento(carteiraDTO.contaInvestimento());
                    recordFound.setLimiteCreditoTotal(carteiraDTO.limiteCreditoTotal());
                    recordFound.limparTransacoes();
                    carteira.getTransacoes().forEach(recordFound::addTransacao);
                    return carteiraRepository.save(recordFound);
                })
                .map(CarteiraMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deletarCarteira(@NotNull @Positive Integer id) {
        carteiraRepository.delete(carteiraRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
