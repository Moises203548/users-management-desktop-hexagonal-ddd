package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ResiduoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ResiduoController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListResiduosHandler implements OperationHandler {

    private final ResiduoController      residuoController;
    private final ResiduoResponsePrinter printer;

    @Override
    public void handle() {
        printer.printList(residuoController.listAll());
    }
}