package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ResiduoController;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetResiduosByTipoHandler implements OperationHandler {

  private final ResiduoController residuoController;
  private final ConsoleIO         console;

  @Override
  public void handle() {
    final List<Map<String, Object>> result = residuoController.getResiduosByTipo(10);
    if (result.isEmpty()) {
      console.println("  No hay residuos registrados.");
      return;
    }
    console.println("\n  Tipos de residuos mas comunes:");
    console.println("  " + "-".repeat(40));
    result.forEach(row ->
        console.printf("  %-30s : %s%n", row.get("tipo_residuo"), row.get("total")));
    console.println("  " + "-".repeat(40));
  }
}
