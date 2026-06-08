package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetResiduosByProductorUseCase;
import com.jcaa.usersmanagement.application.port.out.GetResiduosByProductorPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetResiduosByProductorQuery;
import com.jcaa.usersmanagement.domain.model.ResiduoModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetResiduosByProductorService implements GetResiduosByProductorUseCase {

  private final GetResiduosByProductorPort getResiduosByProductorPort;
  private final Validator                  validator;

  @Override
  public List<ResiduoModel> execute(final GetResiduosByProductorQuery query) {
    final Set<ConstraintViolation<GetResiduosByProductorQuery>> violations = validator.validate(query);
    if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    return getResiduosByProductorPort.getByProductor(query.idProductor());
  }
}
