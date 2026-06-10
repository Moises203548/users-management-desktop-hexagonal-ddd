package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetTotalesByProductorUseCase;
import com.jcaa.usersmanagement.application.port.out.GetTotalesByProductorPort;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetTotalesByProductorService implements GetTotalesByProductorUseCase {

  private final GetTotalesByProductorPort getTotalesByProductorPort;

  @Override
  public List<Map<String, Object>> execute() {
    return getTotalesByProductorPort.getTotalesByProductor();
  }
}
