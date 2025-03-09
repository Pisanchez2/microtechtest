package com.pisa.cuentamovimiento.service.impl;

import com.pisa.common.shared.dto.ClienteDTO;
import com.pisa.cuentamovimiento.service.ClientePersonaCommunicationService;
import com.pisa.cuentamovimiento.service.CuentaService;
import com.pisa.cuentamovimiento.service.MovimientoService;
import com.pisa.cuentamovimiento.service.ReporteService;
import com.pisa.cuentamovimiento.service.dto.CuentaDTO;
import com.pisa.cuentamovimiento.service.dto.MovimientoDTO;
import com.pisa.cuentamovimiento.service.dto.ReporteEstadoCuentaDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ReporteServiceImpl implements ReporteService {

    private final CuentaService cuentaService;
    private final MovimientoService movimientoService;
    private final ClientePersonaCommunicationService clientePersonaCommunicationService;

    public ReporteServiceImpl(CuentaService cuentaService, MovimientoService movimientoService, ClientePersonaCommunicationService clientePersonaCommunicationService) {
        this.cuentaService = cuentaService;
        this.movimientoService = movimientoService;
        this.clientePersonaCommunicationService = clientePersonaCommunicationService;
    }


    public ReporteEstadoCuentaDTO generarReporte(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId) {
        ClienteDTO clienteDTO = clientePersonaCommunicationService.getClienteById(clienteId);

        List<CuentaDTO> cuentas = cuentaService.getCuentasByClientId(clienteId);

        List<CuentaDTO> cuentasDTO = cuentas.stream().map(cuenta -> {
            List<MovimientoDTO> movimientoDTOS = movimientoService.getMovimientosByNumeroCuentaAndFechaBetween(fechaInicio, fechaFin, cuenta.getNumeroCuenta());

            cuenta.setMovimientos(movimientoDTOS);
            return cuenta;
        }).toList();

        return new ReporteEstadoCuentaDTO(clienteDTO, cuentasDTO);
    }
}