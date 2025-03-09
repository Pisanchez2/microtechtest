package com.pisa.cuentamovimiento.service;


import com.pisa.cuentamovimiento.service.dto.ReporteEstadoCuentaDTO;

import java.time.LocalDate;

public interface ReporteService {

    public ReporteEstadoCuentaDTO generarReporte(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId);
}
