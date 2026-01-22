package com.victor.service;

import com.victor.dto.TransacaoDTO;
import com.victor.dto.TransacaoPageDTO;
import com.victor.dto.mapper.TransacaoMapper;
import com.victor.enums.TipoCarteira;
import com.victor.enums.TipoTransacao;
import com.victor.exception.RecordNotFoundException;
import com.victor.model.Transacao;
import com.victor.repository.TransacaoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Validated
@Service
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;
    private final CarteiraService carteiraService;
    private final CategoriaService categoriaService;
    private final UsuarioService usuarioService;

    TransacaoService(TransacaoRepository transacaoRepository, CarteiraService carteiraService, CategoriaService categoriaService, UsuarioService usuarioService) {
        this.transacaoRepository = transacaoRepository;
        this.carteiraService = carteiraService;
        this.categoriaService = categoriaService;
        this.usuarioService = usuarioService;
    }

    // Create
    public TransacaoDTO criarTransacao(@Valid @NotNull TransacaoDTO transacaoDTO) {
        Transacao novaTransacao = TransacaoMapper.toEntity(transacaoDTO);
        novaTransacao.setUsuario(usuarioService.getUsuarioLogado());
        novaTransacao.setCarteira(carteiraService.carteiraTransacaoPorId(transacaoDTO.idCarteira()));
        novaTransacao.setCategoria(categoriaService.categoriaTransacaoPorId(transacaoDTO.idCategoria()));
        return TransacaoMapper.toDTO(transacaoRepository.save(novaTransacao));
    }

    // Read
    public TransacaoPageDTO listarTransacoes(@NotNull TipoTransacao tipoTransacao, @PositiveOrZero int pagina, @Positive @Max(100) int itensPorPagina, @NotNull @Length(min = 6, max = 6) String mesAno, @Nullable Integer idCarteira, @Nullable Integer idCategoria) throws ParseException {
        UUID idUsuario = usuarioService.getUsuarioLogado().getId();
        PageRequest pageRequest = PageRequest.of(pagina, itensPorPagina);
        Page<Transacao> pageTransacoes;
        switch (tipoTransacao) {
            case ENTRADA ->
                    pageTransacoes = getTransacoesEntrada(mesAno, idUsuario, pageRequest, idCarteira, idCategoria);
            case SAIDA -> pageTransacoes = getTransacoesSaida(mesAno, idUsuario, pageRequest, idCarteira, idCategoria);
            default -> throw new RuntimeException("Tipo de transação inválido");
        }
        List<TransacaoDTO> transacoes = pageTransacoes.get().map(TransacaoMapper::toDTO).collect(Collectors.toList());
        return new TransacaoPageDTO(transacoes, pageTransacoes.getNumberOfElements(), pageTransacoes.getTotalPages());
    }

    // Update
    public TransacaoDTO atualizarTransacao(@NotNull @Positive Integer id, @Valid @NotNull TransacaoDTO transacaoDTO) {
        return transacaoRepository.findById(id).map((recordFound) -> {
            recordFound.setTipo(TipoCarteira.convertTipoTransacaoValue(transacaoDTO.tipo()));
            recordFound.setDataTransacao(transacaoDTO.dataTransacao());
            recordFound.setValor(transacaoDTO.valor());
            recordFound.setDescricao(transacaoDTO.descricao());
            recordFound.setParcelas(transacaoDTO.parcelas());
            recordFound.setCarteira(carteiraService.carteiraTransacaoPorId(transacaoDTO.idCarteira()));
            recordFound.setCategoria(categoriaService.categoriaTransacaoPorId(transacaoDTO.idCategoria()));
            return transacaoRepository.save(recordFound);
        }).map(TransacaoMapper::toDTO).orElseThrow(() -> new RecordNotFoundException(id));
    }

    // Delete
    public void deletarTransacao(@NotNull @Positive Integer id) {
        transacaoRepository.delete(transacaoRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }

    private Page<Transacao> getTransacoesEntrada(@NotNull @Length(min = 6, max = 6) String mesAno, @NotNull UUID idUsuario, @NotNull PageRequest pageRequest, @Nullable Integer idCarteira, @Nullable Integer idCategoria) throws ParseException {
        Date dataInicio = getDataInicioMesAno(mesAno);
        Date dataFim = getDataFimMesAno(mesAno);
        if (idCarteira != null && idCategoria != null) {
            return transacaoRepository.findByUsuarioIdAndDataTransacaoBetweenAndCarteiraIdAndCategoriaIdAndValorGreaterThanOrderByDataTransacaoDesc(idUsuario, dataInicio, dataFim, idCarteira, idCategoria, 0.0, pageRequest);
        }
        if (idCarteira != null) {
            return transacaoRepository.findByUsuarioIdAndDataTransacaoBetweenAndCarteiraIdAndValorGreaterThanOrderByDataTransacaoDesc(idUsuario, dataInicio, dataFim, idCarteira, 0.0, pageRequest);
        }
        if (idCategoria != null) {
            return transacaoRepository.findByUsuarioIdAndDataTransacaoBetweenAndCategoriaIdAndValorGreaterThanOrderByDataTransacaoDesc(idUsuario, dataInicio, dataFim, idCategoria, 0.0, pageRequest);
        }
        return transacaoRepository.findByUsuarioIdAndDataTransacaoBetweenAndValorGreaterThanOrderByDataTransacaoDesc(idUsuario, dataInicio, dataFim, 0.0, pageRequest);
    }

    private Page<Transacao> getTransacoesSaida(@NotNull @Length(min = 6, max = 6) String mesAno, @NotNull UUID idUsuario, @NotNull PageRequest pageRequest, @Nullable Integer idCarteira, @Nullable Integer idCategoria) throws ParseException {
        Date dataInicio = getDataInicioMesAno(mesAno);
        Date dataFim = getDataFimMesAno(mesAno);
        if (idCarteira != null && idCategoria != null) {
            return transacaoRepository.findByUsuarioIdAndDataTransacaoBetweenAndCarteiraIdAndCategoriaIdAndValorLessThanOrderByDataTransacaoDesc(idUsuario, dataInicio, dataFim, idCarteira, idCategoria, 0.0, pageRequest);
        }
        if (idCarteira != null) {
            return transacaoRepository.findByUsuarioIdAndDataTransacaoBetweenAndCarteiraIdAndValorLessThanOrderByDataTransacaoDesc(idUsuario, dataInicio, dataFim, idCarteira, 0.0, pageRequest);
        }
        if (idCategoria != null) {
            return transacaoRepository.findByUsuarioIdAndDataTransacaoBetweenAndCategoriaIdAndValorLessThanOrderByDataTransacaoDesc(idUsuario, dataInicio, dataFim, idCategoria, 0.0, pageRequest);
        }
        return transacaoRepository.findByUsuarioIdAndDataTransacaoBetweenAndValorLessThanOrderByDataTransacaoDesc(idUsuario, dataInicio, dataFim, 0.0, pageRequest);
    }

    private Date getDataInicioMesAno(String mesAno) throws ParseException {
        String diaMesAno = "01/" + mesAno.substring(0, 2) + "/" + mesAno.substring(2, 6);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.parse(diaMesAno);
    }

    private Date getDataFimMesAno(String mesAno) throws ParseException {
        // A data final é o primeiro dia do mês seguinte
        int mes = Integer.parseInt(mesAno.substring(0, 2));
        int ano = Integer.parseInt(mesAno.substring(2, 6));
        if (mes == 12) {
            mes = 1;
            ano += 1;
        } else {
            mes += 1;
        }
        String diaMesAno = "01/" + mes + "/" + ano;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.parse(diaMesAno);
    }
}
