package com.pisa.cuentamovimiento.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
public class MovimientoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private Long cuentaId;
    private Date fecha;
    private String tipoMovimiento;
    private double valor;
    private double saldo;
    private String numeroCuenta;

}