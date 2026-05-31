package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record ResiduoResponse(
    String id,
    String idProductor,
    String tipoResiduo,
    boolean peligroso,
    String pesoKg,
    String fechaGeneracion) {
}
