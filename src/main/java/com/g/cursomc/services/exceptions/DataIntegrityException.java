package com.g.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException{

    public DataIntegrityException(String mensagem) {
        super(mensagem);
    }
}
