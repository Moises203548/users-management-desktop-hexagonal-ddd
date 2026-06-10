package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ResiduoController;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetTotalesByProductorHandler implements OperationHandler {

  private final ResiduoController residuoController;
  private final ConsoleIO         console;

  @Override
  public void handle() {
    final List<Map<String, Object>> result = residuoController.getTotalesByProductor();
    if (result.isEmpty()) {
      console.println("  No hay residuos registrados.");
      return;
    }
    console.println("\n  Total de residuos por productor:");
    console.println("  " + "-".repeat(60));
    console.printf("  %-36s  %8s  %s%n", "ID Productor", "Cantidad", "Peso Total (Kg)");
    console.println("  " + "-".repeat(60));
    result.forEach(row ->
        console.printf("  %-36s  %8s  %s%n",
            row.get("id_productor"), row.get("cantidad"), row.get("peso_total")));
    console.println("  " + "-".repeat(60));
  }
}
