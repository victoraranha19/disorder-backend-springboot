package com.victor.service;

import com.victor.dto.CarteiraDTO;
import com.victor.dto.mapper.CarteiraMapper;
import com.victor.exception.RecordNotFoundException;
import com.victor.model.Carteira;
import com.victor.repository.CarteiraRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CarteiraService {
    @Autowired
    CarteiraRepository carteiraRepository;
    @Autowired
    UsuarioService usuarioService;

    public List<CarteiraDTO> listarCarteirasUsuario() {
        return carteiraRepository.findByUsuarioId(usuarioService.getUsuarioLogado().getId())
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
        Carteira novaCarteira = CarteiraMapper.toEntity(carteiraDTO);
        novaCarteira.setUsuario(usuarioService.getUsuarioLogado());
        return CarteiraMapper.toDTO(carteiraRepository.save(novaCarteira));
    }

    public CarteiraDTO atualizarCarteira(@NotNull @Positive Integer id, @Valid @NotNull CarteiraDTO carteiraDTO) {
        return carteiraRepository.findById(id)
                .map((recordFound) -> {
                    recordFound.setId(id);
                    recordFound.setTitulo(carteiraDTO.titulo());
                    recordFound.setContaCorrente(carteiraDTO.contaCorrente());
                    recordFound.setContaPoupanca(carteiraDTO.contaPoupanca());
                    recordFound.setContaInvestimento(carteiraDTO.contaInvestimento());
                    recordFound.setLimiteCreditoTotal(carteiraDTO.limiteCreditoTotal());
                    return carteiraRepository.save(recordFound);
                })
                .map(CarteiraMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deletarCarteira(@NotNull @Positive Integer id) {
        carteiraRepository.delete(carteiraRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
