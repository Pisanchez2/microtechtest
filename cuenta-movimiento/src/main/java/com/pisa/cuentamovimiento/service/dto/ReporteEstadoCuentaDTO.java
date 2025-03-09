package com.pisa.cuentamovimiento.service.dto;

import com.pisa.common.shared.dto.ClienteDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class ReporteEstadoCuentaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private ClienteDTO cliente;
    private List<CuentaDTO> cuentas;
}