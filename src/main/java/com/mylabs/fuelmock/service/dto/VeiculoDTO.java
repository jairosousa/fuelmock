package com.mylabs.fuelmock.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.mylabs.fuelmock.domain.Veiculo} entity.
 */
public class VeiculoDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 3)
    private String fabricante;

    @NotNull
    @Size(min = 3)
    private String modelo;

    private BigDecimal km;

    private BigDecimal capTanque;

    private Integer ano;


    private Long usuarioId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public BigDecimal getKm() {
        return km;
    }

    public void setKm(BigDecimal km) {
        this.km = km;
    }

    public BigDecimal getCapTanque() {
        return capTanque;
    }

    public void setCapTanque(BigDecimal capTanque) {
        this.capTanque = capTanque;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VeiculoDTO veiculoDTO = (VeiculoDTO) o;
        if (veiculoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), veiculoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VeiculoDTO{" +
            "id=" + getId() +
            ", fabricante='" + getFabricante() + "'" +
            ", modelo='" + getModelo() + "'" +
            ", km=" + getKm() +
            ", capTanque=" + getCapTanque() +
            ", ano=" + getAno() +
            ", usuarioId=" + getUsuarioId() +
            "}";
    }
}
