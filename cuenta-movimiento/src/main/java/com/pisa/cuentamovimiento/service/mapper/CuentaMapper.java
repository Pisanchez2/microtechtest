package com.pisa.cuentamovimiento.service.mapper;

import com.pisa.cuentamovimiento.domain.Cuenta;
import com.pisa.cuentamovimiento.service.dto.CuentaDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CuentaMapper extends EntityMapper<CuentaDTO, Cuenta> {

    @Mapping(target = "movimientos", ignore = true)
    CuentaDTO toDto(Cuenta cuenta);

    Cuenta toEntity(CuentaDTO cuentaDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCuentaFromDto(CuentaDTO cuentaDTO, @MappingTarget Cuenta cuenta);
}