package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

public record ResiduoEntity(
    String id,
    String idProductor,
    String tipoResiduo,
    boolean peligroso,
    String pesoKg,
    String fechaGeneracion) {
}
