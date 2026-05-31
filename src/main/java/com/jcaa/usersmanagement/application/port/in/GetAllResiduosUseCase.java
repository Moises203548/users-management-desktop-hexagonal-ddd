package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.ResiduoModel;
import java.util.List;

public interface GetAllResiduosUseCase {
    List<ResiduoModel> execute();
}