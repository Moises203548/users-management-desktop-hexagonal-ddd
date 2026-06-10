package com.jcaa.usersmanagement.application.port.out;

import java.util.List;
import java.util.Map;

public interface GetTotalesByProductorPort {
  List<Map<String, Object>> getTotalesByProductor();
}
