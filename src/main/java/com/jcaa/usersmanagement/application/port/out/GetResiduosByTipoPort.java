package com.jcaa.usersmanagement.application.port.out;

import java.util.List;
import java.util.Map;

public interface GetResiduosByTipoPort {
  List<Map<String, Object>> getCountByTipo(int limit);
}
