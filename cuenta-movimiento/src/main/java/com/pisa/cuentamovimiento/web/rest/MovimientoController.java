package com.pisa.cuentamovimiento.web.rest;

import com.pisa.cuentamovimiento.service.MovimientoService;
import com.pisa.cuentamovimiento.service.dto.MovimientoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos") // Base endpoint for Movimiento
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public MovimientoDTO createMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        return movimientoService.saveMovimiento(movimientoDTO);
    }

    @GetMapping
    public List<MovimientoDTO> getAllMovimientos() {
        return movimientoService.getAllMovimientos();
    }

    @GetMapping("/{id}")
    public MovimientoDTO getMovimientoById(@PathVariable Long id) {
        return movimientoService.getMovimientoById(id);
    }

    @PutMapping("/{id}")
    public MovimientoDTO updateMovimiento(@PathVariable Long id, @RequestBody MovimientoDTO updatedMovimiento) {
        return movimientoService.updateMovimiento(id, updatedMovimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        movimientoService.deleteMovimiento(id);
        return ResponseEntity.ok().build();
    }
}