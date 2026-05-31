package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

import java.math.BigDecimal;

public record CreateResiduoRequest(
    String     id,
    String     idProductor,
    String     tipoResiduo,
    boolean    peligroso,
    BigDecimal pesoKg) {
}
