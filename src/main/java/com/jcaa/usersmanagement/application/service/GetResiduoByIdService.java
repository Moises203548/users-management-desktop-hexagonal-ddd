package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetResiduoByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetResiduoByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetResiduoByIdQuery;
import com.jcaa.usersmanagement.domain.exception.ResiduoNotFoundException;
import com.jcaa.usersmanagement.domain.model.ResiduoModel;
import com.jcaa.usersmanagement.domain.valueobject.ResiduoId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetResiduoByIdService implements GetResiduoByIdUseCase {

  private final GetResiduoByIdPort getResiduoByIdPort;
  private final Validator          validator;

  @Override
  public ResiduoModel execute(final GetResiduoByIdQuery query) {
    final Set<ConstraintViolation<GetResiduoByIdQuery>> violations = validator.validate(query);
    if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    final ResiduoId id = new ResiduoId(query.id());
    return getResiduoByIdPort.getById(id)
        .orElseThrow(() -> ResiduoNotFoundException.becauseIdWasNotFound(id.value()));
  }
}
