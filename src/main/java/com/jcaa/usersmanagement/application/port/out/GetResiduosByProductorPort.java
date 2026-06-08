package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.ResiduoModel;
import java.util.List;

public interface GetResiduosByProductorPort {
  List<ResiduoModel> getByProductor(String idProductor);
}
