package com.victor.controller;

import com.victor.dto.UsuarioLoggedDTO;
import com.victor.dto.UsuarioLoginDTO;
import com.victor.dto.UsuarioRegistroDTO;
import com.victor.infra.security.TokenService;
import com.victor.model.Usuario;
import com.victor.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UsuarioRegistroDTO usuarioRegistroDTO) {
        return usuarioService.criarUsuario(usuarioRegistroDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioLoggedDTO> login(@RequestBody @Valid UsuarioLoginDTO data) {
        Authentication usernamePassword = UsernamePasswordAuthenticationToken.unauthenticated(data.login(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) Objects.requireNonNull(auth.getPrincipal()));
        return ResponseEntity.ok(new UsuarioLoggedDTO(token));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable UUID id) {
        usuarioService.deletarUsuario(id);
    }
}
