package com.g.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String objeto, Integer id) {
        super(objetoNaoEncontrado(objeto, id));
    }

    private static String objetoNaoEncontrado(String objeto, Integer id) {
        return "Objeto " + objeto + " com o ID " + id + ", não encontrado!";
    }
}
