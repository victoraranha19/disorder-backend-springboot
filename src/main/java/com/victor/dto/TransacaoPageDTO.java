package com.victor.dto;

import java.util.List;

public record TransacaoPageDTO(
        List<TransacaoDTO> transacoes,
        int pagina,
        int transacoesPorPagina,
        long totalTransacoes,
        int totalPaginas) {

}
