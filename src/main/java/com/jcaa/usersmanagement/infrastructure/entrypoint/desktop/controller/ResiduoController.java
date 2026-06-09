package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateResiduoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteResiduoUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllResiduosUseCase;
import com.jcaa.usersmanagement.application.port.in.GetResiduoByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.GetResiduosByProductorAndFechasUseCase;
import com.jcaa.usersmanagement.application.port.in.GetResiduosByProductorUseCase;
import com.jcaa.usersmanagement.application.port.in.GetResiduosByTipoUseCase;
import com.jcaa.usersmanagement.application.port.in.GetTotalesByProductorUseCase;
import com.jcaa.usersmanagement.application.service.dto.query.GetResiduoByIdQuery;
import com.jcaa.usersmanagement.application.service.dto.query.GetResiduosByProductorAndFechasQuery;
import com.jcaa.usersmanagement.application.service.dto.query.GetResiduosByProductorQuery;
import com.jcaa.usersmanagement.application.service.dto.query.GetResiduosByTipoQuery;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateResiduoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ResiduoResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.ResiduoDesktopMapper;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ResiduoController {

  private final CreateResiduoUseCase                   createResiduoUseCase;
  private final DeleteResiduoUseCase                   deleteResiduoUseCase;
  private final GetAllResiduosUseCase                  getAllResiduosUseCase;
  private final GetResiduoByIdUseCase                  getResiduoByIdUseCase;
  private final GetResiduosByTipoUseCase               getResiduosByTipoUseCase;
  private final GetTotalesByProductorUseCase           getTotalesByProductorUseCase;
  private final GetResiduosByProductorUseCase          getResiduosByProductorUseCase;
  private final GetResiduosByProductorAndFechasUseCase getResiduosByProductorAndFechasUseCase;

  public ResiduoResponse create(final CreateResiduoRequest request) {
    return ResiduoDesktopMapper.toResponse(
        createResiduoUseCase.execute(ResiduoDesktopMapper.toCreateCommand(request)));
  }

  public void delete(final String id) {
    deleteResiduoUseCase.execute(ResiduoDesktopMapper.toDeleteCommand(id));
  }

  public List<ResiduoResponse> listAll() {
    return ResiduoDesktopMapper.toResponseList(getAllResiduosUseCase.execute());
  }

  public ResiduoResponse findById(final String id) {
    return ResiduoDesktopMapper.toResponse(
        getResiduoByIdUseCase.execute(new GetResiduoByIdQuery(id)));
  }

  public List<Map<String, Object>> getResiduosByTipo(final int limit) {
    return getResiduosByTipoUseCase.execute(new GetResiduosByTipoQuery(limit));
  }

  public List<Map<String, Object>> getTotalesByProductor() {
    return getTotalesByProductorUseCase.execute();
  }

  public List<ResiduoResponse> getByProductor(final String idProductor) {
    return ResiduoDesktopMapper.toResponseList(
        getResiduosByProductorUseCase.execute(new GetResiduosByProductorQuery(idProductor)));
  }

  public List<ResiduoResponse> getByProductorAndFechas(
      final String idProductor, final String desde, final String hasta) {
    return ResiduoDesktopMapper.toResponseList(
        getResiduosByProductorAndFechasUseCase.execute(
            new GetResiduosByProductorAndFechasQuery(idProductor, desde, hasta)));
  }
}
