package com.victor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.victor.dto.TransacaoDTO;
import com.victor.service.TransacaoService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

  private final TransacaoService transacaoService;

  public TransacaoController(TransacaoService transacaoService) {
    this.transacaoService = transacaoService;
  }

  @GetMapping
  public List<TransacaoDTO> listarTransacoes() {
    return transacaoService.listarTransacoes();
  }

  @GetMapping("/{id}")
  public TransacaoDTO transacaoPorId(@PathVariable Long id) {
    return transacaoService.transacaoPorId(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TransacaoDTO criarTransacao(@RequestBody TransacaoDTO transacao) {
    return transacaoService.criarTransacao(transacao);
  }

  @PutMapping("/{id}")
  public TransacaoDTO atualizarTransacao(@PathVariable Long id, @RequestBody TransacaoDTO transacao) {
    return transacaoService.atualizarTransacao(id, transacao);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletarTransacao(@PathVariable Long id) {
    transacaoService.deletarTransacao(id);
  }
}
