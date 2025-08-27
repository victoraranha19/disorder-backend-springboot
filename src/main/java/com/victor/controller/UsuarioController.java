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

import com.victor.model.Usuario;
import com.victor.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping
  public List<Usuario> listarUsuarios() {
    return usuarioService.listarUsuarios();
  }

  @GetMapping("/{id}")
  public Usuario usuarioPorId(@PathVariable Long id) {
    return usuarioService.usuarioPorId(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Usuario criarUsuario(@RequestBody Usuario usuario) {
    return usuarioService.criarUsuario(usuario);
  }

  @PutMapping("/{id}")
  public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
    return usuarioService.atualizarUsuario(id, usuario);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletarUsuario(@PathVariable Long id) {
    usuarioService.deletarUsuario(id);
  }
}
