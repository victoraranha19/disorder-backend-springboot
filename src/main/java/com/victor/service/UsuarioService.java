package com.victor.service;

import com.victor.dto.UsuarioDTO;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Validated
@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username);
    }

    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
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

    public UsuarioDTO atualizarUsuario(@NotNull UUID id, @Valid @NotNull UsuarioDTO usuarioDTO) {
        return usuarioRepository.findById(id)
                .map((recordFound) -> {
                    Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
                    recordFound.setLogin(usuarioDTO.login());
                    recordFound.setNomeCompleto(usuarioDTO.nomeCompleto());
                    recordFound.setEmail(usuarioDTO.email());
                    recordFound.setTelefone(usuarioDTO.telefone());
                    recordFound.setChavePix(usuarioDTO.chavePix());
                    recordFound.setPapel(usuario.getPapel());
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
