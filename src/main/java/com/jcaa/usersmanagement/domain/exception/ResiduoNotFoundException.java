package com.jcaa.usersmanagement.domain.exception;

public final class ResiduoNotFoundException extends DomainException {

  private static final String MESSAGE_BY_ID = "El residuo con id '%s' no fue encontrado.";

  private ResiduoNotFoundException(final String message) {
    super(message);
  }

  public static ResiduoNotFoundException becauseIdWasNotFound(final String id) {
    return new ResiduoNotFoundException(String.format(MESSAGE_BY_ID, id));
  }
}
