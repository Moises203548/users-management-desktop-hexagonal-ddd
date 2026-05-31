package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DeleteResiduoCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteResiduoUseCase {
  void execute(@NotNull @Valid DeleteResiduoCommand command);
}
