package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.ResiduoModel;
import java.time.LocalDate;
import java.util.List;

public interface GetResiduosByProductorAndFechasPort {
  List<ResiduoModel> getByProductorAndFechas(String idProductor, LocalDate desde, LocalDate hasta);
}
