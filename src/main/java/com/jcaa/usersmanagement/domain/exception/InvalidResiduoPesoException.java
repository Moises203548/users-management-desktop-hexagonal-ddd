package com.jcaa.usersmanagement.domain.exception;

import java.math.BigDecimal;

public final class InvalidResiduoPesoException extends DomainException {

  private static final String MESSAGE_NULL     = "El peso del residuo no puede ser nulo.";
  private static final String MESSAGE_NEGATIVE = "El peso '%s' debe ser mayor que cero.";

  private InvalidResiduoPesoException(final String message) {
    super(message);
  }

  public static InvalidResiduoPesoException becauseValueIsNull() {
    return new InvalidResiduoPesoException(MESSAGE_NULL);
  }

  public static InvalidResiduoPesoException becauseValueIsNotPositive(final BigDecimal value) {
    return new InvalidResiduoPesoException(String.format(MESSAGE_NEGATIVE, value));
  }
}
