package com.victor.service;

import com.victor.dto.CategoriaDTO;
import com.victor.dto.mapper.CategoriaMapper;
import com.victor.exception.RecordNotFoundException;
import com.victor.model.Categoria;
import com.victor.repository.CategoriaRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepository.findAll()
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
        return CategoriaMapper.toDTO(categoriaRepository.save(CategoriaMapper.toEntity(categoriaDTO)));
    }

    public CategoriaDTO atualizarCategoria(@NotNull @Positive Integer id, @Valid @NotNull CategoriaDTO categoriaDTO) {
        return categoriaRepository.findById(id)
                .map((recordFound) -> {
                    Categoria categoria = CategoriaMapper.toEntity(categoriaDTO);
                    recordFound.setTitulo(categoriaDTO.titulo());
                    recordFound.setValorPlanejado(categoriaDTO.valorPlanejado());
                    recordFound.limparTransacoes();
                    categoria.getTransacoes().forEach(recordFound::addTransacao);
                    return categoriaRepository.save(recordFound);
                })
                .map(CategoriaMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deletarCategoria(@NotNull @Positive Integer id) {
        categoriaRepository.delete(categoriaRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
