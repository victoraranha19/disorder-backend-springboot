package com.victor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carteiras")
@SoftDelete(strategy = SoftDeleteType.ACTIVE, columnName = "ativo")
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    @Length(max = 50)
    @Column(length = 50, nullable = false)
    private String titulo;

    @NotNull
    @PositiveOrZero
    @Column(precision = 2, nullable = false)
    private Double limiteCreditoTotal;

    @Valid
    @ManyToOne(optional = false)
    @JoinColumn(name = "idUsuario", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuario;

    @NotNull
    @Valid
    @OneToMany(mappedBy = "carteira")
    private List<Transacao> transacoes = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer idCarteira) {
        this.id = idCarteira;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank @Length(max = 50) String nome) {
        this.titulo = nome;
    }

    public Double getLimiteCreditoTotal() {
        return limiteCreditoTotal;
    }

    public void setLimiteCreditoTotal(@NotNull @PositiveOrZero Double limiteCreditoTotal) {
        this.limiteCreditoTotal = limiteCreditoTotal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(@NotNull @Valid Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Transacao> getTransacoes() {
        return new ArrayList<>(this.transacoes);
    }
}
