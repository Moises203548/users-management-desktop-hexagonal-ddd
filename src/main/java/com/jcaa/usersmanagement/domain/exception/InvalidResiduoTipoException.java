package com.jcaa.usersmanagement.domain.exception;

public final class InvalidResiduoTipoException extends DomainException {

  private static final String MESSAGE_EMPTY    = "El tipo de residuo no puede estar vacío.";
  private static final String MESSAGE_TOO_LONG = "El tipo '%s' supera los 100 caracteres permitidos.";

  private InvalidResiduoTipoException(final String message) {
    super(message);
  }

  public static InvalidResiduoTipoException becauseValueIsEmpty() {
    return new InvalidResiduoTipoException(MESSAGE_EMPTY);
  }

  public static InvalidResiduoTipoException becauseValueIsTooLong(final String value) {
    return new InvalidResiduoTipoException(String.format(MESSAGE_TOO_LONG, value));
  }
}
