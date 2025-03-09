package com.pisa.cuentamovimiento.web.rest;

import com.pisa.cuentamovimiento.service.ReporteService;
import com.pisa.cuentamovimiento.service.dto.ReporteEstadoCuentaDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public ResponseEntity<?> generarReporte(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate
                    fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate
                    fechaFin,
            @RequestParam("clienteId") Long clienteId) {

        ReporteEstadoCuentaDTO reporte = reporteService.generarReporte(fechaInicio, fechaFin, clienteId);
        return ResponseEntity.ok(reporte);
    }
}