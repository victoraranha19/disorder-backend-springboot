package com.victor.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.victor.dto.CarteiraDTO;
import com.victor.dto.CategoriaDTO;
import com.victor.dto.TransacaoDTO;
import com.victor.dto.UsuarioDTO;
import com.victor.model.Carteira;
import com.victor.model.Categoria;
import com.victor.model.Transacao;
import com.victor.model.Usuario;

@Component
public class UsuarioMapper {
  public static UsuarioDTO toDTO(Usuario usuario) {
    if (usuario == null) {
      return null;
    }

    List<CarteiraDTO> carteirasDTO = usuario.getCarteiras()
        .stream()
        .map(CarteiraMapper::toDTO)
        .collect(Collectors.toList());

    List<CategoriaDTO> categoriasDTO = usuario.getCategorias()
        .stream()
        .map(CategoriaMapper::toDTO)
        .collect(Collectors.toList());

    List<TransacaoDTO> transacoesDTO = usuario.getTransacoes()
        .stream()
        .map(TransacaoMapper::toDTO)
        .collect(Collectors.toList());

    return new UsuarioDTO(usuario.getIdUsuario(), usuario.getUsername(), usuario.getNomeCompleto(),
        usuario.getEmail(), usuario.getTelefone(), usuario.getChavePix(), carteirasDTO,
        categoriasDTO, transacoesDTO, usuario.getIdAcessor());
  }

  public static Usuario toEntity(UsuarioDTO usuarioDTO) {
    if (usuarioDTO == null) {
      return null;
    }

    Usuario usuario = new Usuario();
    if (usuarioDTO.id() != null) {
      usuario.setIdUsuario(usuarioDTO.id());
    }
    usuario.setUsername(usuarioDTO.username());
    usuario.setPassword("pass");
    usuario.setNomeCompleto(usuarioDTO.nomeCompleto());
    usuario.setEmail(usuarioDTO.email());
    usuario.setTelefone(usuarioDTO.telefone());
    usuario.setChavePix(usuarioDTO.chavePix());

    usuario.getTransacoes().clear();
    usuarioDTO.transacoes().forEach(transacaoDTO -> {
      Transacao transacao = TransacaoMapper.toEntity(transacaoDTO);
      transacao.setUsuario(usuario);
      usuario.getTransacoes().add(transacao);
    });

    usuario.getCarteiras().clear();
    usuarioDTO.carteiras().forEach(carteiraDTO -> {
      Carteira carteira = CarteiraMapper.toEntity(carteiraDTO);
      carteira.setUsuario(usuario);
      usuario.getCarteiras().add(carteira);
    });

    usuario.getCategorias().clear();
    usuarioDTO.categorias().forEach(categoriaDTO -> {
      Categoria categoria = CategoriaMapper.toEntity(categoriaDTO);
      categoria.setUsuario(usuario);
      usuario.getCategorias().add(categoria);
    });

    usuario.setIdAcessor(0);
    return usuario;
  }
}
