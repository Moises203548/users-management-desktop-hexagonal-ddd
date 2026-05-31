package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.ResiduoModel;

public interface SaveResiduoPort {
  ResiduoModel save(ResiduoModel residuo);
}
