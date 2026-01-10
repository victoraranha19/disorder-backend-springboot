package com.victor.service;

import com.victor.dto.UsuarioLoggedDTO;
import com.victor.dto.UsuarioLoginDTO;
import com.victor.infra.security.TokenService;
import com.victor.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@Validated
@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    AuthenticationService(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public UsuarioLoggedDTO login(@RequestBody @Valid UsuarioLoginDTO usuarioLoginDTO) {
        // Realiza a autenticação do usuário
        Authentication usernamePassword = UsernamePasswordAuthenticationToken.unauthenticated(usuarioLoginDTO.login(), usuarioLoginDTO.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        // Gera o token JWT para o usuário autenticado
        String token = tokenService.generateToken((Usuario) Objects.requireNonNull(auth.getPrincipal()));
        return new UsuarioLoggedDTO(token);
    }
}
