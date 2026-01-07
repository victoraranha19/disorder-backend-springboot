package com.victor.controller;

import com.victor.dto.TransacaoDTO;
import com.victor.dto.TransacaoPageDTO;
import com.victor.service.TransacaoService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {
    @Autowired
    TransacaoService transacaoService;

    @GetMapping
    public TransacaoPageDTO listarTransacoes(@RequestParam(defaultValue = "0") @PositiveOrZero int pagina,
                                             @RequestParam(defaultValue = "10") @Positive @Max(100) int itensPorPagina) {
        return transacaoService.listarTransacoes(pagina, itensPorPagina);
    }

    @GetMapping("/{id}")
    public TransacaoDTO transacaoPorId(@PathVariable Integer id) {
        return transacaoService.transacaoPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransacaoDTO criarTransacao(@RequestBody TransacaoDTO transacao) {
        return transacaoService.criarTransacao(transacao);
    }

    @PutMapping("/{id}")
    public TransacaoDTO atualizarTransacao(@PathVariable Integer id, @RequestBody TransacaoDTO transacao) {
        return transacaoService.atualizarTransacao(id, transacao);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarTransacao(@PathVariable Integer id) {
        transacaoService.deletarTransacao(id);
    }
}
