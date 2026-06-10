package com.jcaa.usersmanagement.infrastructure.config;

import com.jcaa.usersmanagement.application.port.in.CreateResiduoUseCase;
import com.jcaa.usersmanagement.application.port.in.CreateUserUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteResiduoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteUserUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllResiduosUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllUsersUseCase;
import com.jcaa.usersmanagement.application.port.in.GetResiduoByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.GetResiduosByProductorAndFechasUseCase;
import com.jcaa.usersmanagement.application.port.in.GetResiduosByProductorUseCase;
import com.jcaa.usersmanagement.application.port.in.GetResiduosByTipoUseCase;
import com.jcaa.usersmanagement.application.port.in.GetTotalesByProductorUseCase;
import com.jcaa.usersmanagement.application.port.in.GetUserByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.LoginUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateUserUseCase;
import com.jcaa.usersmanagement.application.service.CreateResiduoService;
import com.jcaa.usersmanagement.application.service.CreateUserService;
import com.jcaa.usersmanagement.application.service.DeleteResiduoService;
import com.jcaa.usersmanagement.application.service.DeleteUserService;
import com.jcaa.usersmanagement.application.service.EmailNotificationService;
import com.jcaa.usersmanagement.application.service.GetAllResiduosService;
import com.jcaa.usersmanagement.application.service.GetAllUsersService;
import com.jcaa.usersmanagement.application.service.GetResiduoByIdService;
import com.jcaa.usersmanagement.application.service.GetResiduosByProductorAndFechasService;
import com.jcaa.usersmanagement.application.service.GetResiduosByProductorService;
import com.jcaa.usersmanagement.application.service.GetResiduosByTipoService;
import com.jcaa.usersmanagement.application.service.GetTotalesByProductorService;
import com.jcaa.usersmanagement.application.service.GetUserByIdService;
import com.jcaa.usersmanagement.application.service.LoginService;
import com.jcaa.usersmanagement.application.service.UpdateUserService;
import com.jcaa.usersmanagement.infrastructure.adapter.email.JavaMailEmailSenderAdapter;
import com.jcaa.usersmanagement.infrastructure.adapter.email.SmtpConfig;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConfig;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConnectionFactory;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.ResiduoRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.UserRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ResiduoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;
import jakarta.validation.Validator;
import java.sql.Connection;

public final class DependencyContainer {

  private static final String DB_HOST     = "db.host";
  private static final String DB_PORT     = "db.port";
  private static final String DB_NAME     = "db.name";
  private static final String DB_USER     = "db.username";
  private static final String DB_PASSWORD = "db.password";
  private static final String SMTP_HOST      = "smtp.host";
  private static final String SMTP_PORT      = "smtp.port";
  private static final String SMTP_USER      = "smtp.username";
  private static final String SMTP_PASSWORD  = "smtp.password";
  private static final String SMTP_FROM      = "smtp.from.address";
  private static final String SMTP_FROM_NAME = "smtp.from.name";

  private final UserController    userController;
  private final ResiduoController residuoController;

  public DependencyContainer() {
    final AppProperties properties = new AppProperties();
    final Connection    connection = buildDatabaseConnection(properties);
    final Validator     validator  = ValidatorProvider.buildValidator();

    final UserRepositoryMySQL        userRepository    = new UserRepositoryMySQL(connection);
    final JavaMailEmailSenderAdapter emailSender       =
        new JavaMailEmailSenderAdapter(buildSmtpConfig(properties));
    final EmailNotificationService   emailNotification = new EmailNotificationService(emailSender);

    final CreateUserUseCase  createUserUseCase  = new CreateUserService(userRepository, userRepository, emailNotification, validator);
    final UpdateUserUseCase  updateUserUseCase  = new UpdateUserService(userRepository, userRepository, userRepository, emailNotification, validator);
    final DeleteUserUseCase  deleteUserUseCase  = new DeleteUserService(userRepository, userRepository, validator);
    final GetUserByIdUseCase getUserByIdUseCase = new GetUserByIdService(userRepository, validator);
    final GetAllUsersUseCase getAllUsersUseCase  = new GetAllUsersService(userRepository);
    final LoginUseCase       loginUseCase       = new LoginService(userRepository, validator);

    this.userController = new UserController(
        createUserUseCase, updateUserUseCase, deleteUserUseCase,
        getUserByIdUseCase, getAllUsersUseCase, loginUseCase);


    final ResiduoRepositoryMySQL residuoRepository = new ResiduoRepositoryMySQL(connection);

    final CreateResiduoUseCase  createResiduoUseCase  = new CreateResiduoService(residuoRepository, validator);
    final DeleteResiduoUseCase  deleteResiduoUseCase  = new DeleteResiduoService(residuoRepository, residuoRepository, validator);
    final GetAllResiduosUseCase getAllResiduosUseCase  = new GetAllResiduosService(residuoRepository);
    final GetResiduoByIdUseCase getResiduoByIdUseCase = new GetResiduoByIdService(residuoRepository, validator);
    final GetResiduosByTipoUseCase getResiduosByTipoUseCase =
        new GetResiduosByTipoService(residuoRepository);
    final GetTotalesByProductorUseCase getTotalesByProductorUseCase =
        new GetTotalesByProductorService(residuoRepository);
    final GetResiduosByProductorUseCase getResiduosByProductorUseCase =
        new GetResiduosByProductorService(residuoRepository, validator);
    final GetResiduosByProductorAndFechasUseCase getResiduosByProductorAndFechasUseCase =
        new GetResiduosByProductorAndFechasService(residuoRepository, validator);

    this.residuoController = new ResiduoController(
        createResiduoUseCase, deleteResiduoUseCase, getAllResiduosUseCase,
        getResiduoByIdUseCase, getResiduosByTipoUseCase, getTotalesByProductorUseCase,
        getResiduosByProductorUseCase, getResiduosByProductorAndFechasUseCase);
  }

  public UserController    userController()    { return userController; }
  public ResiduoController residuoController() { return residuoController; }

  private static Connection buildDatabaseConnection(final AppProperties properties) {
    final DatabaseConfig config = new DatabaseConfig(
        properties.get(DB_HOST), properties.getInt(DB_PORT),
        properties.get(DB_NAME), properties.get(DB_USER), properties.get(DB_PASSWORD));
    return DatabaseConnectionFactory.createConnection(config);
  }

  private static SmtpConfig buildSmtpConfig(final AppProperties properties) {
    return new SmtpConfig(
        properties.get(SMTP_HOST), properties.getInt(SMTP_PORT),
        properties.get(SMTP_USER), properties.get(SMTP_PASSWORD),
        properties.get(SMTP_FROM), properties.get(SMTP_FROM_NAME));
  }
}
