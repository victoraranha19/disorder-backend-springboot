package com.victor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.victor.dto.TransacaoDTO;
import com.victor.dto.mapper.TransacaoMapper;
import com.victor.exception.RecordNotFoundException;
import com.victor.model.Transacao;
import com.victor.repository.TransacaoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class TransacaoService {

  private final TransacaoRepository transacaoRepository;
  private final TransacaoMapper transacaoMapper;

  public TransacaoService(TransacaoRepository transacaoRepository, TransacaoMapper transacaoMapper) {
    this.transacaoRepository = transacaoRepository;
    this.transacaoMapper = transacaoMapper;
  }

  public List<TransacaoDTO> listarTransacoes() {
    return transacaoRepository.findAll()
        .stream()
        .map(transacaoMapper::toDTO)
        .collect(Collectors.toList());
  }

  public TransacaoDTO transacaoPorId(@NotNull @Positive Long id) {
    return transacaoRepository.findById(id)
        .map(transacaoMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public TransacaoDTO criarTransacao(@Valid @NotNull TransacaoDTO transacaoDTO) {
    return transacaoMapper.toDTO(transacaoRepository.save(transacaoMapper.toEntity(transacaoDTO)));
  }

  public TransacaoDTO atualizarTransacao(@NotNull @Positive Long id, @Valid @NotNull TransacaoDTO transacaoDTO) {
    return transacaoRepository.findById(id)
        .map((recordFound) -> {
          Transacao transacao = transacaoMapper.toEntity(transacaoDTO);
          transacao.setId(recordFound.getId());
          return transacaoRepository.save(transacao);
        })
        .map(transacaoMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public void deletarTransacao(@NotNull @Positive Long id) {
    transacaoRepository.delete(transacaoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
  }
}
