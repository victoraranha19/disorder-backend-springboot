package com.victor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.victor.enums.TipoTransacao;
import com.victor.enums.converters.TipoTransacaoConverter;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Entity
@Table(name = "transacoes")
@SoftDelete(strategy = SoftDeleteType.ACTIVE, columnName = "ativo")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Length(max = 255)
    @Column(length = 255, nullable = false)
    private String descricao;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Double valor;

    @NotNull
    @Column(nullable = false)
    private Date dataTransacao;

    @NotNull
    @Column(nullable = false)
    @Convert(converter = TipoTransacaoConverter.class)
    private TipoTransacao tipo; // 'C' para crédito, 'D' para débito

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer parcelas = 1;

    @Valid
    @ManyToOne(optional = false)
    @JoinColumn(name = "idUsuario", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuario;

    @Valid
    @ManyToOne(optional = true)
    @JoinColumn(name = "idCarteira")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Carteira carteira;

    @Valid
    @ManyToOne(optional = true)
    @JoinColumn(name = "idCategoria")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Categoria categoria;

    public Integer getId() {
        return id;
    }

    public void setId(Integer idTransacao) {
        this.id = idTransacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotNull @Length(max = 255) String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(@NotNull @Positive Double valor) {
        this.valor = valor;
    }

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(@NotNull Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(@NotNull TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(@NotNull @Positive Integer parcelas) {
        this.parcelas = parcelas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(@NotNull @Valid Usuario usuario) {
        this.usuario = usuario;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(@Valid Carteira carteira) {
        this.carteira = carteira;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(@Valid Categoria categoria) {
        this.categoria = categoria;
    }
}
