package com.mylabs.fuelmock.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.mylabs.fuelmock.domain.Abastecimento} entity.
 */
public class AbastecimentoDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String posto;

    private LocalDate data;

    private BigDecimal preco;

    private BigDecimal precoCombustivel;

    private BigDecimal kmAtual;


    private Long veiculoId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosto() {
        return posto;
    }

    public void setPosto(String posto) {
        this.posto = posto;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getPrecoCombustivel() {
        return precoCombustivel;
    }

    public void setPrecoCombustivel(BigDecimal precoCombustivel) {
        this.precoCombustivel = precoCombustivel;
    }

    public BigDecimal getKmAtual() {
        return kmAtual;
    }

    public void setKmAtual(BigDecimal kmAtual) {
        this.kmAtual = kmAtual;
    }

    public Long getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Long veiculoId) {
        this.veiculoId = veiculoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbastecimentoDTO abastecimentoDTO = (AbastecimentoDTO) o;
        if (abastecimentoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abastecimentoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AbastecimentoDTO{" +
            "id=" + getId() +
            ", posto='" + getPosto() + "'" +
            ", data='" + getData() + "'" +
            ", preco=" + getPreco() +
            ", precoCombustivel=" + getPrecoCombustivel() +
            ", kmAtual=" + getKmAtual() +
            ", veiculoId=" + getVeiculoId() +
            "}";
    }
}
