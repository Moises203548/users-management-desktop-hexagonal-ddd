package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ResiduoResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ResiduoResponsePrinter {

  private static final String SEPARATOR  = "-".repeat(56);
  private static final String ROW_FORMAT = "  %-18s : %s%n";

  private final ConsoleIO console;

  public void print(final ResiduoResponse r) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID",               r.id());
    console.printf(ROW_FORMAT, "ID Productor",      r.idProductor());
    console.printf(ROW_FORMAT, "Tipo",              r.tipoResiduo());
    console.printf(ROW_FORMAT, "Peligroso",         r.peligroso() ? "Sí" : "No");
    console.printf(ROW_FORMAT, "Peso (Kg)",         r.pesoKg());
    console.printf(ROW_FORMAT, "Fecha generación",  r.fechaGeneracion());
    console.println(SEPARATOR);
  }

  public void printList(final List<ResiduoResponse> residuos) {
    if (residuos.isEmpty()) {
      console.println("  No se encontraron residuos.");
      return;
    }
    console.printf("%n  Total: %d residuo(s)%n", residuos.size());
    residuos.forEach(this::print);
  }
}
