package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record ResiduoPersistenceDto(
    String id,
    String idProductor,
    String tipoResiduo,
    boolean peligroso,
    String pesoKg,
    String fechaGeneracion) {
}
