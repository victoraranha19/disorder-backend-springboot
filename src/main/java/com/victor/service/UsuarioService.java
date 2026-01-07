package com.victor.service;

import com.victor.dto.UsuarioRegistroDTO;
import com.victor.dto.mapper.UsuarioMapper;
import com.victor.enums.PapelAcesso;
import com.victor.exception.RecordNotFoundException;
import com.victor.model.Usuario;
import com.victor.repository.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
@Service
public class UsuarioService implements UserDetailsService {
    private Usuario usuarioLogado = null;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    @NullMarked
    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username);
    }

    public ResponseEntity criarUsuario(@Valid @NotNull UsuarioRegistroDTO usuarioRegistroDTO) {
        if (usuarioRepository.findByLogin(usuarioRegistroDTO.login()) != null) {
            return ResponseEntity.badRequest().build();
        }
        Usuario novoUsuario = UsuarioMapper.novoUsuario(usuarioRegistroDTO);
        novoUsuario.setPapel(PapelAcesso.USER);
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioRegistroDTO.senha());
        novoUsuario.setSenha(encryptedPassword);
        usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }

    public void deletarUsuario(@NotNull @Positive UUID id) {
        usuarioRepository.delete(usuarioRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }

    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
    }
    public Usuario getUsuarioLogado() {
        return this.usuarioLogado;
    }
}
