package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateResiduoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteResiduoCommand;
import com.jcaa.usersmanagement.domain.model.ResiduoModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateResiduoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ResiduoResponse;

public final class ResiduoDesktopMapper {

  private ResiduoDesktopMapper() {}

  public static CreateResiduoCommand toCreateCommand(final CreateResiduoRequest request) {
    return new CreateResiduoCommand(
            request.id(),
            request.idProductor(),
            request.tipoResiduo(),
            request.peligroso(),
            request.pesoKg());
  }

  public static DeleteResiduoCommand toDeleteCommand(final String id) {
    return new DeleteResiduoCommand(id);
  }

  public static ResiduoResponse toResponse(final ResiduoModel residuo) {
    return new ResiduoResponse(
            residuo.getId().value(),
            residuo.getIdProductor(),
            residuo.getTipoResiduo(),
            residuo.isPeligroso(),
            residuo.getPesoKg().toPlainString(),
            residuo.getFechaGeneracion().toString());
  }
}