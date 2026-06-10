package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetResiduosByTipoUseCase;
import com.jcaa.usersmanagement.application.port.out.GetResiduosByTipoPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetResiduosByTipoQuery;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetResiduosByTipoService implements GetResiduosByTipoUseCase {

  private final GetResiduosByTipoPort getResiduosByTipoPort;

  @Override
  public List<Map<String, Object>> execute(final GetResiduosByTipoQuery query) {
    return getResiduosByTipoPort.getCountByTipo(query.limit());
  }
}
