package com.pisa.cuentamovimiento.service;


import com.pisa.cuentamovimiento.service.dto.MovimientoDTO;

import java.time.LocalDate;
import java.util.List;

public interface MovimientoService {

    public List<MovimientoDTO> getAllMovimientos();

    public List<MovimientoDTO> getMovimientosByNumeroCuentaAndFechaBetween(LocalDate fechaInicio, LocalDate fechaFin, String numeroCuenta);

    public MovimientoDTO getMovimientoById(Long id);

    public MovimientoDTO saveMovimiento(MovimientoDTO movimientoDTO);

    public MovimientoDTO updateMovimiento(Long id, MovimientoDTO movimientoDTO);

    public void deleteMovimiento(Long id);
}