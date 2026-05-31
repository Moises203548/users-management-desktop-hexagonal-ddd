package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateResiduoCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteResiduoCommand;
import com.jcaa.usersmanagement.domain.model.ResiduoModel;
import com.jcaa.usersmanagement.domain.valueobject.ResiduoId;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResiduoApplicationMapper {

  public ResiduoModel fromCreateCommandToModel(final CreateResiduoCommand command) {
    return ResiduoModel.create(
            new ResiduoId(command.id()),
            command.idProductor(),
            command.tipoResiduo(),
            command.peligroso(),
            command.pesoKg());
  }

  public ResiduoId fromDeleteCommandToId(final DeleteResiduoCommand command) {
    return new ResiduoId(command.id());
  }
}