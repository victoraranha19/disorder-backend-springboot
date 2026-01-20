package com.victor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.victor.enums.PapelAcesso;
import com.victor.enums.converters.PapelAcessoConverter;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.validator.constraints.Length;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@SoftDelete(strategy = SoftDeleteType.ACTIVE, columnName = "ativo")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String senha = "";

    @NotBlank
    @Length(min = 3, max = 100)
    @Column(length = 100, nullable = false)
    private String nomeCompleto = "";

    @NotBlank
    @Length(min = 3, max = 100)
    @Email
    @Column(length = 100, unique = true, nullable = false)
    private String email = "";

    @NotNull
    @Length(max = 20)
    @Column(length = 20)
    private String telefone = "";

    @NotNull
    @Length(max = 255)
    @Column()
    private String chavePix = "";

    @NotNull
    @Column(nullable = false)
    @Convert(converter = PapelAcessoConverter.class)
    private PapelAcesso papel = PapelAcesso.USER;

    @Valid
    @ManyToOne()
    @JoinColumn(name = "idAcessor")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario acessor;

    @NotNull
    @Valid
    @OneToMany(mappedBy = "acessor", fetch = FetchType.LAZY)
    private List<Usuario> clientes = new ArrayList<>();

    @NotNull
    @Valid
    @OneToMany(mappedBy = "usuario", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Transacao> transacoes = new ArrayList<>();

    @NotNull
    @Valid
    @OneToMany(mappedBy = "usuario", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Carteira> carteiras = new ArrayList<>();

    @NotNull
    @Valid
    @OneToMany(mappedBy = "usuario", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Categoria> categorias = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID idUsuario) {
        this.id = idUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank String senha) {
        this.senha = senha;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(@NotBlank @Length(min = 3, max = 100) String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Length(max = 100) @Email String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotNull @Length(max = 20) String telefone) {
        this.telefone = telefone;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(@NotNull @Length(max = 255) String chavePix) {
        this.chavePix = chavePix;
    }

    public PapelAcesso getPapel() {
        return papel;
    }

    public void setPapel(@NotNull PapelAcesso papel) {
        this.papel = papel;
    }

    public Usuario getAcessor() {
        return this.acessor;
    }

    public void setAcessor(@Valid Usuario acessor) {
        this.acessor = acessor;
    }

    public List<Usuario> getClientes() {
        return new ArrayList<>(this.clientes);
    }

    public void addCliente(@NotNull @Valid Usuario cliente) {
        this.clientes.add(cliente);
    }

    public void limparClientes() {
        this.clientes.clear();
    }

    public List<Transacao> getTransacoes() {
        return new ArrayList<>(this.transacoes);
    }

    public void addTransacao(@NotNull @Valid Transacao transacao) {
        this.transacoes.add(transacao);
    }

    public void limparTransacoes() {
        this.transacoes.clear();
    }

    public List<Carteira> getCarteiras() {
        return new ArrayList<>(this.carteiras);
    }

    public void addCarteira(@NotNull @Valid Carteira carteira) {
        this.carteiras.add(carteira);
    }

    public void limparCarteiras() {
        this.carteiras.clear();
    }

    public List<Categoria> getCategorias() {
        return new ArrayList<>(this.categorias);
    }

    public void addCategoria(@NotNull @Valid Categoria categoria) {
        this.categorias.add(categoria);
    }

    public void limparCategorias() {
        this.categorias.clear();
    }

    @Override
    @NullMarked
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.papel.toString().equals(PapelAcesso.ADMIN.toString()))
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public @Nullable String getPassword() {
        return getSenha();
    }

    @Override
    @NullMarked
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
