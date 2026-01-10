package com.victor.service;

import com.victor.dto.CarteiraDTO;
import com.victor.dto.mapper.CarteiraMapper;
import com.victor.exception.RecordNotFoundException;
import com.victor.model.Carteira;
import com.victor.repository.CarteiraRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CarteiraService {
    private final CarteiraRepository carteiraRepository;
    private final UsuarioService usuarioService;

    CarteiraService(CarteiraRepository carteiraRepository, UsuarioService usuarioService) {
        this.carteiraRepository = carteiraRepository;
        this.usuarioService = usuarioService;
    }

    // Create
    public CarteiraDTO criarCarteira(@Valid @NotNull CarteiraDTO carteiraDTO) {
        Carteira novaCarteira = CarteiraMapper.toEntity(carteiraDTO);
        novaCarteira.setUsuario(usuarioService.getUsuarioLogado());
        return CarteiraMapper.toDTO(carteiraRepository.save(novaCarteira));
    }

    // Read
    public List<CarteiraDTO> listarCarteirasUsuario() {
        return carteiraRepository.findByUsuarioId(usuarioService.getUsuarioLogado().getId())
                .stream()
                .map(CarteiraMapper::toDTO)
                .collect(Collectors.toList());
    }
    public @Nullable Carteira carteiraTransacaoPorId(Integer idCarteira) {
        if (idCarteira == null) return null;
        return carteiraRepository.findById(idCarteira).orElseThrow(() -> new RecordNotFoundException(idCarteira));
    }

    // Update
    public CarteiraDTO atualizarCarteira(@NotNull @Positive Integer id, @Valid @NotNull CarteiraDTO carteiraDTO) {
        return carteiraRepository.findById(id)
                .map((recordFound) -> {
                    recordFound.setTitulo(carteiraDTO.titulo());
                    recordFound.setLimiteCreditoTotal(carteiraDTO.limiteCreditoTotal());
                    return carteiraRepository.save(recordFound);
                })
                .map(CarteiraMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    // Delete
    public void deletarCarteira(@NotNull @Positive Integer id) {
        carteiraRepository.delete(carteiraRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
