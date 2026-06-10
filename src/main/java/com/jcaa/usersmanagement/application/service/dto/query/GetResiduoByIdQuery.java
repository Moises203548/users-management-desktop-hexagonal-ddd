package com.jcaa.usersmanagement.application.service.dto.query;

import jakarta.validation.constraints.NotBlank;

public record GetResiduoByIdQuery(
    @NotBlank(message = "id no puede estar vacío") String id) {
}
