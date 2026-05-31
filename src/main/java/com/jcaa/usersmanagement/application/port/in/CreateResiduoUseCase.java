package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateResiduoCommand;
import com.jcaa.usersmanagement.domain.model.ResiduoModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateResiduoUseCase {
  ResiduoModel execute(@NotNull @Valid CreateResiduoCommand command);
}
