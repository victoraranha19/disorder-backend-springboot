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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.victor.dto.InstituicaoDTO;
import com.victor.service.CarteiraService;

@RestController
@RequestMapping("/api/carteiras")
public class CarteiraController {

  private final CarteiraService carteiraService;

  public CarteiraController(CarteiraService carteiraService) {
    this.carteiraService = carteiraService;
  }

  @GetMapping
  public @ResponseBody List<InstituicaoDTO> listarCarteiras() {
    return carteiraService.listarCarteiras();
  }

  @GetMapping("/{id}")
  public InstituicaoDTO carteiraPorId(@PathVariable Long id) {
    return carteiraService.carteiraPorId(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public InstituicaoDTO criarCarteira(@RequestBody InstituicaoDTO instituicaoDTO) {
    return carteiraService.criarCarteira(instituicaoDTO);
  }

  @PutMapping("/{id}")
  public InstituicaoDTO atualizarCarteira(@PathVariable Long id, @RequestBody InstituicaoDTO instituicaoDTO) {
    return carteiraService.atualizarCarteira(id, instituicaoDTO);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletarCarteira(@PathVariable Long id) {
    carteiraService.deletarCarteira(id);
  }
}
