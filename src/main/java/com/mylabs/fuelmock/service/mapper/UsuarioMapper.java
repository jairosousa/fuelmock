package com.mylabs.fuelmock.service.mapper;


import com.mylabs.fuelmock.domain.*;
import com.mylabs.fuelmock.service.dto.UsuarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Usuario} and its DTO {@link UsuarioDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UsuarioMapper extends EntityMapper<UsuarioDTO, Usuario> {


    @Mapping(target = "veiculos", ignore = true)
    @Mapping(target = "removeVeiculo", ignore = true)
    Usuario toEntity(UsuarioDTO usuarioDTO);

    default Usuario fromId(Long id) {
        if (id == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }
}
