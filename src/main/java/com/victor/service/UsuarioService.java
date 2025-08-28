package com.victor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.victor.dto.UsuarioDTO;
import com.victor.dto.mapper.UsuarioMapper;
import com.victor.exception.RecordNotFoundException;
import com.victor.model.Usuario;
import com.victor.repository.UsuarioRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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

  public UsuarioDTO usuarioPorId(@NotNull @Positive Long id) {
    return usuarioRepository.findById(id)
        .map(UsuarioMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public UsuarioDTO criarUsuario(@Valid @NotNull UsuarioDTO usuarioDTO) {
    return UsuarioMapper.toDTO(usuarioRepository.save(UsuarioMapper.toEntity(usuarioDTO)));
  }

  public UsuarioDTO atualizarUsuario(@NotNull @Positive Long id, @Valid @NotNull UsuarioDTO usuarioDTO) {
    return usuarioRepository.findById(id)
        .map((recordFound) -> {
          Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
          usuario.setIdUsuario(recordFound.getIdUsuario());
          return usuarioRepository.save(usuario);
        })
        .map(UsuarioMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundException(id));
  }

  public void deletarUsuario(@NotNull @Positive Long id) {
    usuarioRepository.delete(usuarioRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
  }
}
