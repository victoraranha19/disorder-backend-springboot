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
@Table(name = "categorias")
@SoftDelete(strategy = SoftDeleteType.ACTIVE, columnName = "ativo")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    @Length(max = 50)
    @Column(length = 50, nullable = false)
    private String titulo;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Double valorPlanejado;

    @Valid
    @ManyToOne(optional = false)
    @JoinColumn(name = "idUsuario", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuario;

    @NotNull
    @Valid
    @OneToMany(mappedBy = "categoria")
    private List<Transacao> transacoes = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer idCategoria) {
        this.id = idCategoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank @Length(max = 50) String titulo) {
        this.titulo = titulo;
    }

    public Double getValorPlanejado() {
        return valorPlanejado;
    }

    public void setValorPlanejado(@NotNull @PositiveOrZero Double valorPlanejado) {
        this.valorPlanejado = valorPlanejado;
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
