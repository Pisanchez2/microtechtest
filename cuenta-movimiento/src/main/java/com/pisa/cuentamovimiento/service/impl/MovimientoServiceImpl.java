package com.pisa.cuentamovimiento.service.impl;

import com.pisa.cuentamovimiento.domain.Movimiento;
import com.pisa.cuentamovimiento.repository.MovimientoRepository;
import com.pisa.cuentamovimiento.service.CuentaService;
import com.pisa.cuentamovimiento.service.MovimientoService;
import com.pisa.cuentamovimiento.service.dto.CuentaDTO;
import com.pisa.cuentamovimiento.service.dto.MovimientoDTO;
import com.pisa.cuentamovimiento.service.mapper.MovimientoMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaService cuentaService;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository, CuentaService cuentaService, MovimientoMapper movimientoMapper) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaService = cuentaService;
        this.movimientoMapper = movimientoMapper;
    }

    private final MovimientoMapper movimientoMapper;

    public List<MovimientoDTO> getAllMovimientos() {
        List<Movimiento> movimientos = movimientoRepository.findAll();
        return movimientos.stream().map(movimientoMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<MovimientoDTO> getMovimientosByNumeroCuentaAndFechaBetween(LocalDate fechaInicio, LocalDate fechaFin, String numeroCuenta) {
        Date fechaInicioDate = Date.from(fechaInicio.atStartOfDay(ZoneOffset.UTC).toInstant());
        Date fechaFinDate = Date.from(fechaFin.atStartOfDay(ZoneOffset.UTC).plusDays(1).toInstant());
        List<Movimiento> movimientos = movimientoRepository.findByCuenta_NumeroCuentaAndFechaBetween(numeroCuenta, fechaInicioDate, fechaFinDate);
        return movimientos.stream().map(movimientoMapper::toDto).collect(Collectors.toList());
    }

    public MovimientoDTO getMovimientoById(Long id) {
        Movimiento movimiento = movimientoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movimiento no encontrado"));
        return movimientoMapper.toDto(movimiento);
    }

    public MovimientoDTO saveMovimiento(MovimientoDTO movimientoDTO) {
        double saldo = 0;

        CuentaDTO cuentaDTO = cuentaService.getCuentaByNumeroCuenta(movimientoDTO.getNumeroCuenta());
        saldo = cuentaDTO.getSaldoInicial();

        Optional<Movimiento> lastMovimiento = movimientoRepository.findTopByCuentaIdOrderByIdDesc(cuentaDTO.getId());
        if (lastMovimiento.isPresent()) {
            saldo = lastMovimiento.get().getSaldo();
        }

        double valor = movimientoDTO.getValor();

        if (valor == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor no puede ser 0");
        }

        if (saldo + valor < 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Saldo insuficiente");
        }

        movimientoDTO.setSaldo(saldo + valor);

        if (valor < 0) {
            movimientoDTO.setTipoMovimiento("Retiro de " + valor);
        } else {
            movimientoDTO.setTipoMovimiento("Deposito de " + valor);
        }

        movimientoDTO.setCuentaId(cuentaDTO.getId());

        movimientoDTO.setFecha(new Date());
        movimientoDTO.setCuentaId(cuentaDTO.getId());

        Movimiento movimiento = movimientoMapper.toEntity(movimientoDTO);
        Movimiento savedMovimiento = movimientoRepository.save(movimiento);
        return movimientoMapper.toDto(savedMovimiento);
    }

    public MovimientoDTO updateMovimiento(Long id, MovimientoDTO movimientoDTO) {
        Movimiento movimiento = movimientoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movimiento no encontrado"));
        movimientoMapper.updateMovimientoFromDto(movimientoDTO, movimiento);
        Movimiento updatedMovimiento = movimientoRepository.save(movimiento);
        return movimientoMapper.toDto(updatedMovimiento);
    }

    public void deleteMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }
}