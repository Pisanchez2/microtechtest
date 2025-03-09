package com.pisa.cuentamovimiento.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pisa.cuentamovimiento.domain.enumeration.TipoCuenta;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CuentaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private double saldoInicial;
    private boolean estado;

    private Long clienteId;

    private List<MovimientoDTO> movimientos;


}