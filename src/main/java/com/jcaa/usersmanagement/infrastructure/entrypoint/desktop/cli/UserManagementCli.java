package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateResiduoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateUserHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DeleteResiduoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DeleteUserHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.FindUserByIdHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListUsersHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.LoginHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.UpdateUserHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ResiduoResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu.MenuOption;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ResiduoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UserManagementCli {

  private static final String BANNER =
          """
          =============================================
               Sistema de Gestion de Residuos
          =============================================""";

  private static final String MENU_BORDER =
          "  =============================================";

  private final UserController    userController;
  private final ResiduoController residuoController;
  private final ConsoleIO         console;

  public void start() {
    console.println(BANNER);
    final UserResponsePrinter    userPrinter    = new UserResponsePrinter(console);
    final ResiduoResponsePrinter residuoPrinter = new ResiduoResponsePrinter(console);
    runLoop(buildHandlers(userPrinter, residuoPrinter));
  }

  private void runLoop(final Map<MenuOption, OperationHandler> handlers) {
    boolean running = true;
    while (running) {
      printMenu();
      final int choice = console.readInt("\n  Opcion: ");
      final Optional<MenuOption> option = MenuOption.fromNumber(choice);

      if (option.isEmpty()) {
        console.println("  Opcion invalida. Intente de nuevo.");
      } else if (option.get() == MenuOption.EXIT) {
        console.println("\n  Hasta luego!\n");
        running = false;
      } else {
        executeHandler(handlers, option.get());
      }
    }
  }

  private void executeHandler(
          final Map<MenuOption, OperationHandler> handlers, final MenuOption option) {
    try {
      handlers.get(option).handle();
    } catch (final ConstraintViolationException ex) {
      console.println("  Errores de validacion:");
      ex.getConstraintViolations()
              .forEach(v -> console.println("    - " + v.getMessage()));
    } catch (final RuntimeException ex) {
      console.println("  Error inesperado: " + ex.getMessage());
    }
  }

  private Map<MenuOption, OperationHandler> buildHandlers(
          final UserResponsePrinter    userPrinter,
          final ResiduoResponsePrinter residuoPrinter) {

    return Map.ofEntries(
            Map.entry(MenuOption.LIST_USERS,     new ListUsersHandler(userController, userPrinter)),
            Map.entry(MenuOption.FIND_USER,      new FindUserByIdHandler(userController, console, userPrinter)),
            Map.entry(MenuOption.CREATE_USER,    new CreateUserHandler(userController, console, userPrinter)),
            Map.entry(MenuOption.UPDATE_USER,    new UpdateUserHandler(userController, console, userPrinter)),
            Map.entry(MenuOption.DELETE_USER,    new DeleteUserHandler(userController, console)),
            Map.entry(MenuOption.LOGIN,          new LoginHandler(userController, console, userPrinter)),
            Map.entry(MenuOption.CREATE_RESIDUO, new CreateResiduoHandler(residuoController, console, residuoPrinter)),
            Map.entry(MenuOption.DELETE_RESIDUO, new DeleteResiduoHandler(residuoController, console)));
  }

  private void printMenu() {
    console.println();
    console.println(MENU_BORDER);
    console.println("    Menu Principal");
    console.println(MENU_BORDER);
    for (final MenuOption option : MenuOption.values()) {
      console.printf("    [%2d] %s%n", option.getNumber(), option.getDescription());
    }
    console.println(MENU_BORDER);
  }
}