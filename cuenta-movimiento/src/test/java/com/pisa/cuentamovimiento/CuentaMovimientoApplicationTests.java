package com.pisa.cuentamovimiento;

import com.pisa.cuentamovimiento.domain.Cuenta;
import com.pisa.cuentamovimiento.domain.enumeration.TipoCuenta;
import com.pisa.cuentamovimiento.service.dto.CuentaDTO;
import com.pisa.cuentamovimiento.service.mapper.CuentaMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CuentaMovimientoApplicationTests {

    @Autowired
    private CuentaMapper cuentaMapper;

    @Test
    void testDtoToEntityAndBack() {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setId(1L);
        cuentaDTO.setNumeroCuenta("123456");
        cuentaDTO.setTipoCuenta(TipoCuenta.AHORROS);
        cuentaDTO.setSaldoInicial(500.0);
        cuentaDTO.setEstado(true);
        cuentaDTO.setClienteId(10L);

        // Convertir de DTO a Entidad
        Cuenta cuenta = cuentaMapper.toEntity(cuentaDTO);
        Assertions.assertNotNull(cuenta);
        Assertions.assertEquals(cuentaDTO.getId(), cuenta.getId());
        Assertions.assertEquals(cuentaDTO.getNumeroCuenta(), cuenta.getNumeroCuenta());

        // Convertir de Entidad a DTO
        CuentaDTO resultDto = cuentaMapper.toDto(cuenta);
        Assertions.assertNotNull(resultDto);
        Assertions.assertEquals(cuenta.getNumeroCuenta(), resultDto.getNumeroCuenta());
        Assertions.assertEquals(cuenta.getId(), resultDto.getId());
    }

}
