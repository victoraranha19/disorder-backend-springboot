package com.victor.dto.mapper;

import com.victor.dto.UsuarioDTO;
import com.victor.dto.auth.UsuarioRegistrarDTO;
import com.victor.enums.PapelAcesso;
import com.victor.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public static UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;
        return new UsuarioDTO(usuario.getId(), usuario.getNomeCompleto(),
                usuario.getEmail(), usuario.getTelefone(), usuario.getChavePix(), usuario.getPapel().getValue());
    }

    public static Usuario toEntity(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) return null;
        Usuario usuario = new Usuario();
        if (usuarioDTO.id() != null) {
            usuario.setId(usuarioDTO.id());
        }
        usuario.setNomeCompleto(usuarioDTO.nomeCompleto());
        usuario.setEmail(usuarioDTO.email());
        usuario.setTelefone(usuarioDTO.telefone());
        usuario.setChavePix(usuarioDTO.chavePix());
        usuario.setPapel(PapelAcesso.convertPapelAcessoValue(usuarioDTO.papel()));
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
