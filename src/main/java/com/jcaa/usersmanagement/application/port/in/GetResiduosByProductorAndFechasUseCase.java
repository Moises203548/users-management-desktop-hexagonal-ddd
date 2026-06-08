package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetResiduosByProductorAndFechasQuery;
import com.jcaa.usersmanagement.domain.model.ResiduoModel;
import java.util.List;

public interface GetResiduosByProductorAndFechasUseCase {
  List<ResiduoModel> execute(GetResiduosByProductorAndFechasQuery query);
}
