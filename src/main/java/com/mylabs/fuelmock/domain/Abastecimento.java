package com.mylabs.fuelmock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A Abastecimento.
 */
@Entity
@Table(name = "abastecimento")
public class Abastecimento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "posto", nullable = false)
    private String posto;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "preco", precision = 21, scale = 2)
    private BigDecimal preco;

    @Column(name = "preco_combustivel", precision = 21, scale = 2)
    private BigDecimal precoCombustivel;

    @Column(name = "km_atual", precision = 21, scale = 2)
    private BigDecimal kmAtual;

    @ManyToOne
    @JsonIgnoreProperties("abastecimentos")
    private Veiculo veiculo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosto() {
        return posto;
    }

    public Abastecimento posto(String posto) {
        this.posto = posto;
        return this;
    }

    public void setPosto(String posto) {
        this.posto = posto;
    }

    public LocalDate getData() {
        return data;
    }

    public Abastecimento data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Abastecimento preco(BigDecimal preco) {
        this.preco = preco;
        return this;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getPrecoCombustivel() {
        return precoCombustivel;
    }

    public Abastecimento precoCombustivel(BigDecimal precoCombustivel) {
        this.precoCombustivel = precoCombustivel;
        return this;
    }

    public void setPrecoCombustivel(BigDecimal precoCombustivel) {
        this.precoCombustivel = precoCombustivel;
    }

    public BigDecimal getKmAtual() {
        return kmAtual;
    }

    public Abastecimento kmAtual(BigDecimal kmAtual) {
        this.kmAtual = kmAtual;
        return this;
    }

    public void setKmAtual(BigDecimal kmAtual) {
        this.kmAtual = kmAtual;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Abastecimento veiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
        return this;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Abastecimento)) {
            return false;
        }
        return id != null && id.equals(((Abastecimento) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Abastecimento{" +
            "id=" + getId() +
            ", posto='" + getPosto() + "'" +
            ", data='" + getData() + "'" +
            ", preco=" + getPreco() +
            ", precoCombustivel=" + getPrecoCombustivel() +
            ", kmAtual=" + getKmAtual() +
            "}";
    }
}
