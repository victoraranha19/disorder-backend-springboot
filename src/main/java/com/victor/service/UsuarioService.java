package com.victor.service;

import com.victor.exception.RecordNotFoundException;
import com.victor.model.Usuario;
import com.victor.repository.UsuarioRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
@Service
public class UsuarioService implements UserDetailsService {
    private Usuario usuarioLogado = null;

    private final UsuarioRepository usuarioRepository;

    UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Create
    public void criarUsuario(@NotNull Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    // Read
    @Override
    @NullMarked
    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
        return buscarPorEmail(username);
    }

    public Usuario buscarPorEmail(@NotBlank String email) {
        return usuarioRepository.findByEmail(email);
    }
    public boolean verificaEmailExiste(@NotBlank String email) {
        return buscarPorEmail(email) != null;
    }

    // Update
    public void alterarUsuario(@NotNull UUID id, @NotNull Usuario usuario) {
        usuario.setId(id);
        usuarioRepository.save(usuario);
    }

    // Delete
    public void deletarUsuario(@NotNull UUID id) {
        usuarioRepository.delete(usuarioRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }

    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    public Usuario getUsuarioLogado() {
        return this.usuarioLogado;
    }
}
