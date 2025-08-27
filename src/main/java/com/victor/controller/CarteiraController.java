package com.victor.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.victor.model.Carteira;
import com.victor.service.CarteiraService;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/carteiras")
public class CarteiraController {

  private final CarteiraService carteiraService;

  public CarteiraController(CarteiraService carteiraService) {
    this.carteiraService = carteiraService;
  }

  @GetMapping
  public @ResponseBody List<Carteira> listarCarteiras() {
    return carteiraService.listarCarteiras();
  }

  @GetMapping("/{id}")
  public Carteira carteiraPorId(@PathVariable Long id) {
    return carteiraService.carteiraPorId(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Carteira criarCarteira(@RequestBody Carteira carteira) {
    return carteiraService.criarCarteira(carteira);
  }

  @PutMapping("/{id}")
  public Carteira atualizarCarteira(@PathVariable Long id, @RequestBody Carteira carteira) {
    return carteiraService.atualizarCarteira(id, carteira);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletarCarteira(@PathVariable Long id) {
    carteiraService.deletarCarteira(id);
  }
}
