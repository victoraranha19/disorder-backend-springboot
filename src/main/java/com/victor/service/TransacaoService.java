package com.victor.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.victor.dto.TransacaoDTO;
import com.victor.dto.TransacaoPageDTO;
import com.victor.dto.mapper.TransacaoMapper;
import com.victor.exception.RecordNotFoundException;
import com.victor.model.Transacao;
import com.victor.repository.TransacaoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@Service
public class TransacaoService {

  private final TransacaoRepository transacaoRepository;

  public TransacaoService(TransacaoRepository transacaoRepository) {
    this.transacaoRepository = transacaoRepository;
  }

  public TransacaoPageDTO listarTransacoes(@PositiveOrZero int pagina, @Positive @Max(100) int itensPorPagina) {
    Page<Transacao> pageTransacoes = transacaoRepository.findAll(PageRequest.of(pagina, itensPorPagina));
    List<TransacaoDTO> transacoes = pageTransacoes.get().map(TransacaoMapper::toDTO).collect(Collectors.toList());
    return new TransacaoPageDTO(transacoes, pageTransacoes.getNumber(), pageTransacoes.getSize(),
        pageTransacoes.getNumberOfElements(),
        pageTransacoes.getTotalPages());
  }

  public TransacaoDTO transacaoPorId(@NotNull @Positive UUID id) {
    return transacaoRepository.findById(id)
        .map(TransacaoMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public TransacaoDTO criarTransacao(@Valid @NotNull TransacaoDTO transacaoDTO) {
    return TransacaoMapper.toDTO(transacaoRepository.save(TransacaoMapper.toEntity(transacaoDTO)));
  }

  public TransacaoDTO atualizarTransacao(@NotNull @Positive UUID id, @Valid @NotNull TransacaoDTO transacaoDTO) {
    return transacaoRepository.findById(id)
        .map((recordFound) -> {
          Transacao transacao = TransacaoMapper.toEntity(transacaoDTO);
          recordFound.setDescricao(transacaoDTO.descricao());
          recordFound.setValor(transacaoDTO.valor());
          recordFound.setDataTransacao(transacaoDTO.dataTransacao());
          recordFound.setTipo(transacao.getTipo());
          return transacaoRepository.save(recordFound);
        })
        .map(TransacaoMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public void deletarTransacao(@NotNull @Positive UUID id) {
    transacaoRepository.delete(transacaoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
  }
}
