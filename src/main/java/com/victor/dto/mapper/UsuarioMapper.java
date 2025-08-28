package com.victor.dto.mapper;

import org.springframework.stereotype.Component;

import com.victor.dto.UsuarioDTO;
import com.victor.model.Usuario;

@Component
public class UsuarioMapper {
  public static UsuarioDTO toDTO(Usuario usuario) {
    if (usuario == null) {
      return null;
    }

    return new UsuarioDTO(usuario.getIdUsuario(), usuario.getUsername(), usuario.getNomeCompleto(),
        usuario.getEmail(), usuario.getTelefone(), usuario.getChavePix());
  }

  public static Usuario toEntity(UsuarioDTO usuarioDTO) {
    if (usuarioDTO == null) {
      return null;
    }

    Usuario usuario = new Usuario();
    if (usuarioDTO.id() != null) {
      usuario.setIdUsuario(usuarioDTO.id());
    }
    usuario.setUsername(usuarioDTO.username());
    usuario.setPassword("pass");
    usuario.setNomeCompleto(usuarioDTO.nomeCompleto());
    usuario.setEmail(usuarioDTO.email());
    usuario.setTelefone(usuarioDTO.telefone());
    usuario.setChavePix(usuarioDTO.chavePix());
    return usuario;
  }
}
