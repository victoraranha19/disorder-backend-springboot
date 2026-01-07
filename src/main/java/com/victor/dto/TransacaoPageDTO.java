package com.victor.dto;

import java.util.List;

public record TransacaoPageDTO(
        List<TransacaoDTO> transacoes,
        long totalTransacoes,
        int totalPaginas) {

}
