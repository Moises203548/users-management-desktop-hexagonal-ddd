package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.ResiduoModel;
import com.jcaa.usersmanagement.domain.valueobject.ResiduoId;
import java.util.Optional;

public interface GetResiduoByIdPort {
  Optional<ResiduoModel> getById(ResiduoId id);
}
