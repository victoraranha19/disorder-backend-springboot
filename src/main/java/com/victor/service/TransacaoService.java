package com.victor.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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

  public TransacaoService(TransacaoRepository transacaoRepository) {
    this.transacaoRepository = transacaoRepository;
  }

  public List<Transacao> listarTransacoes() {
    return transacaoRepository.findAll();
  }

  public Transacao transacaoPorId(@NotNull @Positive Long id) {
    return transacaoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
  }

  public Transacao criarTransacao(@Valid Transacao transacao) {
    return transacaoRepository.save(transacao);
  }

  public Transacao atualizarTransacao(@NotNull @Positive Long id, @Valid Transacao transacao) {
    return transacaoRepository.findById(id).map((recordFound) -> {
      transacao.setId(recordFound.getId());
      return transacaoRepository.save(transacao);
    }).orElseThrow(() -> new RecordNotFoundException(id));
  }

  public void deletarTransacao(@NotNull @Positive Long id) {
    transacaoRepository.delete(transacaoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
  }
}
