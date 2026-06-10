package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetResiduosByProductorAndFechasUseCase;
import com.jcaa.usersmanagement.application.port.out.GetResiduosByProductorAndFechasPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetResiduosByProductorAndFechasQuery;
import com.jcaa.usersmanagement.domain.model.ResiduoModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetResiduosByProductorAndFechasService
    implements GetResiduosByProductorAndFechasUseCase {

  private final GetResiduosByProductorAndFechasPort getResiduosByProductorAndFechasPort;
  private final Validator                           validator;

  @Override
  public List<ResiduoModel> execute(final GetResiduosByProductorAndFechasQuery query) {
    final Set<ConstraintViolation<GetResiduosByProductorAndFechasQuery>> violations =
        validator.validate(query);
    if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    return getResiduosByProductorAndFechasPort.getByProductorAndFechas(
        query.idProductor(),
        LocalDate.parse(query.fechaDesde()),
        LocalDate.parse(query.fechaHasta()));
  }
}
