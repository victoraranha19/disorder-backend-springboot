package com.victor.controller;

import com.victor.dto.CarteiraDTO;
import com.victor.service.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carteiras")
public class CarteiraController {
    @Autowired
    CarteiraService carteiraService;

    @GetMapping
    public @ResponseBody List<CarteiraDTO> listarCarteiras() {
        return carteiraService.listarCarteirasUsuario();
    }

    @GetMapping("/{id}")
    public CarteiraDTO carteiraPorId(@PathVariable Integer id) {
        return carteiraService.carteiraPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarteiraDTO criarCarteira(@RequestBody CarteiraDTO carteiraDTO) {
        return carteiraService.criarCarteira(carteiraDTO);
    }

    @PutMapping("/{id}")
    public CarteiraDTO atualizarCarteira(@PathVariable Integer id, @RequestBody CarteiraDTO carteiraDTO) {
        return carteiraService.atualizarCarteira(id, carteiraDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCarteira(@PathVariable Integer id) {
        carteiraService.deletarCarteira(id);
    }
}
