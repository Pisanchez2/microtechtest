CREATE TABLE persona
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre         VARCHAR(255) NOT NULL,
    genero         VARCHAR(255) NOT NULL,
    edad           INT          NOT NULL,
    identificacion VARCHAR(255) NOT NULL UNIQUE,
    direccion      VARCHAR(255) NOT NULL,
    telefono       VARCHAR(255) NOT NULL
);

CREATE TABLE cliente
(
    id         BIGINT PRIMARY KEY,
    contraseña VARCHAR(255) NOT NULL,
    estado     BOOLEAN      NOT NULL,
    FOREIGN KEY (id) REFERENCES persona (id)
);

create table cuenta
(
    id            bigint auto_increment
        primary key,
    numero_cuenta varchar(255) not null,
    tipo_cuenta   varchar(255) not null,
    saldo_inicial double       not null,
    estado        bit          not null,
    cliente_id    bigint       null,
    constraint numero_cuenta
        unique (numero_cuenta),
    constraint cuenta_client__fk
        foreign key (cliente_id) references cliente (id)
);

CREATE TABLE movimiento
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    fecha           TIMESTAMP    NOT NULL,
    tipo_movimiento VARCHAR(255) NOT NULL,
    valor           DOUBLE       NOT NULL,
    saldo           DOUBLE       NOT NULL,
    cuenta_id       BIGINT       NOT NULL,
    FOREIGN KEY (cuenta_id) REFERENCES cuenta (id)
);