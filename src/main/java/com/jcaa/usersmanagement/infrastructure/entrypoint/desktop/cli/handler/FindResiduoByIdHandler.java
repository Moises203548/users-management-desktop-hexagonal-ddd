package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.ResiduoNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ResiduoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ResiduoController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindResiduoByIdHandler implements OperationHandler {

  private final ResiduoController      residuoController;
  private final ConsoleIO              console;
  private final ResiduoResponsePrinter printer;

  @Override
  public void handle() {
    final String id = console.readRequired("ID del residuo: ");
    try {
      printer.print(residuoController.findById(id));
    } catch (final ResiduoNotFoundException ex) {
      console.println("  Error: " + ex.getMessage());
    }
  }
}
