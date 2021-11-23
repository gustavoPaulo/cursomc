package com.g.cursomc.exception;

import com.g.cursomc.services.exceptions.DataIntegrityException;
import com.g.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@ControllerAdvice
public class ResourceExceptionHandler {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date HORA = Calendar.getInstance().getTime();

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException error, HttpServletRequest request) {
        StandardError se = new StandardError(HttpStatus.NOT_FOUND.value(), error.getMessage(), sdf.format(HORA));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(se);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException error, HttpServletRequest request) {
        StandardError se = new StandardError(HttpStatus.BAD_REQUEST.value(), error.getMessage(), sdf.format(HORA));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(se);
    }
}
