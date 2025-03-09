package com.pisa.clientepersona.service.mapper;

import com.pisa.clientepersona.domain.Persona;
import com.pisa.common.shared.dto.PersonaDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {})
public interface PersonaMapper {

    PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);

    PersonaDTO toDto(Persona persona);

    Persona toEntity(PersonaDTO personaDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePersonaFromDto(PersonaDTO personaDTO, @MappingTarget Persona persona);
}