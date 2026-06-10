package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetResiduosByProductorQuery;
import com.jcaa.usersmanagement.domain.model.ResiduoModel;
import java.util.List;

public interface GetResiduosByProductorUseCase {
  List<ResiduoModel> execute(GetResiduosByProductorQuery query);
}
