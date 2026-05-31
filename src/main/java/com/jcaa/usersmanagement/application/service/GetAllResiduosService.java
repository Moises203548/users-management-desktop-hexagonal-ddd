package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllResiduosUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllResiduosPort;
import com.jcaa.usersmanagement.domain.model.ResiduoModel;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetAllResiduosService implements GetAllResiduosUseCase {

    private final GetAllResiduosPort getAllResiduosPort;

    @Override
    public List<ResiduoModel> execute() {
        return getAllResiduosPort.getAll();
    }
}