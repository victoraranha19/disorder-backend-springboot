package com.victor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victor.model.Transacao;
import com.victor.repository.TransacaoRepository;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/transacoes")
@AllArgsConstructor
public class TransacaoController {

  private final TransacaoRepository transacaoRepository;

  @GetMapping
  public List<Transacao> listarTransacoes() {
    return transacaoRepository.findAll();
  }
}
