package com.pisa.clientepersona.service.mapper;

import com.pisa.clientepersona.domain.Cliente;
import com.pisa.common.shared.dto.ClienteDTO;
import org.mapstruct.*;
//import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends EntityMapper<ClienteDTO, Cliente> {

    @Mapping(target = "contraseña", ignore = true)
    ClienteDTO toDto(Cliente cliente);

    Cliente toEntity(ClienteDTO clienteDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "persona.id", ignore = true)
    void updateClienteFromDto(ClienteDTO clienteDTO, @MappingTarget Cliente cliente);

}
