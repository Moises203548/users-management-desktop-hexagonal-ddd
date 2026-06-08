package com.jcaa.usersmanagement.application.service.dto.query;

import jakarta.validation.constraints.NotBlank;

public record GetResiduosByProductorQuery(
    @NotBlank(message = "idProductor no puede estar vacío") String idProductor) {
}
