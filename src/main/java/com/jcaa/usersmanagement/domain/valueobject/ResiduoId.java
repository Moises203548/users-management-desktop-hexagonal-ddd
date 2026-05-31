package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidResiduoIdException;
import java.util.Objects;

public record ResiduoId(String value) {

  public ResiduoId {
    final String normalizedValue = Objects.requireNonNull(value, "ResiduoId cannot be null").trim();
    if (normalizedValue.isEmpty()) {
      throw InvalidResiduoIdException.becauseValueIsEmpty();
    }
    value = normalizedValue;
  }

  @Override
  public String toString() {
    return value;
  }
}
