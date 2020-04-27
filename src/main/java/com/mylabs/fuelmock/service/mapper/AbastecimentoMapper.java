package com.mylabs.fuelmock.service.mapper;


import com.mylabs.fuelmock.domain.*;
import com.mylabs.fuelmock.service.dto.AbastecimentoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Abastecimento} and its DTO {@link AbastecimentoDTO}.
 */
@Mapper(componentModel = "spring", uses = {VeiculoMapper.class})
public interface AbastecimentoMapper extends EntityMapper<AbastecimentoDTO, Abastecimento> {

    @Mapping(source = "veiculo.id", target = "veiculoId")
    AbastecimentoDTO toDto(Abastecimento abastecimento);

    @Mapping(source = "veiculoId", target = "veiculo")
    Abastecimento toEntity(AbastecimentoDTO abastecimentoDTO);

    default Abastecimento fromId(Long id) {
        if (id == null) {
            return null;
        }
        Abastecimento abastecimento = new Abastecimento();
        abastecimento.setId(id);
        return abastecimento;
    }
}
