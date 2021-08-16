package com.g.cursomc.exceptionhandler;

public class ObjectNotFoundExcepton extends RuntimeException {

    public ObjectNotFoundExcepton(String objeto, Integer id) {
        super(objetoNaoEncontrado(objeto, id));
    }

    private static String objetoNaoEncontrado(String objeto, Integer id) {
        return "Objeto " + objeto + " com o ID " + id + ", não encontrado!";
    }
}
