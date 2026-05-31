package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;

public record DeleteResiduoCommand(
    @NotBlank(message = "id no puede estar vacío") String id) {
}
