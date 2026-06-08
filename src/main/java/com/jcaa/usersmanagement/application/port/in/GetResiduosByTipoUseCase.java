package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetResiduosByTipoQuery;
import java.util.List;
import java.util.Map;

public interface GetResiduosByTipoUseCase {
  List<Map<String, Object>> execute(GetResiduosByTipoQuery query);
}
