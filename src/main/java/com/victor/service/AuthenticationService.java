package com.victor.service;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.victor.dto.auth.*;
import com.victor.dto.mapper.UsuarioMapper;
import com.victor.enums.PapelAcesso;
import com.victor.infra.email.EmailService;
import com.victor.infra.security.TokenService;
import com.victor.model.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.coyote.BadRequestException;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;
import java.util.UUID;

@Validated
@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioService usuarioService;
    private final EmailService emailService;

    AuthenticationService(AuthenticationManager authenticationManager, TokenService tokenService, UsuarioService usuarioService, EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
        this.emailService = emailService;
    }

    // Create
    public void registrarUsuario(@Valid @NotNull UsuarioRegistrarDTO usuarioRegistrarDTO) throws BadRequestException {
        // Valida se o email já existe
        if (usuarioService.verificaEmailExiste(usuarioRegistrarDTO.email())) {
            throw new BadRequestException("Email já cadastrado. Email: " + usuarioRegistrarDTO.email());
        }
        // Cria o novo usuário com acesso padrão USER
        Usuario novoUsuario = UsuarioMapper.novoUsuario(usuarioRegistrarDTO);
        novoUsuario.setPapel(PapelAcesso.USER);
        // Criptografa a senha
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioRegistrarDTO.senha());
        novoUsuario.setSenha(encryptedPassword);
        // Salva no banco de dados
        usuarioService.criarUsuario(novoUsuario);
    }

    public UsuarioLogadoDTO login(@RequestBody @Valid UsuarioLoginDTO usuarioLoginDTO) throws AuthenticationException, JWTCreationException {
        // Realiza a autenticação do usuário
        Authentication usernamePassword = UsernamePasswordAuthenticationToken.unauthenticated(usuarioLoginDTO.email(), usuarioLoginDTO.senha());
        Authentication auth;
        try {
            auth = this.authenticationManager.authenticate(usernamePassword);
        } catch (AuthenticationException e) {
            throw new AuthenticationServiceException("Credenciais inválidas.", e);
        }
        // Gera o token JWT para o usuário autenticado
        Usuario usuario = (Usuario) Objects.requireNonNull(auth.getPrincipal());
        String token = tokenService.generateToken(usuario.getUsername());
        return new UsuarioLogadoDTO(token);
    }

    public void alterarSenha(@Valid @NotNull UsuarioAlterarSenhaDTO usuarioAlterarSenhaDTO) {
        // Verifica se o usuário está logado
        Usuario usuarioLogado = usuarioService.getUsuarioLogado();
        if (usuarioLogado == null) {
            throw new AuthenticationServiceException("Usuário não autenticado.");
        }
        // Verifica se a senha atual está correta
        Authentication usernamePassword = UsernamePasswordAuthenticationToken.unauthenticated(usuarioLogado.getUsername(), usuarioAlterarSenhaDTO.senhaAntiga());
        try {
            this.authenticationManager.authenticate(usernamePassword);
        } catch (AuthenticationException e) {
            throw new AuthenticationServiceException("Credenciais inválidas.", e);
        }
        // Criptografa a nova senha
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioAlterarSenhaDTO.senhaNova());
        usuarioLogado.setSenha(encryptedPassword);
        // Salva o usuário com a nova senha
        usuarioService.alterarUsuario(usuarioLogado.getId(), usuarioLogado);
    }

    public void recuperarSenha(@Valid @NotNull UsuarioEsqueciSenhaDTO usuarioEsqueciSenhaDTO) throws MailException {
        // Verifica se o usuário existe
        Usuario usuario = usuarioService.buscarPorEmail(usuarioEsqueciSenhaDTO.email());
        if (usuario != null) {
            // Gera uma senha temporária
            String senhaTemporaria = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
            // Criptografa a nova senha
            String encryptedPassword = new BCryptPasswordEncoder().encode(senhaTemporaria);
            usuario.setSenha(encryptedPassword);
            // Salva o usuário com a nova senha
            usuarioService.alterarUsuario(usuario.getId(), usuario);
            // Envia um email para o usuário com a senha temporária
            emailService.enviarNovaSenha(usuario.getEmail(), senhaTemporaria);
        }
    }
}
