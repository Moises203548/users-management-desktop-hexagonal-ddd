package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateResiduoUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveResiduoPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateResiduoCommand;
import com.jcaa.usersmanagement.application.service.mapper.ResiduoApplicationMapper;
import com.jcaa.usersmanagement.domain.model.ResiduoModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public final class CreateResiduoService implements CreateResiduoUseCase {

  private final SaveResiduoPort saveResiduoPort;
  private final Validator       validator;

  @Override
  public ResiduoModel execute(final CreateResiduoCommand command) {
    validateCommand(command);
    final ResiduoModel residuo = ResiduoApplicationMapper.fromCreateCommandToModel(command);
    final ResiduoModel saved   = saveResiduoPort.save(residuo);
    log.info("Residuo creado con id: " + saved.getId().value());
    return saved;
  }

  private void validateCommand(final CreateResiduoCommand command) {
    final Set<ConstraintViolation<CreateResiduoCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
