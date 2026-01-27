package com.victor.controller;

import com.victor.dto.TransacaoDTO;
import com.victor.enums.TipoTransacao;
import com.victor.service.TransacaoService;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {
    private final TransacaoService transacaoService;

    TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping("/entradas")
    public List<TransacaoDTO> listarEntradas(@RequestParam() @NotNull @Length(min = 6, max = 6) String mesAno,
                                             @RequestParam() @Nullable Integer idCarteira,
                                             @RequestParam() @Nullable Integer idCategoria) throws ParseException {
        return transacaoService.listarTransacoes(TipoTransacao.ENTRADA, mesAno, idCarteira, idCategoria);
    }

    @GetMapping("/saidas")
    public List<TransacaoDTO> listarSaidas(@RequestParam() @NotNull @Length(min = 6, max = 6) String mesAno,
                                           @RequestParam() @Nullable Integer idCarteira,
                                           @RequestParam() @Nullable Integer idCategoria) throws ParseException {
        return transacaoService.listarTransacoes(TipoTransacao.SAIDA, mesAno, idCarteira, idCategoria);
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
