package com.victor.service;

import com.victor.dto.CategoriaDTO;
import com.victor.dto.mapper.CategoriaMapper;
import com.victor.exception.RecordNotFoundException;
import com.victor.model.Categoria;
import com.victor.repository.CategoriaRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    UsuarioService usuarioService;

    public List<CategoriaDTO> listarCategoriasUsuario() {
        return categoriaRepository.findByUsuarioId(usuarioService.getUsuarioLogado().getId())
                .stream()
                .map(CategoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO categoriaPorId(@NotNull @Positive Integer id) {
        return categoriaRepository.findById(id)
                .map(CategoriaMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CategoriaDTO criarCategoria(@Valid @NotNull CategoriaDTO categoriaDTO) {
        Categoria novaCategoria = CategoriaMapper.toEntity(categoriaDTO);
        novaCategoria.setUsuario(usuarioService.getUsuarioLogado());
        return CategoriaMapper.toDTO(categoriaRepository.save(novaCategoria));
    }

    public CategoriaDTO atualizarCategoria(@NotNull @Positive Integer id, @Valid @NotNull CategoriaDTO categoriaDTO) {
        return categoriaRepository.findById(id)
                .map((recordFound) -> {
                    recordFound.setId(id);
                    recordFound.setTitulo(categoriaDTO.titulo());
                    recordFound.setValorPlanejado(categoriaDTO.valorPlanejado());
                    return categoriaRepository.save(recordFound);
                })
                .map(CategoriaMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deletarCategoria(@NotNull @Positive Integer id) {
        categoriaRepository.delete(categoriaRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
