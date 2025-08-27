package com.victor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.victor.repository.UsuarioRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/usuarios")
@AllArgsConstructor
public class UsuarioController {

  private final UsuarioRepository usuarioRepository;

  @GetMapping
  public List<Usuario> listarUsuarios() {
    return usuarioRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Usuario> carteiraPorId(@PathVariable @NotNull @Positive Long id) {
    return usuarioRepository.findById(id).map(usuario -> ResponseEntity.ok().body(usuario))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Usuario criarUsuario(@RequestBody @Valid Usuario usuario) {
    return usuarioRepository.save(usuario);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Usuario> atualizarTransacao(@PathVariable @NotNull @Positive Long id,
      @RequestBody @Valid Usuario usuario) {
    if (!usuarioRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    usuario.setId(id);
    Usuario atualizado = usuarioRepository.save(usuario);
    return ResponseEntity.ok().body(atualizado);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarTransacao(@PathVariable @NotNull @Positive Long id) {
    if (!usuarioRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    usuarioRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
