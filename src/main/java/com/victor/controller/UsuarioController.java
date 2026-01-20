package com.victor.controller;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.victor.dto.auth.*;
import com.victor.service.AuthenticationService;
import com.victor.service.UsuarioService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin()
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final AuthenticationService authenticationService;
    private final UsuarioService usuarioService;

    UsuarioController(AuthenticationService authenticationService, UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/registrar")
    public void register(@RequestBody @Valid UsuarioRegistrarDTO usuarioRegistrarDTO) throws BadRequestException {
        authenticationService.registrarUsuario(usuarioRegistrarDTO);
    }

    @PostMapping("/login")
    public UsuarioLogadoDTO login(@RequestBody @Valid UsuarioLoginDTO usuarioLoginDTO) throws AuthenticationException, JWTCreationException {
        return authenticationService.login(usuarioLoginDTO);
    }

    @PostMapping("/alterar-senha")
    public void alterarSenha(@RequestBody @Valid UsuarioAlterarSenhaDTO usuarioAlterarSenhaDTO) throws AuthenticationException, JWTCreationException {
        authenticationService.alterarSenha(usuarioAlterarSenhaDTO);
    }

    @PostMapping("/esqueci-senha")
    public void esqueciSenha(@RequestBody @Valid UsuarioEsqueciSenhaDTO usuarioEsqueciSenhaDTO) throws MailException {
        authenticationService.recuperarSenha(usuarioEsqueciSenhaDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable UUID id) {
        usuarioService.deletarUsuario(id);
    }
}
