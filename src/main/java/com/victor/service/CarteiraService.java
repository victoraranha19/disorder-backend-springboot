package com.victor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.victor.dto.InstituicaoDTO;
import com.victor.dto.mapper.InstituicaoMapper;
import com.victor.exception.RecordNotFoundException;
import com.victor.model.Instituicao;
import com.victor.repository.InstituicaoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CarteiraService {

  private final InstituicaoRepository instituicaoRepository;

  public CarteiraService(InstituicaoRepository carteiraRepository) {
    this.instituicaoRepository = carteiraRepository;
  }

  public List<InstituicaoDTO> listarCarteiras() {
    return instituicaoRepository.findAll()
        .stream()
        .map(InstituicaoMapper::toDTO)
        .collect(Collectors.toList());
  }

  public InstituicaoDTO carteiraPorId(@NotNull @Positive Long id) {
    return instituicaoRepository.findById(id)
        .map(InstituicaoMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public InstituicaoDTO criarCarteira(@Valid @NotNull InstituicaoDTO instituicaoDTO) {
    return InstituicaoMapper.toDTO(instituicaoRepository.save(InstituicaoMapper.toEntity(instituicaoDTO)));
  }

  public InstituicaoDTO atualizarCarteira(@NotNull @Positive Long id, @Valid @NotNull InstituicaoDTO instituicaoDTO) {
    return instituicaoRepository.findById(id)
        .map((recordFound) -> {
          Instituicao carteira = InstituicaoMapper.toEntity(instituicaoDTO);
          recordFound.setNome(instituicaoDTO.nome());
          recordFound.getContas().forEach(carteira::addConta);

          recordFound.limparContas();
          carteira.getContas().forEach(recordFound::addConta);
          return instituicaoRepository.save(recordFound);
        })
        .map(InstituicaoMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public void deletarCarteira(@NotNull @Positive Long id) {
    instituicaoRepository.delete(instituicaoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
  }
}
