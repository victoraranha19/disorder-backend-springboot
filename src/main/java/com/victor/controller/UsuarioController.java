package com.victor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.victor.dto.UsuarioDTO;
import com.victor.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping
  public List<UsuarioDTO> listarUsuarios() {
    return usuarioService.listarUsuarios();
  }

  @GetMapping("/{id}")
  public UsuarioDTO usuarioPorId(@PathVariable Long id) {
    return usuarioService.usuarioPorId(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UsuarioDTO criarUsuario(@RequestBody UsuarioDTO usuario) {
    return usuarioService.criarUsuario(usuario);
  }

  @PutMapping("/{id}")
  public UsuarioDTO atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuario) {
    return usuarioService.atualizarUsuario(id, usuario);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletarUsuario(@PathVariable Long id) {
    usuarioService.deletarUsuario(id);
  }
}
