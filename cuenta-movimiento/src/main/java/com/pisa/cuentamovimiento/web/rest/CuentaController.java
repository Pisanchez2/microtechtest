package com.pisa.cuentamovimiento.web.rest;

import com.pisa.cuentamovimiento.service.CuentaService;
import com.pisa.cuentamovimiento.service.dto.CuentaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping
    public CuentaDTO createCuenta(@RequestBody CuentaDTO cuentaDTO) {
        return cuentaService.saveCuenta(cuentaDTO);
    }

    @GetMapping
    public List<CuentaDTO> getAllCuentas() {
        return cuentaService.getAllCuentas();
    }

    @GetMapping("/{id}")
    public CuentaDTO getCuentaById(@PathVariable Long id) {
        return cuentaService.getCuentaById(id);
    }

    @PutMapping("/{id}")
    public CuentaDTO updateCuenta(@PathVariable Long id, @RequestBody CuentaDTO updatedCuenta) {
        return cuentaService.updateCuenta(id, updatedCuenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        cuentaService.deleteCuenta(id);
        return ResponseEntity.ok().build();
    }
}