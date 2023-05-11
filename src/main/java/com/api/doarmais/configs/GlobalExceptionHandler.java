package com.api.doarmais.configs;

import com.api.doarmais.exceptions.UsuarioNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail handleValidationErrors(MethodArgumentNotValidException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        problemDetail.setType(e.getBody().getType());
        problemDetail.setTitle("Dado não informado corretamente");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("Stacktrace: ", e.getStackTrace());
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    ProblemDetail handleGeneralExceptions(Exception e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        problemDetail.setTitle("Erro interno do servidor");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("Stacktrace: ", e.getStackTrace());
        return problemDetail;
    }

    @ExceptionHandler(RuntimeException.class)
    ProblemDetail handleRuntimeExceptions(RuntimeException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        problemDetail.setTitle("Erro interno do servidor");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("Stacktrace: ", e.getStackTrace());
        return problemDetail;
    }

    @ExceptionHandler(UsuarioNotFound.class)
    ProblemDetail handleUsuarioNotFoundException(UsuarioNotFound e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        problemDetail.setTitle("Usuário não encontrado");
        problemDetail.setDetail("Informe um usuário válido!");
        problemDetail.setProperty("Stacktrace: ", e.getStackTrace());
        return problemDetail;
    }

}
