package com.victor.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.victor.dto.ContaDTO;
import com.victor.dto.InstituicaoDTO;
import com.victor.model.Conta;
import com.victor.model.Instituicao;

@Component
public class InstituicaoMapper {
  public static InstituicaoDTO toDTO(Instituicao instituicao) {
    if (instituicao == null) {
      return null;
    }

    List<ContaDTO> contasDTO = instituicao.getContas()
        .stream()
        .map(ContaMapper::toDTO)
        .collect(Collectors.toList());

    return new InstituicaoDTO(instituicao.getIdInstituicao(), instituicao.getNome(), contasDTO);
  }

  public static Instituicao toEntity(InstituicaoDTO instituicaoDTO) {
    if (instituicaoDTO == null) {
      return null;
    }

    Instituicao instituicao = new Instituicao();
    if (instituicaoDTO.id() != null) {
      instituicao.setIdInstituicao(instituicaoDTO.id());
    }

    instituicao.setNome(instituicaoDTO.nome());

    instituicao.limparContas();
    instituicaoDTO.contas().forEach(contaDTO -> {
      Conta conta = ContaMapper.toEntity(contaDTO);
      conta.setInstituicao(instituicao);
      instituicao.addConta(conta);
    });

    return instituicao;
  }
}
