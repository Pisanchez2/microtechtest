package com.pisa.cuentamovimiento.domain;

import com.pisa.cuentamovimiento.domain.enumeration.TipoCuenta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;

@Entity
@Table(name = "cuenta")
@Getter
@Setter
@NoArgsConstructor
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_cuenta", nullable = false, unique = true)
    private String numeroCuenta;

    @Column(name = "tipo_cuenta", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoCuenta tipoCuenta;

    @Column(name = "saldo_inicial", nullable = false)
    private double saldoInicial;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    public void setSaldoInicial(double saldoInicial) {
        if (saldoInicial < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El saldo inicial no puede ser menor a cero");
        }
        this.saldoInicial = saldoInicial;
    }

    public Cuenta(Long id, String numeroCuenta, TipoCuenta tipoCuenta, double saldoInicial, boolean estado, Long clienteId) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.clienteId = clienteId;
    }

}