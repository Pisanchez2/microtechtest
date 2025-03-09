package com.pisa.cuentamovimiento.service.mapper;

import com.pisa.cuentamovimiento.domain.Movimiento;
import com.pisa.cuentamovimiento.service.dto.MovimientoDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {CuentaMapper.class})
public interface MovimientoMapper extends EntityMapper<MovimientoDTO, Movimiento> {

    @Mapping(target = "cuentaId", source = "cuenta.id")
    @Mapping(target = "numeroCuenta", ignore = true)
    MovimientoDTO toDto(Movimiento movimiento);

    @Mapping(target = "cuenta.id", source = "cuentaId")
    Movimiento toEntity(MovimientoDTO movimientoDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "cuenta", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateMovimientoFromDto(MovimientoDTO movimientoDTO, @MappingTarget Movimiento movimiento);

}