package com.victor.dto.mapper;

import com.victor.dto.TransacaoDTO;
import com.victor.enums.TipoTransacao;
import com.victor.model.Transacao;
import org.springframework.stereotype.Component;

@Component
public class TransacaoMapper {
    public static TransacaoDTO toDTO(Transacao transacao) {
        if (transacao == null) return null;
        return new TransacaoDTO(transacao.getId(), transacao.getDescricao(), transacao.getValor(),
                transacao.getDataTransacao(), transacao.getTipo().getValue(), transacao.getParcelas(),
                transacao.getCarteira().getId(), transacao.getCategoria().getId());
    }

    public static Transacao toEntity(TransacaoDTO transacaoDTO) {
        Transacao transacao = new Transacao();
        if (transacaoDTO == null) return transacao;
        if (transacaoDTO.id() != null) {
            transacao.setId(transacaoDTO.id());
        }
        transacao.setDescricao(transacaoDTO.descricao());
        transacao.setValor(transacaoDTO.valor());
        transacao.setDataTransacao(transacaoDTO.dataTransacao());
        transacao.setTipo(TipoTransacao.convertTipoTransacaoValue(transacaoDTO.tipo()));
        transacao.setParcelas(transacaoDTO.parcelas());
        return transacao;
    }
}
