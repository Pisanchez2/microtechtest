package com.pisa.cuentamovimiento.repository;

import com.pisa.cuentamovimiento.domain.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    Optional<Cuenta> getTopByNumeroCuentaOrderByIdDesc(String numeroCuenta);

    List<Cuenta> getCuentasByClienteId(Long clienteId);

    Optional<Cuenta> getCuentaByNumeroCuenta(String numeroCuenta);
}
