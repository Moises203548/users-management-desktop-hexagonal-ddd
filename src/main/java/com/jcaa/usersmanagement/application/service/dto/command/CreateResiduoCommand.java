package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record CreateResiduoCommand(
    @NotBlank(message = "id no puede estar vacío")
        String id,

    @NotBlank(message = "idProductor no puede estar vacío")
        String idProductor,

    @NotBlank(message = "tipoResiduo no puede estar vacío")
    @Size(max = 100, message = "tipoResiduo no puede superar los 100 caracteres")
        String tipoResiduo,

    @NotNull(message = "peligroso no puede ser nulo")
        Boolean peligroso,

    @NotNull(message = "pesoKg no puede ser nulo")
    @DecimalMin(value = "0.001", message = "pesoKg debe ser mayor que cero")
        BigDecimal pesoKg) {
}
