package com.api.doarmais.configs;

import com.api.doarmais.exceptions.*;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.mail.MailSendException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ProblemDetail handleValidationErrors(MethodArgumentNotValidException e) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
    problemDetail.setType(e.getBody().getType());
    problemDetail.setTitle("Dado não informado corretamente");
    List<FieldError> errors = e.getBindingResult().getFieldErrors();
    String detail = String.join(", ", MethodArgumentNotValidException.errorsToStringList(errors));
    problemDetail.setDetail(detail);
    return problemDetail;
  }

  @ExceptionHandler(Exception.class)
  ProblemDetail handleGeneralExceptions(Exception e) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
    problemDetail.setTitle("Erro interno do servidor");
    problemDetail.setDetail(e.getMessage());
    return problemDetail;
  }

  @ExceptionHandler(RuntimeException.class)
  ProblemDetail handleRuntimeExceptions(RuntimeException e) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
    problemDetail.setTitle("Erro interno do servidor");
    problemDetail.setDetail(e.getMessage());
    return problemDetail;
  }

  @ExceptionHandler(UserNotFound.class)
  ProblemDetail handleUsuarioNotFoundException(UserNotFound e) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    problemDetail.setTitle("Usuário não encontrado");
    problemDetail.setDetail(e.getMessage());
    return problemDetail;
  }

  @ExceptionHandler(AuthenticationException.class)
  ProblemDetail handleAuthenticationException(AuthenticationException e) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.getLocalizedMessage());
    problemDetail.setTitle("Credenciais inválidas");
    problemDetail.setDetail("Usuário ou senha inválidos");
    return problemDetail;
  }

  @ExceptionHandler(UserAlreadyExists.class)
  ProblemDetail handleUserAlreadyExistsException(UserAlreadyExists e) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, e.getLocalizedMessage());
    problemDetail.setTitle("Erro na criação de conta");
    problemDetail.setDetail(e.getMessage());
    return problemDetail;
  }

  @ExceptionHandler(ResetNotFound.class)
  ProblemDetail handleResetNotFound(ResetNotFound e) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    problemDetail.setTitle("Erro no reset de senha");
    problemDetail.setDetail(e.getMessage());
    return problemDetail;
  }

  @ExceptionHandler(ResetAlreadyExists.class)
  ProblemDetail handleResetAlreadyExists(ResetAlreadyExists e) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, e.getLocalizedMessage());
    problemDetail.setTitle("Erro no reset de senha");
    problemDetail.setDetail(e.getMessage());
    return problemDetail;
  }

  @ExceptionHandler(MailSendException.class)
  ProblemDetail handleMailSendException(MailSendException e) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
    problemDetail.setTitle("Erro no envio do email");
    problemDetail.setDetail(e.getMessage());
    return problemDetail;
  }

  @ExceptionHandler(PasswordNotEqual.class)
  ProblemDetail handlePasswordNotEqual(PasswordNotEqual e) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
    problemDetail.setTitle("Erro na troca de senha");
    problemDetail.setDetail(e.getMessage());
    return problemDetail;
  }

  @ExceptionHandler(ResetExpired.class)
  ProblemDetail handleResetExpired(ResetExpired e) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
    problemDetail.setTitle("Erro na troca de senha");
    problemDetail.setDetail(e.getMessage());
    return problemDetail;
  }

  @ExceptionHandler(LinkAlreadyUsed.class)
  ProblemDetail handleLinkAlreadyUsed(LinkAlreadyUsed e) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
    problemDetail.setTitle("Erro no link");
    problemDetail.setDetail(e.getMessage());
    return problemDetail;
  }

  @ExceptionHandler(AccountNotVerifiedByAdm.class)
  ProblemDetail handleAccountNotVerifiedByAdm(AccountNotVerifiedByAdm e) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
    problemDetail.setTitle("Erro no login");
    problemDetail.setDetail(e.getMessage());
    return problemDetail;
  }

  @ExceptionHandler(BlockedAccount.class)
  ProblemDetail handleBlockedAccount(BlockedAccount e) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
    problemDetail.setTitle("Erro no login");
    problemDetail.setDetail(e.getMessage());
    return problemDetail;
  }

  @ExceptionHandler(EmailAccountNotVerified.class)
  ProblemDetail handleEmailAccountNotVerified(EmailAccountNotVerified e) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
    problemDetail.setTitle("Erro no login");
    problemDetail.setDetail(e.getMessage());
    return problemDetail;
  }

  @ExceptionHandler(SuspendedAccount.class)
  ProblemDetail handleSuspendedAccount(SuspendedAccount e) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
    problemDetail.setTitle("Erro no login");
    problemDetail.setDetail(e.getMessage());
    return problemDetail;
  }

  @ExceptionHandler(InvalidDocument.class)
  ProblemDetail handleInvalidDocument(InvalidDocument e) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
    problemDetail.setTitle("Erro na criação de conta");
    problemDetail.setDetail(e.getMessage());
    return problemDetail;
  }

  @ExceptionHandler(AddressAlreadyExists.class)
  ProblemDetail handleAddressAlreadyExists(AddressAlreadyExists e) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
    problemDetail.setTitle("Erro na criação de endereço");
    problemDetail.setDetail(e.getMessage());
    return problemDetail;
  }
}
