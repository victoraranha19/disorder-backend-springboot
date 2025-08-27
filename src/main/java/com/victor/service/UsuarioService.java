package com.victor.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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

  public List<Usuario> listarUsuarios() {
    return usuarioRepository.findAll();
  }

  public Usuario usuarioPorId(@NotNull @Positive Long id) {
    return usuarioRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
  }

  public Usuario criarUsuario(@Valid Usuario usuario) {
    return usuarioRepository.save(usuario);
  }

  public Usuario atualizarUsuario(@NotNull @Positive Long id, @Valid Usuario usuario) {
    return usuarioRepository.findById(id).map((recordFound) -> {
      usuario.setId(recordFound.getId());
      return usuarioRepository.save(usuario);
    }).orElseThrow(() -> new RecordNotFoundException(id));
  }

  public void deletarUsuario(@NotNull @Positive Long id) {
    usuarioRepository.delete(usuarioRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
  }
}
