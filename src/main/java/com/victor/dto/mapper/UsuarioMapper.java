package com.victor.dto.mapper;

import com.victor.dto.UsuarioDTO;
import com.victor.dto.auth.UsuarioRegistrarDTO;
import com.victor.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public static UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;
        return new UsuarioDTO(usuario.getNomeCompleto(),
                usuario.getEmail(), usuario.getTelefone(), usuario.getChavePix());
    }

    public static Usuario toEntity(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) return null;
        Usuario usuario = new Usuario();

        usuario.setNomeCompleto(usuarioDTO.nomeCompleto());
        usuario.setEmail(usuarioDTO.email());
        usuario.setTelefone(usuarioDTO.telefone());
        usuario.setChavePix(usuarioDTO.chavePix());
        return usuario;
    }

    public static Usuario novoUsuario(UsuarioRegistrarDTO usuarioRegistrarDTO) {
        if (usuarioRegistrarDTO == null) return null;
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioRegistrarDTO.email());
        usuario.setNomeCompleto(usuarioRegistrarDTO.nomeCompleto());
        return usuario;
    }
}
