package com.pisa.common.shared.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO extends PersonaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean estado;
    private String contraseña;

}
