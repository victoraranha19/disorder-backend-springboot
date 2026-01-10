package com.victor.controller;

import com.victor.dto.TransacaoDTO;
import com.victor.dto.TransacaoPageDTO;
import com.victor.enums.TipoTransacao;
import com.victor.service.TransacaoService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {
    private final TransacaoService transacaoService;

    TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping("/entradas")
    public TransacaoPageDTO listarEntradas(@RequestParam(defaultValue = "0") @PositiveOrZero int pagina,
                                             @RequestParam(defaultValue = "10") @Positive @Max(100) int itensPorPagina,
                                             @RequestParam() @NotNull @Length(min = 6, max = 6) String mesAno,
                                             @RequestParam() @Nullable Integer carteiraId,
                                             @RequestParam() @Nullable Integer categoriaId) throws ParseException {
        return transacaoService.listarTransacoes(TipoTransacao.ENTRADA, pagina, itensPorPagina, mesAno, carteiraId, categoriaId);
    }
    @GetMapping("/saidas")
    public TransacaoPageDTO listarSaidas(@RequestParam(defaultValue = "0") @PositiveOrZero int pagina,
                                             @RequestParam(defaultValue = "10") @Positive @Max(100) int itensPorPagina,
                                             @RequestParam() @NotNull @Length(min = 6, max = 6) String mesAno,
                                             @RequestParam() @Nullable Integer carteiraId,
                                             @RequestParam() @Nullable Integer categoriaId) throws ParseException {
        return transacaoService.listarTransacoes(TipoTransacao.SAIDA, pagina, itensPorPagina, mesAno, carteiraId, categoriaId);
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
