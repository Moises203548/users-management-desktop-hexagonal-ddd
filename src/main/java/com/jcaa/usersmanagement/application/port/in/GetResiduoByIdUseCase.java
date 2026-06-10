package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetResiduoByIdQuery;
import com.jcaa.usersmanagement.domain.model.ResiduoModel;

public interface GetResiduoByIdUseCase {
  ResiduoModel execute(GetResiduoByIdQuery query);
}
