package com.victor.service;

import com.victor.dto.CategoriaDTO;
import com.victor.dto.mapper.CategoriaMapper;
import com.victor.exception.RecordNotFoundException;
import com.victor.model.Categoria;
import com.victor.repository.CategoriaRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final UsuarioService usuarioService;

    CategoriaService(CategoriaRepository categoriaRepository, UsuarioService usuarioService) {
        this.categoriaRepository = categoriaRepository;
        this.usuarioService = usuarioService;
    }

    // Create
    public CategoriaDTO criarCategoria(@Valid @NotNull CategoriaDTO categoriaDTO) {
        Categoria novaCategoria = CategoriaMapper.toEntity(categoriaDTO);
        novaCategoria.setUsuario(usuarioService.getUsuarioLogado());
        return CategoriaMapper.toDTO(categoriaRepository.save(novaCategoria));
    }

    // Read
    public List<CategoriaDTO> listarCategoriasUsuario() {
        return categoriaRepository.findByUsuarioId(usuarioService.getUsuarioLogado().getId())
                .stream()
                .map(CategoriaMapper::toDTO)
                .collect(Collectors.toList());
    }
    public @Nullable Categoria categoriaTransacaoPorId(Integer idCategoria) {
        if (idCategoria == null) return null;
        return categoriaRepository.findById(idCategoria).orElseThrow(() -> new RecordNotFoundException(idCategoria));
    }

    // Update
    public CategoriaDTO atualizarCategoria(@NotNull @Positive Integer id, @Valid @NotNull CategoriaDTO categoriaDTO) {
        return categoriaRepository.findById(id)
                .map((recordFound) -> {
                    recordFound.setTitulo(categoriaDTO.titulo());
                    recordFound.setValorPlanejado(categoriaDTO.valorPlanejado());
                    return categoriaRepository.save(recordFound);
                })
                .map(CategoriaMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    // Delete
    public void deletarCategoria(@NotNull @Positive Integer id) {
        categoriaRepository.delete(categoriaRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
