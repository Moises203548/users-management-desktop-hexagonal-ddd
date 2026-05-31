package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.valueobject.ResiduoId;

public interface DeleteResiduoPort {
  void delete(ResiduoId id);
}
