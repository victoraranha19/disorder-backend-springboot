package com.victor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.victor.model.Transacao;
import com.victor.repository.TransacaoRepository;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/transacoes")
@AllArgsConstructor
public class TransacaoController {

  private final TransacaoRepository transacaoRepository;

  @GetMapping
  public List<Transacao> listarTransacoes() {
    return transacaoRepository.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Transacao criarTransacao(@RequestBody Transacao transacao) {
    return transacaoRepository.save(transacao);
  }
}
