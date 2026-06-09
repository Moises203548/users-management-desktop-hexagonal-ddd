package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ResiduoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ResiduoController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetResiduosByProductorHandler implements OperationHandler {

  private final ResiduoController      residuoController;
  private final ConsoleIO              console;
  private final ResiduoResponsePrinter printer;

  @Override
  public void handle() {
    final String idProductor = console.readRequired("ID del productor: ");
    printer.printList(residuoController.getByProductor(idProductor));
  }
}
