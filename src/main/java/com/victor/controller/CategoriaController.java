package com.victor.controller;

import com.victor.dto.CategoriaDTO;
import com.victor.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> listarCategorias() {
        return categoriaService.listarCategoriasUsuario();
    }

    @GetMapping("/{id}")
    public CategoriaDTO categoriaPorId(@PathVariable Integer id) {
        return categoriaService.categoriaPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaDTO criarCategoria(@RequestBody CategoriaDTO categoria) {
        return categoriaService.criarCategoria(categoria);
    }

    @PutMapping("/{id}")
    public CategoriaDTO atualizarCategoria(@PathVariable Integer id, @RequestBody CategoriaDTO categoria) {
        return categoriaService.atualizarCategoria(id, categoria);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCategoria(@PathVariable Integer id) {
        categoriaService.deletarCategoria(id);
    }
}
