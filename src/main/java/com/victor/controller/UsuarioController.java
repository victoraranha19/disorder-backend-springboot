package com.victor.controller;

import com.victor.dto.UsuarioLoggedDTO;
import com.victor.dto.UsuarioLoginDTO;
import com.victor.dto.UsuarioRegistroDTO;
import com.victor.service.AuthenticationService;
import com.victor.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/register")
    public void register(@RequestBody @Valid UsuarioRegistroDTO usuarioRegistroDTO) {
        usuarioService.criarUsuario(usuarioRegistroDTO);
    }

    @PostMapping("/login")
    public UsuarioLoggedDTO login(@RequestBody @Valid UsuarioLoginDTO usuarioLoginDTO) {
        return authenticationService.login(usuarioLoginDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable UUID id) {
        usuarioService.deletarUsuario(id);
    }
}
