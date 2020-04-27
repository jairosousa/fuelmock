package com.mylabs.fuelmock.service.mapper;


import com.mylabs.fuelmock.domain.*;
import com.mylabs.fuelmock.service.dto.VeiculoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Veiculo} and its DTO {@link VeiculoDTO}.
 */
@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface VeiculoMapper extends EntityMapper<VeiculoDTO, Veiculo> {

    @Mapping(source = "usuario.id", target = "usuarioId")
    VeiculoDTO toDto(Veiculo veiculo);

    @Mapping(target = "abastecimentos", ignore = true)
    @Mapping(target = "removeAbastecimento", ignore = true)
    @Mapping(source = "usuarioId", target = "usuario")
    Veiculo toEntity(VeiculoDTO veiculoDTO);

    default Veiculo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Veiculo veiculo = new Veiculo();
        veiculo.setId(id);
        return veiculo;
    }
}
