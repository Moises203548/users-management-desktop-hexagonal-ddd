package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteResiduoUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteResiduoPort;
import com.jcaa.usersmanagement.application.port.out.GetResiduoByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteResiduoCommand;
import com.jcaa.usersmanagement.application.service.mapper.ResiduoApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.ResiduoNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.ResiduoId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteResiduoService implements DeleteResiduoUseCase {

  private final DeleteResiduoPort  deleteResiduoPort;
  private final GetResiduoByIdPort getResiduoByIdPort;
  private final Validator          validator;

  @Override
  public void execute(final DeleteResiduoCommand command) {
    validateCommand(command);
    final ResiduoId id = ResiduoApplicationMapper.fromDeleteCommandToId(command);
    getResiduoByIdPort
        .getById(id)
        .orElseThrow(() -> ResiduoNotFoundException.becauseIdWasNotFound(id.value()));
    deleteResiduoPort.delete(id);
  }

  private void validateCommand(final DeleteResiduoCommand command) {
    final Set<ConstraintViolation<DeleteResiduoCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
