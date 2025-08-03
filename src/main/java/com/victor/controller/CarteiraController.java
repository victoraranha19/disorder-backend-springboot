package com.victor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victor.model.Carteira;
import com.victor.repository.CarteiraRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/carteiras")
@AllArgsConstructor
public class CarteiraController {

  private final CarteiraRepository carteiraRepository;

  @GetMapping
  public List<Carteira> listarCarteiras() {
    return carteiraRepository.findAll();
  }
}
