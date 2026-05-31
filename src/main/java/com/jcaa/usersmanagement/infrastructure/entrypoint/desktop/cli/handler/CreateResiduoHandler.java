package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ResiduoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ResiduoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateResiduoRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ResiduoResponse;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateResiduoHandler implements OperationHandler {

  private final ResiduoController      residuoController;
  private final ConsoleIO              console;
  private final ResiduoResponsePrinter printer;

  @Override
  public void handle() {
    final String id          = console.readRequired("ID (UUID)          : ");
    final String idProductor = console.readRequired("ID Productor       : ");
    final String tipo        = console.readRequired("Tipo de residuo    : ");
    final String peligrosoS  = console.readRequired("¿Peligroso? (s/n)  : ");
    final String pesoS       = console.readRequired("Peso en Kg         : ");

    final boolean    peligroso = peligrosoS.trim().equalsIgnoreCase("s");
    final BigDecimal pesoKg    = new BigDecimal(pesoS.trim());

    final ResiduoResponse created =
        residuoController.create(new CreateResiduoRequest(id, idProductor, tipo, peligroso, pesoKg));
    console.println("\n  Residuo creado exitosamente.");
    printer.print(created);
  }
}
