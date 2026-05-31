package com.jcaa.usersmanagement.domain.exception;

public final class InvalidResiduoIdException extends DomainException {

  private static final String MESSAGE_EMPTY = "El ID del residuo no puede estar vacío.";

  private InvalidResiduoIdException(final String message) {
    super(message);
  }

  public static InvalidResiduoIdException becauseValueIsEmpty() {
    return new InvalidResiduoIdException(MESSAGE_EMPTY);
  }
}
