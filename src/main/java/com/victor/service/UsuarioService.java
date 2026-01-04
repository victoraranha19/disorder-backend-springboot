package com.victor.service;

import com.victor.dto.UsuarioDTO;
import com.victor.dto.mapper.CategoriaMapper;
import com.victor.dto.mapper.UsuarioMapper;
import com.victor.exception.RecordNotFoundException;
import com.victor.model.Categoria;
import com.victor.model.Usuario;
import com.victor.repository.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Validated
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO usuarioPorId(@NotNull @Positive UUID id) {
        return usuarioRepository.findById(id)
                .map(UsuarioMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public UsuarioDTO criarUsuario(@Valid @NotNull UsuarioDTO usuarioDTO) {
        return UsuarioMapper.toDTO(usuarioRepository.save(UsuarioMapper.toEntity(usuarioDTO)));
    }

    public UsuarioDTO atualizarUsuario(@NotNull @Positive UUID id, @Valid @NotNull UsuarioDTO usuarioDTO) {
        return usuarioRepository.findById(id)
                .map((recordFound) -> {
                    Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
                    recordFound.setUsername(usuarioDTO.username());
                    recordFound.setNomeCompleto(usuarioDTO.nomeCompleto());
                    recordFound.setEmail(usuarioDTO.email());
                    recordFound.setTelefone(usuarioDTO.telefone());
                    recordFound.setChavePix(usuarioDTO.chavePix());
                    recordFound.setPapel(usuarioDTO.papel());
                    recordFound.limparClientes();
                    usuario.getClientes().forEach(recordFound::addCliente);
                    recordFound.limparTransacoes();
                    usuario.getTransacoes().forEach(recordFound::addTransacao);
                    recordFound.limparCarteiras();
                    usuario.getCarteiras().forEach(recordFound::addCarteira);
                    recordFound.limparCategorias();
                    usuario.getCategorias().forEach(recordFound::addCategoria);
                    return usuarioRepository.save(recordFound);
                })
                .map(UsuarioMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deletarUsuario(@NotNull @Positive UUID id) {
        usuarioRepository.delete(usuarioRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
