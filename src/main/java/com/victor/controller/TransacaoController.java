package com.victor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.victor.model.Transacao;
import com.victor.repository.TransacaoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@RestController
@RequestMapping("/api/transacoes")
@AllArgsConstructor
public class TransacaoController {

  private final TransacaoRepository transacaoRepository;

  @GetMapping
  public List<Transacao> listarTransacoes() {
    return transacaoRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Transacao> carteiraPorId(@PathVariable @NotNull @Positive Long id) {
    return transacaoRepository.findById(id).map(transacao -> ResponseEntity.ok().body(transacao))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Transacao criarTransacao(@RequestBody @Valid Transacao transacao) {
    return transacaoRepository.save(transacao);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Transacao> atualizarTransacao(@PathVariable @NotNull @Positive Long id,
      @RequestBody @Valid Transacao transacao) {
    if (!transacaoRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    transacao.setId(id);
    Transacao atualizada = transacaoRepository.save(transacao);
    return ResponseEntity.ok().body(atualizada);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarTransacao(@PathVariable @NotNull @Positive Long id) {
    if (!transacaoRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    transacaoRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
