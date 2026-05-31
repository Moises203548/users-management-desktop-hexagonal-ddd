package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.ResiduoNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ResiduoController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteResiduoHandler implements OperationHandler {

  private final ResiduoController residuoController;
  private final ConsoleIO         console;

  @Override
  public void handle() {
    final String id = console.readRequired("ID del residuo a eliminar: ");
    try {
      residuoController.delete(id);
      console.println("  Residuo eliminado exitosamente.");
    } catch (final ResiduoNotFoundException ex) {
      console.println("  Error: " + ex.getMessage());
    }
  }
}
