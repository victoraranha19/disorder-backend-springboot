package com.victor.service;

import com.victor.dto.TransacaoDTO;
import com.victor.dto.mapper.TransacaoMapper;
import com.victor.enums.TipoCarteira;
import com.victor.enums.TipoTransacao;
import com.victor.exception.RecordNotFoundException;
import com.victor.model.Transacao;
import com.victor.repository.TransacaoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    public List<TransacaoDTO> listarTransacoes(@NotNull TipoTransacao tipoTransacao, @NotNull @Length(min = 6, max = 6) String mesAno, @Nullable Integer idCarteira, @Nullable Integer idCategoria) throws ParseException {
        UUID idUsuario = usuarioService.getUsuarioLogado().getId();
        return switch (tipoTransacao) {
            case ENTRADA ->
                    getTransacoesEntrada(mesAno, idUsuario, idCarteira, idCategoria).stream().map(TransacaoMapper::toDTO).toList();
            case SAIDA ->
                    getTransacoesSaida(mesAno, idUsuario, idCarteira, idCategoria).stream().map(TransacaoMapper::toDTO).toList();
        };
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

    private List<Transacao> getTransacoesEntrada(@NotNull @Length(min = 6, max = 6) String mesAno, @NotNull UUID idUsuario, @Nullable Integer idCarteira, @Nullable Integer idCategoria) throws ParseException {
        Date dataInicio = getDataInicioMesAno(mesAno);
        Date dataFim = getDataFimMesAno(mesAno);
        if (idCarteira != null && idCategoria != null) {
            return transacaoRepository.findByUsuarioIdAndDataTransacaoBetweenAndCarteiraIdAndCategoriaIdAndValorGreaterThanOrderByDataTransacaoDesc(idUsuario, dataInicio, dataFim, idCarteira, idCategoria, 0.0);
        }
        if (idCarteira != null) {
            return transacaoRepository.findByUsuarioIdAndDataTransacaoBetweenAndCarteiraIdAndValorGreaterThanOrderByDataTransacaoDesc(idUsuario, dataInicio, dataFim, idCarteira, 0.0);
        }
        if (idCategoria != null) {
            return transacaoRepository.findByUsuarioIdAndDataTransacaoBetweenAndCategoriaIdAndValorGreaterThanOrderByDataTransacaoDesc(idUsuario, dataInicio, dataFim, idCategoria, 0.0);
        }
        return transacaoRepository.findByUsuarioIdAndDataTransacaoBetweenAndValorGreaterThanOrderByDataTransacaoDesc(idUsuario, dataInicio, dataFim, 0.0);
    }

    private List<Transacao> getTransacoesSaida(@NotNull @Length(min = 6, max = 6) String mesAno, @NotNull UUID idUsuario, @Nullable Integer idCarteira, @Nullable Integer idCategoria) throws ParseException {
        Date dataInicio = getDataInicioMesAno(mesAno);
        Date dataFim = getDataFimMesAno(mesAno);
        if (idCarteira != null && idCategoria != null) {
            return transacaoRepository.findByUsuarioIdAndDataTransacaoBetweenAndCarteiraIdAndCategoriaIdAndValorLessThanOrderByDataTransacaoDesc(idUsuario, dataInicio, dataFim, idCarteira, idCategoria, 0.0);
        }
        if (idCarteira != null) {
            return transacaoRepository.findByUsuarioIdAndDataTransacaoBetweenAndCarteiraIdAndValorLessThanOrderByDataTransacaoDesc(idUsuario, dataInicio, dataFim, idCarteira, 0.0);
        }
        if (idCategoria != null) {
            return transacaoRepository.findByUsuarioIdAndDataTransacaoBetweenAndCategoriaIdAndValorLessThanOrderByDataTransacaoDesc(idUsuario, dataInicio, dataFim, idCategoria, 0.0);
        }
        return transacaoRepository.findByUsuarioIdAndDataTransacaoBetweenAndValorLessThanOrderByDataTransacaoDesc(idUsuario, dataInicio, dataFim, 0.0);
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
