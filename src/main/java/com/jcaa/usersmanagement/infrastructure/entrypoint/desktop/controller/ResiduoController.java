package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateResiduoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteResiduoUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateResiduoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ResiduoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.ResiduoDesktopMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ResiduoController {

  private final CreateResiduoUseCase createResiduoUseCase;
  private final DeleteResiduoUseCase deleteResiduoUseCase;

  public ResiduoResponse create(final CreateResiduoRequest request) {
    final var command = ResiduoDesktopMapper.toCreateCommand(request);
    final var residuo = createResiduoUseCase.execute(command);
    return ResiduoDesktopMapper.toResponse(residuo);
  }

  public void delete(final String id) {
    final var command = ResiduoDesktopMapper.toDeleteCommand(id);
    deleteResiduoUseCase.execute(command);
  }
}