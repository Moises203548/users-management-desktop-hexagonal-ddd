package com.jcaa.usersmanagement;

import com.jcaa.usersmanagement.infrastructure.config.DependencyContainer;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.UserManagementCli;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Main {

  public static void main(final String[] args) {
    final DependencyContainer container = new DependencyContainer();
    final ConsoleIO console = new ConsoleIO(new Scanner(System.in), System.out);
    final UserManagementCli cli = new UserManagementCli(
            container.userController(),
            container.residuoController(),
            console);
    cli.start();
  }
}