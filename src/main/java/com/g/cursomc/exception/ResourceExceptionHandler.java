package com.g.cursomc.exception;

import com.g.cursomc.domain.enums.TipoTextoPadrao;
import com.g.cursomc.services.exceptions.DataIntegrityException;
import com.g.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@ControllerAdvice
public class ResourceExceptionHandler {

    SimpleDateFormat sdf = new SimpleDateFormat(TipoTextoPadrao.PADRAO_DATA_HORA.getDescricao());
    Date HORA = Calendar.getInstance().getTime();

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException error, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new StandardError(HttpStatus.NOT_FOUND.value(), error.getMessage(), sdf.format(HORA)));
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException error, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new StandardError(HttpStatus.BAD_REQUEST.value(), error.getMessage(), sdf.format(HORA)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException error, HttpServletRequest request) {
        ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(),
                TipoTextoPadrao.ERRO_VALIDACAO.getDescricao(), sdf.format(HORA));

        error.getBindingResult().getFieldErrors().forEach(erro ->
                validationError.addError(erro.getField(), erro.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }
}
