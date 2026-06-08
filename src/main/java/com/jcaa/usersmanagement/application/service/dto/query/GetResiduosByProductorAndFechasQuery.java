package com.jcaa.usersmanagement.application.service.dto.query;

import jakarta.validation.constraints.NotBlank;

public record GetResiduosByProductorAndFechasQuery(
    @NotBlank(message = "idProductor no puede estar vacío") String idProductor,
    @NotBlank(message = "fechaDesde no puede estar vacía") String fechaDesde,
    @NotBlank(message = "fechaHasta no puede estar vacía") String fechaHasta) {
}
