package com.pisa.clientepersona.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
public class Cliente extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String contraseña;

    @Column(nullable = false)
    private boolean estado;

    public Cliente(String identificacion, String nombre, String genero, int edad, String direccion, String telefono,
                   String contraseña, boolean estado) {
        super(identificacion, nombre, genero, edad, direccion, telefono);
        this.contraseña = contraseña;
        this.estado = estado;
    }
}
