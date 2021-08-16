package com.g.cursomc.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@ControllerAdvice
public class ResourceException {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date HORA = Calendar.getInstance().getTime();

    @ExceptionHandler(ObjectNotFoundExcepton.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundExcepton error, HttpServletRequest request) {
        StandardError se = new StandardError(HttpStatus.NOT_FOUND.value(), error.getMessage(), sdf.format(HORA));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(se);
    }
}
