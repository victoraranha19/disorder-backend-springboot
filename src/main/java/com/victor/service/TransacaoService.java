package com.victor.service;

import com.victor.dto.TransacaoDTO;
import com.victor.dto.TransacaoPageDTO;
import com.victor.dto.mapper.TransacaoMapper;
import com.victor.enums.TipoTransacao;
import com.victor.exception.RecordNotFoundException;
import com.victor.model.Carteira;
import com.victor.model.Categoria;
import com.victor.model.Transacao;
import com.victor.repository.CarteiraRepository;
import com.victor.repository.CategoriaRepository;
import com.victor.repository.TransacaoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class TransacaoService {
    @Autowired
    TransacaoRepository transacaoRepository;
    @Autowired
    CarteiraRepository carteiraRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    UsuarioService usuarioService;

    public TransacaoPageDTO listarTransacoes(@PositiveOrZero int pagina, @Positive @Max(100) int itensPorPagina) {
        Page<Transacao> pageTransacoes = transacaoRepository.findByUsuarioId(usuarioService.getUsuarioLogado().getId(), PageRequest.of(pagina, itensPorPagina));
        List<TransacaoDTO> transacoes = pageTransacoes.get().map(TransacaoMapper::toDTO).collect(Collectors.toList());
        return new TransacaoPageDTO(transacoes,
                pageTransacoes.getNumberOfElements(),
                pageTransacoes.getTotalPages());
    }

    public TransacaoDTO transacaoPorId(@NotNull @Positive Integer id) {
        return transacaoRepository.findById(id)
                .map(TransacaoMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public TransacaoDTO criarTransacao(@Valid @NotNull TransacaoDTO transacaoDTO) {
        Transacao novaTransacao = TransacaoMapper.toEntity(transacaoDTO);
        novaTransacao.setUsuario(usuarioService.getUsuarioLogado());
        novaTransacao.setCarteira(carteiraTransacaoPorId(transacaoDTO.idCarteira()));
        novaTransacao.setCategoria(categoriaTransacaoPorId(transacaoDTO.idCategoria()));
        return TransacaoMapper.toDTO(transacaoRepository.save(novaTransacao));
    }

    public TransacaoDTO atualizarTransacao(@NotNull @Positive Integer id, @Valid @NotNull TransacaoDTO transacaoDTO) {
        return transacaoRepository.findById(id)
                .map((recordFound) -> {
                    recordFound.setId(id);
                    recordFound.setTipo(TipoTransacao.convertTipoTransacaoValue(transacaoDTO.tipo()));
                    recordFound.setDataTransacao(transacaoDTO.dataTransacao());
                    recordFound.setValor(transacaoDTO.valor());
                    recordFound.setDescricao(transacaoDTO.descricao());
                    recordFound.setParcelas(transacaoDTO.parcelas());
                    recordFound.setCarteira(carteiraTransacaoPorId(transacaoDTO.idCarteira()));
                    recordFound.setCategoria(categoriaTransacaoPorId(transacaoDTO.idCategoria()));
                    return transacaoRepository.save(recordFound);
                })
                .map(TransacaoMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deletarTransacao(@NotNull @Positive Integer id) {
        transacaoRepository.delete(transacaoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }

    private @Nullable Carteira carteiraTransacaoPorId(Integer idCarteira) {
        if (idCarteira == null) return null;
        return carteiraRepository.findById(idCarteira).orElseThrow(() -> new RecordNotFoundException(idCarteira));
    }
    private @Nullable Categoria categoriaTransacaoPorId(Integer idCategoria) {
        if (idCategoria == null) return null;
        return categoriaRepository.findById(idCategoria).orElseThrow(() -> new RecordNotFoundException(idCategoria));
    }
}
