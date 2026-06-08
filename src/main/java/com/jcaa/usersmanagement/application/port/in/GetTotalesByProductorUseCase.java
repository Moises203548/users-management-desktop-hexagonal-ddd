package com.jcaa.usersmanagement.application.port.in;

import java.util.List;
import java.util.Map;

public interface GetTotalesByProductorUseCase {
  List<Map<String, Object>> execute();
}
