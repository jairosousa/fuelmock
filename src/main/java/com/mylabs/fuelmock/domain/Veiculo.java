package com.mylabs.fuelmock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A Veiculo.
 */
@Entity
@Table(name = "veiculo")
public class Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "fabricante", nullable = false)
    private String fabricante;

    @NotNull
    @Size(min = 3)
    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "km", precision = 21, scale = 2)
    private BigDecimal km;

    @Column(name = "cap_tanque", precision = 21, scale = 2)
    private BigDecimal capTanque;

    @Column(name = "ano")
    private Integer ano;

    @OneToMany(mappedBy = "veiculo")
    private Set<Abastecimento> abastecimentos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("veiculos")
    private Usuario usuario;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFabricante() {
        return fabricante;
    }

    public Veiculo fabricante(String fabricante) {
        this.fabricante = fabricante;
        return this;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public Veiculo modelo(String modelo) {
        this.modelo = modelo;
        return this;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public BigDecimal getKm() {
        return km;
    }

    public Veiculo km(BigDecimal km) {
        this.km = km;
        return this;
    }

    public void setKm(BigDecimal km) {
        this.km = km;
    }

    public BigDecimal getCapTanque() {
        return capTanque;
    }

    public Veiculo capTanque(BigDecimal capTanque) {
        this.capTanque = capTanque;
        return this;
    }

    public void setCapTanque(BigDecimal capTanque) {
        this.capTanque = capTanque;
    }

    public Integer getAno() {
        return ano;
    }

    public Veiculo ano(Integer ano) {
        this.ano = ano;
        return this;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Set<Abastecimento> getAbastecimentos() {
        return abastecimentos;
    }

    public Veiculo abastecimentos(Set<Abastecimento> abastecimentos) {
        this.abastecimentos = abastecimentos;
        return this;
    }

    public Veiculo addAbastecimento(Abastecimento abastecimento) {
        this.abastecimentos.add(abastecimento);
        abastecimento.setVeiculo(this);
        return this;
    }

    public Veiculo removeAbastecimento(Abastecimento abastecimento) {
        this.abastecimentos.remove(abastecimento);
        abastecimento.setVeiculo(null);
        return this;
    }

    public void setAbastecimentos(Set<Abastecimento> abastecimentos) {
        this.abastecimentos = abastecimentos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Veiculo usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Veiculo)) {
            return false;
        }
        return id != null && id.equals(((Veiculo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
            "id=" + getId() +
            ", fabricante='" + getFabricante() + "'" +
            ", modelo='" + getModelo() + "'" +
            ", km=" + getKm() +
            ", capTanque=" + getCapTanque() +
            ", ano=" + getAno() +
            "}";
    }
}
