package com.pisa.cuentamovimiento.repository;

import com.pisa.cuentamovimiento.domain.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    Optional<Movimiento> findTopByCuentaIdOrderByIdDesc(Long cuenta_id);

    List<Movimiento> findByCuenta_NumeroCuentaAndFechaBetween(String cuentaNumeroCuenta, Date fechaAfter, Date fechaBefore);
}